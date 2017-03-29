package com.ptja.android.mms.activity.task;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipmentDeptBean;
import com.ptja.android.mms.bean.QueryUserBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.View.SelectPicPopupWindow;
import hz.framework.android.base.BaseActivity;
import hz.framework.android.utils.IatUtil;

public class AddNewTaskActivity extends BaseActivity {


    @Bind(R.id.task_type_value)
    TextView taskTypeValue;
    @Bind(R.id.task_type_layout)
    RelativeLayout taskTypeLayout;
    @Bind(R.id.input_speech)
    ImageView inputSpeech;
    @Bind(R.id.task_content)
    EditText taskContent;
    @Bind(R.id.task_address_value)
    TextView taskAddressValue;
    @Bind(R.id.task_location_layout)
    RelativeLayout taskLocationLayout;
    @Bind(R.id.task_starttime_value)
    TextView taskStarttimeValue;
    @Bind(R.id.task_starttime_layout)
    RelativeLayout taskStarttimeLayout;
    @Bind(R.id.task_endtime_value)
    TextView taskEndtimeValue;
    @Bind(R.id.task_endtime_layout)
    RelativeLayout taskEndtimeLayout;
    @Bind(R.id.task_consignee_value)
    TextView taskConsigneeValue;
    @Bind(R.id.task_consignee_layout)
    RelativeLayout taskConsigneeLayout;
    @Bind(R.id.mask)
    View mask;
    @Bind(R.id.main)
    FrameLayout main;
    private String selectedTaskLocationId;
    private String selectedTaskTypeId;
    private String selectedPersonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "新建任务", null, 0, R.drawable.submit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {
        HashMap<String, String> params = new HashMap<>();

        if (taskContent.getText().toString().equals("")){
            Toast.makeText(AddNewTaskActivity.this, "请输入任务内容", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("task_content",taskContent.getText().toString());
        }
        if (taskStarttimeValue.getText().toString().equals("请选择")){
            Toast.makeText(AddNewTaskActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("start_time",taskStarttimeValue.getText().toString());
        }
        if (taskEndtimeValue.getText().toString().equals("请选择")){
            Toast.makeText(AddNewTaskActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("end_time",taskEndtimeValue.getText().toString());
        }
        if (selectedPersonId==null){
            Toast.makeText(AddNewTaskActivity.this, "请指定负责人", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("to_user_id",selectedPersonId);
        }
        if (selectedTaskTypeId==null){
            Toast.makeText(AddNewTaskActivity.this, "请指定任务类型", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("task_type_id",selectedTaskTypeId);
        }
        if (selectedTaskLocationId==null){
            Toast.makeText(AddNewTaskActivity.this, "请指定任务位置", Toast.LENGTH_SHORT).show();
            return;
        }else{
            params.put("depot_id",selectedTaskLocationId);
        }
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "提交中", null, UrlConstants.URL_TASK_ADD, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    org.json.JSONObject response = new org.json.JSONObject(s);
                    if (response.getInt("code") == 0) {
                        finish();
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
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:

                break;
            case 3:
                final List<String> depts = new ArrayList<>();
                for (EquipmentDeptBean bean : deptBeans) {
                    depts.add(bean.getDept_name());
                }
                showSelectPopWindow(mask, main, depts, "选择任务位置", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        for (EquipmentDeptBean bean : deptBeans) {
                            if (bean.getDept_name().equals(result)){
                                selectedTaskLocationId = bean.getDept_id();
                            }
                        }
                        taskAddressValue.setText(result);
                    }
                });
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
                        taskConsigneeValue.setText(result);
                    }
                });
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }



//    @OnClick({R.id.discard_result_layout, R.id.discard_time_layout})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.discard_result_layout:
//                List<String> names = new ArrayList<>();
//                Iterator<String> iterator = GlobeVariable.DISCARD_TASK_RESULT.keySet().iterator();
//                while (iterator.hasNext()) {
//                    names.add(GlobeVariable.REPAIR_TASK_RESULT.get(iterator.next()));
//                }
//                mask.setVisibility(View.VISIBLE);
//                SelectPicPopupWindow menuWindow_dept = new SelectPicPopupWindow(DealDiscardTaskActivity.this, new SelectPicPopupWindow.OnConfirmListener() {
//
//                    @Override
//                    public void onConfirm(String result) {
//                        discardTimeValue.setText(result);
//                        mask.setVisibility(View.GONE);
//                    }
//                }, "选择结果", names, false);
//                menuWindow_dept.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//                    @Override
//                    public void onDismiss() {
//                        mask.setVisibility(View.GONE);
//                    }
//                });
//                //显示窗口
//                menuWindow_dept.showAtLocation(DealDiscardTaskActivity.this.findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
//
//                break;
//            case R.id.discard_time_layout:
//                if (discardTimeValue.getText().equals("")) {
//                    getDate(discardResultValue, null);
//                } else {
//                    getDate(discardResultValue, discardResultValue.getText().toString());
//                }
//                break;
//        }
//    }

    StringBuffer sb = new StringBuffer();
    @OnClick({R.id.task_type_layout, R.id.input_speech, R.id.task_location_layout, R.id.task_starttime_layout, R.id.task_endtime_layout, R.id.task_consignee_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.task_type_layout:
                final List<String> taskTypes = new ArrayList<>();
                Iterator<String> iterator = GlobeVariable.TASK_TYPE.keySet().iterator();
                while(iterator.hasNext()){
                    taskTypes.add(iterator.next());
                }
                showSelectPopWindow(mask, main, taskTypes, "选择任务类型", false, new SelectPicPopupWindow.OnConfirmListener() {
                    @Override
                    public void onConfirm(String result) {
                        taskTypeValue.setText(result);
                        selectedTaskTypeId = GlobeVariable.TASK_TYPE.get(result);
                    }
                });
                break;
            case R.id.input_speech:
                IatUtil.getInstance(this).startRecorgnize(new IatUtil.IatListener() {
                    @Override
                    public void onSuccess(String result, boolean isLast) {
                        if(isLast){
                            sb.append(result);
                            taskContent.setText(sb.toString());
                            taskContent.setSelection(taskContent.length());
                        }
                    }
                });
                break;
            case R.id.task_location_layout:
                getEquipDept();
                break;
            case R.id.task_starttime_layout:
                    getDate(taskStarttimeValue);
                break;
            case R.id.task_endtime_layout:
                    getDate(taskEndtimeValue);
                break;
            case R.id.task_consignee_layout:
                getUsers();
                break;
        }
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
    /**
     * 获取单位人员
     */
    private List<QueryUserBean> deptUserBeans = new ArrayList<>();

    private void getUsers() {
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        params.put("dept_id",selectedTaskLocationId==null?"":selectedTaskLocationId);
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
}
