package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ptja.android.mms.activity.ScanCaptureActivity;
import com.ptja.android.mms.commons.GlobeVariable;

import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.R;
import hz.framework.android.base.BaseFragment;

/**
 * Initial the camera
 *
 * @author Ryan.Tang & PengJian.Wu
 */
public class FragmentCapture extends BaseFragment {


    @Bind(R.id.equip_add)
    RelativeLayout equipAdd;
    @Bind(R.id.equip_out)
    RelativeLayout equipOut;
    @Bind(R.id.equip_in)
    RelativeLayout equipIn;
    @Bind(R.id.equip_fix)
    RelativeLayout equipFix;
    @Bind(R.id.equip_discard)
    RelativeLayout equipDiscard;
    private View view;

    /**
     * Called when the fragment is first created.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.activity_capture, null);
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

    @OnClick({R.id.equip_add, R.id.equip_out, R.id.equip_in, R.id.equip_fix, R.id.equip_discard})
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(),ScanCaptureActivity.class);
        switch (view.getId()) {
            case R.id.equip_add:
                intent.putExtra("oper_type", GlobeVariable.SCAN_TYPE_ADD);
                startActivity(intent);
                break;
            case R.id.equip_out:
                intent.putExtra("oper_type",GlobeVariable.SCAN_TYPE_OUT);
                startActivity(intent);
                break;
            case R.id.equip_in:
                intent.putExtra("oper_type",GlobeVariable.SCAN_TYPE_IN);
                startActivity(intent);
                break;
            case R.id.equip_fix:
                intent.putExtra("oper_type",GlobeVariable.SCAN_TYPE_FIX);
                startActivity(intent);
                break;
            case R.id.equip_discard:
                intent.putExtra("oper_type",GlobeVariable.SCAN_TYPE_DISCARD);
                startActivity(intent);
                break;
        }
    }
}