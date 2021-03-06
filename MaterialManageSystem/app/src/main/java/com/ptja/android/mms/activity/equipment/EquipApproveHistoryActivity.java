package com.ptja.android.mms.activity.equipment;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.adapter.EquipApproveHistoryListAdapter;
import com.ptja.android.mms.bean.EquipApproveBean;
import com.ptja.android.mms.commons.GlobeVariable;
import com.ptja.android.mms.commons.UrlConstants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.View.RefreshLoadMoreListView;
import hz.framework.android.base.BaseActivity;

public class EquipApproveHistoryActivity extends BaseActivity {
    @Bind(R.id.taskListView)
    RefreshLoadMoreListView taskPullRefreshListView;
    private EquipApproveHistoryListAdapter taskListAdapter;
    List<EquipApproveBean.ResponseBean.DataBean> taskBeans = new ArrayList<>();
    private int currentPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_approve_list);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "已审批", null, 0, 0,null);

        createTaskListview();
    }

    private void requestTaskListData(final RefreshLoadMoreListView.RefreshUIListener listener) {
        HashMap<String, String> params = new HashMap<>();
//        params.put("page", currentPageIndex + "");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_EQUIP_ARROVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    if (currentPageIndex == 1) {
                        taskBeans.clear();
                    }
                    EquipApproveBean bean = com.alibaba.fastjson.JSONObject.parseObject(s, EquipApproveBean.class);
                    if (bean.getCode() == 0) {
                        Message msg = new Message();
                        msg.what = 2;
                        for (EquipApproveBean.ResponseBean.DataBean b:bean.getResponse().getData()){
                            if (b.getCurrent_workflow_info().contains("结束")){
                                taskBeans.add(b);
                            }
                        }
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
                if (listener != null) {
                    listener.onUpdate();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (listener != null) {
                    listener.onUpdate();
                }
            }
        });
    }

    private void createTaskListview() {
        this.taskPullRefreshListView.setRefreshLoadmoreListenr(new RefreshLoadMoreListView.RefreshLoadMoreListviewListener() {
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
            this.taskListAdapter = new EquipApproveHistoryListAdapter(
                    getApplicationContext(), taskBeans, mHandler);

        this.taskPullRefreshListView.setAdapter(taskListAdapter, new AdapterView.OnItemClickListener() {

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
                currentPageIndex++;
                taskListAdapter.notifyDataSetChanged();
                break;
            case 3:
                currentPageIndex = 1;
                requestTaskListData(null);
                break;
            case 101://审批
                int re = msg.arg1;
                String approveId = (String) msg.obj;
                approve(re,approveId);
                break;
        }
        return true;
    }

    private void approve(int re,String approveId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("application_id", approveId);
        params.put("result", re + "");
        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
        sendPostRequest(params, "提交中", null, UrlConstants.URL_EQUIP_APPROVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    if (obj.getInt("code") == 0) {
                        Message msg = new Message();
                        msg.what = 3;
                        mHandler.sendMessage(msg);

                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = obj.get("msg");
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
