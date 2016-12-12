package com.guoqiang.dbflowtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.dbflowtest.R;
import com.guoqiang.dbflowtest.R2;
import com.guoqiang.dbflowtest.entitiy.People;
import com.guoqiang.dbflowtest.entitiy.People_Table;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wangguoqiang on 2016/11/10.
 */
public class DbFlowTestActivity extends Activity {


    private static final String TAG = DbFlowTestActivity.class.getSimpleName();
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
        setContentView(R.layout.activity_dbflow);
        ButterKnife.bind(this);
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
     * 增
     */
    private void testAdd() {
        for (int i = 0; i < 20; i++) {
            People people = new People();
            people.name = "国强" + i;
            people.gender = 12 + i;
            people.save();
        }
    }

    /**
     * 删
     */
    private void testDelete() {
        //查询gender = 1的所有People
        List<People> peoples2 = new Select().from(People.class).where(People_Table.gender.eq(16)).queryList();
        for(People people : peoples2){
            people.delete();
        }
    }

    /**
     * 改
     */
    private void testChange() {
        //查询gender = 1的所有People
        List<People> peoples2 = new Select().from(People.class).where(People_Table.gender.eq(16)).queryList();
        for(People people : peoples2){
            people.name = "国强加油";
            people.update();
        }
    }

    /**
     * 遍历
     */
    private void testSQLList() {
        //返回所有查询结果
        List<People> peoples = new Select().from(People.class).queryList();
        //返回单个查询结果
        People people = new Select().from(People.class).querySingle();
        //查询gender = 1的所有People
        List<People> peoples2 = new Select().from(People.class).where(People_Table.gender.eq(1)).queryList();

        for(int i = 0; i< peoples.size();i++){
            Log.d(TAG," -----    >>>>>   "+peoples.get(i).name + "  ++++++    "+peoples.get(i).gender);
        }
    }

}
