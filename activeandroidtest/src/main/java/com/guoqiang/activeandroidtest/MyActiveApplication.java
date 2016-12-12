package com.guoqiang.activeandroidtest;


import com.activeandroid.ActiveAndroid;

/**
 * Created by wangguoqiang on 2016/10/31.
 */
public class MyActiveApplication extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
