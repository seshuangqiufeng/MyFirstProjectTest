package com.guoqiang.myandroidstudytest.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tablefreezepane.TableMainActivity;
import com.guoqiang.activeandroidtest.activity.ActiveActivity;
import com.guoqiang.broadcasttest.BroadCastTestActivity;
import com.guoqiang.dbflowtest.activity.DbFlowTestActivity;
import com.guoqiang.fangqqcehuashanchu.activity.CeHuaShanchuActivity;
import com.guoqiang.graphviewtest.GraphViewActivity;
import com.guoqiang.greendao.activity.GreenDaoActivity;
import com.guoqiang.listviewandcheckbox.activity.ListViewandCheckBoxActivity;
import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.view.WGQFlowLayout;
import com.guoqiang.myselfview.activity.MySelfViewActivity;
import com.guoqiang.ormlite.activity.MyOrmliteActivity;
import com.guoqiang.pullandswipelisttest.activity.PullAndSwipeTestActivity;
import com.guoqiang.realmtest.activity.RealmTestActivity;
import com.guoqiang.threelevellistview.activity.ThreeLevelActivity;
import com.guoqiang.wgqviewtest.activity.WGQActivity;

import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener {

    private WGQFlowLayout mFlowLayout;


    private String mNames[] = {
            "QQCehuaShanchu","EmailTest","TableFreeTest",
            "CustomViewTest","ListToListTest","GraphViewTest",
            "侧滑滑出侧滑菜单","自定义View","广播通知测试",
            "测试ListViewAndCheckBox","自定义View","activeAndroidTest",
            "ormliteTest","threelevellistview","realmtest","dbflowtest","greendaotest","pullandswipe","wgqViewTest"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initChildViews();
    }


    private void initChildViews() {
        // TODO Auto-generated method stub
        mFlowLayout = (WGQFlowLayout) findViewById(R.id.wgqflowlayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setOnClickListener(this);
            view.setTag(mNames[i]);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            mFlowLayout.addView(view,lp);
        }
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if(tag.equalsIgnoreCase("QQCehuaShanchu")){
            startActivity(new Intent(this,CeHuaShanchuActivity.class));
        }else if(tag.equalsIgnoreCase("EmailTest")){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:wangguoqiang_4585@126.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "zheshibiaoti");
            intent.putExtra(Intent.EXTRA_TEXT,"哈哈哈哈");
            startActivity(intent);
        }else if(tag.equalsIgnoreCase("TableFreeTest")){
            startActivity(new Intent(this, TableMainActivity.class));
        }else if(tag.equalsIgnoreCase("CustomViewTest")){
            startActivity(new Intent(this,CustomViewActivity.class));
        }else if(tag.equalsIgnoreCase("ListToListTest")){
            startActivity(new Intent(this,ListToListActivity.class));
        }else if(tag.equalsIgnoreCase("GraphViewTest")){
            startActivity(new Intent(this,GraphViewActivity.class));
        }else if(tag.equalsIgnoreCase("侧滑滑出侧滑菜单")){
            startActivity(new Intent(this,SideSlipActivity.class));
        }else if(tag.equalsIgnoreCase("自定义View")){
            startActivity(new Intent(this,MySelfViewActivity.class));
        }else if(tag.equalsIgnoreCase("广播通知测试")){
            startActivity(new Intent(this,BroadCastTestActivity.class));
        }else if(tag.equalsIgnoreCase("测试ListViewAndCheckBox")){
            startActivity(new Intent(this,ListViewandCheckBoxActivity.class));
        }else if(tag.equalsIgnoreCase("自定义View")){
            startActivity(new Intent(this,MySelfViewActivity.class));
        }else if(tag.equalsIgnoreCase("activeAndroidTest")){
            startActivity(new Intent(this, ActiveActivity.class));
        }else if(tag.equalsIgnoreCase("ormliteTest")){
            startActivity(new Intent(this, MyOrmliteActivity.class));
        }else if(tag.equalsIgnoreCase("threelevellistview")){
            startActivity(new Intent(this, ThreeLevelActivity.class));
        }else if(tag.equalsIgnoreCase("realmtest")){
            startActivity(new Intent(this, RealmTestActivity.class));
        }else if(tag.equalsIgnoreCase("dbflowtest")){
            startActivity(new Intent(this, DbFlowTestActivity.class));
        }else if(tag.equalsIgnoreCase("greendaotest")){
            startActivity(new Intent(this, GreenDaoActivity.class));
        }else if(tag.equalsIgnoreCase("pullandswipe")){
            startActivity(new Intent(this, PullAndSwipeTestActivity.class));
        }else if(tag.equalsIgnoreCase("wgqViewTest")){
            startActivity(new Intent(this, WGQActivity.class));
        }
    }
}
