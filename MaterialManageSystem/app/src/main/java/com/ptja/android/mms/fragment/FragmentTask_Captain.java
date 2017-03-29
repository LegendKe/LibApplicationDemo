package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.CaptainRequireApplyActivity;
import com.ptja.android.mms.activity.equipment.EquipApproveActivity;
import com.ptja.android.mms.activity.equipment.EquipSearchActivity;
import com.ptja.android.mms.activity.task.CaptainTaskListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentTask_Captain extends Fragment {

    @Bind(R.id.task_list)
    RelativeLayout taskList;
    @Bind(R.id.require_apply)
    RelativeLayout requireApply;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_task_captain, null);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.task_list, R.id.require_apply})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.task_list:
                startActivity(new Intent(getContext(), CaptainTaskListActivity.class));
                break;
            case R.id.require_apply:
                startActivity(new Intent(getContext(), EquipApproveActivity.class));
                break;
        }
    }
}
