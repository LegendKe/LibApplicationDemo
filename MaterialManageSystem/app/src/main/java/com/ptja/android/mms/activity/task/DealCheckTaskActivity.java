package com.ptja.android.mms.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.common.ShowPhotoActivity;
import com.ptja.android.mms.adapter.PhotoListAdapter;
import com.ptja.android.mms.bean.EquipmentBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.InsideGridView;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseActivity;

public class DealCheckTaskActivity extends BaseActivity {


    PhotoListAdapter photoAdapter;
    List<String> photos = new ArrayList<String>();
    EquipmentBean bean;
    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.equip_type)
    TextView equipType;
    @Bind(R.id.equip_name)
    TextView equipName;
    @Bind(R.id.equip_model)
    TextView equipModel;
    @Bind(R.id.equip_standard)
    TextView equipStandard;
    @Bind(R.id.equip_unit)
    TextView equipUnit;
    @Bind(R.id.equip_num)
    TextView equipNum;
    @Bind(R.id.equip_store_location)
    TextView equipStoreLocation;
    @Bind(R.id.equip_produce_date)
    TextView equipProduceDate;
    @Bind(R.id.equip_keep_pre)
    TextView equipKeepPre;
    @Bind(R.id.arrow)
    TextView arrow;
    @Bind(R.id.equip_keep)
    TextView equipKeep;
    @Bind(R.id.addPhoto)
    InsideGridView addPhoto;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_info);
        ButterKnife.bind(this);
        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "添加装备", "操作", 0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation();
            }
        });

        bean = (EquipmentBean) getIntent().getExtras().getSerializable("data");
        code.setText(bean.getCode());
        equipType.setText(bean.getEquipment_type().getEquipment_type_name());
        equipName.setText(bean.getEquipment_name());
        equipModel.setText(bean.getModel());
        equipStandard.setText(bean.getStandard());
        equipUnit.setText(bean.getUnit());
        equipNum.setText(bean.getTotal_number());
        equipStoreLocation.setText(bean.getDepot().getDepot_name());
        equipProduceDate.setText(bean.getPro_date());
        equipKeep.setText(bean.getShelf_life());

        EquipmentBean.GalleryBean galleryBean = bean.getGallery();
        for(int i = 0;i<galleryBean.getImages().size();i++){
            photos.add(galleryBean.getImages().get(i).getUrl());
        }
        photoAdapter = new PhotoListAdapter(getApplicationContext(), photos);
        addPhoto.setAdapter(photoAdapter);
        addPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                    Intent intent = new Intent(getApplicationContext(), ShowPhotoActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("data", (Serializable) photos);
                    b.putInt("curPosition", arg2);
                    b.putInt("type", 0);
                    intent.putExtras(b);
                    startActivity(intent);
            }
        });
    }

    String filename;

    private void operation() {
        List<String> types = new ArrayList<String>();
        types.add("出库");
        types.add("入库");
        types.add("维保");
        types.add("报废");
        showSelectPopWindow(mask, main, types, "选择操作", false, new SelectPicPopupWindow.OnConfirmListener() {
            @Override
            public void onConfirm(String result) {
                if (result.equals("出库")) {

                } else if (result.equals("入库")){
                }else if(result.equals("维保")){
                }else if (result.equals("报废")){
                }

            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        return true;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onClick(View v) {
    }



}
