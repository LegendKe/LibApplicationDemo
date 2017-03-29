package com.ptja.android.mms.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ptja.android.mms.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentTruck extends Fragment {

    @Bind(R.id.truck_query)
    RelativeLayout truckQuery;
    @Bind(R.id.truck_task)
    RelativeLayout truckTask;
    @Bind(R.id.truck_location)
    RelativeLayout truckLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_truck, null);


        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.truck_query, R.id.truck_task, R.id.truck_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.truck_query:

                break;
            case R.id.truck_task:
                break;
            case R.id.truck_location:
                break;
        }
    }
}
