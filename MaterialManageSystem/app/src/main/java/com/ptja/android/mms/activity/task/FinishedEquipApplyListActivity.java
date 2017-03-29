package com.ptja.android.mms.activity.task;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.adapter.EquipApplyFinishedListAdapter;
import com.ptja.android.mms.adapter.EquipApplyListAdapter;
import com.ptja.android.mms.adapter.TaskDealListAdapter;
import com.ptja.android.mms.bean.EquipApplyBean;
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
import hz.framework.android.utils.DateUtil;

public class FinishedEquipApplyListActivity extends BaseActivity {
    @Bind(R.id.equipApplyListView)
    RefreshLoadMoreListView taskPullRefreshListView;
    private EquipApplyFinishedListAdapter taskListAdapter;
    List<EquipApplyBean> equipApplyBeans = new ArrayList<>();
    private int currentPageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_apply_track_ed_list);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "已完成装备申请", null, 0, 0,null);


        createTaskListview();
    }


    private void requestTaskListData(final RefreshLoadMoreListView.RefreshUIListener listener) {
        if (listener!=null){
            listener.onUpdate();
            return;
        }
        for (int i = 0;i<20;i++){
            EquipApplyBean bean = new EquipApplyBean();
            bean.setDate(DateUtil.getDateString("yyyy-MM-dd HH:mm"));
            bean.setEquipName("灭火器");
            bean.setNumber((i+1)+"");
            bean.setStatus((i%2)+1);
            bean.setTitle("特殊装备领取");

            equipApplyBeans.add(bean);
        }

        taskListAdapter.notifyDataSetChanged();



//        HashMap<String, String> params = new HashMap<>();
//        params.put("page", currentPageIndex + "");
//        params.put("access_token", GlobeVariable.UserInfos.getAccess_token());
//        sendPostRequest(params, "查询中", null, UrlConstants.URL_QUERY_TASK, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                try {
//                    if (currentPageIndex == 1) {
//                        equipApplyBeans.clear();
//                    }
//                    JSONObject response = new JSONObject(s);
//                    if (response.get("code") == 0) {
//                        if (response.get("response") instanceof JSONArray) {
//                            JSONArray array = ((JSONArray) response.get("response"));
//
//                            for (int i = 0; i < array.length(); i++) {
//                                TaskBean taskBean = com.alibaba.fastjson.JSONObject.parseObject(array.getJSONObject(i).toString(), TaskBean.class);
//                               if (taskBean.getStatus_text().equals("未处理")){
//                                   equipApplyBeans.add(taskBean);
//                               }
//
//                            }
//                        }
//                        Message msg = new Message();
//                        msg.what = 2;
//                        mHandler.sendMessage(msg);
//                        if (listener != null) {
//                            listener.onUpdate();
//                        }
//                    } else {
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.obj = response.getString("msg");
//                        mHandler.sendMessage(msg);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//            }
//        });
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
        this.taskListAdapter = new EquipApplyFinishedListAdapter(
                getApplicationContext(), equipApplyBeans, mHandler);

        this.taskPullRefreshListView.setAdapter(taskListAdapter, new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long id) {
            }
        });
        requestTaskListData(null);
    }

    @Override
    public boolean handleMessage(Message msg) {
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
