package com.guoqiang.realmtest;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by wangguoqiang on 2016/11/9.
 */
public class RealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("xiaomii.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
