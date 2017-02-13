package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/7.
 */
public class wgqTextView extends TextView {

    private Context mContext;

    private Paint mPaint;
    private Paint mPaint1;
    private Paint mPaint2;

    private int mViewWidth = 0;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate = 0;

    public wgqTextView(Context context) {
        super(context);
    }

    public wgqTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public wgqTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        mPaint1 = new Paint();
//        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_bright));
//        mPaint1.setStyle(Paint.Style.FILL);
//        mPaint2 = new Paint();
//        mPaint2.setColor(Color.YELLOW);
//        mPaint2.setStyle(Paint.Style.FILL);
//        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
//        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
//        canvas.save();
//        canvas.translate(10,0);

        super.onDraw(canvas);
//        canvas.restore();

        if(mGradientMatrix != null){
            mTranslate += mViewWidth/5;
            if(mTranslate > 2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            LogUtil.d("WGQ","自定义TextView的宽度   " +  mViewWidth);
            if(mViewWidth > 0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int[]{Color.RED,0xffffffff,Color.BLUE,Color.YELLOW,Color.DKGRAY},null, Shader.TileMode.MIRROR);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();

            }
        }
    }
}
