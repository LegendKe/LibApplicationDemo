package com.ptja.android.mms.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.adapter.TaskDealListAdapter;
import com.ptja.android.mms.bean.TaskBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.base.BaseActivity;

public class TaskListActivity extends BaseActivity {
    @Bind(R.id.taskListView)
    ListView taskList;
    private TaskDealListAdapter taskListAdapter;
    TaskBean taskBean;
    private static final int REQUEST_CODE_ADD_TASK = 1;
    public static final int REQUEST_CODE_DEAL_TASK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_deal_list);
        ButterKnife.bind(this);
        taskBean = (TaskBean) getIntent().getSerializableExtra("data");
        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "处理任务", null, 0, R.drawable.add,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskRecord();
            }
        });


        createTaskListview();

    }

    private void addTaskRecord() {
        Intent intent = new Intent(getApplicationContext(), ScanCaptureActivity.class);
        intent.putExtra("oper_type", GlobeVariable.TASK_TYPE_ADD_TYPE.get(taskBean.getTask_type().getTask_type_id()));
        intent.putExtra("task_id",taskBean.getTask_id());
        startActivityForResult(intent,REQUEST_CODE_ADD_TASK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK){
                    queryTaskInfo();
                }
    }

    private void queryTaskInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("task_id",taskBean.getTask_id());
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_TASK, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {
                        if (response.get("response") instanceof JSONArray) {
                            JSONArray array = ((JSONArray) response.get("response"));

                            for (int i = 0; i < array.length(); i++) {
                                taskBean = com.alibaba.fastjson.JSONObject.parseObject(array.getJSONObject(i).toString(), TaskBean.class);
                            }
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

    private void createTaskListview() {
        this.taskListAdapter = new TaskDealListAdapter(
                getApplicationContext(), taskBean, mHandler);

        this.taskList.setAdapter(taskListAdapter);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                this.taskListAdapter = new TaskDealListAdapter(
                        getApplicationContext(), taskBean, mHandler);

                this.taskList.setAdapter(taskListAdapter);
                break;
            case 100://deal task
                TaskBean.TaskRecordBean bean = (TaskBean.TaskRecordBean) msg.obj;
                Intent intent = new Intent(getApplicationContext(), ScanCaptureActivity.class);
                intent.putExtra("oper_type",GlobeVariable.TASK_TYPE_OPERATE_TYPE.get(taskBean.getTask_type_id()));
                intent.putExtra("equipment_id", bean.getEquipment_id());
                intent.putExtra("task_id",bean.getTask_id());
                intent.putExtra("task_record_id",bean.getTask_record_id());
                startActivityForResult(intent,REQUEST_CODE_DEAL_TASK);
                break;
        }
        return true;
    }

    @Override
    public void onCancel() {

    }

    public void onClick(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
