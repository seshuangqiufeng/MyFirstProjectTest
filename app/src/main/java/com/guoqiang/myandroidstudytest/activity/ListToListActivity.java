package com.guoqiang.myandroidstudytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.adapter.ProAdapter;
import com.guoqiang.myandroidstudytest.entity.NameEntity;
import com.guoqiang.myandroidstudytest.entity.ProEntity;
import com.guoqiang.myandroidstudytest.packageinterface.ItemClickListener;
import com.guoqiang.myandroidstudytest.view.MyListView;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/8/8.
 */
public class ListToListActivity extends Activity implements ItemClickListener{

    private MyListView mListView;
    private MyListView mListView1;
    private MyListView mListView2;


    private ArrayList<ProEntity> proList = new ArrayList<ProEntity>();

    private ProAdapter mProAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtolist);
        initView();
        initData();
    }

    /**
     *
     */
    private void initData() {
        ProEntity p = new ProEntity();
        p.setName("主板");
        ArrayList<NameEntity> namelist1 = new ArrayList<NameEntity>();
        NameEntity name = new NameEntity();
        name.setName1("处理器");
        name.setNum(2);
        NameEntity name1 = new NameEntity();
        name1.setName1("CPU");
        name1.setNum(3);
        NameEntity name2 = new NameEntity();
        name2.setName1("内存");
        name2.setNum(2);
        NameEntity name3 = new NameEntity();
        name3.setName1("硬盘");
        name3.setNum(2);
        NameEntity name4 = new NameEntity();
        name4.setName1("靶材");
        name4.setNum(2);
        namelist1.add(name);
        namelist1.add(name1);
        namelist1.add(name2);
        namelist1.add(name3);
        namelist1.add(name4);
        p.setList(namelist1);


        ProEntity p2 = new ProEntity();
        p2.setName("主板");
        ArrayList<NameEntity> namelist2 = new ArrayList<NameEntity>();
        NameEntity name11 = new NameEntity();
        name11.setName1("处理器");
        name11.setNum(2);
        NameEntity name12 = new NameEntity();
        name12.setName1("CPU");
        name12.setNum(3);
        NameEntity name22 = new NameEntity();
        name22.setName1("内存");
        name22.setNum(2);
        NameEntity name32 = new NameEntity();
        name32.setName1("硬盘");
        name32.setNum(2);
        NameEntity name42 = new NameEntity();
        name42.setName1("靶材");
        name42.setNum(2);
        namelist2.add(name11);
        namelist2.add(name12);
        namelist2.add(name22);
        namelist2.add(name32);
        namelist2.add(name42);
        p2.setList(namelist2);


        ProEntity p3 = new ProEntity();
        p3.setName("主板");
        ArrayList<NameEntity> namelist3 = new ArrayList<NameEntity>();
        NameEntity name13 = new NameEntity();
        name13.setName1("处理器");
        name13.setNum(2);
        NameEntity name133 = new NameEntity();
        name133.setName1("CPU");
        name133.setNum(3);
        NameEntity name23 = new NameEntity();
        name23.setName1("内存");
        name23.setNum(2);
        NameEntity name33 = new NameEntity();
        name33.setName1("硬盘");
        name33.setNum(2);
//        NameEntity name43 = new NameEntity();
//        name43.setName1("靶材");
//        name43.setNum(2);
        namelist3.add(name13);
        namelist3.add(name133);
        namelist3.add(name23);
        namelist3.add(name33);
//        namelist1.add(name43);
        p3.setList(namelist3);


        ProEntity p4 = new ProEntity();
        p4.setName("主板");
        ArrayList<NameEntity> namelist14 = new ArrayList<NameEntity>();
        NameEntity name234 = new NameEntity();
        name234.setName1("处理器");
        name234.setNum(2);
        NameEntity name14 = new NameEntity();
        name14.setName1("CPU");
        name14.setNum(3);
        NameEntity name24 = new NameEntity();
        name24.setName1("内存");
        name24.setNum(2);
        NameEntity name34 = new NameEntity();
        name34.setName1("硬盘");
        name34.setNum(2);
        NameEntity name44 = new NameEntity();
        name44.setName1("靶材");
        name44.setNum(2);
        namelist14.add(name234);
        namelist14.add(name14);
        namelist14.add(name24);
        namelist14.add(name34);
        namelist14.add(name44);
        p4.setList(namelist14);


        proList.add(p);
        proList.add(p2);
        proList.add(p3);
        proList.add(p4);

        mProAdapter = new ProAdapter(this,proList);
        mProAdapter.setItemClickListener(this);
        mListView.setAdapter(mProAdapter);
        mListView1.setAdapter(mProAdapter);
        mListView2.setAdapter(mProAdapter);
        mProAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mListView = (MyListView) findViewById(R.id.lv_first);
        mListView1 = (MyListView) findViewById(R.id.lv_first1);
        mListView2 = (MyListView) findViewById(R.id.lv_first2);
    }


    @Override
    public void onClick(int index) {
        mProAdapter.setIndex(index);
        mListView.setAdapter(mProAdapter);
        setListViewWidthBasedOnChildren(mListView);
        Log.d("this","这里走了  index == "+index);
        mProAdapter.notifyDataSetChanged();
//        setListViewHeightBasedOnChildren(mListView);
        Log.d("this","这里走了  ListView的高度 == "+ mListView.getMeasuredHeight());
    }

//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            // listItem.measure(0, 0);
//            listItem.measure(
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight
//                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }


    /**
     * 设置listView自适应高度
     */
    public void setListViewWidthBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalWidth = 0;
        int[] width = new int[listAdapter.getCount()];
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            Log.d("this","item 宽度为 "+listItem.getMeasuredWidth());
            width[i] = listItem.getMeasuredWidth();
        }

        int maxWidth = width[0];
        for(int i = 0;i<width.length;i++){
            if(width[i] > maxWidth){
                maxWidth = width[i];
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.width = maxWidth;
        Log.d("this","最长 宽度为 "+params.width);
        listView.setLayoutParams(params);
    }

}
