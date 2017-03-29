package hz.framework.android.utils;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.SeekBar;

public class AudioPlayUtil implements OnBufferingUpdateListener,
		OnCompletionListener, OnPreparedListener {
	
	private AudioPlayUtil(){
		try {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
		} catch (Exception e) {
			Log.e("mediaPlayer", "error", e);
		}

		mTimer.schedule(mTimerTask, 0, 1000);
	}
	
	private OnBufferingUpdateListener bufferChangeListener;
	
	public OnBufferingUpdateListener getBufferChangeListener() {
		return bufferChangeListener;
	}

	public void setBufferChangeListener(
			OnBufferingUpdateListener bufferChangeListener) {
		this.bufferChangeListener = bufferChangeListener;
	}

	private Context mContext;
	private static AudioPlayUtil instance;
	
	public static AudioPlayUtil getInstance(Context context){
		if(instance == null){
			instance = new AudioPlayUtil();
		}
		instance.mContext = context;
		return instance;
	}
	
	
	private MediaPlayer mediaPlayer;
	private Timer mTimer = new Timer();


	/*******************************************************
	 * 通过定时器和Handler来更新进度条
	 ******************************************************/
	TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (mediaPlayer.isPlaying()/* && skbProgress.isPressed() == false */) {
				handleProgress.sendEmptyMessage(0);
			}
		}
	};

	Handler handleProgress = new Handler() {
		public void handleMessage(Message msg) {

			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();

			if (duration > 0) {
				// long pos = skbProgress.getMax() * position / duration;
				// skbProgress.setProgress((int) pos);
			}
		};
	};

	// *****************************************************

	public void play() {
		mediaPlayer.start();
	}

	private OnCompletionListener completetionListener;
	public void playUrl(String videoUrl,OnCompletionListener listener) {
		try {
			this.completetionListener = listener;
			mediaPlayer.reset();
			mediaPlayer.setDataSource(videoUrl);
			mediaPlayer.prepare();// prepare之后自动播放
			mediaPlayer.setOnPreparedListener(this);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	/**  
	 * 通过onPrepared播放  
	 */
	public void onPrepared(MediaPlayer arg0) {
		arg0.start();
		if(this.completetionListener!=null){
			arg0.setOnCompletionListener(completetionListener);
		}
		
		Log.e("mediaPlayer", "onPrepared");
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		
		Log.e("mediaPlayer", "onCompletion");
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int bufferingProgress) {
		if(bufferChangeListener!=null){
			bufferChangeListener.onBufferingUpdate(mp, bufferingProgress);
		}
		// skbProgress.setSecondaryProgress(bufferingProgress);
		// int
		// currentProgress=skbProgress.getMax()*mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration();
		// Log.e(currentProgress+"% play", bufferingProgress + "% buffer");
	}

	public boolean isPlaying() {
		if(mediaPlayer == null){
			return false;
		}
		return mediaPlayer.isPlaying();
	}

}