package com.guoqiang.realmtest.util;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by wangguoqiang on 2016/11/10.
 */
public class RealmUtil {

    private static RealmUtil sInstance;
    public Context mContext;
    private String realmName = "guoqiang.realm";

    public RealmUtil(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 双检索单例
     * @param context
     * @return
     */
    public static RealmUtil getIntance(Context context){

        if (sInstance == null) {
            synchronized (RealmUtil.class) {
                if (sInstance == null) {
                    sInstance = new RealmUtil(context);
                }
            }
        }
        return  sInstance;
    }

    /**
     * 获取realm对象
     * @return
     */
    public Realm getRealm(){
//        Realm realm =Realm.getInstance(new RealmConfiguration.Builder().name(realmName).build());
        Realm realm = Realm.getDefaultInstance();
        return  realm;
    }
}
