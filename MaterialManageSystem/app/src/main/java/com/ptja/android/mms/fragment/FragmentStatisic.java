package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.activity.statisic.EquipDeptRateStatisicActivity;
import com.ptja.android.mms.activity.statisic.EquipStatusStatisicActivity;
import com.ptja.android.mms.activity.statisic.EquipTotalStatisicActivity;
import com.ptja.android.mms.activity.statisic.EquipTypeStatisicActivity;
import com.ptja.android.mms.commons.GlobeVariable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.base.BaseFragment;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentStatisic extends BaseFragment {
    @Bind(R.id.equip_total)
    RelativeLayout equipTotal;
    @Bind(R.id.equip_type)
    RelativeLayout equipType;
    @Bind(R.id.equip_status)
    RelativeLayout equipStatus;
    @Bind(R.id.equip_depart)
    RelativeLayout equipDepart;
    private View view;

    /**
     * Called when the fragment is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.activity_statistic, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onCancel() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.equip_total, R.id.equip_type, R.id.equip_status, R.id.equip_depart})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.equip_total:
                intent.setClass(getActivity(), EquipTotalStatisicActivity.class);
                startActivity(intent);
                break;
            case R.id.equip_type:
                intent.setClass(getActivity(), EquipTypeStatisicActivity.class);
                startActivity(intent);
                break;
            case R.id.equip_status:
                intent.setClass(getActivity(), EquipStatusStatisicActivity.class);
                startActivity(intent);
                break;
            case R.id.equip_depart:
                intent.setClass(getActivity(), EquipDeptRateStatisicActivity.class);
                startActivity(intent);
                break;
        }
    }
}
