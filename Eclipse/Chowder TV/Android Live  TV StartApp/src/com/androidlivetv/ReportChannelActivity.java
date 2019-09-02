package com.androidlivetv;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imageloader.ImageLoader;
import com.example.util.Constant;
import com.example.util.JsonUtils;

public class ReportChannelActivity extends ActionBarActivity {


	ImageView img_Channel;
	TextView txt_ChannelName,txt_ChannelDesc;
	EditText edt_comment;
	Button btn_submit;
	String Name,Image,Id,Desc;
	ImageLoader loader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reportchannel);
		Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitle(Constant.CATEGORY_TITLE);
		this.setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		img_Channel=(ImageView)findViewById(R.id.img_channel);
		txt_ChannelName=(TextView)findViewById(R.id.txt_channelname);
		txt_ChannelDesc=(TextView)findViewById(R.id.txt_channeldesc);
		edt_comment=(EditText)findViewById(R.id.edt_comment);
		btn_submit=(Button)findViewById(R.id.btn_submit);
		loader=new ImageLoader(ReportChannelActivity.this);
		
		Intent i=getIntent();
		Id=i.getStringExtra("ID");
		Name=i.getStringExtra("NAME");
		Desc=i.getStringExtra("DESC");
		Image=i.getStringExtra("IMAGE");
	
		txt_ChannelName.setText(Name);
		if(Desc.length() >=35)
		{
			txt_ChannelDesc.setText(Desc.replace("u201C", "\"").replace("u200C", "'").substring(0, 35)+" ...");
		}
		else
		{
			txt_ChannelDesc.setText(Desc);	
		}
		loader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER_THUMB+Image, img_Channel);
		
		btn_submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String comment=edt_comment.getText().toString().replace(" ", "%20");
				if(comment.length()>0)
				{
					if (JsonUtils.isNetworkAvailable(ReportChannelActivity.this)) {
						new Report().execute(Constant.REPORT_CHANNEL_URL+comment+"&channel_id="+Id);
					} else {
						showToast(getString(R.string.conn_msg3));
						
					}
				}
				else
				{
					showToast(getString(R.string.comment_area));
				}
			}
		});
	}


	private class Report extends AsyncTask<String, Void, String>
	{

		ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pDialog = new ProgressDialog(ReportChannelActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
		}


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return JsonUtils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (null != pDialog && pDialog.isShowing()) {
				pDialog.dismiss();
			}

			if (null == result || result.length() == 0) {
				showToast(getString(R.string.nodata));


			} else {

				try {
					JSONObject mainJson = new JSONObject(result);
					JSONArray jsonArray = mainJson.getJSONArray(Constant.REPORT_CHANNEL_ARRAY_NAME);
					JSONObject objJson = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						objJson = jsonArray.getJSONObject(i);
						showToast(objJson.getString(Constant.REPORT_CHANNEL_MSG));
						finish();
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public void showToast(String msg) {
		Toast.makeText(ReportChannelActivity.this, msg, Toast.LENGTH_LONG).show();
	}

}
