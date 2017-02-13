package com.guoqiang.wgqviewtest.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.guoqiang.wgqviewtest.R;
import com.guoqiang.wgqviewtest.util.LogUtil;
import com.guoqiang.wgqviewtest.view.wgqTitleBar;

/**
 * Created by wangguoqiang on 2017/2/7.
 */
public class WGQActivity extends Activity implements wgqTitleBar.TopbarClickListener {

    private static final String TAG = WGQActivity.class.getSimpleName();
    private Context mContext;


    private wgqTitleBar mwgqTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wgqview);
        mContext = this.getApplicationContext();
        mwgqTitleBar = (wgqTitleBar) this.findViewById(R.id.topbar);
        mwgqTitleBar.setTopbarClickListener(this);
//        LogUtil.d(TAG,"------------>   " + "onCreate");
    }


    @Override
    protected void onResume() {
        super.onResume();
//        LogUtil.d(TAG,"------------>   " + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG,"------------>   " + "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        LogUtil.d(TAG,"------------>   " + "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG,"------------>   " + "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG,"------------>   " + "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,"------------>   " + "onDestroy");
    }

    @Override
    public void leftClick() {
        Toast.makeText(this,"左侧点击了",Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void rightClick() {
        Toast.makeText(this,"右侧点击了",Toast.LENGTH_SHORT).show();
    }
}
