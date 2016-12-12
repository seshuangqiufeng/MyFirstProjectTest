package com.guoqiang.ormlite.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.ormlite.R;
import com.guoqiang.ormlite.R2;
import com.guoqiang.ormlite.bean.User;
import com.guoqiang.ormlite.dao.UserDao;
import com.guoqiang.ormlite.helper.DataBaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangguoqiang on 2016/11/1.
 */
public class MyOrmliteActivity extends Activity {

    private static final String TAG = MyOrmliteActivity.class.getSimpleName();

    @BindView(R2.id.tv_active_insert)
    TextView tvActiveInsert;
    @BindView(R2.id.tv_active_delete)
    TextView tvActiveDelete;
    @BindView(R2.id.tv_active_check)
    TextView tvActiveCheck;
    @BindView(R2.id.tv_active_change)
    TextView tvActiveChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite);
        ButterKnife.bind(this);
//        FingerprintManager
    }

    @OnClick({R2.id.tv_active_insert, R2.id.tv_active_delete, R2.id.tv_active_check, R2.id.tv_active_change})
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.tv_active_insert){//增
            Toast.makeText(this,"增",Toast.LENGTH_SHORT).show();
            testAdd();
            testSQLList();
        }else if(id == R.id.tv_active_delete){//删
            Toast.makeText(this,"删",Toast.LENGTH_SHORT).show();
            testDelete();
            testSQLList();
        }else if(id == R.id.tv_active_check){//查
            Toast.makeText(this,"查",Toast.LENGTH_SHORT).show();
            testCheck();
        }else if(id == R.id.tv_active_change){//改
            Toast.makeText(this,"改",Toast.LENGTH_SHORT).show();
            testChange();
        }
    }

    /**
     *
     */
    private void testChange() {
        UserDao userDao = new UserDao(getApplicationContext());
        userDao.updataOne("国强",45);
        testSQLList();
    }

    /**
     * 查询
     */
    private void testCheck() {
        UserDao userDao = new UserDao(getApplicationContext());
        User user = userDao.findOne("id",5,"guoqiang");
        Log.d(TAG,"user ==  "+ user.toString());
    }

    /**
     * 增加
     */
    List<User> lists = new ArrayList<>();
    private void testAdd(){
        User user = new User("guoqiang",29);
        DataBaseHelper helper = DataBaseHelper.getInstance(this);
        Log.d(TAG,"insert起始时间："+System.currentTimeMillis());
        try {
            for(int i = 0;i< 100000;i++){
                user = new User("guoqian" + i,29);
                lists.add(user);
            }
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian1g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian2g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian3g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian4g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian5g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian6g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian7g",29);
//            helper.getDao(User.class).create(user);
//            user = new User("guoqian8g",29);
//            helper.getDao(User.class).create(user);
            helper.getDao(User.class).create(lists);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"insert结束时间："+System.currentTimeMillis());
    }

    /**
     * 删除
     */
    private void testDelete() {
        try {
            UserDao userDao = new UserDao(getApplicationContext());
            for(User user : userDao.findAll()){
                userDao.delete(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历所有的数据库表中的对象
     */
    private void testSQLList() {
        Log.d(TAG,"query起始时间：" + System.currentTimeMillis());
        DataBaseHelper helper = DataBaseHelper.getInstance(getApplicationContext());
        try {
            List<User> lists = helper.getDao(User.class).queryForAll();
            Log.d(TAG,"query结束时间：" + System.currentTimeMillis()+"\n"+"size == " + lists.size()+"\n" + lists.toString());
//            for(User user : lists){
//                Log.d(TAG,"user ==  "+ user.toString());
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
