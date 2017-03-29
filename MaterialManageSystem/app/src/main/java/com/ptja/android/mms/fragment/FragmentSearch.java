package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipListActivity;
import com.ptja.android.mms.bean.EquipMentTypeBean;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.bean.EquipmentDeptBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.View.AutoHiddenHintEditText;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseFragment;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentSearch extends BaseFragment {
    @Bind(R.id.equip_txt)
    TextView equipTxt;
    @Bind(R.id.equip_type)
    RelativeLayout equipType;
    @Bind(R.id.equip_name)
    AutoHiddenHintEditText equipName;
    @Bind(R.id.equip_model)
    AutoHiddenHintEditText equipModel;
    @Bind(R.id.equip_status_value)
    TextView equipStatusValue;
    @Bind(R.id.equip_status_layout)
    RelativeLayout equipStatusLayout;
    @Bind(R.id.equip_store_location_txt)
    TextView equipStoreLocationTxt;
    @Bind(R.id.equip_store_location)
    RelativeLayout equipStoreLocation;
    @Bind(R.id.equip_in_date_value)
    TextView equipInDateValue;
    @Bind(R.id.search)
    Button search;
    @Bind(R.id.equip_in_date)
    RelativeLayout equipInDate;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;
    private String selectedEquipTypeId,selectedEquipDeptId;
    private int selectedEquipStatus = -2;


    List<EquipmentBean> equipList = new ArrayList<>();
    private void search() {
        HashMap<String, String> params = new HashMap<>();
        if (selectedEquipDeptId!=null){
            params.put("depot_id",selectedEquipDeptId);
        }
        if (!equipName.getText().toString().equals("")){
            params.put("key_word",equipName.getText().toString());
        }
        if (selectedEquipTypeId!=null){
            params.put("equipment",selectedEquipTypeId);
        }
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_EQUIP, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    org.json.JSONObject response = new org.json.JSONObject(s);
                    if (response.getInt("code") == 0) {
                        if ((response.getJSONObject("response").get("data") instanceof org.json.JSONArray)) {
                            org.json.JSONArray array = (org.json.JSONArray) response.getJSONObject("response").get("data");
                            for (int i = 0;i<array.length();i++){
                                org.json.JSONObject o = array.getJSONObject(i);
                                EquipmentBean equipmentBean = com.alibaba.fastjson.JSONObject.parseObject(o.toString(), EquipmentBean.class);
                                equipList.add(equipmentBean);
                            }
                            Intent intnet = new Intent(getContext(),EquipListActivity.class);
                            intnet.putExtra("data", (Serializable) equipList);
                            startActivity(intnet);
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


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(getActivity(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
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
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.equip_type, R.id.equip_status_layout, R.id.equip_store_location, R.id.equip_in_date,R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                search();
                break;
            case R.id.equip_type:
                getEquipType();
                break;
            case R.id.equip_status_layout:
                final List<String> status = new ArrayList<>();
                Iterator<String> iterator = GlobeVariable.EQUIP_STATUS.keySet().iterator();
                while (iterator.hasNext()){
                    status.add(iterator.next());
                }
                showSelectPopWindow(mask, main, status, "选择装备状态", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        selectedEquipStatus = GlobeVariable.EQUIP_STATUS.get(result);
                        equipStatusValue.setText(result);
                    }
                });
                break;
            case R.id.equip_store_location:
                getEquipDept();
                break;
            case R.id.equip_in_date:
                getDate(equipInDateValue);
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
                    org.json.JSONObject response = new org.json.JSONObject(s);
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

                    org.json.JSONObject response = new org.json.JSONObject(s);
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


    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.fragment_search,null);
        ButterKnife.bind(this,v);
        return v;
    }
}
