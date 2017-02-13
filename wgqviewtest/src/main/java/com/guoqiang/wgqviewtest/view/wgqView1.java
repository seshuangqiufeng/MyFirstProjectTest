package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/7.
 */
public class wgqView1 extends View {

    private Context mContext ;

    private int onMeasureNum = 0;
    private int onLayoutNum = 0;
    private int onDrawNum = 0;

    public wgqView1(Context context) {
        super(context);
        LogUtil.d("WGQ","构造函数   一个参数  context " );
    }

    public wgqView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogUtil.d("WGQ","构造函数   两个参数  context    attrs" );
    }

    public wgqView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogUtil.d("WGQ","构造函数   三个参数  context   attrs   defStyleAttr" );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));

        LogUtil.d("WGQ","宽度  ==   " + measureWidth(widthMeasureSpec));
        LogUtil.d("WGQ","高度  ==   " + measureHeight(heightMeasureSpec));

        LogUtil.d("WGQ","onMeasure   执行次数  " + (++onMeasureNum));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d("WGQ","onAttachedToWindow     ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.d("WGQ","onLayout   执行次数  " + (++onLayoutNum));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        LogUtil.d("WGQ","dispatchDraw     " );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogUtil.d("WGQ","onDraw   执行次数  " + (++onDrawNum));
    }

    /**
     * 自己测量View的宽度
     * @param measureSpec
     * @return
     */
    private int measureWidth(int measureSpec) {
        int result = 200;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = 200;
                LogUtil.d("WGQ","measureWidth   UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,specSize);
                LogUtil.d("WGQ","measureWidth   AT_MOST ");
                break;
            case MeasureSpec.EXACTLY:
                LogUtil.d("WGQ","measureWidth   EXACTLY");
                result = specSize;
                break;
        }
        return result;
    }

    /**
     * 自己测量View的高度
     * @param measureSpec
     * @return
     */
    private int measureHeight(int measureSpec) {
        int result = 200;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                LogUtil.d("WGQ","measureHeight   UNSPECIFIED");
                result = 200;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,specSize);
                LogUtil.d("WGQ","measureHeight   AT_MOST ");
                break;
            case MeasureSpec.EXACTLY:
                LogUtil.d("WGQ","measureHeight    EXACTLY");
                result = specSize;
                break;
        }
        return result;
    }
}
