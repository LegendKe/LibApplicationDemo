package com.ptja.android.mms.adapter;

import java.io.File;
import java.util.List;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ptja.android.mms.R;

import hz.framework.android.utils.ImageLoaderUtils;

public class PhotoListAdapter extends BaseAdapter {

	private List<String> photos;
	
	private Context mContext;

	public PhotoListAdapter(Context context, List<String> photos) {
		this.photos = photos;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return photos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return photos.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
//		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_photo_list, null);
			holder = new ViewHolder();

			holder.img = (ImageView) convertView.findViewById(R.id.imgview);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}


		if(!photos.get(position).trim().equals("")){
//			holder.img.setImageBitmap(BitmapFactory.decodeFile(photos.get(position)));
			if (photos.get(position).contains("http")){
				ImageLoaderUtils.getInstance(mContext).displayImageSimple(
						photos.get(position).toString(), holder.img);
			}else{
				ImageLoaderUtils.getInstance(mContext).displayImageSimple(
						Uri.fromFile(new File(photos.get(position))).toString(), holder.img);
			}

		}else{
			holder.img.setImageResource(R.drawable.add_photo);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView img;
	}

}
