package com.ptja.android.mms.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipMentTypeBean;
import com.ptja.android.mms.bean.EquipmentDeptBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
public class FragmentEquipApply extends BaseFragment {
    public static FragmentEquipApply instance;
    @Bind(R.id.equip_txt)
    TextView equipTxt;
    @Bind(R.id.equip_type)
    RelativeLayout equipType;
    @Bind(R.id.equip_name_value)
    AutoHiddenHintEditText equipNameValue;
    @Bind(R.id.equip_name_layout)
    RelativeLayout equipNameLayout;
    @Bind(R.id.equip_num_value)
    TextView equipNumValue;
    @Bind(R.id.equip_num_layout)
    RelativeLayout equipNumLayout;
    @Bind(R.id.task_content)
    AutoHiddenHintEditText taskContent;
    @Bind(R.id.equip_time_value)
    TextView equipTimeValue;
    @Bind(R.id.equip_time_layout)
    RelativeLayout equipTimeLayout;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;

    public FragmentEquipApply() {
        instance = this;
    }

    private String selectedEquipTypeId, selectedEquipDeptId;
    private int selectedEquipStatus = -2;


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
            case 3:
                Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
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


    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_equip_apply, null);
        ButterKnife.bind(this, v);
        return v;
    }

    public void submit() {


        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        params.put("reason",taskContent.getText().toString());
        params.put("receive_time",equipTimeValue.getText().toString());
        params.put("equipment_type_id",selectedEquipTypeId);
        params.put("equipment_name",equipNameValue.getText().toString());
        params.put("equipment_count",equipNumValue.getText().toString());

        sendPostRequest(params, "提交中", null, UrlConstants.URL_EQUIP_APPROVE_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                deptBeans.clear();
                try {

                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.equip_type, R.id.equip_name_layout, R.id.equip_num_layout, R.id.equip_time_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.equip_type:
                getEquipType();
                break;
            case R.id.equip_name_layout:
                break;
            case R.id.equip_num_layout:
                final List<String> numbers = new ArrayList<>();
                for (int i = 1;i<50;i++){
                    numbers.add(i+"");
                }
                showSelectPopWindow(mask, main, numbers, "选择数量", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        equipNumValue.setText(result);
                    }
                });
                break;
            case R.id.equip_time_layout:
                getDate(equipTimeValue);
                break;
        }
    }
}
