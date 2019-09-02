package com.androidlivetv;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.util.Constant;
import com.startapp.android.publish.Ad;
import com.startapp.android.publish.AdEventListener;
import com.startapp.android.publish.StartAppAd;

public class MainActivity extends ActionBarActivity implements MaterialTabListener {

	MaterialTabHost tabHost;
	ViewPager pager;
	ViewPagerAdapter adapter;
	private String[] tabs = {"Home","Category","Favourite"};
	Toolbar toolbar;
	String strMessage;
 	private StartAppAd startAppAd = new StartAppAd(this); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StartAppAd.init(this, getString(R.string.startapp_dev_id), getString(R.string.startapp_app_id,false));
 		toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitle(getString(R.string.app_name));
		this.setSupportActionBar(toolbar);
		
		StartAppAd.showSlider(this);
 
		startAppAd.loadAd(new AdEventListener() {

			@Override
			public void onReceiveAd(Ad arg0) {
				// TODO Auto-generated method stub
				startAppAd.showAd(); // show the ad
				startAppAd.loadAd(); // load the next ad
			}

			@Override
			public void onFailedToReceiveAd(Ad arg0) {
				// TODO Auto-generated method stub

			}
		});
 		 
		tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
		pager = (ViewPager) this.findViewById(R.id.pager );
		adapter = new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);

		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// when user do a swipe the selected tab change
				tabHost.setSelectedNavigationItem(position);

			}
		});

		for(String tab_name:tabs)
		{
			tabHost.addTab(
					tabHost.newTab()
					.setText(tab_name)
					.setTabListener(MainActivity.this)
					);
		}

	}

	@Override
	public void onTabSelected(MaterialTab tab) {
		// TODO Auto-generated method stub
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(MaterialTab tab) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(MaterialTab tab) {
		// TODO Auto-generated method stub

	}

	private class ViewPagerAdapter extends FragmentStatePagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		public Fragment getItem(int num) {

			switch (num) {
			case 0:
				return new HomeFragment();
			case 1:
				return new CategoryFragment();
			case 2:
				return new FavouriteFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.home, menu);
		final MenuItem searchMenuItem = menu.findItem(R.id.search);
		final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchMenuItem);

		searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus) {
					MenuItemCompat.collapseActionView(searchMenuItem);
					searchView.setQuery("", false);
				}
			}
		});

		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				// TODO Auto-generated method stub
				Constant.SHOP_SEARCH=arg0;
				Intent intsearch=new Intent(MainActivity.this,Search.class);
				startActivity(intsearch);
				return false;
			}

			@Override
			public boolean onQueryTextChange(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		switch (menuItem.getItemId()) 
		{
		case R.id.about: 
			Intent intab=new Intent(MainActivity.this,AboutActivity.class);
			startActivity(intab);
			break;

		default:
			return super.onOptionsItemSelected(menuItem);
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Toast.makeText(appContext, "BAck", Toast.LENGTH_LONG).show();
			AlertDialog.Builder alert = new AlertDialog.Builder(
					MainActivity.this);
			alert.setTitle(getString(R.string.app_name));
			alert.setIcon(R.drawable.app_icon);
			alert.setMessage("Are You Sure You Want To Quit?");

			alert.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {

					finish();
				}



			});

			alert.setNegativeButton("Rate App",
					new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					final String appName = getPackageName();//your application package name i.e play store application url
					try {
						startActivity(new Intent(Intent.ACTION_VIEW,
								Uri.parse("market://details?id="
										+ appName)));
					} catch (android.content.ActivityNotFoundException anfe) {
						startActivity(new Intent(
								Intent.ACTION_VIEW,
								Uri.parse("http://play.google.com/store/apps/details?id="
										+ appName)));
					}

				}
			});
			alert.show();
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
}
