package com.guoqiang.ormlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.guoqiang.ormlite.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by wangguoqiang on 2016/11/1.
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {



    /**
     * 基本单例模式:
     * 1、先把构造函数私有化
     * 2、对外提供一个静态方法
     * 3、在方法中判断如果已经存在就不再创建，如果不存在再创建
     * 这样保证永远只有一个DataBaseHelper对象
     * 4、为了线程安全，需要在方法前提供一个线程安全关键字synchronized
     * 如果一个调用时，另一个就不允许调用
     */

    private static final String DB_NAME = "test1.db";
    private static final int DB_VERSON = 1;
    private static DataBaseHelper dataBaseHelper;

    private DataBaseHelper(Context mContext) {
        super(mContext, DB_NAME, null, DB_VERSON);
    }

    public synchronized static DataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            synchronized (DataBaseHelper.class) {
                if (dataBaseHelper == null)
                    dataBaseHelper = new DataBaseHelper(context);
            }
        }
        return dataBaseHelper;
    }

//    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
//        super(context, databaseName, factory, databaseVersion);
//    }
//
//    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, int configFileId) {
//        super(context, databaseName, factory, databaseVersion, configFileId);
//    }
//
//    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, File configFile) {
//        super(context, databaseName, factory, databaseVersion, configFile);
//    }
//
//    public DataBaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, InputStream stream) {
//        super(context, databaseName, factory, databaseVersion, stream);
//    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //创建表
        try {
            //User
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //删除表
        try {
            TableUtils.dropTable(connectionSource,User.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();

    }
}
