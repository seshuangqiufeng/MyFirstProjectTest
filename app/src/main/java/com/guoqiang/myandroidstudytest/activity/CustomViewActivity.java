package com.guoqiang.myandroidstudytest.activity;

import android.app.Activity;
import android.os.Bundle;

import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.view.CustomView;

/**
 * Created by wangguoqiang on 2016/7/18.
 */
public class CustomViewActivity extends Activity {

    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
    }
}
