package hz.framework.android.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hz.framework.android.R;
import hz.framework.android.View.ProgressDialog;
import hz.framework.android.app.AppManager;
import hz.framework.android.utils.TANetWorkUtil;

/**
 *
 * @author houzheng
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements Handler.Callback ,OnClickListener,ProgressDialog.CancelListener{

	protected Handler mHandler;

	public RequestQueue mQueue;

	public ProgressDialog mProgressDialog;

	protected int DEFAULT_LEFT_DRAWABLE = 1;

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

//	public void setCommonReturnHeader(String title){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView back = (ImageView) findViewById(R.id.left_btn);
//		titleBar.setText(title);
//		back.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
//		titleBar.setVisibility(View.VISIBLE);
//		back.setVisibility(View.VISIBLE);
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
//     */
//	public void setOnlyTitleHeader(String title){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		titleBar.setText(title);
//		titleBar.setVisibility(View.VISIBLE);
//		findViewById(R.id.right_btn).setVisibility(View.INVISIBLE);
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
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
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
//	}
//	public void setOnlyRightTextHeaderWithBadge(String title,String rightText,int count,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		TextView badge = (TextView) findViewById(R.id.rightTitleBadgeCount);
//		Button rightBtn = (Button) findViewById(R.id.right_btn);
//		badge.setVisibility(View.VISIBLE);
//		badge.setText(count+"");
//		rightBtn.setText(rightText);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//	}
//
//	public void setLeftAndRightBtnHeader(String title,int leftDrawable,OnClickListener leftListener,int rightDrawable,OnClickListener rightListener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView rightBtn = (ImageView) findViewById(R.id.right_image_btn);
//		ImageView leftBtn = (ImageView) findViewById(R.id.left_btn);
//		leftBtn.setImageResource(leftDrawable);
//		rightBtn.setImageResource(rightDrawable);
//		rightBtn.setOnClickListener(rightListener);
//		leftBtn.setOnClickListener(leftListener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		leftBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
//	}
//	public void setOnlyRightBtnHeader(String title,int rightDrawable,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		ImageView rightBtn = (ImageView) findViewById(R.id.right_image_btn);
//		rightBtn.setImageResource(rightDrawable);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
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
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
//	}
//	public void setLeftAndRightBtnHeader(String title,String rightBtnText,OnClickListener listener){
//		TextView titleBar = (TextView) findViewById(R.id.title);
//		TextView rightBtn = (TextView) findViewById(R.id.right_btn);
//		ImageView leftBtn = (ImageView) findViewById(R.id.left_btn);
//		rightBtn.setText(rightBtnText);
//		rightBtn.setOnClickListener(listener);
//		titleBar.setText(title);
//		rightBtn.setVisibility(View.VISIBLE);
//		leftBtn.setVisibility(View.VISIBLE);
//		titleBar.setVisibility(View.VISIBLE);
//		findViewById(R.id.rightTitleBadgeCount).setVisibility(View.INVISIBLE);
//	}

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

	protected void sendPostRequest(final HashMap<String,String> params, final HashMap<String,String> headers, String url, final Response.Listener<String> listener, final Response.ErrorListener errorListener){
		if (!TANetWorkUtil.isNetworkAvailable(getApplicationContext())){
			Toast toast = Toast.makeText(getApplicationContext(), "请检查网络！",
					Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			return;
		}
		mProgressDialog.show("加载中","请稍候...");
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
}
