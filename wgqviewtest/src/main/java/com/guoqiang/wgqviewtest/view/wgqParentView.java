package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by wangguoqiang on 2017/2/7.
 */
public class wgqParentView extends ViewGroup {

    private Context mContext;


    public wgqParentView(Context context) {
        super(context);
    }

    public wgqParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public wgqParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
