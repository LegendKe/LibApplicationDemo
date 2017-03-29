package com.ptja.android.mms.activity.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.common.PickPhotoActivity;
import com.ptja.android.mms.activity.common.ShowPhotoActivity;
import com.ptja.android.mms.adapter.PhotoListAdapter;
import com.ptja.android.mms.bean.EquipApproveBean;
import com.ptja.android.mms.bean.EquipMentTypeBean;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.bean.RepaireSubmitResultBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.View.InsideGridView;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.utils.DateUtil;

/**
 *
 */
public class DealRepairTaskActivity extends BaseActivity {


    PhotoListAdapter photoAdapter;
    List<String> photos = new ArrayList<String>();
    EquipmentBean bean;
    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.equip_name)
    TextView equipName;
    @Bind(R.id.equip_type)
    TextView equipType;
    @Bind(R.id.repair_person)
    TextView repairPerson;
    @Bind(R.id.repair_result_layout)
    RelativeLayout repairResultLayout;
    @Bind(R.id.repair_time)
    TextView repairTime;
    @Bind(R.id.repair_result_value)
    TextView repairResultValue;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;
    @Bind(R.id.addPhoto)
    InsideGridView addPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_repair_task);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "装备维保", null, 0, R.drawable.submit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        bean = (EquipmentBean) getIntent().getExtras().getSerializable("data");
        code.setText(bean.getCode());
        equipType.setText(bean.getEquipment_type().getEquipment_type_name());
        equipName.setText(bean.getEquipment_name());
        repairPerson.setText(GlobeVariable.UserInfos.getReal_name());
        repairTime.setText(DateUtil.getDateString("yyyy-MM-dd HH:mm:ss"));


        photos.add("");
        photoAdapter = new PhotoListAdapter(getApplicationContext(), photos);
        addPhoto.setAdapter(photoAdapter);
        addPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (photos.get(arg2).equals("")) {
                    mask.setVisibility(View.VISIBLE);
                    List<String> types = new ArrayList<String>();
                    types.add("相册");
                    types.add("相机");
                    showSelectPopWindow(mask, main, types, "选择来源", false, new SelectPicPopupWindow.OnConfirmListener() {
                        @Override
                        public void onConfirm(String result) {
                            if (result.equals("相册")) {
                                Intent intent = new Intent(getApplicationContext(), PickPhotoActivity.class);
                                intent.putExtra("max", 5 - photos.size() + 1);
                                startActivityForResult(intent, TAKE_PHOTO_BY_GALLERY);
                            } else {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                filename = Environment.getExternalStorageDirectory() + "/image" + System.currentTimeMillis() + ".jpg";
                                File file = new File(filename);

                                Uri imageUri = Uri.fromFile(file);
                                // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                startActivityForResult(intent, TAKE_PHOTO_BY_CAMERA);
                            }
                        }
                    });
                } else {
                    Intent intent = new Intent(getApplicationContext(), ShowPhotoActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("data", (Serializable) photos);
                    b.putInt("curPosition", arg2);
                    b.putInt("type", 1);
                    intent.putExtras(b);
                    startActivityForResult(intent, EDIT_PHOTO_LIST);
                }
            }
        });
    }

    String filename;

    private void submit() {
        HashMap<String, String> params = new HashMap<>();
        params.put("equipment_id",bean.getEquipment_id());
        params.put("result",GlobeVariable.REPAIR_TASK_RESULT.get(repairResultValue.getText().toString()));
        params.put("maintenance_content",repairResultValue.getText().toString());
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "提交中", null, UrlConstants.URL_SUBMIT_REPAIRE_TASK, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    RepaireSubmitResultBean bean = com.alibaba.fastjson.JSONObject.parseObject(s, RepaireSubmitResultBean.class);
                    if (bean.getCode() == 0) {
                        Message msg = new Message();
                        msg.what = 2;
                        msg.obj = bean.getMsg();
                        mHandler.sendMessage(msg);
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = bean.getMsg();
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }
    private void submitTask() {
        HashMap<String, String> params = new HashMap<>();
        params.put("equipment_id",bean.getEquipment_id());
        params.put("result",GlobeVariable.REPAIR_TASK_RESULT.get(repairResultValue.getText().toString()));
        if (getIntent().getStringExtra("task_record_id")!=null){
            params.put("task_record_id",getIntent().getStringExtra("task_record_id"));
        }
        params.put("task_id",getIntent().getStringExtra("task_id"));
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "提交中", null, UrlConstants.URL_TASK_RECORD_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    if (obj.getInt("code") == 0) {
                        setResult(RESULT_OK,getIntent());
                        finish();
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = obj.getString("msg");
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:
                Toast.makeText(DealRepairTaskActivity.this, (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                if (getIntent().getStringExtra("task_id")!=null){
                    submitTask();
                }else{
                    finish();
                }
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case TAKE_PHOTO_BY_CAMERA:
                if (resultCode == RESULT_OK) {
                    photos.remove(photos.size() - 1);
                    photos.add(filename);
                    if (photos.size() < 5) {
                        photos.add("");
                    }
                    photoAdapter.notifyDataSetChanged();
                }
                break;
            case TAKE_PHOTO_BY_GALLERY:
                if (resultCode == RESULT_OK) {
                    List<String> re = (List<String>) data.getSerializableExtra("data");
                    if (re != null) {
                        photos.remove(photos.size() - 1);
                        photos.addAll(re);
                        if (photos.size() < 5) {
                            photos.add("");
                        }

                    }
                    photoAdapter.notifyDataSetChanged();
                }
                break;
            case EDIT_PHOTO_LIST:
                if (resultCode == RESULT_OK) {
                    List<String> re = (List<String>) data.getSerializableExtra("data");
                    if (re != null) {
                        photos = re;
                        if (photos.size() < 5) {
                            photos.add("");
                        }
                    }
                    photoAdapter = new PhotoListAdapter(getApplicationContext(), photos);
                    addPhoto.setAdapter(photoAdapter);
                    addPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                                long arg3) {
                            if (photos.get(arg2).equals("")) {
                                mask.setVisibility(View.VISIBLE);
                                List<String> types = new ArrayList<String>();
                                types.add("相册");
                                types.add("相机");
                                showSelectPopWindow(mask, main, types, "选择来源", false, new SelectPicPopupWindow.OnConfirmListener() {
                                    @Override
                                    public void onConfirm(String result) {
                                        if (result.equals("相册")) {
                                            Intent intent = new Intent(getApplicationContext(), PickPhotoActivity.class);
                                            intent.putExtra("max", 5 - photos.size() + 1);
                                            startActivityForResult(intent, TAKE_PHOTO_BY_GALLERY);
                                        } else {
                                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            filename = Environment.getExternalStorageDirectory() + "/image" + System.currentTimeMillis() + ".jpg";
                                            File file = new File(filename);
                                            Uri imageUri = Uri.fromFile(file);
                                            // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                                            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                            startActivityForResult(intent, TAKE_PHOTO_BY_CAMERA);
                                        }
                                    }
                                });
                            } else {
                                Intent intent = new Intent(getApplicationContext(), ShowPhotoActivity.class);
                                Bundle b = new Bundle();
                                b.putSerializable("data", (Serializable) photos);
                                b.putInt("curPosition", arg2);
                                b.putInt("type", 1);
                                intent.putExtras(b);
                                startActivityForResult(intent, EDIT_PHOTO_LIST);
                            }
                        }
                    });
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @OnClick(R.id.repair_result_layout)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.repair_result_layout:
                List<String> names = new ArrayList<>();
                Iterator<String> iterator = GlobeVariable.REPAIR_TASK_RESULT.keySet().iterator();
                while (iterator.hasNext()){
                    names.add(iterator.next());
                }
                showSelectPopWindow(mask, main, names, "选择结果", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        repairResultValue.setText(result);
                    }
                });
                break;
        }

    }
}
