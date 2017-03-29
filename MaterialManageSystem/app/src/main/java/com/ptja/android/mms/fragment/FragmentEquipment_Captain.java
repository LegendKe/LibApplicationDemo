package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipApproveActivity;
import com.ptja.android.mms.activity.equipment.EquipSearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.base.BaseFragment;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentEquipment_Captain extends BaseFragment {

    @Bind(R.id.unitList)
    ListView unitList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_equip_captain, null);

        ButterKnife.bind(this, v);


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onClick(View v) {

    }
}
