package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/13.
 */
public class wgqScrollViewGroup extends ViewGroup {

    private Context mContext;
    private int mScreenHeight ;

    private int mLastY;
    private int mStart;

    private Scroller mScroller;
    private int mEnd;

    public wgqScrollViewGroup(Context context) {
        super(context);
        init(context, null, 0);

    }

    public wgqScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public wgqScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenHeight = wm.getDefaultDisplay().getHeight();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        for(int i = 0;i< count;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams params = (MarginLayoutParams) getLayoutParams();
        params.height = mScreenHeight * childCount;
        setLayoutParams(params);

        for(int i = 0;i < childCount;i++){
            View child = getChildAt(i);
            if(child.getVisibility() != View.GONE){
                child.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                mLastY = y;
                mStart = getScrollY();

                LogUtil.d("WGQ", "ACTION_DOWN   " + "   mLastY == " + mLastY + "\n"
                                                + " mStart == " + mStart  + "\n"
                        + "mScreenHeight ==  " + mScreenHeight);

                break;
            case MotionEvent.ACTION_MOVE:

                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }

                int dy = mLastY - y;
                if(getScrollY() < 0){
                    dy = 0;
                }

                if(getScrollY() > getHeight() - mScreenHeight){
                    dy = 0;
                }

                scrollBy(0,dy);
                mLastY = y;
                LogUtil.d("WGQ", "ACTION_MOVE   " + "   mLastY == " + mLastY + "\n"
                        + " mStart == " + mStart + "\n"
                        + "mScreenHeight ==  " + mScreenHeight );
                break;
            case MotionEvent.ACTION_UP:

                mEnd = getScrollY();
                int dScrollY = checkAlignment();

                if(dScrollY > 0){
                    if(dScrollY < mScreenHeight /3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{
                    if(-dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight - dScrollY);
                    }
                }
                LogUtil.d("WGQ", "ACTION_UP   " + "   mLastY == " + mLastY + "\n"
                        + " mStart == " + mStart  + "\n"
                            + "mScreenHeight ==  " + mScreenHeight);
                break;
        }

        postInvalidate();

        return true;
    }

    private int checkAlignment() {
        int mEnd = getScrollY();
        boolean isUp = ((mEnd - mStart) > 0) ? true : false;
        int lastPrev = mEnd % mScreenHeight;
        int lastNext = mScreenHeight - lastPrev;
        if (isUp) {
            //向上的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
