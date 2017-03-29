package com.ptja.android.mms.activity.common;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ptja.android.mms.R;
import com.ptja.android.mms.adapter.RecentPhotoListAdapter;

import hz.framework.android.View.ListImageDirPopupWindow;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.utils.ImageFloder;

public class PickPhotoActivity extends BaseActivity implements ListImageDirPopupWindow.OnImageDirSelected,View.OnClickListener
{
	private ProgressDialog mProgressDialog;

	/**
	 * 存储文件夹中的图片数量
	 */
	private int mPicsSize;
	/**
	 * 图片数量最多的文件夹
	 */
	private File mImgDir;
	/**
	 * 所有的图片
	 */
	private List<String> mImgs;
	private GridView mGirdView;
	private RecentPhotoListAdapter mAdapter;
	/**
	 * 临时的辅助类，用于防止同一个文件夹的多次扫描
	 */
	private ArrayList<String> mDirPaths = new ArrayList<String>();

	
	private List<Boolean> selected = new ArrayList<Boolean>();
	/**
	 * 扫描拿到所有的图片文件夹
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	int totalCount = 0;

	private int mScreenHeight;

	private ListImageDirPopupWindow mListImageDirPopupWindow;
	

	/**
	 * 为View绑定数据
	 */
	private void data2View()
	{
		if(mAdapter ==null){
			mAdapter = new RecentPhotoListAdapter(getApplicationContext(), mDirPaths,mHandler,selected);
			mGirdView.setAdapter(mAdapter);
		}else{
			mAdapter.notifyDataSetChanged();
		}
	};

	/**
	 * 初始化展示文件夹的popupWindw
	 */
	private void initListDirPopupWindw()
	{
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
				mImageFloders, LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.list_dir, null));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener()
		{

			@Override
			public void onDismiss()
			{
				// 设置背景颜色变暗
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		// 设置选择文件夹的回调
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}


	/**
	 * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
	 */
	private void getImages()
	{
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
		{
			Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

		
		
		  
		  
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{

				// 获取SDcard卡的路径
				  String sdcardPath = Environment.getExternalStorageDirectory().toString();

				  ContentResolver mContentResolver = getContentResolver();
				  Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
				    new String[]{MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA}, 
				    MediaStore.Images.Media.MIME_TYPE + "=? OR " + MediaStore.Images.Media.MIME_TYPE + "=?", 
				    new String[] { "image/jpeg", "image/png" }, MediaStore.Images.Media._ID + " DESC"); // 按图片ID降序排列

				  while (mCursor.moveToNext()) {
				      // 打印LOG查看照片ID的值
				      long id = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Images.Media._ID));
				      Log.i("MediaStore.Media_ID=", id + "");
				      
				      // 过滤掉不需要的图片，只获取拍照后存储照片的相册里的图片
				      String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
				      if (path.contains("DCIM") ) {
				    Log.i("image path=", path);
				    if(!new File(path).exists()){
				    	continue;
				    }
				    mDirPaths.add(path);
				   
				    selected.add(false);
				    mHandler.sendEmptyMessage(1);
				      }
				  }
				  mCursor.close();
				// 扫描完成，辅助的HashSet也就可以释放内存了
//				mDirPaths = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(2);

			}
		}).start();

	}

	/**
 * 初始化View
 */
private void initView()
{
	mGirdView = (GridView) findViewById(R.id.id_gridView);

}

	@Override
	public void selected(ImageFloder floder)
	{

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		/**
		 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
		 */
		mAdapter = new RecentPhotoListAdapter(getApplicationContext(), mDirPaths,mHandler,selected);
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		mListImageDirPopupWindow.dismiss();

	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			data2View();
			break;
		case 2:
			data2View();
			mProgressDialog.dismiss();
			break;
		case 10:
			int position = msg.arg1;
			updateNumText(position);
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * 
	 */
	private void updateNumText(int position) {
		int count = 0;
		for(int i = 0;i<selected.size();i++){
			if(selected.get(i)){
				count++;
			}
		}
		if(count>maxNum){
			Toast.makeText(getApplicationContext(),"最多可以选择"+maxNum+"张图片",Toast.LENGTH_SHORT).show();
			selected.set(position,false);
			mAdapter.notifyDataSetChanged();
			return;
		}
		if(count == 0){
			setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			}, "选择图片", "确定", 0, 0,this);

		}else{
			setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			}, "选择图片", "确定("+count+"张)", 0, 0,this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_btn:
			List<String> re = new ArrayList<String>();
			for(int i = 0;i<selected.size();i++){
				if(selected.get(i)){
					re.add(mDirPaths.get(i));
				}
			}
			Bundle b = new Bundle();
			b.putSerializable("data", (Serializable) re);
			getIntent().putExtras(b);
			setResult(RESULT_OK, getIntent());
			finish();
			break;

		default:
			break;
		}
	}



	private int maxNum  = 5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick);
		setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		}, "选择图片", "确定", 0, 0,this);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;
		maxNum = getIntent().getIntExtra("max",5);
		initView();
		getImages();

	}

	@Override
	public void onCancel() {

	}

}
