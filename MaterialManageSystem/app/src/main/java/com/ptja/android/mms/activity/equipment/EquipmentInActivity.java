package com.ptja.android.mms.activity.equipment;

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
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import org.w3c.dom.Text;

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

public class EquipmentInActivity extends BaseActivity {


    PhotoListAdapter photoAdapter;
    List<String> photos = new ArrayList<String>();
    private final int TAKE_PHOTO_BY_CAMERA = 1;
    private final int TAKE_PHOTO_BY_GALLERY = 2;
    private final int EDIT_PHOTO_LIST = 3;
    @Bind(R.id.equip_name)
    TextView equipName;
    @Bind(R.id.equip_num_voice_input)
    ImageView equipNumVoiceInput;
    @Bind(R.id.equip_num)
    AutoHiddenHintEditText equipNum;
    @Bind(R.id.addPhoto)
    InsideGridView addPhoto;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;
    EquipmentBean beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_in);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "物资入库", null, 0, R.drawable.submit,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        beans = (EquipmentBean) getIntent().getSerializableExtra("data");
//        EquipmentListAdapter adapter = new EquipmentListAdapter(getApplicationContext(),beans);
//        equipList.setAdapter(adapter);
//        equipList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(),EquipmentInfoActivity.class);
//                intent.putExtra("data",beans.get(position));
//                startActivity(intent);
//            }
//        });
        equipName.setText(beans.getEquipment_name());
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
            Toast.makeText(EquipmentInActivity.this, "请输入归还数量", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Integer.parseInt(equipNum.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(EquipmentInActivity.this, "归还数量只能为数字", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        params.put("equipment_id", beans.getEquipment_id());
        params.put("oper_count", equipNum.getText().toString());

        FormFile[] files = new FormFile[photos.size() - 1];
        for (int i = 0; i < files.length; i++) {
            File f = new File(photos.get(i));
            FormFile formFile = new FormFile(f.getName(), f, f.getName(), null);
            files[i] = formFile;
        }

        sendPostWithFiles(UrlConstants.URL_COMMIT_IN_STOCK, params, files, "提交中", new SendPostWithFilesCallback() {
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
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onClick(View v) {
    }
}
