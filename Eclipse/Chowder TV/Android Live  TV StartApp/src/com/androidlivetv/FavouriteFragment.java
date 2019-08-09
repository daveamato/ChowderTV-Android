package com.androidlivetv;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.HomeGridAdapter;
import com.example.database.DatabaseHandler;
import com.example.item.ItemHome;
import com.example.util.Constant;

public class FavouriteFragment extends Fragment {

	ListView lsv_latest;
	List<ItemHome> arrayOfHome;
	HomeGridAdapter objAdapterHome;
	DatabaseHandler db;
	TextView txt_no;
	ItemHome objAllBean;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.favourite_fragment, container, false);
		lsv_latest=(ListView)rootView.findViewById(R.id.lsv_latest);
		txt_no=(TextView)rootView.findViewById(R.id.textView1);
		arrayOfHome=new ArrayList<ItemHome>();
		db=new DatabaseHandler(getActivity());
		setAdapterToListview();
		
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

	public void setAdapterToListview() {
		arrayOfHome=db.getAllData();
		db.close();
		if(arrayOfHome.size()==0)
		{
			txt_no.setVisibility(View.VISIBLE);

		}
		else
		{
			txt_no.setVisibility(View.INVISIBLE);

		}

		objAdapterHome = new HomeGridAdapter(getActivity(), R.layout.list_row,
				arrayOfHome);
		lsv_latest.setAdapter(objAdapterHome);
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setAdapterToListview();
	}

}
