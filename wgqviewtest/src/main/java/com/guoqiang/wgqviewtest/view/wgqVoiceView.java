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
public class wgqVoiceView extends View {

    private Context mContext;

    private int mRectColor;
    private float mRectWidth;

    private Paint mRectPaint;

    private RectF mRectRectF;

    private int[] colors = new int[]{0xffff0000,0xff458689,0xffee4589,0xff9fa589,0xff0ff00f};

    public wgqVoiceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    public wgqVoiceView(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public wgqVoiceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    /**
     *
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr){
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.wgqVoiceView);

        mRectColor = a.getColor(R.styleable.wgqVoiceView_rectColor, Color.RED);
        mRectWidth = a.getDimension(R.styleable.wgqVoiceView_rectWidth,10);

        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRectPaint = new Paint();
        mRectPaint.setColor(mRectColor);
        int height = getHeight();
        int width = getWidth()/5;

        float x = getX();
        float y = getY();

        LogUtil.d("WGQ","height == " + height + "   width == " + width + "\n"
                            + "横坐标 == " + x + "\n"
                            + "纵坐标 == " + y);
        for(int i = 0;i < 5 ;i++){
            float top = (float) (100 + Math.random()*10)+ (float) (Math.random()* 80) * i;
            mRectRectF = new RectF(20 + x*(i+1) + 45*i,top ,20 + x*(i+1) + 45*i + mRectWidth,350);
            mRectPaint.setColor(colors[i]);
            canvas.drawRect(mRectRectF,mRectPaint);
            LogUtil.d("WGQ","x + i*15 + 5 == " + (x + i*15 + 5) + "   width == " + width + "\n"
                    + "TOP == " + top + "\n"
                    + "x + i*15 + 5 + mRectWidth == " + (x + i*15 + 5 + mRectWidth));
        }

        postInvalidateDelayed(300);
    }
}
