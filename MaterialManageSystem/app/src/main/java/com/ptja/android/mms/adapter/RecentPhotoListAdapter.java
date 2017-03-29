package com.ptja.android.mms.adapter;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.common.ShowPhotoActivity;

import hz.framework.android.utils.ImageLoaderUtils;

public class RecentPhotoListAdapter extends BaseAdapter {

	private List<String> photos;
	private List<Boolean> selected;


	private Context mContext;
	
	private Handler mHandler;

	public RecentPhotoListAdapter(Context context, List<String> photos,Handler mHandler,List<Boolean> selected) {
		this.photos = photos;
		this.mContext = context;
		this.mHandler = mHandler;
		this.selected = selected;
	}

	@Override
	public int getCount() {
		return photos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_recent_photo_list, null);
			holder = new ViewHolder();

			holder.img = (ImageView) convertView.findViewById(R.id.imgview);
			holder.selected = (ImageButton) convertView.findViewById(R.id.id_item_select);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoaderUtils.getInstance(mContext).displayImageRound(
				Uri.fromFile(new File(photos.get(position))).toString(),
				holder.img);
		holder.img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, ShowPhotoActivity.class);
				Bundle b = new Bundle();
				b.putInt("curPosition", position);
				b.putSerializable("data", (Serializable) photos);
				b.putInt("type", 0);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtras(b);
				mContext.startActivity(intent);
			}
		});
		holder.selected.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				ImageButton ib = (ImageButton) v;
				if(selected.get(position)){
					ib.setImageResource(R.drawable.picture_unselected);
					selected.set(position, false);
				}else{
					ib.setImageResource(R.drawable.pictures_selected);
					selected.set(position, true);
				}
				Message msg = new Message();
				msg.what = 10;
				msg.arg1 = position;
				mHandler.sendMessage(msg);
			}
		});
		
		if(selected.get(position)){
			holder.selected.setImageResource(R.drawable.pictures_selected);
		}else{
			holder.selected.setImageResource(R.drawable.picture_unselected);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView img;
		ImageButton selected;
	}

}
