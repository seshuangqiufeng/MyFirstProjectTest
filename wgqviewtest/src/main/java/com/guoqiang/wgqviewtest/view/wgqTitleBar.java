package com.guoqiang.wgqviewtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guoqiang.wgqviewtest.R;
import com.guoqiang.wgqviewtest.util.DensityUtils;
import com.guoqiang.wgqviewtest.util.LogUtil;

/**
 * Created by wangguoqiang on 2017/2/8.
 */
public class wgqTitleBar extends RelativeLayout {

    private Context mContext;

    private TopbarClickListener mTopbarClickListener;

    private TextView mLeftBtn;
    private TextView mRightBtn;
    private TextView mTitleName;

    private String mTitle;
    private int mTitleTextColor;
    private float mTitleTextSize;

    private String mLeftText;
    private int mLeftTextColor;
    private float mLeftTextSize;
    private Drawable mLeftBackgroud;

    private String mRightText;
    private int mRightTextColor;
    private float mRightTextSize;
    private Drawable mRightBackgroud;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleNameParams;


    public wgqTitleBar(Context context) {
        super(context);
        initView(context,null,0);
    }

    public wgqTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs,0);
    }

    public wgqTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    /**
     *
     * @param context
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.wgqTitleBar);



        mTitle = a.getString(R.styleable.wgqTitleBar_wgqtitleText);
        mTitleTextColor = a.getColor(R.styleable.wgqTitleBar_wgqtitleTextColor, Color.red(0xff0000));
        mTitleTextSize = DensityUtils.px2dip(a.getDimension(R.styleable.wgqTitleBar_wgqtitleTextSize,12));

        mLeftText = a.getString(R.styleable.wgqTitleBar_leftText);
        mLeftTextColor = a.getColor(R.styleable.wgqTitleBar_leftTextColor,Color.CYAN);
        mLeftTextSize = DensityUtils.px2dip(a.getDimension(R.styleable.wgqTitleBar_leftTextSize,12));
        mLeftBackgroud = a.getDrawable(R.styleable.wgqTitleBar_leftBackground);


        mRightText = a.getString(R.styleable.wgqTitleBar_rightText);
        mRightTextColor = a.getColor(R.styleable.wgqTitleBar_rightTextColor,Color.BLUE);
        mRightTextSize = DensityUtils.px2dip(a.getDimension(R.styleable.wgqTitleBar_rightTextSize,12));
        mRightBackgroud = a.getDrawable(R.styleable.wgqTitleBar_rightBackground);

        a.recycle();

        LogUtil.d("WGQ","TItle 字体大小  " + mTitleTextSize + "\n"
                + "mLeft 字体大小  " + mLeftTextSize + "\n"
                + "mRight 字体大小   " + mRightTextSize);

        initCompontents(context);

    }

    /**
     *
     */
    private void initCompontents(Context context) {
        mLeftBtn = new TextView(context);
        mRightBtn = new TextView(context);
        mTitleName = new TextView(context);


        mTitleName.setText(mTitle);
        mTitleName.setTextColor(mTitleTextColor);
        mTitleName.setTextSize(mTitleTextSize);
        mTitleName.setGravity(Gravity.CENTER);

        mLeftBtn.setText(mLeftText);
        mLeftBtn.setGravity(Gravity.CENTER);
        mLeftBtn.setTextColor(mLeftTextColor);
        mLeftBtn.setTextSize(mLeftTextSize);
//        mLeftBtn.setBackground(mLeftBackgroud);

        mRightBtn.setText(mRightText);
        mRightBtn.setTextColor(mRightTextColor);
        mRightBtn.setGravity(Gravity.CENTER);
        mRightBtn.setTextSize(mRightTextSize);
//        mRightBtn.setBackground(mRightBackgroud);
        LogUtil.d("WGQ","TItle 字体大小  " + mTitleName.getTextSize() + "\n"
                        + "mLeft 字体大小  " + mLeftBtn.getTextSize() + "\n"
                        + "mRight 字体大小   " + mRightBtn.getTextSize());

        mTitleNameParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleNameParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleName,mTitleNameParams);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mLeftBtn,mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightBtn,mRightParams);


        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopbarClickListener.leftClick();
            }
        });

        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopbarClickListener.rightClick();
            }
        });
    }


    public interface TopbarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setTopbarClickListener(TopbarClickListener topbarClick){
        mTopbarClickListener = topbarClick;
    }

}
