package com.ptja.android.mms.activity.login;

import com.ptja.android.mms.R;

import hz.framework.android.base.BaseSplashActivity;

public class LoadingActivity extends BaseSplashActivity {

    @Override
    protected int getSpLaunchImage() {
        return R.drawable.splash;
    }

    @Override
    protected Class<?> getNextActivity() {
        return LoginActivity.class;
    }

    @Override
    protected boolean enableMultiPage() {
        return false;
    }

    @Override
    protected int[] getImages() {
        return new int[]{R.drawable.splash};
    }
}
