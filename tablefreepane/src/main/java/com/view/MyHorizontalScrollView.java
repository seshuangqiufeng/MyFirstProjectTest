package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by mi on 2016/7/8.
 */
public class MyHorizontalScrollView extends HorizontalScrollView {
    private Listener listener;
    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        String tag = (String) this.getTag();

        if (tag.equalsIgnoreCase("horizontal scroll view b")) {
            listener.onTopScroll(l);
        } else {
            listener.onBottomScroll(l);
        }
    }

    interface Listener {
        void onTopScroll(int left);
        void onBottomScroll(int left);
    }

    void setOnListener(Listener listenter){
        this.listener = listenter;
    }
}
