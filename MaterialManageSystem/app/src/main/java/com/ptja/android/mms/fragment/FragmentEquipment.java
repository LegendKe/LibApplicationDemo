package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipApproveActivity;
import com.ptja.android.mms.activity.equipment.EquipSearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentEquipment extends Fragment {

    LineChart mChart;
    @Bind(R.id.judge)
    RelativeLayout judge;
    @Bind(R.id.equip_query)
    RelativeLayout equipQuery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_equip, null);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.judge, R.id.equip_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.judge:
                startActivity(new Intent(getContext(), EquipApproveActivity.class));
                break;
            case R.id.equip_query:
                startActivity(new Intent(getContext(), EquipSearchActivity.class));
                break;
        }
    }
}
