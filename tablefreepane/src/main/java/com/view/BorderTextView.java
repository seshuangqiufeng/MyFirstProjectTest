package com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaomi on 2016/7/25.
 */
public class BorderTextView extends TextView {

    /**
     * 边框宽度 单位px 默认为1
     */
    private int mBorderWidth = 1;
    private int mBorderLeftWidth = 1;
    private int mBorderRightWidth = 1;
    private int mBorderTopWidth = 1;
    private int mBorderBottomWidth = 1;

    /**
     * 边框颜色
     */
    private int mBorderColor = 0;
    private int mBorderLeftColor = -1;//0 是透明色的意思 这里用-1表示
    private int mBorderRightColor = -1;
    private int mBorderTopColor = -1;
    private int mBorderBottomColor = -1;

    public BorderTextView(Context context) {
        super(context);
    }
    public BorderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBorderColor != 0){
            if(mBorderLeftColor == -1){
                mBorderLeftColor = mBorderColor;
            }
            if(mBorderRightColor == -1){
                mBorderRightColor = mBorderColor;
            }
            if(mBorderTopColor == -1){
                mBorderTopColor = mBorderColor;
            }
            if(mBorderBottomColor == -1){
                mBorderBottomColor = mBorderColor;
            }
        }
        super.onDraw(canvas);
        canvas.translate(getScrollX(), 0);//fix 修复设置了 Gravity.CENTER 和 singleLine=true 边框无法绘制的问题
        Paint paint = new Paint();
        paint.setColor(mBorderTopColor);
        canvas.drawLine(0, 0, this.getWidth() - mBorderWidth, 0, paint);//TOP

        paint.setColor(mBorderLeftColor);
        canvas.drawLine(0, 0, 0, this.getHeight() - mBorderWidth, paint);//LEFT

        paint.setColor(mBorderRightColor);
        canvas.drawLine(this.getWidth() - mBorderWidth, 0, this.getWidth() - mBorderWidth, this.getHeight(), paint);//RIGHT

        paint.setColor(mBorderBottomColor);
        canvas.drawLine(0, this.getHeight() - mBorderWidth, this.getWidth() - mBorderWidth, this.getHeight() - mBorderWidth, paint);//BOTTOM
    }


    public void setBorderWidth(int mBorderWidth){
        this.mBorderWidth = mBorderWidth;
    }

    public void setBorderWidth(int l, int t, int r, int b){
        this.mBorderLeftWidth = l;
        this.mBorderTopWidth = t;
        this.mBorderRightWidth = r;
        this.mBorderBottomWidth = b;
    }

    public void setBorderColor(int mBorderColor) {
        this.mBorderColor = mBorderColor;
    }

    public void setBorderLeftColor(int mBorderLeftColor) {
        this.mBorderLeftColor = mBorderLeftColor;
    }

    public void setBorderRightColor(int mBorderRightColor) {
        this.mBorderRightColor = mBorderRightColor;
    }

    public void setBorderTopColor(int mBorderTopColor) {
        this.mBorderTopColor = mBorderTopColor;
    }

    public void setBorderBottomColor(int mBorderBottomColor) {
        this.mBorderBottomColor = mBorderBottomColor;
    }
}
