package com.guoqiang.greendao;

import android.app.Application;

import com.guoqiang.greendao.entity.DaoMaster;
import com.guoqiang.greendao.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by wangguoqiang on 2016/11/25.
 */
public class App extends Application {

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"greendao-db",null);
//        Database db = helper.getWritableDb();
//        DaoMaster daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
