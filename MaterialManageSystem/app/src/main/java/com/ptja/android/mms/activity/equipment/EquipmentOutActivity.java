package com.ptja.android.mms.activity.equipment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.common.PickPhotoActivity;
import com.ptja.android.mms.activity.common.ShowPhotoActivity;
import com.ptja.android.mms.adapter.EquipmentListAdapter;
import com.ptja.android.mms.adapter.PhotoListAdapter;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.bean.EquipmentDeptBean;
import com.ptja.android.mms.bean.QueryUserBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.AutoHiddenHintEditText;
import hz.framework.android.View.InsideGridView;
import hz.framework.android.View.InsideListView;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.models.FormFile;

public class EquipmentOutActivity extends BaseActivity {


    PhotoListAdapter photoAdapter;
    List<String> photos = new ArrayList<String>();
    private final int TAKE_PHOTO_BY_CAMERA = 1;
    private final int TAKE_PHOTO_BY_GALLERY = 2;
    private final int EDIT_PHOTO_LIST = 3;
    @Bind(R.id.equipList)
    InsideListView equipList;
    @Bind(R.id.equip_num_voice_input)
    ImageView equipNumVoiceInput;
    @Bind(R.id.equip_num)
    AutoHiddenHintEditText equipNum;
    @Bind(R.id.equip_person)
    TextView equipPerson;
    @Bind(R.id.equip_dept)
    TextView equipDept;
    @Bind(R.id.equip_dept_layout)
    RelativeLayout equipDeptLayout;
    @Bind(R.id.equip_person_layout)
    RelativeLayout equip_person_click_layout;
    @Bind(R.id.equip_use_type)
    TextView equipUseType;
    @Bind(R.id.equip_use_type_layout)
    RelativeLayout equipUseTypeLayout;
    @Bind(R.id.addPhoto)
    InsideGridView addPhoto;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.equip_user_layout)
    LinearLayout equip_person_layout;
    @Bind(R.id.main)
    FrameLayout main;
    private String selectedEquipDeptId,selectedPersonId;
    List<EquipmentBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_out);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "物资出库", null, 0, R.drawable.submit,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        beans = (List<EquipmentBean>) getIntent().getSerializableExtra("data");
        EquipmentListAdapter adapter = new EquipmentListAdapter(getApplicationContext(),beans);
        equipList.setAdapter(adapter);
        equipList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EquipmentInfoActivity.class);
                intent.putExtra("data",beans.get(position));
                startActivity(intent);
            }
        });
        equipDeptLayout.setOnClickListener(this);
        equipUseTypeLayout.setOnClickListener(this);
        equip_person_click_layout.setOnClickListener(this);



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
                                b.putInt("curPosition", arg2 - 1);
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
        if (equipNum.getText().toString().equals("")) {
            Toast.makeText(EquipmentOutActivity.this, "请输入领取数量", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            Integer.parseInt(equipNum.getText().toString());
        }catch (NumberFormatException e){
            Toast.makeText(EquipmentOutActivity.this, "领取数量只能为数字", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipPerson.getText().toString().equals("") && equipDept.getText().toString().equals("请选择")) {
            Toast.makeText(EquipmentOutActivity.this, "请选择领取单位或领取人", Toast.LENGTH_SHORT).show();
            return;
        }
        if (equipUseType.getText().toString().equals("请选择")) {
            Toast.makeText(EquipmentOutActivity.this, "请选择使用类型", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
//        params.put("equipment_name", equipName.getText().toString());
        if(selectedEquipDeptId!=null){
            params.put("out_dept_id", selectedEquipDeptId);
        }
        if(selectedPersonId!=null){
            params.put("out_user_id", selectedEquipDeptId);
        }
        org.json.JSONArray array = new org.json.JSONArray();

        for (EquipmentBean b: beans){
            JSONObject obj = new JSONObject();
            try {
                obj.put("equipment_id",Integer.parseInt(b.getEquipment_id()));
                obj.put("oper_type",2);
                obj.put("oper_count",Integer.parseInt(equipNum.getText().toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }
        params.put("data", array.toString());

        FormFile[] files = new FormFile[photos.size() - 1];
        for (int i = 0; i < files.length; i++) {
            File f = new File(photos.get(i));
            FormFile formFile = new FormFile(f.getName(), f, f.getName(), null);
            files[i] = formFile;
        }

        sendPostWithFiles(UrlConstants.URL_COMMIT_OUT_STOCK, params, files, "提交中", new SendPostWithFilesCallback() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "提交成功", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(), "提交失败", Toast.LENGTH_SHORT).show();
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
                final List<String> depts = new ArrayList<>();
                for (EquipmentDeptBean bean : deptBeans) {
                    depts.add(bean.getDept_name());
                }
                showSelectPopWindow(mask, main, depts, "选择领用单位", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        for (EquipmentDeptBean bean : deptBeans) {
                            if (bean.getDept_name().equals(result)) {
                                selectedEquipDeptId = bean.getDept_id();
                            }
                        }
                        equipDept.setText(result);
                        Message msg = new Message();
                        msg.what = 3;
                        mHandler.sendMessage(msg);
                    }
                });
                break;
            case 3:
                equip_person_layout.setVisibility(View.VISIBLE);
                break;
            case 4:
                final List<String> users = new ArrayList<>();
                for (QueryUserBean bean : deptUserBeans) {
                    users.add(bean.getReal_name());
                }
                showSelectPopWindow(mask, main, users, "选择领用人", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        for (QueryUserBean bean : deptUserBeans) {
                            if (bean.getReal_name().equals(result)) {
                                selectedPersonId = bean.getUser_id();
                            }
                        }
                        equipPerson.setText(result);
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
            case R.id.equip_dept_layout:
                getEquipDept();
                break;
            case R.id.equip_use_type_layout:
                showSelectPopWindow(mask, main, GlobeVariable.USE_TYPE, "选择使用类型", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        equipUseType.setText(result);
                    }
                });
                break;
            case R.id.equip_person_layout:
                getUsers();
                break;
        }
    }

    /**
     * 获取单位人员
     */
    private List<QueryUserBean> deptUserBeans = new ArrayList<>();

    private void getUsers() {
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        params.put("dept_id",selectedEquipDeptId);
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_DEPT_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deptBeans.clear();
                try {

                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {

                        JSONArray array = JSONArray.parseArray(response.getJSONArray("response").toString());
                        for (int i = 0; i < array.size(); i++) {
                         QueryUserBean bean = com.alibaba.fastjson.JSONObject.parseObject(array.get(i).toString(), QueryUserBean.class);
                            deptUserBeans.add(bean);
                        }
                        Message msg = new Message();
                        msg.what = 4;
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
     * 获取物品单位
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


}
