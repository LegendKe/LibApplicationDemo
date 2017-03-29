package com.ptja.android.mms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipApproveActivity;
import com.ptja.android.mms.activity.equipment.EquipSearchActivity;
import com.ptja.android.mms.activity.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhenghou on 2016/6/3.
 */
public class FragmentSetup extends Fragment {
    @Bind(R.id.view_settingpage_name)
    TextView viewSettingpageName;
    @Bind(R.id.user_icon)
    ImageView userIcon;
    @Bind(R.id.entry_item_left_icon)
    TextView entryItemLeftIcon;
    @Bind(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @Bind(R.id.al_icon)
    ImageView alIcon;
    @Bind(R.id.auto_login_mode)
    CheckBox autoLoginMode;
    @Bind(R.id.rl_default)
    RelativeLayout rlDefault;
    @Bind(R.id.am_icon)
    ImageView amIcon;
    @Bind(R.id.update_icon)
    ImageView updateIcon;
    @Bind(R.id.arrow)
    ImageView arrow;
    @Bind(R.id.versionCode)
    TextView versionCode;
    @Bind(R.id.checkUpdate)
    RelativeLayout checkUpdate;
    @Bind(R.id.logout_icon)
    ImageView logoutIcon;
    @Bind(R.id.logout_btn)
    RelativeLayout logoutBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setup, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.logout_btn, R.id.checkUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
            case R.id.checkUpdate:
                startActivity(new Intent(getContext(), EquipSearchActivity.class));
                break;
        }
    }
}
