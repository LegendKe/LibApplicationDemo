package com.ptja.android.mms.activity.task;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.adapter.TaskListAdapter;
import com.ptja.android.mms.bean.TaskBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hz.framework.android.base.BaseActivity;

public class FinishedTaskListActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        ButterKnife.bind(this);

    }


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onCancel() {

    }

    public void onClick(View view) {
    }
}
