package com.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import com.util.DensityUtils;
import com.util.EntityCommonInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguoqiang on 2016/10/17.
 */
public class ChildTableLayout extends AbstractTableLayout {


    private Context mContext;//上下文


    protected String headers[] = {};//指定TOP表头，单行
    protected List<EntityCommonInterface> dataList = new ArrayList<>();//数据

    private int mRowCount;//行数量
    private int mColumnCount;//列数量
    private int borderWidth = 1;
    private int borderColor = Color.LTGRAY;
    private boolean singleLine = true;
    private int textColor ;

    public ChildTableLayout(Context mContext) {
        super(mContext);
        init(mContext);
    }

    public ChildTableLayout(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        init(mContext);
    }

    private void init(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void convertDataToArray() {
        headerList.clear();
        headerList.add(headers);

        int headerSize = headerList.size();
        int dataSize = dataList.size();
        mTableArray = new String[dataList.size() + headerSize][];
        for (int i = 0; i < headerSize; i++) {
            mTableArray[i] = headerList.get(i);
        }

        for (int i = 0; i < dataSize; i++) {
            String[] colums = new String[headers.length];
            EntityCommonInterface demoDetail = dataList.get(i);
            for (int j = 0; j < headers.length; j++) {
                colums[j] = demoDetail.getElement(j);
            }
            mTableArray[i + headerSize] = colums;

        }
        mTableArray[1][0] = "合计";
        /**
         * 记录表格的行数量和列数量
         */
        if (mTableArray.length >= 1) {
            mRowCount = mTableArray.length;
            mColumnCount = mTableArray[0].length;
        }
    }

    @Override
    public View generateView(String text, int row, int column, int width, int height) {
        int cellPadding = DensityUtils.dip2px(mTableCellPadding);

        BorderTextView borderTextView = new BorderTextView(mContext);
        borderTextView.setBorderWidth(1);
        borderTextView.setBorderColor(Color.LTGRAY);
        borderTextView.setText(text);
        borderTextView.setSingleLine(true);
        borderTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mContentFontSize);
//        borderTextView.setTextColor(getResources().getColor(R.color.app_blue));

        /**
         * 是否绘制某一侧的表格宽度
         */
        //第一行
        if (row == 0) {
            if (column >= 1) {
                borderTextView.setBorderLeftColor(Color.TRANSPARENT);//左侧表格绘制成透明色
            }
        } else {
            if (column == 0) {
                borderTextView.setBorderTopColor(Color.TRANSPARENT);
            } else {
                borderTextView.setBorderTopColor(Color.TRANSPARENT);
                borderTextView.setBorderLeftColor(Color.TRANSPARENT);
            }
        }
        //table A
        if (row < mTopFixedRowCount && column < mLeftFixedColumnCount) {
            borderTextView.setGravity(Gravity.CENTER);
            borderTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTitleFontSize);
            borderTextView.setBackgroundColor(0xeef0f4fe);//半透明的背景色
            borderTextView.setBorderRightColor(0xFFA6B4F2);

            //TODO 表头下沉问题
//            textView.setPadding(64, 36, 0, 0); //针对1080分辨率
            return borderTextView;
        } else
            //TB
            if (row < mTopFixedRowCount && column >= mLeftFixedColumnCount) {
                borderTextView.setGravity(Gravity.CENTER);
                borderTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTitleFontSize);
                borderTextView.setBackgroundColor(0xeef0f4fe);//半透明的背景色
                return borderTextView;

            } else
                //TC
                if (row >= mTopFixedRowCount && column < mLeftFixedColumnCount) {

//                    borderTextView.setTextColor(getResources().getColor(R.color.secondary_text_color));
                    borderTextView.setGravity(Gravity.LEFT + Gravity.CENTER_VERTICAL);
//                    if(!CharUtil.isNumeric(text)) {
//                        borderTextView.setGravity(Gravity.LEFT + Gravity.CENTER_VERTICAL);
//                        borderTextView.setPadding(cellPadding, 0, 0, 0);
//                    }else {
//                        borderTextView.setGravity(Gravity.RIGHT + Gravity.CENTER_VERTICAL);
//                        borderTextView.setPadding(0, 0, cellPadding, 0);
//                    }
                    borderTextView.setPadding(cellPadding, 0, 0, 0);
                    borderTextView.setBackgroundColor(0xeefbfbfc);//半透明的背景色
                    borderTextView.setBorderRightColor(0xFFA6B4F2);

                    if (text.equals("合计")) {
                        SpannableString msp = new SpannableString(text);
                        borderTextView.setText(msp);
                        borderTextView.setTypeface(Typeface.MONOSPACE, Typeface.ITALIC);
                        borderTextView.setBorderBottomColor(0xFFA6B4F2);
                    }
                    return borderTextView;

                } else {
//                    borderTextView.setTextColor(getResources().getColor(R.color.secondary_text_color));
                    borderTextView.setGravity(Gravity.RIGHT + Gravity.CENTER_VERTICAL);
//                    if(!CharUtil.isNumeric(text)) {
//                        borderTextView.setGravity(Gravity.LEFT + Gravity.CENTER_VERTICAL);
//                        borderTextView.setPadding(cellPadding, 0, 0, 0);
//                    }else {
//                        borderTextView.setGravity(Gravity.RIGHT + Gravity.CENTER_VERTICAL);
//                        borderTextView.setPadding(0, 0, cellPadding, 0);
//                    }
                    borderTextView.setPadding(0, 0, cellPadding, 0);
                    borderTextView.setBackgroundColor(0xeefbfbfc);//半透明的背景色
                    if (row == 1) {
                        borderTextView.setSoundEffectsEnabled(false);
                        borderTextView.setEnabled(false);
                        borderTextView.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);//斜体
                        borderTextView.setBorderBottomColor(0xFFA6B4F2);
                    }
                    if (text.equals("0")) {
                        borderTextView.setSoundEffectsEnabled(false);
                        borderTextView.setEnabled(false);
                    }
                    return borderTextView;
                }
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public void setDataList(List<EntityCommonInterface> dataList) {
        this.dataList = dataList;
    }
}
