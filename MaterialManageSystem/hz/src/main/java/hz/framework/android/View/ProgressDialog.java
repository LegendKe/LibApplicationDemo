package hz.framework.android.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import hz.framework.android.R;

/**
 * @author zhenghou
 */
public class ProgressDialog extends Dialog {

	private CancelListener cancelListener;
	private boolean cancelable = true;//默认可以取消
	private String title;
	private Activity mContext;
	public ProgressDialog(Activity context,CancelListener cancelListener) {
		super(context, R.style.ImageloadingDialogStyle);
		this.mContext = context;
		this.cancelListener = cancelListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_imageloading);
		ImageView spaceshipImage = (ImageView) findViewById(R.id.img);
		TextView title = (TextView) findViewById(R.id.dialog_title);
		if(this.title!=null){
			title.setText(this.title);
		}
		if(this.cancelListener!=null){
			this.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					cancelListener.onCancel();
				}
			});
		}
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getContext(),
				R.anim.animation);
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		
	}
	
	public interface CancelListener{
		public void onCancel();
	}

	public void show(int dialog_title, int dialog_message) {
		this.title = mContext.getResources().getString(dialog_message);
		show();
	}
	public void show(String dialog_title, String dialog_message) {
		this.title = dialog_title;
		show();
	}

}
