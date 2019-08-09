package com.example.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlivetv.R;
import com.example.imageloader.ImageLoader;
import com.example.item.ItemHome;
import com.example.util.Constant;

public class HomeGridAdapter extends ArrayAdapter<ItemHome>{


	private Activity activity;
	private List<ItemHome> itemsLatest;
	private ItemHome objLatestBean;
	private int row;
	public ImageLoader imageLoader; 
	
	 
	public HomeGridAdapter(Activity act, int resource, List<ItemHome> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.itemsLatest = arrayList;
		imageLoader=new ImageLoader(activity);
	
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((itemsLatest == null) || ((position + 1) > itemsLatest.size()))
			return view;

		objLatestBean = itemsLatest.get(position);


		holder.imgv_latetst=(ImageView)view.findViewById(R.id.imageView1);
		holder.name=(TextView)view.findViewById(R.id.textView1);
		holder.desc=(TextView)view.findViewById(R.id.textView2);
		holder.imgv_latetst.setScaleType(ImageView.ScaleType.CENTER_CROP);
		
		imageLoader.DisplayImage(Constant.SERVER_IMAGE_UPFOLDER_THUMB+objLatestBean.getImage().toString(), holder.imgv_latetst);
		holder.name.setText(objLatestBean.getChannelName().toString());
		
		holder.desc.setText(objLatestBean.getDescription().toString());	
	 

		return view;

	}

	public class ViewHolder {

		public ImageView imgv_latetst;
		public  TextView name,desc;

	}

}
