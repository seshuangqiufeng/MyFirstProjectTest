package com.guoqiang.wgqviewtest;

import android.app.Application;

/**
 * Created by wangguoqiang on 2017/2/7.
 */
public class BaseApplication extends Application {

    public static boolean DEBUG = true;

    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static boolean isUserDebug() {
        return DEBUG;
    }

    public static BaseApplication getContext() {
        return mContext;
    }
}
