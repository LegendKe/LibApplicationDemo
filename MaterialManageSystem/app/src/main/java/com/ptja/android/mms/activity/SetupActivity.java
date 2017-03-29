package com.ptja.android.mms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.activity.equipment.EquipSearchActivity;
import com.ptja.android.mms.activity.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hz.framework.android.base.BaseActivity;

public class SetupActivity extends BaseActivity {


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setup);
        ButterKnife.bind(this);

        setHeader(DEFAULT_LEFT_DRAWABLE, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        },"设置",null,0,0,null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @OnClick({R.id.logout_btn, R.id.checkUpdate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_btn:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                break;
            case R.id.checkUpdate:
                startActivity(new Intent(getApplicationContext(), EquipSearchActivity.class));
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onCancel() {

    }
}
