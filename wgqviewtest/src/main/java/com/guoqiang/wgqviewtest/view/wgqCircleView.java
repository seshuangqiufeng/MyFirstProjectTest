package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.guoqiang.wgqviewtest.R;
import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/9.
 */
public class wgqCircleView extends View {

    private Context mContext;

    private int mOutColor;
    private float mOutRadius;
    private int mMiddleColor;
    private float mMiddleRadius;
    private int mInsideColor;
    private float mInsideRadius;
    private int mArcColor;
    private float mArcRadius;

    private Paint mInsidePaint;
    private Paint mMiddlePaint;
    private Paint mOutPaint;
    private Paint mArcPaint;

    private RectF mArcRectF;

    public wgqCircleView(Context context) {
        super(context);
        initView(context,null,0);
    }

    public wgqCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs,0);
    }

    public wgqCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.wgqCircleView);

        mOutColor = a.getColor(R.styleable.wgqCircleView_outcolor, Color.BLACK);
        mOutRadius = a.getDimension(R.styleable.wgqCircleView_outradius,25);

        mMiddleColor = a.getColor(R.styleable.wgqCircleView_middlecolor,Color.BLUE);
        mMiddleRadius = a.getDimension(R.styleable.wgqCircleView_middleradius,20);

        mInsideColor = a.getColor(R.styleable.wgqCircleView_insidecolor,Color.CYAN);
        mInsideRadius = a.getDimension(R.styleable.wgqCircleView_insideradius,15);

        mArcColor = a.getColor(R.styleable.wgqCircleView_arccolor,Color.RED);
        mArcRadius = a.getDimension(R.styleable.wgqCircleView_arcradius,30);

        a.recycle();

        initCompontents();
    }

    /**
     *
     */
    private void initCompontents() {
        mInsidePaint = new Paint();
        mInsidePaint.setColor(mInsideColor);
        mMiddlePaint = new Paint();
        mMiddlePaint.setColor(mMiddleColor);
        mOutPaint = new Paint();
        mOutPaint.setColor(mOutColor);
        mArcPaint = new Paint();
        mArcPaint.setColor(mArcColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth()/2;
        int cy = getHeight()/2;
        mArcRectF = new RectF(cx/2,cy/2,cx,cy);
        canvas.drawArc(mArcRectF,30,360,true,mArcPaint);
        canvas.drawCircle(cx,cy,mOutRadius,mOutPaint);
        canvas.drawCircle(cx,cy,mMiddleRadius,mMiddlePaint);
        canvas.drawCircle(cx,cy,mInsideRadius,mInsidePaint);

        LogUtil.d("WGQ","中心横坐标 == " + cx + "\n"
                       + "中心纵坐标 == " + cy + "\n"
                       + "外层半径 == " + mOutRadius + "\n"
                        + "中层半径 == " + mMiddleRadius + "\n"
                        + "内层半径 == " + mInsideRadius );
    }
}
