package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/10.
 */
public class wgqTouchGroup1 extends RelativeLayout {

    public wgqTouchGroup1(Context context) {
        super(context);
    }

    public wgqTouchGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public wgqTouchGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("WGQ","wgqTouchGroup1 " +" onInterceptTouchEvent ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.d("WGQ","wgqTouchGroup1 " +" dispatchTouchEvent ");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("WGQ","wgqTouchGroup1 " +" onTouchEvent ");



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                float x = event.getX();
                float y = event.getY();

                float rawX = event.getRawX();
                float rawY = event.getRawY();

                LogUtil.d("WGQ","wgqTouchGroup1    "+"x == " + x + "\n"
                        +"y == " + y + "\n"
                        +"rawX == " + rawX + "\n"
                        +"rawY == " + rawY);

                break;
            case MotionEvent.ACTION_MOVE:

                LogUtil.d("WGQ","wgqTouchGroup1   " + "MotionEvent.ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:

                LogUtil.d("WGQ","wgqTouchGroup1   " + "MotionEvent.ACTION_UP");

                break;

        }



        return super.onTouchEvent(event);
    }

}
