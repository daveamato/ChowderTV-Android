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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.androidlivetv.R;
import com.example.database.DatabaseHandler;
import com.example.imageloader.ImageLoader;
import com.example.item.ItemHome;
import com.example.item.ItemRelated;
import com.example.util.AlertDialogManager;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.StartAppAd;

public class SingleChannelActivity extends ActionBarActivity {

	ProgressBar pbar;
	List<ItemHome> arrayOfHome;
	AlertDialogManager alert = new AlertDialogManager();
	LinearLayout linear;
	TextView txt_title,txt_desc;
	ImageView img_channel,img_ply;
	String Name,Description,Url,ChannelUrl,Id;
	ItemHome objAllBean;
	ImageLoader loader;
	public DatabaseHandler db;
	private Menu menu;
	List<ItemRelated> arrayofRelated ;
	LinearLayout linearContent;
	ItemRelated objChildBean;
	private int mPhotoSize;
	Button btn_report;
	private AdView mAdView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.singlechannel_activity);
		StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id,false));

		db=new DatabaseHandler(getApplicationContext());
		Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitle(Constant.SINGLE_CHANNEL);
		this.setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		StartAppAd.showSlider(this);
		
		mAdView = (AdView) findViewById(R.id.adView);
	    mAdView.loadAd(new AdRequest.Builder().build());
	    
		pbar=(ProgressBar)findViewById(R.id.progressBar1);
		linear=(LinearLayout)findViewById(R.id.content);
		txt_title=(TextView)findViewById(R.id.txt_channelname);
		txt_desc=(TextView)findViewById(R.id.txt_details);
		img_channel=(ImageView)findViewById(R.id.img_channel);
		img_ply=(ImageView)findViewById(R.id.img_play);
		btn_report=(Button)findViewById(R.id.btn_report);
		arrayOfHome=new ArrayList<ItemHome>();
		loader=new ImageLoader(SingleChannelActivity.this);
		arrayofRelated=new ArrayList<ItemRelated>();
		linearContent=(LinearLayout)findViewById(R.id.rel_c_content);
		mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
		if (JsonUtils.isNetworkAvailable(SingleChannelActivity.this)) {
			new MyTask().execute(Constant.SINGLE_CHANNEL_URL+Constant.SINGLE_ID);
		} else {
			showToast(getString(R.string.conn_msg3));
			alert.showAlertDialog(SingleChannelActivity.this, getString(R.string.conn_msg4),
					getString(R.string.conn_msg2), false);
		}

		img_channel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inttv=new Intent(SingleChannelActivity.this,TvPlay.class);
				inttv.putExtra("url",ChannelUrl);
				startActivity(inttv);
			}
		});
		img_ply.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent inttv=new Intent(SingleChannelActivity.this,TvPlay.class);
				inttv.putExtra("url",ChannelUrl);
				startActivity(inttv);
			}
		});
		
		btn_report.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent report=new Intent(SingleChannelActivity.this,ReportChannelActivity.class);
				report.putExtra("ID", Id);
				report.putExtra("NAME", Name);
				report.putExtra("DESC", Description);
				report.putExtra("IMAGE", Url);
				startActivity(report);
			}
		});
	}

	private	class MyTask extends AsyncTask<String, Void, String> {



		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			pbar.setVisibility(View.VISIBLE);
			linear.setVisibility(View.GONE);
		}

		@Override
		protected String doInBackground(String... params) {
			return JsonUtils.getJSONString(params[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			pbar.setVisibility(View.INVISIBLE);
			linear.setVisibility(View.VISIBLE);


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
						objItem.setChannelUrl(objJson.getString(Constant.lATETST_CHANNEL_URL));
						objItem.setDescription(objJson.getString(Constant.LATEST_CHANNEL_DESCRIPTION));
						objItem.setImage(objJson.getString(Constant.LATEST_CHANNEL_IMAGE));

						arrayOfHome.add(objItem);

						JSONArray jsonArraychild = objJson.getJSONArray(Constant.RELATED_ITEM_ARRAY_NAME);
						if(jsonArraychild.length()==0)
						{

						}
						else
						{
							for(int j=0 ;j< jsonArraychild.length();j++)
							{
								JSONObject objChild = jsonArraychild.getJSONObject(j);
								ItemRelated item=new ItemRelated();
								item.setRelatedId(objChild.getString(Constant.RELATED_ITEM_CHANNEL_ID));
								item.setRelatedTitle(objChild.getString(Constant.RELATED_ITEM_CHANNEL_NAME));
								item.setRelatedImage(objChild.getString(Constant.RELATED_ITEM_CHANNEL_THUMB));
								arrayofRelated.add(item);
							}
						}

					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

				setAdapterToListview();
			}

		}
	}


	public void setAdapterToListview() {

		objAllBean=arrayOfHome.get(0);
		Id=String.valueOf(objAllBean.getId());
		Name=objAllBean.getChannelName();
		Description=objAllBean.getDescription();
		Url=objAllBean.getImage();
		ChannelUrl=objAllBean.getChannelUrl();
		txt_title.setText(Name);
		txt_desc.setText(Description);
		
		Picasso.with(SingleChannelActivity.this).load(Constant.SERVER_IMAGE_UPFOLDER+Url.replace(" ", "%20")).into(img_channel);	

		if(arrayofRelated.size()==0)
		{
			linearContent.removeAllViews();
			TextView txt=new TextView(SingleChannelActivity.this);
			txt.setText("Related content does not found.");
			txt.setTextColor(getResources().getColor(R.color.black));
			txt.setTextSize(16f);
			linearContent.addView(txt);
		}
		else
		{
			RelatedVideoContent();	
		}

	}

	public void showToast(String msg) {
		Toast.makeText(SingleChannelActivity.this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
		{
		case android.R.id.home: 
			onBackPressed();
			break;
		case R.id.fav:
			List<ItemHome> pojolist=db.getFavRow(String.valueOf(Constant.SINGLE_ID));
			if(pojolist.size()==0)
			{
				AddtoFav();//if size is zero i.e means that record not in database show add to favorite 
			}
			else
			{	
				if(pojolist.get(0).getId()==Constant.SINGLE_ID)
				{
					RemoveFav();
				}

			}
			break;
			
		case R.id.share:
			
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.share_msg)+getPackageName());
			sendIntent.setType("text/plain");
			startActivity(sendIntent); 
			
			break;
		default:
			return super.onOptionsItemSelected(menuItem);
		}
		return true;
	}

	public void AddtoFav()
	{
		db.AddtoFavorite(new ItemHome(Constant.SINGLE_ID,Name,Url,Description));
		db.close();
		Toast.makeText(getApplicationContext(),getString(R.string.fav_msg_1), Toast.LENGTH_SHORT).show();
		menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fav_hover));
	}

	//remove from favorite
	public void RemoveFav()
	{

		db.RemoveFav(new ItemHome(Constant.SINGLE_ID));
		db.close();
		Toast.makeText(getApplicationContext(), getString(R.string.fav_msg_2), Toast.LENGTH_SHORT).show();
		menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fav));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		this.menu = menu;
		FirstFav();
		return super.onCreateOptionsMenu(menu);
	}

	public void FirstFav()
	{
		List<ItemHome> pojolist=db.getFavRow(String.valueOf(Constant.SINGLE_ID));
		if(pojolist.size()==0)
		{
			menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fav));

		}
		else
		{	
			if(pojolist.get(0).getId()==Constant.SINGLE_ID)
			{
				menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.fav_hover));

			}

		}
	}

	public void RelatedVideoContent()
	{
		linearContent.removeAllViews();
		int i=0;
		do
		{
			if(i>=arrayofRelated.size())
			{
				return;
			}

			View view = getLayoutInflater().inflate(R.layout.related_content, null);
			final ImageView imageView = (ImageView)view.findViewById(R.id.img_c);
			imageView.setId(i);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(mPhotoSize,
					mPhotoSize));
			linearContent.addView(view);
			objChildBean=arrayofRelated.get(i);
 			
			Picasso.with(SingleChannelActivity.this).load(Constant.SERVER_IMAGE_UPFOLDER_THUMB+objChildBean.getRelatedImage().toString().replace(" ", "%20")).into(imageView);	


			imageView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					objChildBean=arrayofRelated.get(imageView.getId());
					Intent intdetials=new Intent(SingleChannelActivity.this,SingleChannelActivity.class);
					Constant.SINGLE_ID=Integer.parseInt(objChildBean.getRelatedId());
					Constant.SINGLE_CHANNEL=objChildBean.getRelatedTitle();
					intdetials.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
					startActivity(intdetials);
				}
			});
			i++;
		}while(true);
	}

}
