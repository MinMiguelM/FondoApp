package tk.fondomon.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import tk.fondomon.activities.R;

/**
 * Created by miguel on 8/06/16.
 */
public class RequestsData extends BaseAdapter {

    static final String KEY_NAME = "name";
    static final String KEY_DATE = "date";
    static final String KEY_AMOUNT = "amount";
    static final String KEY_TIME = "time_limit";
    static final String KEY_FEE = "fee";
    static final String KEY_STATE = "state";

    LayoutInflater inflater;
    List<HashMap<String,String>> requestsDataCollection;
    ViewHolder holder;

    public RequestsData() {
        // TODO Auto-generated constructor stub
    }

    public RequestsData(Activity act, List<HashMap<String,String>> map) {
        this.requestsDataCollection = map;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return requestsDataCollection.size();
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Get the resources and edit it into the layout
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(R.layout.requests_row, null);
            holder = new ViewHolder();
            holder.name_text = (TextView)vi.findViewById(R.id.name);
            holder.amount_money_text = (TextView)vi.findViewById(R.id.amount_money);
            holder.date_text =(TextView)vi.findViewById(R.id.date);
            holder.time_limit_text = (TextView)vi.findViewById(R.id.time_limit);
            holder.fee_text = (TextView)vi.findViewById(R.id.fee);
            holder.state_text = (TextView)vi.findViewById(R.id.state);
            vi.setTag(holder);
        }else{
            holder = (ViewHolder)vi.getTag();
        }

        holder.name_text.setText(vi.getContext().getApplicationContext().getString(R.string.real_name)+": "
                +requestsDataCollection.get(position).get(KEY_NAME));
        holder.amount_money_text.setText(vi.getContext().getApplicationContext().getString(R.string.amount_loan_info)+": "
                +requestsDataCollection.get(position).get(KEY_AMOUNT));
        holder.date_text.setText(vi.getContext().getApplicationContext().getString(R.string.date_info)+": "
                +requestsDataCollection.get(position).get(KEY_DATE));
        holder.time_limit_text.setText(vi.getContext().getApplicationContext().getString(R.string.time_limit)+": "
                +requestsDataCollection.get(position).get(KEY_TIME));
        holder.fee_text.setText(vi.getContext().getApplicationContext().getString(R.string.fee)+": "
                +requestsDataCollection.get(position).get(KEY_FEE));
        String state = requestsDataCollection.get(position).get(KEY_STATE);
        holder.state_text.setText(vi.getContext().getApplicationContext().getString(R.string.state_info)+": "
                +state);
        if(state.equals("Espera")) // color naranja
            holder.state_text.setTextColor(Color.parseColor("#DC8A2D"));
        else if(state.equals("Terminado")) // color negro
            holder.state_text.setTextColor(Color.BLACK);
        else if(state.equals("Activo")) // verde
            holder.state_text.setTextColor(Color.parseColor("#2DDC32"));

        return vi;
    }

    static class ViewHolder{
        TextView name_text;
        TextView amount_money_text;
        TextView date_text;
        TextView time_limit_text;
        TextView fee_text;
        TextView state_text;
    }
}
