package hz.framework.android.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.sunflower.FlowerCollector;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * @author zhenghou
 *讯飞语音转写工具类
 *集成步骤：
 *1.拷贝asset文件
 *2.在application中初始化
 *3.在manifest.xml中注册录音权限
 *4.调用
 */
public class IatUtil {
	private IatUtil(){
		
	}
	private String TAG = "IatUtil";
	
	// 语音听写对象
		private SpeechRecognizer mIat;
		// 语音听写UI
		private RecognizerDialog mIatDialog;
		// 用HashMap存储听写结果
		private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

		private Toast mToast;
		// 引擎类型
		private String mEngineType = SpeechConstant.TYPE_CLOUD;
		
		
	private Activity mActivity;
	private static IatUtil instance;
	public static IatUtil getInstance(Activity activity){
		if(instance == null){
			instance = new IatUtil();
		}
		if(instance.mActivity == null || instance.mActivity!=activity){
			instance.mActivity = activity;
			instance.mIat = SpeechRecognizer.createRecognizer(activity, instance.mInitListener);
			
			// 初始化听写Dialog，如果只使用有UI听写功能，无需创建SpeechRecognizer
			// 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
			instance.mIatDialog = new RecognizerDialog(activity, instance.mInitListener);

			instance.mToast = Toast.makeText(instance.mActivity, "", Toast.LENGTH_SHORT);
		}
		return instance;
	} 
	
	/**
	 * 初始化监听器。
	 */
	private InitListener mInitListener = new InitListener() {
		@Override
		public void onInit(int code) {
			Log.d(TAG, "SpeechRecognizer init() code = " + code);
			if (code != ErrorCode.SUCCESS) {
				showTip("初始化失败，错误码：" + code);
			}
		}
	};
	
	private void showTip(final String str) {
		mToast.setText(str);
		mToast.show();
	}
	
	/**
	 * 听写UI监听器
	 */
	private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
		public void onResult(RecognizerResult results, boolean isLast) {
			printResult(results,isLast);
		}

		/**
		 * 识别回调错误.
		 */
		public void onError(SpeechError error) {
			showTip(error.getPlainDescription(true));
		}

	};
	
	private void printResult(RecognizerResult results,boolean isLast) {
		String text = JsonParser.parseIatResult(results.getResultString());

		String sn = null;
		// 读取json结果中的sn字段
		try {
			JSONObject resultJson = new JSONObject(results.getResultString());
			sn = resultJson.optString("sn");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mIatResults.put(sn, text);

		StringBuffer resultBuffer = new StringBuffer();
		for (String key : mIatResults.keySet()) {
			resultBuffer.append(mIatResults.get(key));
		}

		if(iatListener!=null){
			iatListener.onSuccess(resultBuffer.toString(),isLast);
		}
	}
	
	/**
	 * 参数设置
	 * 
	 * @param param
	 * @return
	 */
	public void setParam() {
		// 清空参数
		mIat.setParameter(SpeechConstant.PARAMS, null);

		// 设置听写引擎
		mIat.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
		// 设置返回结果格式
		mIat.setParameter(SpeechConstant.RESULT_TYPE, "json");

		String lag ="mandarin";
		if (lag.equals("en_us")) {
			// 设置语言
			mIat.setParameter(SpeechConstant.LANGUAGE, "en_us");
		} else {
			// 设置语言
			mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
			// 设置语言区域
			mIat.setParameter(SpeechConstant.ACCENT, lag);
		}

		// 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
		mIat.setParameter(SpeechConstant.VAD_BOS, "4000");
		
		// 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
		mIat.setParameter(SpeechConstant.VAD_EOS,"1000");
		
		// 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
		mIat.setParameter(SpeechConstant.ASR_PTT, "1");
		
		// 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
		// 注：AUDIO_FORMAT参数语记需要更新版本才能生效
		mIat.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
		mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/iat.wav");
		
		// 设置听写结果是否结果动态修正，为“1”则在听写过程中动态递增地返回结果，否则只在听写结束之后返回最终结果
		// 注：该参数暂时只对在线听写有效
		mIat.setParameter(SpeechConstant.ASR_DWA, "0");
	}
	public void startRecorgnize(IatListener listener){
		this.iatListener = listener;
		mIatResults.clear();
		// 设置参数
		setParam();
			// 显示听写对话框
			mIatDialog.setTitle("开始说话");
			mIatDialog.setListener(mRecognizerDialogListener);
			mIatDialog.show();
			showTip("请开始说话");
	}
	
	private IatListener iatListener;
	public interface IatListener{
		public void onSuccess(String result, boolean isLast);
	}
	
	public void resume(){
		FlowerCollector.onResume(instance.mActivity);
		FlowerCollector.onPageStart(TAG);
	}
	
	public void pause(){
		FlowerCollector.onPageEnd(TAG);
		FlowerCollector.onPause(instance.mActivity);
	}
	
	public void destroy(){
		mIat.cancel();
		mIat.destroy();
	}
	
}
