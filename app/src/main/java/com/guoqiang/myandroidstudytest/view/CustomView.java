package com.guoqiang.myandroidstudytest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.guoqiang.myandroidstudytest.R;

/**
 * Created by wangguoqiang on 2016/7/18.
 */
public class CustomView extends View {

    private static final String TAG = CustomView.class.getSimpleName();

    private Context mContext;

    private int mViewColor ;
    private float mViewDemission;

    private Paint mPaint;
    private RectF mBounds;

    private float width;
    private float height;

    float radius ;
    float smallLength;
    float largeLength;


    public CustomView(Context context) {
        super(context);

    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context);
        init(context,attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context,attrs);
    }

    /**
     *
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView,0,0);

        try {
            mViewColor = typeArray.getColor(R.styleable.CustomView_border_color,0xff000000);
            mViewDemission = typeArray.getDimension(R.styleable.CustomView_border_width,2);
        }catch (Exception e){

        }finally {
            typeArray.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mViewDemission);
        mPaint.setColor(mViewColor);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());
        width = mBounds.width()- mBounds.left;
        height = mBounds.bottom-mBounds.top;

        if(width < height){
            radius = width/4;

        }else{
            radius = height/4;
        }

        smallLength = 10;
        largeLength = 20;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff000000);
        mPaint.setColor(0x66555555);
        canvas.drawRoundRect(new RectF(mBounds.centerX()-(float)0.9*width/2, mBounds.centerY() - (float)0.9*height/2, mBounds.centerX() + (float)0.9*width/2, mBounds.centerY() + (float)0.9*height/2), 30, 30, mPaint);
        mPaint.setColor(mViewColor);
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),radius,mPaint);
        float start_x,start_y;
        float end_x,end_y;
        for(int i = 0;i< 60;i++){
            start_x= radius *(float)Math.cos(Math.PI/180 * i * 6);
            start_y= radius *(float)Math.sin(Math.PI/180 * i * 6);
            if(i%5==0){
                end_x = start_x+largeLength*(float)Math.cos(Math.PI / 180 * i * 6);
                end_y = start_y+largeLength*(float)Math.sin(Math.PI/180 * i * 6);
            }else{
                end_x = start_x+smallLength*(float)Math.cos(Math.PI/180 * i * 6);
                end_y = start_y+smallLength*(float)Math.sin(Math.PI/180 * i * 6);
            }
            start_x+=mBounds.centerX();
            end_x+=mBounds.centerX();
            start_y+=mBounds.centerY();
            end_y+=mBounds.centerY();
            canvas.drawLine(start_x, start_y, end_x, end_y, mPaint);
        }
        canvas.drawCircle(mBounds.centerX(),mBounds.centerY(),20,mPaint);
        canvas.rotate(60,mBounds.centerX(),mBounds.centerY());
        canvas.drawLine(mBounds.centerX(),mBounds.centerY(),mBounds.centerX(),mBounds.centerY()-radius,mPaint);
    }
    int i;
    float downX = 0;
    float downY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d("this","进来方法   i  === "+i++);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                Log.d("this","downX == "+ downX);
                Toast.makeText(mContext,"已经按下了！",Toast.LENGTH_LONG).show();

                break;
            case MotionEvent.ACTION_MOVE:
//                Toast.makeText(this,"开始滑动了！！",Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_UP:

                float moveX = event.getX();
                float moveY = event.getY();

                Log.d("this","downX == "+ downX+"  moveX === " + moveX);

                if((moveX - downX)>20){
                    Toast.makeText(mContext,"子View    左侧滑动，弹出侧滑菜单！",Toast.LENGTH_LONG).show();
                }

                break;

        }

        return true;
    }
}
