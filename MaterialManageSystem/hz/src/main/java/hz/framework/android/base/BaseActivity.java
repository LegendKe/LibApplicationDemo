package hz.framework.android.base;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hz.framework.android.R;
import hz.framework.android.View.DatePickDialogWithoutHours;
import hz.framework.android.View.ProgressDialog;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.app.AppManager;
import hz.framework.android.models.FormFile;
import hz.framework.android.request.SocketHttpRequester;
import hz.framework.android.utils.TANetWorkUtil;

/**
 *
 * @author houzheng
 *
 */
public abstract class BaseActivity extends Activity implements Handler.Callback ,OnClickListener,ProgressDialog.CancelListener{

	protected final int TAKE_PHOTO_BY_CAMERA = 1;

	protected final int TAKE_PHOTO_BY_GALLERY = 2;

	protected final int EDIT_PHOTO_LIST = 3;

	protected Handler mHandler;

	public RequestQueue mQueue;

	public ProgressDialog mProgressDialog;

	protected int DEFAULT_LEFT_DRAWABLE = 1;

//	public void setCommonReturnHeader(String title){
//			TextView titleBar = (TextView) findViewById(R.id.title);
//			ImageView back = (ImageView) findViewById(R.id.left_btn);
//			titleBar.setText(title);
//			back.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					finish();
//				}
//			});
//			titleBar.setVisibility(View.VISIBLE);
//			back.setVisibility(View.VISIBLE);
//	}
//
//	public void setCommonReturnHeaderWithCustomCallback(String title,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView back = (ImageView) findViewById(R.id.left_btn);
//		titleBar.setText(title);
//		back.setOnClickListener(listener);
//		titleBar.setVisibility(View.VISIBLE);
//		back.setVisibility(View.VISIBLE);
//	}
//
//	/**
//	 * only title visible
//	 * @param title
//	 */
//	public void setOnlyTitleHeader(String title){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		titleBar.setText(title);
//		titleBar.setVisibility(View.VISIBLE);
//		findViewById(R.id.right_btn).setVisibility(View.INVISIBLE);
//	}
//
//	public void setOnlyRightTextHeader(String title,String rightText,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		Button rightBtn = (Button) findViewById(R.id.right_btn);
//		rightBtn.setText(rightText);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//	}
//
//	public void setOnlyRightBtnHeader(String title,int rightDrawable,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView rightBtn = (ImageView) findViewById(R.id.right_image_btn);
//		rightBtn.setImageResource(rightDrawable);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//	}
//
//	public void setLeftAndRightBtnHeader(String title,int rightDrawable,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView rightBtn = (ImageView) findViewById(R.id.right_image_btn);
//		ImageView leftBtn = (ImageView) findViewById(R.id.left_btn);
//		rightBtn.setImageResource(rightDrawable);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		leftBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//	}
//	public void setLeftAndRightBtnHeader(String title,String rightBtnText,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		TextView rightBtn = (TextView) findViewById(R.id.right_btn);
//		ImageView leftBtn = (ImageView) findViewById(R.id.left_btn);
//		leftBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//		rightBtn.setText(rightBtnText);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		leftBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//	}
public void setHeader(int leftDrawable,OnClickListener leftListener,String title,String rightText,int rightBadgeCount,int rightDrawable,OnClickListener rightListener){
	TextView titleBar = (TextView) findViewById(R.id.title);
	ImageView back = (ImageView) findViewById(R.id.left_btn);
	Button rightBtn = (Button) findViewById(R.id.right_btn);
	TextView badge = (TextView) findViewById(R.id.rightTitleBadgeCount);
	ImageView rightImageBtn = (ImageView) findViewById(R.id.right_image_btn);

	if (leftDrawable != 0){
		back.setVisibility(View.VISIBLE);
	}else{
		back.setVisibility(View.GONE);
	}
	if (leftDrawable!=DEFAULT_LEFT_DRAWABLE){
		back.setImageResource(leftDrawable);
	}
	back.setOnClickListener(leftListener);

	titleBar.setVisibility(View.VISIBLE);
	titleBar.setText(title);

	if (rightText!=null){
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setOnClickListener(rightListener);
	}else{
		rightBtn.setVisibility(View.GONE);
	}

	if (rightBadgeCount !=0){
		badge.setVisibility(View.VISIBLE);
		badge.setText(rightBadgeCount);
	}else{
		badge.setVisibility(View.GONE);
	}

	if (rightDrawable!=0){
		rightImageBtn.setVisibility(View.VISIBLE);
		rightImageBtn.setImageResource(rightDrawable);
		rightImageBtn.setOnClickListener(rightListener);
	}else{
		rightImageBtn.setVisibility(View.GONE);
	}
}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mQueue = Volley.newRequestQueue(this);
		mProgressDialog = new ProgressDialog(this,this);
		mHandler = new Handler(this);
		AppManager.getAppManager().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		AppManager.getAppManager().removeActivity(this);
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#startActivityForResult(android.content.Intent, int)
	 */
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
	}

	protected void sendPostWithFiles(final String url,final HashMap<String,String> params, final FormFile[] files, final String message, final SendPostWithFilesCallback callback) {

		if (!TANetWorkUtil.isNetworkAvailable(getApplicationContext())){
			Toast toast = Toast.makeText(getApplicationContext(), "请检查网络！",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			return;
		}
		mProgressDialog = new ProgressDialog(this,null);
		mProgressDialog.show(message,"请稍候...");

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if(SocketHttpRequester.post(url, params, files)){
						if(callback!=null){
							callback.onSuccess();
						}
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mProgressDialog.dismiss();
							}
						});
					}else{
						if(callback!=null){
							callback.onFailed();
						}
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								mProgressDialog.dismiss();
							}
						});
					}
				} catch (Exception e1) {
					if(callback!=null){
						callback.onFailed();
					}
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mProgressDialog.dismiss();
						}
					});
				}

			}
		}).start();

	}

	protected  interface SendPostWithFilesCallback{
		public void onSuccess();
		public void onFailed();
	}

	protected void sendPostRequest(final HashMap<String,String> params, final String message,final HashMap<String,String> headers, String url, final Response.Listener<String> listener, final Response.ErrorListener errorListener){
		if (!TANetWorkUtil.isNetworkAvailable(getApplicationContext())){
			Toast toast = Toast.makeText(getApplicationContext(), "请检查网络！",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			return;
		}
		mProgressDialog = new ProgressDialog(this,null);
		mProgressDialog.show(message,"请稍候...");
		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				listener.onResponse(response);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mProgressDialog.dismiss();
					}
				});
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				errorListener.onErrorResponse(error);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mProgressDialog.dismiss();
					}
				});
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> header = super.getHeaders();
				if (header != null) {
					if (headers != null) {
						header.putAll(headers);
					}
				}
				return header;
			}
		};
		RequestQueue v = Volley.newRequestQueue(getApplicationContext());
		v.add(stringRequest);
	}

	protected void sendGetRequest(final HashMap<String,String> params, final HashMap<String,String> headers, String url, final Response.Listener<String> listener, final Response.ErrorListener errorListener){
		if (!TANetWorkUtil.isNetworkAvailable(getApplicationContext())){
			Toast toast = Toast.makeText(getApplicationContext(), "请检查网络！",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			return;
		}
		mProgressDialog.show("加载中","请稍候...");
		StringBuffer strParams = new StringBuffer();
		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()){
			String key = iterator.next();
			String value = params.get(key);
			if (iterator.hasNext()){
				strParams.append(key+"="+value+"&");
			}else{
				strParams.append(key+"="+value);
			}
		}
		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				url+"?"+strParams, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				listener.onResponse(response);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mProgressDialog.dismiss();
					}
				});
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				errorListener.onErrorResponse(error);
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mProgressDialog.dismiss();
					}
				});
			}
		}){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> header = super.getHeaders();
				if (header != null) {
					if (headers != null) {
						header.putAll(headers);
					}
				}
				return header;
			}
		};
		RequestQueue v = Volley.newRequestQueue(getApplicationContext());
		v.add(stringRequest);
	}

	protected void showSelectPopWindow(final View maskView, View mainView,List<String> items,String title,boolean isMultiSelected, final SelectPicPopupWindow.OnConfirmListener listener){
		maskView.setVisibility(View.VISIBLE);
		SelectPicPopupWindow menuWindow_dept = new SelectPicPopupWindow(this, new SelectPicPopupWindow.OnConfirmListener() {

			@Override
			public void onConfirm(String result) {
				if (listener !=null){
					listener.onConfirm(result);
				}
				maskView.setVisibility(View.GONE);
			}
		}, title, items, isMultiSelected);
		menuWindow_dept.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				maskView.setVisibility(View.GONE);
			}
		});
		//显示窗口
		menuWindow_dept.showAtLocation(mainView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
	}
	// 时间
	public void getDate(final TextView et) {
		String curDate = et.getText().toString();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date temp = sdf.parse(curDate);
		}catch (Exception e){
			curDate = sdf.format(new Date());
		}
		int[] date = getYMDArray(curDate.split(" ")[0], "-");
		DatePickDialogWithoutHours birthDiolog = new DatePickDialogWithoutHours(this,
				new DatePickDialogWithoutHours.PriorityListener() {
					@Override
					public void refreshPriorityUI(String year, String month,
												  String day) {
						et.setText(year + "-" + month + "-" + day);
					}
				}, date[0], date[1], date[2], width,
				height, "选择时间");
		Window window = birthDiolog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
		window.setWindowAnimations(R.style.dialogstyle); // 添加动画
		birthDiolog.setCancelable(true);
		birthDiolog.show();
	}

	/**
	 * 时间截取
	 *
	 * @param datetime
	 * @param splite
	 * @return
	 */
	public static int[] getYMDArray(String datetime, String splite) {
		int date[] = { 0, 0, 0, 0, 0 };
		if (datetime != null && datetime.length() > 0) {
			String[] dates = datetime.split(splite);
			int position = 0;
			for (String temp : dates) {
				date[position] = Integer.valueOf(temp);
				position++;
			}
		}
		return date;
	}

	public void deleteFiles(List<String> files){
		for (String path:files){
			if(path.equals("")){
				continue;
			}
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
	}
	public void deleteSingleFile(String path){
		if(!path.equals("")){
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
	}
}