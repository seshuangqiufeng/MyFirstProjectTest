package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/10.
 */
public class wgqTouchView extends View {


    public wgqTouchView(Context context) {
        super(context);
    }

    public wgqTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public wgqTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("WGQ","wgqTouchView " +" onTouchEvent ");

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                float x = event.getX();
                float y = event.getY();

                float rawX = event.getRawX();
                float rawY = event.getRawY();

                LogUtil.d("WGQ","wgqTouchView     "+"x == " + x + "\n"
                                +"y == " + y + "\n"
                                +"rawX == " + rawX + "\n"
                                +"rawY == " + rawY);

                break;
            case MotionEvent.ACTION_MOVE:

                LogUtil.d("WGQ","wgqTouchView   " + "MotionEvent.ACTION_MOVE");

                break;
            case MotionEvent.ACTION_UP:

                LogUtil.d("WGQ","wgqTouchView   " + "MotionEvent.ACTION_UP");

                break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.d("WGQ","wgqTouchView " +" dispatchTouchEvent ");
        return super.dispatchTouchEvent(event);
    }


}
