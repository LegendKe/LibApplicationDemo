package hz.framework.android.app;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechUtility;

import hz.framework.android.map.LocationService;
import hz.framework.android.map.WriteLog;
import hz.framework.android.utils.CrashHandler;

public abstract class BaseApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    public static BaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (enableExceptionCaught()){
            registerUncaughtExceptionHandler();
        }

        if(getIflytekId()!=null){
            SpeechUtility.createUtility(this, "appid="+getIflytekId());
        }

        if (enableBaiduLoc()){
            /***
             * 初始化定位sdk，建议在Application中创建
             */
            locationService = new LocationService(getApplicationContext());
            mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
            WriteLog.getInstance().init(); // 初始化日志
            SDKInitializer.initialize(getApplicationContext());
        }

    }

    protected abstract String getIflytekId();

    protected abstract boolean enableBaiduLoc();

    protected abstract boolean enableJPush();

    protected abstract boolean enableExceptionCaught();
    // 注册App异常崩溃处理器
    private void registerUncaughtExceptionHandler() {
        CrashHandler mCustomCrashHandler = CrashHandler
                .getInstance();
        mCustomCrashHandler.init(getApplicationContext());
    }

}