package com.guoqiang.pullandswipestudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.guoqiang.pullandswipestudy.R;

/**
 * Created by wangguoqiang on 2016/12/2.
 */
public class MySwipeRefreshListView extends ListView implements AbsListView.OnScrollListener {

    private static final int TOUCH_STATE_NONE = 0;
    private static final int TOUCH_STATE_X = 1;
    private static final int TOUCH_STATE_Y = 2;
    private static final int BOTH = 2;
    private static final int HEADER = 0;
    private static final int FOOTER = 1;
    public static String TAG = "";
    public static final String REFRESH = "refresh";
    public static final String LOAD = "load";
    private int MAX_X = 3;
    private int MAX_Y = 5;
    private float mDownX;
    private float mDownY;
    private int mTouchState;
    private int mTouchPositon;
    private SwipeMenuLayout mTouchView;
    private OnSwipeListener mOnSwipeListener;

    private SwipeMenuCreator mMenuCreator;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private Interpolator mCloseInterpolator;
    private Interpolator mOpenInterpolator;

    private float mLastY = -1; // save event y

    private Scroller mScroller; // used for scroll back
    private OnScrollListener mScrollListener; // user's scroll listener


    private OnRefreshListener mOnRefreshListener;

    private RefreshListHeader mHeaderView;
    // header view content, use it to calculate the Header's height. And hide it
    // when disable pull refresh.
    private RelativeLayout mHeaderViewContent;
    private TextView mHeaderTimeView;
    private int mHeaderViewHeight; // header view's height
    private boolean mEnablePullRefresh = true;
    private boolean mPullRefreshing = false; // is refreashing.

    // -- footer view
    private RefreshListFooter mFooterView;
    private boolean mEnablePullLoad;
    private boolean mPullLoading;
    private boolean mIsFooterReady = false;
    private int mTotalItemCount;
    private int mScrollBack;
    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLLBACK_FOOTER = 1;

    private final static int SCROLL_DURATION = 400;
    private final static int PULL_LOAD_MORE_DELTA = 50;
    private final static float OFFSET_RADIO = 1.8f;
    private boolean isFooterVisible=false;

    public MySwipeRefreshListView(Context context) {
        super(context);
        init(context);
    }

    public MySwipeRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MySwipeRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     *
     * @param context
     */
    private void init(Context context) {
        mScroller = new Scroller(context,new DecelerateInterpolator());
        super.setOnScrollListener(this);
        mHeaderView = new RefreshListHeader(context);
        mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.xlistview_header_content);
        mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.xlistview_header_time);
        addHeaderView(mHeaderView);

        // init footer view
        mFooterView = new RefreshListFooter(context);

        // init header height
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeaderViewHeight = mHeaderViewContent.getHeight();
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        MAX_X = dp2px(MAX_X);
        MAX_Y = dp2px(MAX_Y);
        mTouchState = TOUCH_STATE_NONE;
    }


    @Override
    public void setAdapter(ListAdapter adapter) {
        if(mIsFooterReady == false){
            mIsFooterReady = true;
            addFooterView(mFooterView);
        }
        super.setAdapter(new SwipeMenuAdapter(getContext(),adapter){
            @Override
            public void createMenu(SwipeMenu menu) {
                if (mMenuCreator != null) {
                    mMenuCreator.create(menu);
                }
            }

            @Override
            public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
                if (mOnMenuItemClickListener != null) {
                    mOnMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
                }
                if (mTouchView != null) {
                    mTouchView.smoothCloseMenu();
                }
            }
        });
    }

    public Interpolator getmCloseInterpolator() {
        return mCloseInterpolator;
    }

    public void setmCloseInterpolator(Interpolator mCloseInterpolator) {
        this.mCloseInterpolator = mCloseInterpolator;
    }

    public Interpolator getmOpenInterpolator() {
        return mOpenInterpolator;
    }

    public void setmOpenInterpolator(Interpolator mOpenInterpolator) {
        this.mOpenInterpolator = mOpenInterpolator;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(mLastY == -1){
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                setRefreshTime(RefreshTime.getRefreshTime(getContext()));
                int oldPos = mTouchPositon;
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                mTouchState = TOUCH_STATE_NONE;

                mTouchPositon = pointToPosition((int)ev.getX(),(int)ev.getY());
                if(mTouchPositon == oldPos && mTouchView != null && mTouchView.isOpen()){
                    mTouchState = TOUCH_STATE_X;
                    mTouchView.onSwipe(ev);
                    return true;
                }

                View view = getChildAt(mTouchPositon - getFirstVisiblePosition());

                if(mTouchView != null && mTouchView.isOpen()){
                    mTouchView.smoothCloseMenu();
                    mTouchView = null;
                    return super.onTouchEvent(ev);
                }

                if(view instanceof SwipeMenuLayout){
                    mTouchView = (SwipeMenuLayout) view;
                }

                if(mTouchView != null){
                    mTouchView.onSwipe(ev);
                }

                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY()-mLastY;
                float dy = Math.abs(ev.getY()-mDownY);
                float dx = Math.abs(ev.getX()-mDownX);
                mLastY = ev.getRawY();

                if((mTouchView != null || !mTouchView.isActive()) && Math.pow(dx,2)/Math.pow(dy,2) <= 3){
                    if(getFirstVisiblePosition() == 0 && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)){
                        // the first item is showing, header has shown or pull down.
                        updateHeaderHeight(deltaY / OFFSET_RADIO);
                        invokeOnScrolling();
                    }else if(mFooterView.getBottomMargin()>0 || deltaY < 0){
                        if(mEnablePullLoad){
                            updateFooterHeight(-deltaY/OFFSET_RADIO);
                        }
                    }
                }

                if(mTouchState == TOUCH_STATE_X){
                    if (mTouchView != null) {
                        mTouchView.onSwipe(ev);
                    }
                    getSelector().setState(new int[] { 0 });
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                    super.onTouchEvent(ev);
                    return true;
                } else if (mTouchState == TOUCH_STATE_NONE) {
                    if (Math.abs(dy) > MAX_Y) {
                        mTouchState = TOUCH_STATE_Y;
                    } else if (dx > MAX_X) {
                        mTouchState = TOUCH_STATE_X;
                        if (mOnSwipeListener != null) {
                            mOnSwipeListener.onSwipeStart(mTouchPositon);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastY = -1;

                break;

        }
        return super.onTouchEvent(ev);
    }


    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
                // more.
                mFooterView.setState(RefreshListFooter.STATE_READY);
            } else {
                mFooterView.setState(RefreshListFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);
        if(height>=200){
            resetFooterHeight();
        }
        // setSelection(mTotalItemCount - 1); // scroll to bottom
    }
    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }



    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
        if (mEnablePullRefresh && !mPullRefreshing) {
            if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
                mHeaderView.setState(RefreshListHeader.STATE_READY);
            } else {
                mHeaderView.setState(RefreshListHeader.STATE_NORMAL);
            }
        }
        setSelection(0); // scroll to top each time
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }


    /**
     * you can listen ListView.OnScrollListener or this one. it will invoke
     * onXScrolling when header/footer scroll back.
     */
    public interface OnXScrollListener extends OnScrollListener {
        public void onXScrolling(View view);
    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    /**
     * set last refresh time
     *
     * @param time
     */
    public void setRefreshTime(String time) {
        mHeaderTimeView.setText(time);
    }


    public static interface OnSwipeListener {
        void onSwipeStart(int position);

        void onSwipeEnd(int position);
    }

    /**
     * implements this interface to get refresh/load more event.
     */
    public interface OnRefreshListener {
        public void onRefresh();
        public void onLoadMore();
    }

    public static interface OnMenuItemClickListener {
        void onMenuItemClick(int position, SwipeMenu menu, int index);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }

}
