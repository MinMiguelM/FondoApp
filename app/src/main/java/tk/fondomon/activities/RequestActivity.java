package tk.fondomon.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import tk.fondomon.adapters.RequestsData;
import tk.fondomon.entities.SmfMember;
import tk.fondomon.entities.SmfMessage;
import tk.fondomon.persistence.Queries;

public class RequestActivity extends AppCompatActivity {

    private SmfMember user=null;
    private String inception;
    private List<SmfMessage> requests = new ArrayList<>();
    private List<HashMap<String,String>> requestsUser = new ArrayList<>();

    private ListView list;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        user = (SmfMember) getIntent().getExtras().getSerializable("user");
        inception = (String)getIntent().getExtras().get("inception");
        loadRequests();
    }

    public void loadRequests(){
        if(inception.equals("1")){
            // load my requests
            showProgress(getString(R.string.msg_loading),true);
            (new FetchRequestTask(this)).execute();
        }else if(inception.equals("2")){
            showProgress(getString(R.string.msg_loading),true);
            (new FetchRequestTask(this)).execute();
        }
    }

    /**
     * show the progress of the current task
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(String message, boolean show){
        if(!show)
            progress.dismiss();
        else
            progress = ProgressDialog.show(RequestActivity.this, null, message, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * show a message in the actual context
     */
    public void showMessage( String nameError, String detail) {
        AlertDialog alert = new AlertDialog.Builder(RequestActivity.this).create();
        alert.setTitle(nameError);
        alert.setMessage(detail);
        alert.show();
    }

    /**
     * fill the requests into a list of map
     */
    public void fillRequests(){
        for (SmfMessage s: requests) {
            HashMap<String,String> map = new HashMap<>();
            String body = s.getBody();
            body = body.replace("\r","");
            body = body.replace("[b]","");
            body = body.replace("[/b]","");
            StringTokenizer tok = new StringTokenizer(body,":\n");
            tok.nextToken(); tok.nextToken();
            map.put("name",tok.nextToken().trim());
            tok.nextToken();
            String date = tok.nextToken().trim();
            if(date.equalsIgnoreCase("Valor a solicitar"))
                map.put("date","");
            else
                map.put("date",date);
            tok.nextToken();
            map.put("amount",tok.nextToken().trim());
            tok.nextToken();
            map.put("time_limit",tok.nextToken().trim());
            tok.nextToken();
            map.put("fee",tok.nextToken().trim());
            map.put("id",s.getIdMsg()+"");
            requestsUser.add(map);
        }
    }

    /**
     * Represents an asynchronous fetch request task
     */
    public class FetchRequestTask extends AsyncTask<String, Void, Boolean> {

        private Activity act;
        private int state;

        public FetchRequestTask(Activity act){
            this.act = act;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                SmfMessage messages[] = null;
                if (inception.equals("1"))
                    messages = restTemplate.getForObject(Queries.GET_REQUESTS_BY_IDMEMBER + user.getIdMember(), SmfMessage[].class, "Android");
                else
                    messages = restTemplate.getForObject(Queries.GET_ALL_REQUESTS, SmfMessage[].class, "Android");
                if(messages == null) {
                    state = 1; // no hay nada
                    return false;
                }

                for(int i = 0;i<messages.length;i++)
                    requests.add(messages[i]);
            } catch (ResourceAccessException ex) {
                ex.printStackTrace();
                state = 2;
                return false;
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                fillRequests();
                RequestsData requestData = new RequestsData(act,requestsUser);
                list = (ListView) findViewById(R.id.listRequests);
                list.setAdapter(requestData);
                // Click event for single list row
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(requestsUser.get(position).toString());
                    }
                });
                showProgress(null, false);
            } else {
                showProgress(null, false);
                switch (state){
                    case 1:
                        showMessage(getString(R.string.msg_list_empty_title), getString(R.string.msg_list_empty));
                        break;
                    case 2:
                        showMessage(getString(R.string.error_connection_failed), getString(R.string.error_server_not_found));
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(null, false);
        }
    }
}
