package com.ptja.android.mms.activity.equipment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.adapter.EquipListAdapter;
import com.ptja.android.mms.adapter.TaskDealListAdapter;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.bean.TaskBean;
import com.ptja.android.mms.commons.GlobeVariable;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.base.BaseActivity;

public class EquipListActivity extends BaseActivity {
    @Bind(R.id.taskListView)
    ListView taskList;
    private EquipListAdapter taskListAdapter;
    List<EquipmentBean> equipList;
    private static final int REQUEST_CODE_ADD_TASK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_deal_list);
        ButterKnife.bind(this);
        equipList = (List<EquipmentBean>) getIntent().getSerializableExtra("data");

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }, "搜索结果", null, 0, 0,null);

        createTaskListview();
    }


    private void createTaskListview() {
        this.taskListAdapter = new EquipListAdapter(
                getApplicationContext(), equipList, mHandler);

        this.taskList.setAdapter(taskListAdapter);
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EquipmentInfoActivity.class);
                intent.putExtra("data",equipList.get(position));
                startActivity(intent);
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

    public void onClick(View view) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
