package tk.fondomon.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateRequestActivity extends AppCompatActivity {

    private String fee;
    private String payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);

        Spinner spinnerFee = (Spinner)findViewById(R.id.spinnerFee);
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
        categoriesFee.add(getString(R.string.fee));
        categoriesFee.add(getString(R.string.fee_monthly));
        categoriesFee.add(getString(R.string.fee_unique));

        ArrayAdapter<String> dataAdapterFee = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesFee);

        dataAdapterFee.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFee.setAdapter(dataAdapterFee);
        // ------------------------------------------------------------------
        Spinner spinnerPayment = (Spinner)findViewById(R.id.spinnerPayment);
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
        categoriesPayment.add(getString(R.string.payment));
        categoriesPayment.add(getString(R.string.payment_account));
        categoriesPayment.add(getString(R.string.payment_cash));

        ArrayAdapter<String> dataAdapterPayment = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesPayment);

        dataAdapterPayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPayment.setAdapter(dataAdapterPayment);
        // ------------------------------------------------------------------
        Button sendButton = (Button)findViewById(R.id.button_save);
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

}
