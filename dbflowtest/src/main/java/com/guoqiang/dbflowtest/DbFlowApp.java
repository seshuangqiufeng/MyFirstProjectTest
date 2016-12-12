package com.guoqiang.dbflowtest;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by wangguoqiang on 2016/11/11.
 */
public class DbFlowApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
