package com.ptja.android.mms;

import hz.framework.android.app.BaseApplication;

/**
 * Created by zhenghou on 2016/6/1.
 */
public class MMSApplication extends BaseApplication {
    @Override
    protected String getIflytekId() {
        return "577c66dc";
    }

    @Override
    protected boolean enableBaiduLoc() {
        return true;
    }

    @Override
    protected boolean enableJPush() {
        return false;
    }

    @Override
    protected boolean enableExceptionCaught() {
        return true;
    }
}
