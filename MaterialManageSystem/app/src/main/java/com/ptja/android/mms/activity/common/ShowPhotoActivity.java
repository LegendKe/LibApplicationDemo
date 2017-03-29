package com.ptja.android.mms.activity.common;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.ptja.android.mms.R;

import hz.framework.android.base.BaseActivity;
import hz.framework.android.utils.ImageLoaderUtils;

/**
 * 查看图片
 * 
 * @author zhenghou
 * 
 */
public class ShowPhotoActivity extends BaseActivity {

	private ViewPager viewPager;  

	private List<String> paths;
	
	private int curPosition = 0;
	
	private int type;//0-浏览  1-编辑
	
	TextView rightButton;
	
	MyAdapter mAdapter ;

	@Override
	public boolean handleMessage(Message msg) {
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_btn:
			
			if(paths.size()==1){
				paths.remove(0);
				Bundle b = new Bundle();
				b.putSerializable("data", (Serializable) paths);
				getIntent().putExtras(b);
				setResult(RESULT_OK, getIntent());
				finish();
			}else{
				int index = viewPager.getCurrentItem();
				if(index == paths.size()-1){
					deleteSingleFile(paths.get(viewPager.getCurrentItem()));
					paths.remove(viewPager.getCurrentItem());
					mAdapter = new MyAdapter();
					viewPager.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
					viewPager.setCurrentItem(index-1);
				}else{
					deleteSingleFile(paths.get(viewPager.getCurrentItem()));
					paths.remove(viewPager.getCurrentItem());
					mAdapter = new MyAdapter();
					viewPager.setAdapter(mAdapter);
					mAdapter.notifyDataSetChanged();
					viewPager.setCurrentItem(index);
				}

			}
			
			
			break;
		default:
			break;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showphoto);

		paths = (List<String>) getIntent().getExtras().getSerializable("data");
		if(paths.contains("")){
			paths.remove("");
		}
		curPosition = getIntent().getExtras().getInt("curPosition");
		type = getIntent().getExtras().getInt("type", 0);
		if(paths == null || paths.size() == 0){
			finish();
		}
		if(type == 0){
			setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			}, "图片列表", null, 0, 0,null);

		}else{
			setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle b = new Bundle();
					b.putSerializable("data", (Serializable) paths);
					getIntent().putExtras(b);
					setResult(RESULT_OK, getIntent());
					finish();
				}
			}, "图片列表", "删除", 0, 0,this);
		}
		if(type > 0){
			setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle b = new Bundle();
					b.putSerializable("data", (Serializable) paths);
					getIntent().putExtras(b);
					setResult(RESULT_OK, getIntent());
					finish();
				}
			}, "图片列表", "删除", 0, 0,this);

		}
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(mAdapter = new MyAdapter());
		viewPager.setCurrentItem(curPosition);



	}


	@Override
	public void onCancel() {

	}

	public class MyAdapter extends PagerAdapter{
		
        @Override  
        public int getCount() {  
            return paths.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public void destroyItem(View container, int position, Object object) {  
//            ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);  
              
        }  
  
        /** 
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
         */  
        @Override  
        public Object instantiateItem(ViewGroup container, int position) {  
        	ImageView im=new ImageView(ShowPhotoActivity.this);
        	im.setScaleType(ScaleType.FIT_XY);
			if (paths.get(position).contains("http")){
				ImageLoaderUtils.getInstance(getApplicationContext()).displayImageSimple(paths.get(position), im);
			}else{
				ImageLoaderUtils.getInstance(getApplicationContext()).displayImageSimple("file://"+paths.get(position), im);
			}

            container.addView(im);
            return im;
        }  
    }
}
