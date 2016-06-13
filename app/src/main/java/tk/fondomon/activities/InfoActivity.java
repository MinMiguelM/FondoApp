package tk.fondomon.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import tk.fondomon.entities.SmfMember;

public class InfoActivity extends AppCompatActivity {

    private SmfMember user;

    private TextView emailView;
    private TextView nameView;
    private TextView quotaView;
    private TextView balanceView;
    private TextView amountCreditsView;
    private TextView valueCreditsView;
    private TextView contributionsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        user = (SmfMember) getIntent().getExtras().getSerializable("user");

        emailView = (TextView)findViewById(R.id.email);
        nameView = (TextView)findViewById(R.id.name);
        quotaView = (TextView)findViewById(R.id.quota);
        balanceView = (TextView)findViewById(R.id.balance);
        amountCreditsView = (TextView)findViewById(R.id.amount_credits);
        valueCreditsView = (TextView)findViewById(R.id.value_credits);
        contributionsView = (TextView)findViewById(R.id.contributions);

        fillInformation();
    }

    public void fillInformation(){
        emailView.setText(getString(R.string.email_info) +": "+ user.getEmailAddress());
        nameView.setText(getString(R.string.name_info) +": "+ user.getRealName());
        quotaView.setText(getString(R.string.quota_info) +": $"+ user.getQuota());
        balanceView.setText(getString(R.string.balance_info) +": $"+ user.getBalance());
        amountCreditsView.setText(getString(R.string.amount_credits_info) +": "+ user.getAmountCredits());
        if(!user.getValueCredits().equals("-"))
            valueCreditsView.setText(getString(R.string.value_credits) +": $"+ user.getValueCredits());
        else
            valueCreditsView.setText(getString(R.string.value_credits) +": $"+ 0);
        if(!user.getContributions().equals("-"))
            contributionsView.setText(getString(R.string.contributions) +": $"+ user.getContributions());
        else
            contributionsView.setText(getString(R.string.contributions) +": $"+ 0);
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
