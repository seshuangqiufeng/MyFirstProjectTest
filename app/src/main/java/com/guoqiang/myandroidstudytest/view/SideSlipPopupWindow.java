package com.guoqiang.myandroidstudytest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.PopupWindow;

/**
 * Created by wangguoqiang on 2016/8/10.
 */
public class SideSlipPopupWindow extends PopupWindow {

    private Context mContext;

    public SideSlipPopupWindow(Context context) {
        super(context);
        init(context);
    }

    public SideSlipPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SideSlipPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }
}
