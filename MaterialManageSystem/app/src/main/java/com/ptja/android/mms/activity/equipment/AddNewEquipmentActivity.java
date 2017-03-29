package com.ptja.android.mms.activity.equipment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.common.PickPhotoActivity;
import com.ptja.android.mms.activity.common.ShowPhotoActivity;
import com.ptja.android.mms.adapter.PhotoListAdapter;
import com.ptja.android.mms.bean.EquipMentTypeBean;
import com.ptja.android.mms.bean.EquipmentDeptBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.AutoHiddenHintEditText;
import hz.framework.android.View.InsideGridView;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.models.FormFile;
import hz.framework.android.utils.ImageFactory;

public class AddNewEquipmentActivity extends BaseActivity {


    PhotoListAdapter photoAdapter;
    List<String> photos = new ArrayList<String>();
    private final int TAKE_PHOTO_BY_CAMERA = 1;

    private final int TAKE_PHOTO_BY_GALLERY = 2;

    private final int EDIT_PHOTO_LIST = 3;
    @Bind(R.id.left_btn)
    ImageView leftBtn;
    @Bind(R.id.right_btn)
    Button rightBtn;
    @Bind(R.id.right_image_btn)
    ImageView rightImageBtn;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.oper_type)
    TextView operType;
    @Bind(R.id.equip_txt)
    TextView equipTxt;
    @Bind(R.id.equip_type)
    RelativeLayout equipType;
    @Bind(R.id.equip_name)
    AutoHiddenHintEditText equipName;
    @Bind(R.id.equip_unit_txt)
    TextView equipUnitTxt;
    @Bind(R.id.equip_unit)
    RelativeLayout equipUnit;
    @Bind(R.id.equip_num_txt)
    TextView equipNumTxt;
    @Bind(R.id.equip_num_value)
    AutoHiddenHintEditText equipNumValue;
    @Bind(R.id.equip_store_location_txt)
    TextView equipStoreLocationTxt;
    @Bind(R.id.equip_store_location)
    RelativeLayout equipStoreLocation;
    @Bind(R.id.equip_keep_txt)
    AutoHiddenHintEditText equipKeepTxt;
    @Bind(R.id.equip_keep_time)
    RelativeLayout equipKeepTime;
    @Bind(R.id.addPhoto)
    InsideGridView photoContainer;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;
    @Bind(R.id.equip_model)
    AutoHiddenHintEditText equipModel;
    @Bind(R.id.equip_standard)
    AutoHiddenHintEditText equipStandard;
    @Bind(R.id.equip_store_location_pre)
    TextView equipStoreLocationPre;
    @Bind(R.id.arrow9)
    ImageView arrow9;
    @Bind(R.id.equip_keep_pre)
    TextView equipKeepPre;
    @Bind(R.id.arrow10)
    TextView arrow10;
    @Bind(R.id.scroll)
    ScrollView scroll;
    @Bind(R.id.equip_produce_date_txt)
    TextView equipProduceDateTxt;
    @Bind(R.id.equip_produce_date)
    RelativeLayout equipProduceDate;

    private String qrCode;
    private String selectedEquipTypeId, selectedEquipDeptId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_equip);
        ButterKnife.bind(this);
        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "装备类型统计", null, 0, R.drawable.submit,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        qrCode = getIntent().getStringExtra("code");
        code.setText(qrCode);
        operType.setText("入库");
        equipType.setOnClickListener(this);
        equipStoreLocation.setOnClickListener(this);
        equipUnit.setOnClickListener(this);
        equipProduceDate.setOnClickListener(this);

        photos.add("");
        photoAdapter = new PhotoListAdapter(getApplicationContext(), photos);
        photoContainer.setAdapter(photoAdapter);
        photoContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case TAKE_PHOTO_BY_CAMERA:
                if (resultCode == RESULT_OK) {
                    photos.remove(photos.size() - 1);
                    String newFileName = Environment.getExternalStorageDirectory() + "/image" + System.currentTimeMillis() + ".jpg";
                    try {
                        ImageFactory.getInstance().compressAndGenImage(filename,newFileName,true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    photos.add(newFileName);
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
                    photoContainer.setAdapter(photoAdapter);
                    photoContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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

    private void submit() {
        if (equipTxt.getText().toString().equals("请选择")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请选择物品类型", Toast.LENGTH_SHORT).show();
            return;
        }

        if (equipUnitTxt.getText().toString().equals("请选择")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请选择物品单位", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipStoreLocationTxt.getText().toString().equals("请选择")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请选择存储位置", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipProduceDateTxt.getText().toString().equals("请选择")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请选择生产日期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipName.getText().toString().equals("")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请输入物品名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipNumValue.getText().toString().equals("")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请输入物品数量", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            Integer.parseInt(equipNumValue.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(AddNewEquipmentActivity.this, "物品数量只能为数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipKeepTxt.getText().toString().equals("")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请输入物品保质期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipModel.getText().toString().equals("")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请输入物品型号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipStandard.getText().toString().equals("")) {
            Toast.makeText(AddNewEquipmentActivity.this, "请输入物品规格", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("access_token",GlobeVariable.UserInfos.getAccess_token());
        params.put("equipment_name", equipName.getText().toString());
        params.put("model", equipModel.getText().toString());
        params.put("standard", equipStandard.getText().toString());
        params.put("code", code.getText().toString());
        params.put("total_number", equipNumValue.getText().toString());
        params.put("unit", equipUnitTxt.getText().toString());
        params.put("equipment_type_id", selectedEquipTypeId);
        params.put("depot_id", selectedEquipDeptId);
        params.put("pro_date", equipProduceDateTxt.getText().toString());
        params.put("shelf_life", equipKeepTxt.getText().toString());

        FormFile [] files = new FormFile[photos.size()-1];
        for(int i = 0;i<files.length;i++){
            File f = new File(photos.get(i));
            FormFile formFile = new FormFile(f.getName(), f, f.getName(), null);
            files[i] = formFile;
        }

        sendPostWithFiles(UrlConstants.URL_COMMIT_NEW_EQUIP, params, files, "提交中", new SendPostWithFilesCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                        deleteFiles(photos);
                    }
                });

                finish();
            }

            @Override
            public void onFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"提交失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                List<String> types = new ArrayList<>();
                for (EquipMentTypeBean bean : typeBeans) {
                    types.add(bean.getEquipment_type_name());
                }
                showSelectPopWindow(mask, main, types, "选择物品类型", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        equipTxt.setText(result);
                        for (EquipMentTypeBean bean : typeBeans) {
                            if (bean.getEquipment_type_name().equals(result)){
                                selectedEquipTypeId = bean.getEquipment_type_id();
                            }
                        }
                    }
                });
                break;
            case 3:
                final List<String> depts = new ArrayList<>();
                for (EquipmentDeptBean bean : deptBeans) {
                    depts.add(bean.getDept_name());
                }
                showSelectPopWindow(mask, main, depts, "选择存储位置", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        for (EquipmentDeptBean bean : deptBeans) {
                            if (bean.getDept_name().equals(result)){
                                selectedEquipDeptId = bean.getDept_id();
                            }
                        }
                        equipStoreLocationTxt.setText(result);
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.equip_type:
                getEquipType();
                break;
            case R.id.equip_store_location:
                getEquipDept();
                break;
            case R.id.equip_unit:
                showSelectPopWindow(mask, main, GlobeVariable.UNITS, "选择单位", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        equipUnitTxt.setText(result);
                    }
                });
                break;
            case R.id.equip_produce_date:
                getDate(equipProduceDateTxt);
                break;
        }
    }

    /**
     * 获取物品类型
     */
    private List<EquipMentTypeBean> typeBeans = new ArrayList<>();

    private void getEquipType() {
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_EQUIP_TYPE, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                typeBeans.clear();
                try {
                    typeBeans.clear();
                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {

                        JSONArray array = JSONArray.parseArray(response.getJSONArray("response").toString());
                        for (int i = 0; i < array.size(); i++) {
                            EquipMentTypeBean bean = com.alibaba.fastjson.JSONObject.parseObject(array.get(i).toString(), EquipMentTypeBean.class);
                            typeBeans.add(bean);
                        }
                        Message msg = new Message();
                        msg.what = 2;
                        mHandler.sendMessage(msg);
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

    /**
     * 获取物质单位
     */
    private List<EquipmentDeptBean> deptBeans = new ArrayList<>();

    private void getEquipDept() {
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_DEPT, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deptBeans.clear();
                try {

                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {

                        JSONArray array = JSONArray.parseArray(response.getJSONArray("response").toString());
                        for (int i = 0; i < array.size(); i++) {
                            EquipmentDeptBean bean = com.alibaba.fastjson.JSONObject.parseObject(array.get(i).toString(), EquipmentDeptBean.class);
                            deptBeans.add(bean);
                        }
                        Message msg = new Message();
                        msg.what = 3;
                        mHandler.sendMessage(msg);
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
