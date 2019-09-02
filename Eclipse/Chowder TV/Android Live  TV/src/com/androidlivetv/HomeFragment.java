package com.androidlivetv;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.androidlivetv.R;
import com.example.adapter.HomeGridAdapter;
import com.example.item.ItemHome;
import com.example.util.AlertDialogManager;
import com.example.util.Constant;
import com.example.util.JsonUtils;

public class HomeFragment extends Fragment {
	
	ProgressBar pbar;
	ListView lsv_latest;
	List<ItemHome> arrayOfHome;
	HomeGridAdapter objAdapterHome;
	AlertDialogManager alert = new AlertDialogManager();
	ItemHome objAllBean;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.home_fragment, container, false);
		pbar=(ProgressBar)rootView.findViewById(R.id.progressBar1);
		lsv_latest=(ListView)rootView.findViewById(R.id.lsv_latest);
		arrayOfHome=new ArrayList<ItemHome>();
		
		if (JsonUtils.isNetworkAvailable(getActivity())) {
			new MyTask().execute(Constant.LATEST_URL);
		} else {
			showToast(getString(R.string.conn_msg3));
			alert.showAlertDialog(getActivity(), getString(R.string.conn_msg4),
					getString(R.string.conn_msg2), false);
		}
		
		lsv_latest.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				objAllBean=arrayOfHome.get(position);
				Constant.SINGLE_ID=objAllBean.getId();
				Constant.SINGLE_CHANNEL=objAllBean.getChannelName();
				Intent intcat=new Intent(getActivity(),SingleChannelActivity.class);
				startActivity(intcat);
			}
		});
		
		return rootView;
	}
	
	private	class MyTask extends AsyncTask<String, Void, String> {

		 

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pbar.setVisibility(View.VISIBLE);
			lsv_latest.setVisibility(View.GONE);
		}

		@Override
		protected String doInBackground(String... params) {
			return JsonUtils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			pbar.setVisibility(View.INVISIBLE);
			lsv_latest.setVisibility(View.VISIBLE);


			if (null == result || result.length() == 0) {
				showToast(getString(R.string.nodata));
				 
				
			} else {

				try {
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
					JSONObject objJson = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						  objJson = jsonArray.getJSONObject(i);

						ItemHome objItem = new ItemHome();

						objItem.setId(objJson.getInt(Constant.CHANNEL_ID));
						objItem.setChannelName(objJson.getString(Constant.LATEST_CHANNEL_NAME));
						objItem.setImage(objJson.getString(Constant.LATEST_CHANNEL_IMAGE));
						objItem.setDescription(objJson.getString(Constant.LATEST_CHANNEL_DESCRIPTION));
						arrayOfHome.add(objItem);
					 

					}
					 
				} catch (JSONException e) {
					e.printStackTrace();
				}
				 

  			setAdapterToListview();
  		}

		}
	}

	  
	public void setAdapterToListview() {
		objAdapterHome = new HomeGridAdapter(getActivity(), R.layout.list_row,
				arrayOfHome);
		lsv_latest.setAdapter(objAdapterHome);
	}
	
	public void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}
}
