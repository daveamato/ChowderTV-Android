package com.androidlivetv;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;

public class AboutActivity extends ActionBarActivity {
 
	WebView webview;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		Toolbar toolbar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolbar);
		toolbar.setTitle(getString(R.string.menu_about));
		this.setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       
		webview = (WebView)findViewById(R.id.webView1);
		 
		try {
			InputStream fin = getAssets().open("index.html");
			byte[] buffer = new byte[fin.available()];
			fin.read(buffer);
			fin.close();
			webview.loadData(new String(buffer), "text/html", "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
