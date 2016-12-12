package com.guoqiang.dbflowtest.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by wangguoqiang on 2016/11/11.
 */
@Database(name = AppDatabase.NAME,version = AppDatabase.VERSION)
public class AppDatabase {
    //数据库名称
    public static final String NAME = "AppDatabase";
    //数据库版本号
    public static final int VERSION = 1;
}
