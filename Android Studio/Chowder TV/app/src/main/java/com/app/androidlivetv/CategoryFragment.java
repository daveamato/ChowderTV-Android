package com.app.androidlivetv;

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

import com.com.example.item.ItemCategory;
import com.example.adapter.CategoryGridAdapter;
import com.util.AlertDialogManager;
import com.util.Constant;
import com.util.JsonUtils;


public class CategoryFragment extends Fragment {

	ProgressBar pbar;
	ListView lsv_latest;
	List<ItemCategory> arrayOfCategory;
	CategoryGridAdapter objAdapterCategory;
	AlertDialogManager alert = new AlertDialogManager();
	private ItemCategory objAllBean;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.category_fragment, container, false);
		pbar=(ProgressBar)rootView.findViewById(R.id.progressBar1);
		lsv_latest=(ListView)rootView.findViewById(R.id.lsv_latest);
		arrayOfCategory=new ArrayList<ItemCategory>();

		if (JsonUtils.isNetworkAvailable(getActivity())) {
			new MyTask().execute(Constant.CATEGORY_URL);
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
				objAllBean=arrayOfCategory.get(position);
				Constant.CATEGORY_ID=objAllBean.getCategoryId();
				Constant.CATEGORY_TITLE=objAllBean.getCategoryName();
				Intent intcat=new Intent(getActivity(),CategoryItemActivity.class);
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
					JSONArray jsonArray = mainJson.getJSONArray(Constant.CATEGORY_ARRAY_NAME);
					JSONObject objJson = null;
					for (int i = 0; i < jsonArray.length(); i++) {
						objJson = jsonArray.getJSONObject(i);
						ItemCategory objItem = new ItemCategory();
						objItem.setCategoryName(objJson.getString(Constant.CATEGORY_NAME));
						objItem.setCategoryId(objJson.getInt(Constant.CATEGORY_CID));
						objItem.setCategoryImageurl(objJson.getString(Constant.CATEGORY_IMAGE));
						arrayOfCategory.add(objItem);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

				setAdapterToListview();
			}

		}
	}

	public void setAdapterToListview() {
		objAdapterCategory = new CategoryGridAdapter(getActivity(), R.layout.list_row,
				arrayOfCategory);
		lsv_latest.setAdapter(objAdapterCategory);

	}

	public void showToast(String msg) {
		Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
	}
}
