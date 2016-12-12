package com.guoqiang.fangqqcehuashanchu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.guoqiang.fangqqcehuashanchu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangguoqiang on 2016/6/16.
 */
public class CeHuaShanchuActivity extends Activity {

    private static final String TAG = CeHuaShanchuActivity.class.getName();

    private SwipeRefreshLayout mContainerLayout;
    private ListView mListView;

    private List<Map<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cehuashanchu);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        data  = new ArrayList< Map<String,Object> >();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("nametext","第一个功能");
        map1.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("nametext","第二个功能");
        map2.put("iconid",R.mipmap.ic_launcher);


        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("nametext","第三个功能");
        map3.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("nametext","第四个功能");
        map4.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("nametext","第五个功能");
        map5.put("iconid",R.mipmap.ic_launcher);

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mContainerLayout = (SwipeRefreshLayout) findViewById(R.id.srl_listview);
        mListView = (ListView) findViewById(R.id.lv_listview);
        mContainerLayout.setOnRefreshListener(new OnRefrashListener());
        mListView.setAdapter(new SimpleAdapter(this,data,R.layout.item_fangqqcehuashanchu,new String[]{"nametext","iconid"},new int[]{R.id.tv,R.id.iv}));
    }

    class OnRefrashListener implements SwipeRefreshLayout.OnRefreshListener{

        @Override
        public void onRefresh() {
            getMoredata();
        }
    }

    private void getMoredata(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("nametext","第一个功能");
        map1.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("nametext","第二个功能");
        map2.put("iconid",R.mipmap.ic_launcher);


        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("nametext","第三个功能");
        map3.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("nametext","第四个功能");
        map4.put("iconid",R.mipmap.ic_launcher);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("nametext","第五个功能");
        map5.put("iconid",R.mipmap.ic_launcher);

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
        Log.d(TAG, data.toString() + "   " + data.size());
        mContainerLayout.setRefreshing(false);
        mListView.setAdapter(new SimpleAdapter(this, data, R.layout.item_fangqqcehuashanchu, new String[]{"nametext", "iconid"}, new int[]{R.id.tv, R.id.iv}));
    }

}
