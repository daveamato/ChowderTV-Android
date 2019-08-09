package com.androidlivetv;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.adapter.HomeGridAdapter;
import com.example.item.ItemHome;
import com.example.util.AlertDialogManager;
import com.example.util.Constant;
import com.example.util.JsonUtils;

public class Search extends ActionBarActivity{
	
	
	ProgressBar pbar;
	ListView lsv_latest;
	List<ItemHome> arrayOfHome;
	HomeGridAdapter objAdapterHome;
	AlertDialogManager alert = new AlertDialogManager();
	ItemHome objAllBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categoryitem);
		Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitle(Constant.CATEGORY_TITLE);
		this.setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
		pbar=(ProgressBar)findViewById(R.id.progressBar1);
		lsv_latest=(ListView)findViewById(R.id.lsv_latest);
		arrayOfHome=new ArrayList<ItemHome>();
		String temp=Constant.SHOP_SEARCH.replace(" ", "%20");
		if (JsonUtils.isNetworkAvailable(Search.this)) {
			new MyTask().execute(Constant.SEARCH_URL+temp);
		} else {
			showToast(getString(R.string.conn_msg3));
			alert.showAlertDialog(Search.this, getString(R.string.conn_msg4),
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
				Intent intcat=new Intent(Search.this,SingleChannelActivity.class);
				startActivity(intcat);
			}
		});
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
			return JsonUtils.readSearchFeed(params[0]);
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
		objAdapterHome = new HomeGridAdapter(Search.this, R.layout.list_row,
				arrayOfHome);
		lsv_latest.setAdapter(objAdapterHome);

	}

	public void showToast(String msg) {
		Toast.makeText(Search.this, msg, Toast.LENGTH_LONG).show();
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
        {
        case android.R.id.home: 
            onBackPressed();
            break;

        default:
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
	}

}
