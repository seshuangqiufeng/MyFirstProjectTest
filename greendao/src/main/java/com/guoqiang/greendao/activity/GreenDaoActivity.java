package com.guoqiang.greendao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.greendao.App;
import com.guoqiang.greendao.R;
import com.guoqiang.greendao.R2;
import com.guoqiang.greendao.entity.DaoSession;
import com.guoqiang.greendao.entity.Note;
import com.guoqiang.greendao.entity.NoteDao;
import com.guoqiang.greendao.entity.User;
import com.guoqiang.greendao.entity.UserDao;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangguoqiang on 2016/11/24.
 */
public class GreenDaoActivity extends Activity {

    private static final String TAG = GreenDaoActivity.class.getSimpleName();
    @BindView(R2.id.tv_active_insert)
    TextView tvActiveInsert;
    @BindView(R2.id.tv_active_delete)
    TextView tvActiveDelete;
    @BindView(R2.id.tv_active_check)
    TextView tvActiveCheck;
    @BindView(R2.id.tv_active_change)
    TextView tvActiveChange;

    DaoSession daoSession;
    NoteDao noteDao;
    Query<Note> notesQuery;

    UserDao userDao;
    Query<User> userQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        ButterKnife.bind(this);
        daoSession = ((App)getApplication()).getDaoSession();
        userDao = daoSession.getUserDao();
        userQuery = userDao.queryBuilder().build();
    }

    @OnClick({R2.id.tv_active_insert, R2.id.tv_active_delete, R2.id.tv_active_check, R2.id.tv_active_change})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_active_insert) {//增
            Toast.makeText(this, "增", Toast.LENGTH_SHORT).show();
            testAdd();
            testSQLList();
        } else if (id == R.id.tv_active_delete) {//删
            Toast.makeText(this, "删", Toast.LENGTH_SHORT).show();
            testDelete();
            testSQLList();
        } else if (id == R.id.tv_active_check) {//查
            Toast.makeText(this, "查", Toast.LENGTH_SHORT).show();
            testSQLList();
        } else if (id == R.id.tv_active_change) {//改
            Toast.makeText(this, "改", Toast.LENGTH_SHORT).show();
            testChange();
            testSQLList();
        }
    }

    /**
     *
     */
    List<User> lists = new ArrayList<>();
    private void testAdd() {
        Log.d(TAG,"insert起始时间   "+ System.currentTimeMillis());
        for(int i = 0;i < 100000;i++) {
//            Note note = new Note(null, "heheda", "live" + i, new Date(System.currentTimeMillis()));
//            noteDao.insert(note);
            User user = new User("国强" + i,28,"男");
            lists.add(user);
//            userDao.insert(user);
        }
        userDao.insertInTx(lists);
        Log.d(TAG,"insert结束时间   "+ System.currentTimeMillis());

    }

    /**
     *
     */
    private void testDelete() {
        userDao.deleteAll();
    }

    /**
     *
     */
    private void testChange() {
    }

    /**
     *
     */
    private void testSQLList() {
        Log.d(TAG,"query起始时间   "+ System.currentTimeMillis());
        List<User> notes = userQuery.list();
        Log.d(TAG,"query结束时间   "+ System.currentTimeMillis() + "\n" + notes.toString());

    }
}
