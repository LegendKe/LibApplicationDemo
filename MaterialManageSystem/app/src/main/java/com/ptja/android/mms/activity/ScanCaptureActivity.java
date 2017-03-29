package com.ptja.android.mms.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dtr.zbar.build.ZBarDecoder;
import com.ptja.android.mms.activity.equipment.AddNewEquipmentActivity;
import com.ptja.android.mms.activity.equipment.EquipmentInActivity;
import com.ptja.android.mms.activity.equipment.EquipmentOutActivity;
import com.ptja.android.mms.activity.task.DealDiscardTaskActivity;
import com.ptja.android.mms.activity.task.DealRepairTaskActivity;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;
import com.zbar.scan.CameraManager;
import com.zbar.scan.CameraPreview;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hz.framework.android.R;
import hz.framework.android.base.BaseActivity;

public class ScanCaptureActivity extends BaseActivity {
    private String result = "";
    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private CameraManager mCameraManager;

    private FrameLayout scanPreview;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;
    List<EquipmentBean> beans = new ArrayList<>();
    private Rect mCropRect = null;
    private boolean barcodeScanned = false;
    private boolean previewing = true;
    private int operationType = -1;
    private static final int REQUEST_CODE_TASK = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zbar_scan_capture);
        operationType = getIntent().getIntExtra("oper_type", -1);
        switch (operationType) {
            case GlobeVariable.SCAN_TYPE_ADD:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"装备录入",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_DISCARD:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"装备报废",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_FIX:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"装备维保",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_IN:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"装备入库",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_OUT:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"装备出库","处理(0)",0,0,new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (beans.size()==0){
                            Toast.makeText(ScanCaptureActivity.this, "请扫描需要出库的物品", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(getApplicationContext(), EquipmentOutActivity.class);
                        intent.putExtra("data", (Serializable) beans);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case GlobeVariable.SCAN_TYPE_DEAL_REPAIR_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"维保任务处理",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_DEAL_DISCARD_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"报废任务处理",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_DEAL_CHECK_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"检查任务处理",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_ADD_CHECK_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"添加检查项",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_ADD_REPAIR_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"添加维保项",null,0,0,null);
                break;
            case GlobeVariable.SCAN_TYPE_ADD_DISCARD_TASK:
                setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                },"添加报废项",null,0,0,null);
                break;
            default:
                if (operationType == -1){
                    Toast.makeText(getApplicationContext(),"参数错误",Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
                break;
        }


        findViewById();
        initViews();
    }

    private void findViewById() {
        scanPreview = (FrameLayout) findViewById(R.id.capture_preview);
        scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);
    }

    private void initViews() {
        autoFocusHandler = new Handler();
        mCameraManager = new CameraManager(this);
        try {
            mCameraManager.openDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //调整扫描框大小,自适应屏幕
        Display display = this.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) scanCropView.getLayoutParams();
        linearParams.height = (int) (width * 0.8);
        linearParams.width = (int) (width * 0.8);
        scanCropView.setLayoutParams(linearParams);

        mCamera = mCameraManager.getCamera();
        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        scanPreview.addView(mPreview);
    }

    public void onPause() {
        super.onPause();
//		releaseCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    PreviewCallback previewCb = new PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {

            Size size = camera.getParameters().getPreviewSize();

            // 这里需要将获取的data翻转一下，因为相机默认拿的的横屏的数据
            byte[] rotatedData = new byte[data.length];
            for (int y = 0; y < size.height; y++) {
                for (int x = 0; x < size.width; x++)
                    rotatedData[x * size.height + size.height - y - 1] = data[x + y * size.width];
            }

            // 宽高也要调整
            int tmp = size.width;
            size.width = size.height;
            size.height = tmp;

            initCrop();
            ZBarDecoder zBarDecoder = new ZBarDecoder();
            String result2 = zBarDecoder.decodeCrop(rotatedData, size.width, size.height, mCropRect.left, mCropRect.top, mCropRect.width(), mCropRect.height());


            if (!TextUtils.isEmpty(result2)) {
                if(result.equals(result2)){
                    return;
                }else{
                    result = result2;
                }
                playBeepSoundAndVibrate();
//                if (operationType == GlobeVariable.SCAN_TYPE_ADD || operationType == GlobeVariable.SCAN_TYPE_DISCARD || operationType == GlobeVariable.SCAN_TYPE_FIX){
//                    previewing = false;
//                    mCamera.setPreviewCallback(null);
//                    mCamera.stopPreview();
//                    releaseCamera();
//                    barcodeScanned = true;
//                }

                HashMap<String, String> params = new HashMap<>();
                params.put("code", result);
                params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
                sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_EQUIP_BY_CODE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject response = new JSONObject(s);
                            if (response.getInt("code") == 0) {
                                if ((response.get("response") instanceof JSONObject)) {
                                    EquipmentBean equipmentBean = com.alibaba.fastjson.JSONObject.parseObject(response.getJSONObject("response").toString(), EquipmentBean.class);
                                    Intent intent = new Intent();
                                    switch (operationType) {
                                        case GlobeVariable.SCAN_TYPE_ADD:
                                            Message msg = new Message();
                                            msg.what = 1;
                                            msg.obj = "已经存在此物品";
                                            mHandler.sendMessage(msg);
                                            return;
                                        case GlobeVariable.SCAN_TYPE_OUT:
                                            beans.add(equipmentBean);
                                            mHandler.sendMessage(Message.obtain(mHandler,2));
                                            return;
                                        case GlobeVariable.SCAN_TYPE_IN:
                                            intent.setClass(getApplicationContext(), EquipmentInActivity.class);
//                                            beans.add(equipmentBean);
//                                            mHandler.sendMessage(Message.obtain(mHandler,2));
                                            break;
                                        case GlobeVariable.SCAN_TYPE_DISCARD:
                                            intent.setClass(getApplicationContext(), DealDiscardTaskActivity.class);
                                            intent.putExtra("data", (Serializable) equipmentBean);
                                            startActivityForResult(intent,REQUEST_CODE_TASK);
                                            return;
                                        case GlobeVariable.SCAN_TYPE_FIX:
                                            intent.setClass(getApplicationContext(), DealRepairTaskActivity.class);
                                            intent.putExtra("data", (Serializable) equipmentBean);
                                            startActivityForResult(intent,REQUEST_CODE_TASK);
                                            return;
                                        case GlobeVariable.SCAN_TYPE_DEAL_REPAIR_TASK:
                                            Intent dealRepairTaskIntent = new Intent(getApplicationContext(), DealRepairTaskActivity.class);
                                            dealRepairTaskIntent.putExtra("data", (Serializable) equipmentBean);
                                            dealRepairTaskIntent.putExtra("task_id",getIntent().getStringExtra("task_id"));
                                            dealRepairTaskIntent.putExtra("task_record_id",getIntent().getStringExtra("task_record_id"));
                                            startActivityForResult(dealRepairTaskIntent,REQUEST_CODE_TASK);
                                            return;

                                        case GlobeVariable.SCAN_TYPE_DEAL_DISCARD_TASK:
                                            Intent dealDiscardTaskIntent = new Intent(getApplicationContext(), DealDiscardTaskActivity.class);
                                            dealDiscardTaskIntent.putExtra("data", (Serializable) equipmentBean);
                                            dealDiscardTaskIntent.putExtra("task_id",getIntent().getStringExtra("task_id"));
                                            dealDiscardTaskIntent.putExtra("task_record_id",getIntent().getStringExtra("task_record_id"));
                                            startActivityForResult(dealDiscardTaskIntent,REQUEST_CODE_TASK);
                                            return;
                                        case GlobeVariable.SCAN_TYPE_DEAL_CHECK_TASK:
//                                            Intent dealCheckTaskIntent = new Intent(getApplicationContext(), EquipmentOutActivity.class);
//                                            dealCheckTaskIntent.putExtra("data", (Serializable) equipmentBean);
//                                            startActivity(dealCheckTaskIntent);
//                                            finish();
                                            return;
                                        case GlobeVariable.SCAN_TYPE_ADD_REPAIR_TASK:
                                            Intent addRepairTaskIntent = new Intent(getApplicationContext(), DealRepairTaskActivity.class);
                                            addRepairTaskIntent.putExtra("data", (Serializable) equipmentBean);
                                            addRepairTaskIntent.putExtra("task_id",getIntent().getStringExtra("task_id"));
                                            startActivityForResult(addRepairTaskIntent,REQUEST_CODE_TASK);
                                            return;

                                        case GlobeVariable.SCAN_TYPE_ADD_DISCARD_TASK:
                                            Intent addDiscardTaskIntent = new Intent(getApplicationContext(), DealDiscardTaskActivity.class);
                                            addDiscardTaskIntent.putExtra("data", (Serializable) equipmentBean);
                                            addDiscardTaskIntent.putExtra("task_id",getIntent().getStringExtra("task_id"));
                                            startActivityForResult(addDiscardTaskIntent,REQUEST_CODE_TASK);
                                            return;
                                        case GlobeVariable.SCAN_TYPE_ADD_CHECK_TASK:
//                                            Intent dealCheckTaskIntent = new Intent(getApplicationContext(), EquipmentOutActivity.class);
//                                            dealCheckTaskIntent.putExtra("data", (Serializable) equipmentBean);
//                                            startActivity(dealCheckTaskIntent);
//                                            finish();
                                            return;
                                    }
                                    Bundle b = new Bundle();
                                    b.putSerializable("data", equipmentBean);
                                    intent.putExtras(b);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    if (operationType == GlobeVariable.SCAN_TYPE_ADD) {
                                        Intent intent = new Intent();
                                        intent.setClass(getApplicationContext(), AddNewEquipmentActivity.class);
                                        intent.putExtra("code", result);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Message msg = new Message();
                                        msg.what = 1;
                                        msg.obj = "没有查到该物品";
                                        mHandler.sendMessage(msg);
                                    }

                                }

                            } else {
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj = response.getString("msg");
                                mHandler.sendMessage(msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK){
                    setResult(RESULT_OK,data);
                    finish();
                }
    }

    private void reset() {
//		try {
//			mCameraManager.openDriver();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        mCamera = mCameraManager.getCamera();
        mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
        scanPreview.removeAllViews();
        scanPreview.addView(mPreview, 0);
        previewing = true;
        barcodeScanned = false;
    }

    // Mimic continuous auto-focusing
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = mCameraManager.getCameraResolution().y;
        int cameraHeight = mCameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
//                reset();
                break;
            case 2:
                if (operationType == GlobeVariable.SCAN_TYPE_OUT){

                    setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    },"装备出库","处理("+beans.size()+")",0,0,new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (beans.size()==0){
                                Toast.makeText(ScanCaptureActivity.this, "请扫描需要出库的物品", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent intent = new Intent(getApplicationContext(), EquipmentOutActivity.class);
                            intent.putExtra("data", (Serializable) beans);
                            startActivity(intent);
                            finish();
                        }
                    });

                }else if(operationType == GlobeVariable.SCAN_TYPE_IN){
                    setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    },"装备入库","处理("+beans.size()+")",0,0,new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (beans.size()==0){
                                Toast.makeText(ScanCaptureActivity.this, "请扫描需要出库的物品", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent intent = new Intent(getApplicationContext(), EquipmentOutActivity.class);
                            intent.putExtra("data", (Serializable) beans);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    public void onClick(View v) {

    }
    private static final long VIBRATE_DURATION = 200L;

    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }
    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }
}
