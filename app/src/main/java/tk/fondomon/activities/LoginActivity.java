package tk.fondomon.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import org.springframework.http.HttpMessage;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import tk.fondomon.entities.SmfMember;
import tk.fondomon.persistence.Queries;

public class LoginActivity extends AppCompatActivity {

    private Button login_button;
    private EditText mPasswordView;
    private EditText mUsernameView;

    private SmfMember user=null;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPasswordView = (EditText)findViewById(R.id.passwordText);
        mUsernameView = (EditText)findViewById(R.id.usernameText);
        login_button = (Button)findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAction();
            }
        });
    }

    /**
     * Check if there is an user log in, if it is, launch the main.
     */
    @Override
    public void onStart(){
        super.onStart();
        Gson gson = new Gson();
        SharedPreferences settings;
        settings = getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
        String json = settings.getString("user", null);
        user = gson.fromJson(json,SmfMember.class);
        if(user!=null){
            showProgress(getString(R.string.msg_loading),true);
            UserLoginTask task = new UserLoginTask(user.getMemberName(),user.getPasswd());
            try {
                task.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Convert a byte array to hex
     */
    public String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }

    /**
     * Login to the main application
     */
    public void loginAction(){
        mPasswordView.setError(null);
        mUsernameView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if(TextUtils.isEmpty(username)){
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }else if (TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        if(cancel)
            focusView.requestFocus();
        else{
            showProgress(getString(R.string.msg_authentication),true);
            MessageDigest pass = null;
            try {
                pass = MessageDigest.getInstance("SHA-1");
                pass.reset();
                pass.update((username.toLowerCase().trim() + password).getBytes("UTF-8"));
                String sha1Pass = byteArrayToHex(pass.digest());
                UserLoginTask loginTask = new UserLoginTask(username,sha1Pass);
                loginTask.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * show a message in the actual context
     */
    public void showMessage( String nameError, String detail) {
        AlertDialog alert = new AlertDialog.Builder(LoginActivity.this).create();
        alert.setTitle(nameError);
        alert.setMessage(detail);
        alert.show();
    }

    /**
     * show the progress of the current task
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(String message, boolean show){
        if(!show)
            progress.dismiss();
        else
            progress = ProgressDialog.show(this, null, message, true);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<String, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private int state;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                // Simulate network access.
                //Thread.sleep(2000);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                user = restTemplate.getForObject(Queries.GET_USER_BY_USERNAME + mUsername, SmfMember.class, "Android");

                if (user == null) {
                    state = 1;
                    return false;
                }
                if (mPassword.equals(user.getPasswd())) {
                    Gson gson = new Gson();
                    SharedPreferences preferencesUser = getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencesUser.edit();
                    editor.putString("user", gson.toJson(user));
                    editor.commit();
                    return true;
                }
            } catch (ResourceAccessException ex){
                ex.printStackTrace();
                state = 2;
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            state = 1;
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                showProgress(null, false);
            } else {
                showProgress(null, false);
                SharedPreferences settings= getSharedPreferences("PreferencesUser", Context.MODE_PRIVATE);
                settings.edit().remove("user").commit();
                switch (state){
                    case 1: // username or password incorrect
                        showMessage(getString(R.string.error_connection_failed), getString(R.string.error_authentication));
                        break;
                    case 2: // server not found
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
