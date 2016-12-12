package com.guoqiang.myandroidstudytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.view.CustomView;

/**
 * Created by wangguoqiang on 2016/8/10.
 */
public class SideSlipActivity extends Activity {

    private CustomView customView;
    private LinearLayout mll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sideslip);
        customView = (CustomView) findViewById(R.id.cv_view);
        mll = (LinearLayout) findViewById(R.id.ll_diyige);
    }

    int i;
    float downX = 0;
    float downY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d("this","进来方法   i  === "+i++);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                Log.d("this","downX == "+ downX);
                Toast.makeText(this,"已经按下了！",Toast.LENGTH_LONG).show();

                break;
            case MotionEvent.ACTION_MOVE:
//                Toast.makeText(this,"开始滑动了！！",Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_UP:

                float moveX = event.getX();
                float moveY = event.getY();

                Log.d("this","downX == "+ downX+"  moveX === " + moveX);

                if((moveX - downX)>20){
                    Toast.makeText(this,"左侧滑动，弹出侧滑菜单！",Toast.LENGTH_LONG).show();
                }

                break;

        }

        return false;
    }

}
