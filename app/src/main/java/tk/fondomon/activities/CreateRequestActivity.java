package tk.fondomon.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tk.fondomon.entities.SmfMember;

public class CreateRequestActivity extends AppCompatActivity {

    private String fee;
    private String payment;

    private EditText money;
    private EditText time;
    private EditText infAdditional;
    private Spinner spinnerFee;
    private Spinner spinnerPayment;
    private ProgressDialog progress;
    private SmfMember user;
    private TextView dateDisbursement;

    private int month, day, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        user = (SmfMember) getIntent().getExtras().getSerializable("user");

        money = (EditText)findViewById(R.id.money_requested);
        time = (EditText)findViewById(R.id.time_limit);
        infAdditional = (EditText)findViewById(R.id.inf_additional);
        dateDisbursement = (TextView)findViewById(R.id.disbursement);
        // ------------------------------------------------------------------
        spinnerFee = (Spinner)findViewById(R.id.spinnerFee);
        spinnerFee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fee = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categoriesFee = new ArrayList<>();
        categoriesFee.add(getString(R.string.fee_monthly));
        categoriesFee.add(getString(R.string.fee_unique));

        ArrayAdapter<String> dataAdapterFee = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesFee);

        dataAdapterFee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFee.setAdapter(dataAdapterFee);
        // ------------------------------------------------------------------
        spinnerPayment = (Spinner)findViewById(R.id.spinnerPayment);
        spinnerPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payment = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> categoriesPayment = new ArrayList<>();
        categoriesPayment.add(getString(R.string.payment_account));
        categoriesPayment.add(getString(R.string.payment_cash));

        ArrayAdapter<String> dataAdapterPayment = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesPayment);

        dataAdapterPayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPayment.setAdapter(dataAdapterPayment);
        // ------------------------------------------------------------------
        Button sendButton = (Button)findViewById(R.id.button_save);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });

        ((TextView)findViewById(R.id.real_name)).setText(getString(R.string.real_name)+": "+user.getRealName());

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Button datePicker = (Button) findViewById(R.id.date);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            String date = arg3+"/"+(arg2+1)+"/"+arg1;
            dateDisbursement.setText(date);
        }
    };

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

    public void sendRequest(){
        String moneyStr = money.getText().toString();
        String timeStr = time.getText().toString();
        String dateStr = dateDisbursement.getText().toString();
        String inf = infAdditional.getText().toString();

        View focusView = null;
        boolean cancel = false;

        if(TextUtils.isEmpty(moneyStr)){
            money.setError(getString(R.string.error_field_required));
            focusView = money;
            cancel = true;
        }else if(TextUtils.isEmpty(timeStr)){
            time.setError(getString(R.string.error_field_required));
            focusView = time;
            cancel = true;
        }else if(dateStr.equals(getString(R.string.date_disbursement))){
            showMessage("Error",getString(R.string.error_field_date_disbursement));
            focusView = dateDisbursement;
            cancel = true;
        }

        if(cancel)
            focusView.requestFocus();
        else{
            showProgress(getString(R.string.msg_sending),true);
            PublishRequestTask publishTask = new PublishRequestTask(user);
            publishTask.execute();
            // Connection with the server, authentication

            /*NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_menu_send)
                            .setContentTitle("My notification")
                            .setContentText("Hello World!");
            // Creates an explicit intent for an Activity in your app
            Intent resultIntent = new Intent(this, MainActivity.class);

            // The stack builder object will contain an artificial back stack for the
            // started Activity.
            // This ensures that navigating backward from the Activity leads out of
            // your application to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            // Adds the back stack for the Intent (but not the Intent itself)
            stackBuilder.addParentStack(MainActivity.class);
            // Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // mId allows you to update the notification later on.
            mNotificationManager.notify(1, mBuilder.build());*/
        }
    }

    /**
     * show a message in the actual context
     */
    public void showMessage( String nameError, String detail) {
        AlertDialog alert = new AlertDialog.Builder(CreateRequestActivity.this).create();
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
            progress = ProgressDialog.show(CreateRequestActivity.this, null, message, true);
    }

    /**
     * Represents an asynchronous publish request task used to authenticate
     * the user.
     */
    public class PublishRequestTask extends AsyncTask<Void, Void, Boolean> {

        private final SmfMember user;

        PublishRequestTask(SmfMember user) {
            this.user = user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(null, false);
            try {
                if (success) {
                    showMessage(getString(R.string.msg_notify_title), getString(R.string.msg_notify));
                    Thread.sleep(2000);
                    Intent intent = new Intent(CreateRequestActivity.this, MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                } else {
                    showMessage(getString(R.string.error_connection_failed), getString(R.string.error_authentication));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(null, false);
        }
    }
}
