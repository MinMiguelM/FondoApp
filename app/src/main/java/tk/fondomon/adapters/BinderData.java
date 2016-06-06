package tk.fondomon.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import tk.fondomon.activities.R;

public class BinderData extends BaseAdapter {

	static final String KEY_TITLE = "title";
	static final String KEY_DESC = "desc";
	static final String KEY_IMG = "img";
	
	LayoutInflater inflater;
	List<HashMap<String,String>> actionDataCollection;
	ViewHolder holder;

	public BinderData() {
		// TODO Auto-generated constructor stub
	}
	
	public BinderData(Activity act, List<HashMap<String,String>> map) {
		this.actionDataCollection = map;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	

	public int getCount() {
		// TODO Auto-generated method stub
		return actionDataCollection.size();
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
			vi = inflater.inflate(R.layout.list_row, null);
			holder = new ViewHolder();
	      	holder.action_text = (TextView)vi.findViewById(R.id.action_text); // Action text
	      	holder.desc_text = (TextView)vi.findViewById(R.id.desc_text); // description
	      	holder.action_image =(ImageView)vi.findViewById(R.id.list_image); // thumb image
			vi.setTag(holder);
	    }else{
	    	holder = (ViewHolder)vi.getTag();
	    }

		int strTitle = vi.getContext().getApplicationContext().getResources().getIdentifier(actionDataCollection.get(position).get(KEY_TITLE)
				, "string", vi.getContext().getApplicationContext().getPackageName());
		int strDesc = vi.getContext().getApplicationContext().getResources().getIdentifier(actionDataCollection.get(position).get(KEY_DESC)
				, "string", vi.getContext().getApplicationContext().getPackageName());

		holder.action_text.setText(vi.getContext().getApplicationContext().getString(strTitle));
		holder.desc_text.setText(vi.getContext().getApplicationContext().getString(strDesc));

		String uri = "drawable/" + actionDataCollection.get(position).get(KEY_IMG);
		int imageResource = vi.getContext().getApplicationContext().getResources().getIdentifier(uri, null, vi.getContext().getApplicationContext().getPackageName());
		Drawable image = vi.getContext().getResources().getDrawable(imageResource);
		holder.action_image.setImageDrawable(image);
	      
		return vi;
	}

	static class ViewHolder{
		TextView action_text;
		TextView desc_text;
		ImageView action_image;
	}
}
