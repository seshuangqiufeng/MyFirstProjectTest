package com.guoqiang.myselfview.activity;

import android.app.Activity;
import android.os.Bundle;

import com.guoqiang.myselfview.CustomTitleView;
import com.guoqiang.myselfview.R;

/**
 * Created by wangguoqiang on 2016/8/23.
 */
public class MySelfViewActivity extends Activity {

    private CustomTitleView customTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myselfview);
        customTitleView = (CustomTitleView) findViewById(R.id.ctv_myself);
    }
}
