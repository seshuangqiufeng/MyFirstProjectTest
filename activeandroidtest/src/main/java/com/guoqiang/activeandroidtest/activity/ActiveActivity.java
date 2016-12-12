package com.guoqiang.activeandroidtest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.guoqiang.activeandroidtest.R;
import com.guoqiang.activeandroidtest.R2;
import com.guoqiang.activeandroidtest.entity.People;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wangguoqiang on 2016/10/31.
 */
public class ActiveActivity extends Activity {


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
        setContentView(R.layout.activity_active);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.tv_active_insert, R2.id.tv_active_delete, R2.id.tv_active_check, R2.id.tv_active_change})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_active_insert) {
            ActiveAndroid.beginTransaction();
            try {
                for (int i = 0; i < 10000; i++) {
                    People people = new People();
                    people.name = "xiaomi" + i;
                    people.age = 29;
                    people.save();
                }
                ActiveAndroid.setTransactionSuccessful();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ActiveAndroid.endTransaction();
            }
        } else if (id == R.id.tv_active_delete) {
            Log.d("Acitve", "tv_active_insert");
            Toast.makeText(this, "tv_active_delete", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_active_check) {
            Log.d("Acitve", "tv_active_insert");
            Toast.makeText(this, "tv_active_check", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_active_change) {
            Log.d("Acitve", "tv_active_insert");
            Toast.makeText(this, "tv_active_change", Toast.LENGTH_SHORT).show();
        }
    }


}
