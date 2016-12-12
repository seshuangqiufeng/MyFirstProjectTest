package com;

import android.app.Application;

/**
 * Created by wangguoqiang on 2016/10/17.
 */
public class BaseApplication extends Application{

    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static BaseApplication getContext() {
        return mContext;
    }
}
