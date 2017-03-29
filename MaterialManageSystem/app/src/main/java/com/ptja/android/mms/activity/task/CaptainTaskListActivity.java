package com.ptja.android.mms.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.adapter.TaskListCaptainAdapter;
import com.ptja.android.mms.bean.TaskBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.RefreshLoadMoreListView;
import hz.framework.android.base.BaseActivity;

public class CaptainTaskListActivity extends BaseActivity {
    @Bind(R.id.taskListView)
    RefreshLoadMoreListView taskListView;
    @Bind(R.id.main)
    LinearLayout main;
    private TaskListCaptainAdapter taskListAdapter;
    List<TaskBean> taskBeans  = new ArrayList<>();
    private static final int REQUEST_CODE_ADD_TASK = 1;
    private int currentPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_finished_list);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "任务列表", null, 0, R.drawable.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddNewTaskActivity.class));
            }
        });

        createTaskListview();
    }

    private void requestTaskListData(final RefreshLoadMoreListView.RefreshUIListener listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("page", currentPageIndex + "");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_TASK, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    if (currentPageIndex == 1) {
                        taskBeans.clear();
                    }
                    JSONObject response = new JSONObject(s);
                    if (response.getInt("code") == 0) {
                        if (response.get("response") instanceof JSONArray) {
                            JSONArray array = ((JSONArray) response.get("response"));

                            for (int i = 0; i < array.length(); i++) {
                                TaskBean taskBean = com.alibaba.fastjson.JSONObject.parseObject(array.getJSONObject(i).toString(), TaskBean.class);
                                    taskBeans.add(taskBean);
                            }
                        }
                        Message msg = new Message();
                        msg.what = 2;
                        mHandler.sendMessage(msg);
                        if (listener != null) {
                            listener.onUpdate();
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

    private void createTaskListview() {
        this.taskListView.setRefreshLoadmoreListenr(new RefreshLoadMoreListView.RefreshLoadMoreListviewListener() {
            @Override
            public void onRefrsh(final RefreshLoadMoreListView.RefreshUIListener listener) {
                currentPageIndex = 1;
                requestTaskListData(listener);
            }

            @Override
            public void loadMore(final RefreshLoadMoreListView.RefreshUIListener listener) {
                requestTaskListData(listener);
            }
        });
            this.taskListAdapter = new TaskListCaptainAdapter(
                    getApplicationContext(), taskBeans, mHandler);

        this.taskListView.setAdapter(taskListAdapter, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
//                        Intent i = new Intent();
//                        i.setClass(getApplicationContext(),
//                                TaskInfoActivity.class);
//                        i.putExtra(TASK, JSONUtils.toJson(unfinishedTaskListItems.get(position-1)));
//                        startActivity(i);
            }
        });
        requestTaskListData(null);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                taskListAdapter.notifyDataSetChanged();
                break;
            case 100://deal task
                TaskBean taskBean = (TaskBean) msg.obj;
                Intent intent = new Intent(getApplicationContext(), ScanCaptureActivity.class);
                intent.putExtra("oper_type", GlobeVariable.TASK_TYPE_OPERATE_TYPE.get(taskBean.getTask_type().getTask_type_id()));
                intent.putExtra("data", (Serializable) msg.obj);
                startActivity(intent);
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
