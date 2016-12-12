package com.view;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.tablefreezepane.R;
import com.util.DensityUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 固定表头是布局
 * A   B
 * C   D
 *
 * 整体思路
 * 1.需要获取每一列的宽度（高度是固定的值）--可以设置参数，字体是隐藏、折行
 *
 * 每一个单元格可定制，可点击
 *
 * a.可定制  开放接口的形式
 * b.可点击  监听钩子
 *
 * TODO
 * b 多行显示 单元格子行resize问题
 */
public abstract class AbstractTableLayout extends RelativeLayout implements MyHorizontalScrollView.Listener {

    public final String TAG = AbstractTableLayout.class.getSimpleName();

    protected List<String[]> headerList = new ArrayList<>();//指定TOP表头，多行

    private TableComponentClickListener tableComponentClickListener;

    private android.widget.TableLayout tableA;
    private android.widget.TableLayout tableB;
//    private android.widget.TableLayout tableB2;
    private android.widget.TableLayout tableC;
    private android.widget.TableLayout tableD;

    private MyHorizontalScrollView horizontalScrollViewB;
    private MyHorizontalScrollView horizontalScrollViewD;

    private ScrollView scrollViewC;
    private ScrollView scrollViewD;

    private Context mContext;//上下文

    private WindowManager windowManager;
    private LayoutInflater inflater;

    private int mWindowWidth;//屏幕宽度
    private int mWindowHeight;//屏幕高度

    private int mWidth;//表格高度
    private int mHeight;//表格宽度

    protected float mTitleHeight = 40f;//标题栏高度
    protected float mContentHeight = 33f;//内容高度
    protected float mMinWidth = 66.6f;//表格最小宽度

    protected float mTitleFontSize = 13f;//标题字体大小
    protected float mContentFontSize = 12f;//内容字体大小

    protected float mTableCellPadding = 6f;//单元格内补边距

    protected String[][] mTableArray;//需要显示的数据的二维数组

    protected int mTopFixedRowCount = 1;//默认顶端固定的行
    protected int mLeftFixedColumnCount = 1;//默认左侧固定的列

    protected boolean mMatchWidth = true;//默认宽度至少补满屏幕
    protected boolean singleLine = true;//单元格是否单行显示
    protected boolean showViewBHorizontalScrollBar = true;//是否显示表格B的水平滚动条
    protected Dialog mDialog;//是不是对话框的样式使用

    protected int tableCellsWidth[];//获取每一列的宽度

    protected float tableCellsFixDpWidth[] = new float[0]; //从外部指定的每一列的宽度
    protected int tableCellsFixPercentWidth[] = new int[0];//从外部指定的按照百分比的宽度

    public static final String HORIZONTAL_SCROLL_VIEW_B = "horizontal scroll view b";
    public static final String HORIZONTAL_SCROLL_VIEW_D = "horizontal scroll view d";
    public static final String SCROLL_VIEW_C = "scroll view c";
    public static final String SCROLL_VIEW_D = "scroll view d";

    public AbstractTableLayout(Context mContext) {
        super(mContext);
        init(mContext);
    }

    public AbstractTableLayout(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        init(mContext);
    }

    /**
     * 初始化
     * @param mContext
     */
    private void init(Context mContext) {
        this.mContext = mContext;
        windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        // initialize the main components (TableLayouts, HorizontalScrollView, ScrollView)
        this.initComponents();
        this.setComponentsId();
        this.setScrollViewAndHorizontalScrollViewTag();

        // no need to assemble component A, since it is just a table
        this.horizontalScrollViewB.addView(this.tableB);
        this.scrollViewC.addView(this.tableC);
        this.scrollViewD.addView(this.horizontalScrollViewD);
        this.horizontalScrollViewD.addView(this.tableD);

        // add the components to be part of the main layout
        this.addComponentToMainLayout();
    }

    /**
     * 手动？
     */
    public void handleInvalidate() {
        /**
         * 高度和宽度横竖屏切换会有变化
         */
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        mWindowHeight = dm.heightPixels;
        mWindowWidth = dm.widthPixels;
        //需要置为dialog的宽度
        if(mDialog != null){
            mWindowWidth = Math.min(mWindowHeight, mWindowWidth);
            mWindowWidth = mWindowWidth * 3 / 4;
        }
        mWindowWidth = mWindowWidth - getPaddingRight() - getPaddingLeft();
        if(!showViewBHorizontalScrollBar && Build.VERSION.SDK_INT >= 16){
            this.horizontalScrollViewB.setScrollBarSize(0);
            this.horizontalScrollViewB.setHorizontalScrollBarEnabled(false);
        }

        if(mWidth != 0){
            mWindowWidth = mWidth;
        }
        if(mHeight != 0){
            mWindowHeight = mHeight;
        }

        this.convertDataToArray();
        this.getTableRowCellWidth();//计算表格宽度和高度
        this.addTableRowToTableA();
        this.addTableRowToTableB();
        this.addTableC_AndTable_D();
    }

    /**
     * 需要子类继承 把需要展示的表格转化成二维数组
     */
    public abstract void convertDataToArray();

    /**
     * 初始化相关组件
     */
    private void initComponents() {
        inflater = (LayoutInflater)(this.mContext.getApplicationContext()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.tableA = new android.widget.TableLayout(this.mContext);
        this.tableB = new android.widget.TableLayout(this.mContext);
        this.tableC = new android.widget.TableLayout(this.mContext);
        this.tableD = new android.widget.TableLayout(this.mContext);

        this.horizontalScrollViewD = new MyHorizontalScrollView(this.mContext);
        this.horizontalScrollViewB = (MyHorizontalScrollView) inflater.inflate(R.layout.myhorizontalscrollview,null).findViewById(R.id.horizontalscrollview);

        if(Build.VERSION.SDK_INT >= 16) {
            this.horizontalScrollViewD.setScrollBarSize(0);
        }

        this.horizontalScrollViewB.setOverScrollMode(OVER_SCROLL_NEVER);
        this.horizontalScrollViewD.setOverScrollMode(OVER_SCROLL_NEVER);

        this.scrollViewC = new MyScrollView(this.mContext);
        this.scrollViewD = new MyScrollView(this.mContext);
        this.scrollViewC.setOverScrollMode(View.OVER_SCROLL_NEVER);
        this.scrollViewD.setOverScrollMode(View.OVER_SCROLL_NEVER);
        this.horizontalScrollViewB.setOnListener(this);
        this.horizontalScrollViewD.setOnListener(this);
        this.horizontalScrollViewB.setBackgroundColor(Color.LTGRAY);//LTGRAY
        this.horizontalScrollViewD.setHorizontalScrollBarEnabled(false);
        this.scrollViewC.setVerticalScrollBarEnabled(false);
    }

    /**
     * 设置TABLE D 的背景
     * @param background
     */
    public void setTableDBackground(Drawable background) {
        ViewCompat.setBackground(this.tableD, background);
    }

    /**
     * set essential component IDs
      */
    private void setComponentsId() {
        this.tableA.setId(R.id.view_table_a_id);
        this.horizontalScrollViewB.setId(R.id.view_horizontal_scroll_table_b_id);
        this.scrollViewC.setId(R.id.view_scroll_table_c_id);
        this.scrollViewD.setId(R.id.view_scroll_table_d_id);
    }

    /**
     * set tags for some horizontal and vertical scroll view
     */
    private void setScrollViewAndHorizontalScrollViewTag() {
        this.horizontalScrollViewB.setTag(HORIZONTAL_SCROLL_VIEW_B);
        this.horizontalScrollViewD.setTag(HORIZONTAL_SCROLL_VIEW_D);
        this.scrollViewC.setTag(SCROLL_VIEW_C);
        this.scrollViewD.setTag(SCROLL_VIEW_D);
    }

    /**
     * 添加组件排列顺序
     */
    private void addComponentToMainLayout() {
        // RelativeLayout params were very useful here
        // the addRule method is the key to arrange the components properly
        LayoutParams componentB_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.tableA.getId());
//        componentB_Params.leftMargin = -1;//fix 双边框问题

        LayoutParams componentC_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentC_Params.addRule(RelativeLayout.BELOW, this.tableA.getId());

        LayoutParams componentD_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewC.getId());
        componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollViewB.getId());
//        componentD_Params.leftMargin = -1;//fix 双边框问题

        // 'this' is a relative layout,
        // we extend this table layout as relative layout as seen during the creation of this class
        this.addView(this.tableA);
        this.addView(this.horizontalScrollViewB, componentB_Params);
        this.addView(this.scrollViewC, componentC_Params);
        this.addView(this.scrollViewD, componentD_Params);
    }

    /**
     * 构建TABLE A
     */
    private void addTableRowToTableA() {
        this.tableA.removeAllViews();
        if(mTopFixedRowCount >= 1 && mLeftFixedColumnCount >= 1){
            for(int row = 0; row < 1; row ++ ){
                TableRow tableRow = this.componentATableRow(mTableArray[row], row);
                this.tableA.addView(tableRow);
            }
        }
    }

    /**
     * 构建TABLE B
     */
    private void addTableRowToTableB() {
        this.tableB.removeAllViews();
        if(mTopFixedRowCount >= 1){
            for(int row = 0; row < mTopFixedRowCount; row ++ ){
                TableRow tableRow = this.componentBTableRow(mTableArray[row], row);
                this.tableB.addView(tableRow);
            }
        }
    }

    /**
     * 构建TABLE A 的一行记录
     * @return
     */
    // generate table row of table A
    TableRow componentATableRow(String[] rowData, int row) {
        TableRow componentATableRow = new TableRow(this.mContext);
        for(int column = 0; column < mLeftFixedColumnCount; column ++){
            String text = rowData[column];
            int width = tableCellsWidth[column];
            int height = DensityUtils.dip2px(mTitleHeight);
            buildTableCell(componentATableRow, text, row, column, width, height);
        }
        return componentATableRow;
    }

    /**
     * 构建TABLE B 的一行记录
     * @return
     */
    TableRow componentBTableRow(String[] rowData, int row) {
        TableRow componentBTableRow = new TableRow(this.mContext);
        int headerFieldCount = rowData.length;
        for (int column = mLeftFixedColumnCount; column < headerFieldCount; column ++) {
            String text = rowData[column];
            int width = tableCellsWidth[column];
            int height = DensityUtils.dip2px(mTitleHeight);
            buildTableCell(componentBTableRow, text, row, column, width, height);
        }
        return componentBTableRow;
    }

    /**
     * 构建一个单元格子
     * @param row
     * @param tableRow
     * @param column
     * @param text
     */
    private void buildTableCell(TableRow tableRow, String text, int row, int column, int width, int height) {

        View view = this.generateView(text, row, column, width, height);
        //设置每个单元格子可以点击
        final int finalRow = row;
        final int finalColumn = column;
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tableComponentClickListener != null){
                    tableComponentClickListener.onClick(v, finalRow, finalColumn);
                }
            }
        });
        TableRow.LayoutParams params = new TableRow.LayoutParams(width, height);
        tableRow.addView(view, params);
    }

    /**
     * 构建tableC和tableD
     */
    // add table row of table C and table D
    private void addTableC_AndTable_D() {
        this.tableC.removeAllViews();
        this.tableD.removeAllViews();
        int contentSize = mTableArray.length;
        for (int row = mTopFixedRowCount; row < contentSize; row ++) {
            TableRow tableRowForTableC = this.tableRowForTableC(mTableArray[row], row);
            TableRow tableRowForTableD = this.tableRowForTableD(mTableArray[row], row);
            this.tableC.addView(tableRowForTableC);
            this.tableD.addView(tableRowForTableD);
        }
    }

    /**
     * 构建tableC
     * @param rowData
     * @return
     */
    TableRow tableRowForTableC(String[] rowData, int row) {
        TableRow tableRowForTableC = new TableRow(this.mContext);
        if(mLeftFixedColumnCount > 0){
            for(int column = 0; column < mLeftFixedColumnCount; column ++){
                String text = rowData[column];
                int width = tableCellsWidth[column];
                int height = DensityUtils.dip2px(mContentHeight);
                buildTableCell(tableRowForTableC, text, row, column, width, height);
            }
        }
        return tableRowForTableC;
    }

    /**
     * 构建tableD
     * @param rowData
     * @return
     */
    TableRow tableRowForTableD(String[] rowData, int row) {
        TableRow taleRowForTableD = new TableRow(this.mContext);
        int loopCount = rowData.length;
        for (int column = mLeftFixedColumnCount; column < loopCount; column ++) {
            String text = rowData[column];
            int width = tableCellsWidth[column];
            int height = DensityUtils.dip2px(mContentHeight);
            buildTableCell(taleRowForTableD, text, row, column, width, height);
        }
        return taleRowForTableD;
    }

    //---------------------------------构建每一个单元格子View-------------------------------------------

    /**
     * 构建单元格视图
     * @param text 单元格填充的文本
     * @param row 单元格子位置 第row行
     * @param column 单元格子位置 第column列
     * @param width 单元格子宽度px
     * @param height 单元格子高度px
     * @return 创建好的单元格对象
     *
     * 可被子类覆盖重新计算
     */
    public abstract View generateView(String text, int row, int column, int width, int height);

    /**
     * 计算每一列的最小宽度
     * 第一列固定宽度
     * 测量算法
     * 可被子类覆盖重新计算
     */
    protected void getTableRowCellWidth() {
        computeRowCellWidth();
        /**
         * 如果宽度需要撑满，重新计算每一列的宽度
         */
        if(mMatchWidth){
            adaptMatchWidth();
        }
    }

    /**
     * 每行宽度计算算法
     * 提供三种方式
     * 1、通过<code>tableCellsFixDpWidth</code>固定某一列的列宽
     * 2、通过<code>tableCellsFixPercentWidth</code>按照等比例指定列宽
     * 3、按照显示的字段长度自动计算调整每列宽度
     */
    protected void computeRowCellWidth(){

        //开始计算
        int cellCount = mTableArray[0].length;
        this.tableCellsWidth = new int[cellCount];

        //index 表示列
        for (int index = 0; index < cellCount; index ++) {

            if(tableCellsFixDpWidth != null && tableCellsFixDpWidth.length > index){
                int fixWidth = DensityUtils.dip2px(tableCellsFixDpWidth[index]);
                int minWidth = DensityUtils.dip2px(mMinWidth);
                int width = Math.max(fixWidth, minWidth);
                this.tableCellsWidth[index] = width;
            }else
            //2
            if(tableCellsFixPercentWidth != null && tableCellsFixPercentWidth.length > 0){
                if(tableCellsFixPercentWidth.length != cellCount){
                    throw new IllegalArgumentException("The param tableCellsFixDpWidth.length is not equal column size,please check");
                }
                int sumPercent = 0;
                for(int i = 0; i < tableCellsFixPercentWidth.length; i ++){
                    sumPercent += tableCellsFixPercentWidth[i];
                }
                int averagePiece = mWindowWidth / sumPercent;//每一份宽度
                int percentWidth = DensityUtils.dip2px(averagePiece * tableCellsFixPercentWidth[index]);
                int minWidth = DensityUtils.dip2px(mMinWidth);
                int width = Math.max(percentWidth, minWidth);
                this.tableCellsWidth[index] = width;
            }else {
                //获取某一列 最长的字符串
                double temp = 0;
                int rowIndex = 0;//记录
                int viewWidth = 0;
                for(int i = 0; i < mTableArray.length; i++){
                    String str = mTableArray[i][index];
                    TextView textView = new TextView(this.mContext);
                    textView.setText(str);
                    if(rowIndex < mTopFixedRowCount){
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mTitleFontSize);
                    }else{
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, mContentFontSize);
                    }
                   int maxViewWidth = this.viewWidth(textView) + (DensityUtils.dip2px(getTableCellPadding()) * 2);//在这里获取每一个列的数据 + 内补的距离才行
                    viewWidth = Math.max(viewWidth, maxViewWidth);
                }
                int minWidth = DensityUtils.dip2px(mMinWidth);
                int width = Math.max(viewWidth, minWidth);//如果表格宽度小于最小宽度，则显示最小宽度

                Log.d(TAG, "viewWidth=" + viewWidth + ",width=" + width);

                this.tableCellsWidth[index] = width;
            }
        }
    }

    /**
     * 宽度全屏适配算法
     * 1.如果所有列的累加和宽度已经大于屏幕宽度，则表示已经撑满，不需要再均分，显示滚动条即可
     * 2.否则
     *  a)计算剩余的部分，然后均分，并增加到每一列上去
     *
     *  可被子类覆盖重新计算
     */
    protected void adaptMatchWidth(){
        //开始计算
        int cellCount = mTableArray[0].length;
        int currentSumWidth = 0;
        for(int i = 0; i < cellCount; i ++){
            currentSumWidth += tableCellsWidth[i];
        }
        if(currentSumWidth >= mWindowWidth){
            return;
        }
        //剩余宽度
        int restWidth = mWindowWidth - currentSumWidth;
        int everyRestWidth = restWidth / cellCount;
        for(int i = 0; i < cellCount; i ++){
            tableCellsWidth[i] = tableCellsWidth[i] + everyRestWidth;
        }
    }

    /**
     * 测量视图的高度
     * @param view
     * @return
     */
    private int viewHeight(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight();
    }

    /**
     * 测量视图的高度
     * @param view
     * @return
     */
    private int viewWidth(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    @Override
    public void onTopScroll(int left) {
        this.horizontalScrollViewD.scrollTo(left, 0);
    }

    @Override
    public void onBottomScroll(int left) {
        this.horizontalScrollViewB.scrollTo(left, 0);
    }

    // scroll view custom class
    class MyScrollView extends ScrollView {

        public MyScrollView(Context mContext) {
            super(mContext);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            String tag = (String) this.getTag();
            if (tag.equalsIgnoreCase(SCROLL_VIEW_C)) {
                scrollViewD.smoothScrollTo(0, t);
            } else {
                scrollViewC.smoothScrollTo(0, t);
            }
        }
    }

    /**
     * 点击事件
     */
    public interface TableComponentClickListener {
        void onClick(View view, int row, int column);
    }

    public void setTableComponentClickListener(TableComponentClickListener tableComponentClickListener) {
        this.tableComponentClickListener = tableComponentClickListener;
    }

    /**
     * 获取字符串长度
     * @param s
     * @return
     */
   public double getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        //进位取整
        return  Math.ceil(valueLength);
    }

    /**
     * 版本兼容测试
     */
    protected static class ViewCompat {
        public static void setBackground(View view, Drawable background){
            if(view == null || background == null){
                return;
            }
            if(Build.VERSION.SDK_INT >= 16) {
                view.setBackground(background);
            }else{
                view.setBackgroundDrawable(background);
            }
        }
    }

    /**
     * 千分隔位
     * @param num
     * @return
     */
    public static String intToDecimal(double num){
        DecimalFormat df = new DecimalFormat("#,###.#####");
        return df.format(num);
    }

    public int[] getTableCellsFixPercentWidth() {
        return tableCellsFixPercentWidth;
    }

    public void setTableCellsFixPercentWidth(int[] tableCellsFixPercentWidth) {
        this.tableCellsFixPercentWidth = tableCellsFixPercentWidth;
    }

    public float[] getTableCellsFixDpWidth() {
        return tableCellsFixDpWidth;
    }

    public void setTableCellsFixDpWidth(float[] tableCellsFixDpWidth) {
        this.tableCellsFixDpWidth = tableCellsFixDpWidth;
    }

    public int[] getTableCellsWidth() {
        return tableCellsWidth;
    }

    public void setTableCellsWidth(int[] tableCellsWidth) {
        this.tableCellsWidth = tableCellsWidth;
    }

    public boolean isSingleLine() {
        return singleLine;
    }

    public void setSingleLine(boolean singleLine) {
        this.singleLine = singleLine;
    }

    public boolean isMatchWidth() {
        return mMatchWidth;
    }

    public void setMatchWidth(boolean mMatchWidth) {
        this.mMatchWidth = mMatchWidth;
    }

    public int getLeftFixedColumnCount() {
        return mLeftFixedColumnCount;
    }

    public void setLeftFixedColumnCount(int mLeftFixedColumnCount) {
        this.mLeftFixedColumnCount = mLeftFixedColumnCount;
    }

    public int getTopFixedRowCount() {
        return mTopFixedRowCount;
    }

    public void setTopFixedRowCount(int mTopFixedRowCount) {
        this.mTopFixedRowCount = mTopFixedRowCount;
    }

    public String[][] getTableArray() {
        return mTableArray;
    }

    public void setTableArray(String[][] mTableArray) {
        this.mTableArray = mTableArray;
    }

    public float getTableCellPadding() {
        return mTableCellPadding;
    }

    public void setTableCellPadding(float mTableCellPadding) {
        this.mTableCellPadding = mTableCellPadding;
    }

    public float getContentFontSize() {
        return mContentFontSize;
    }

    public void setContentFontSize(float mContentFontSize) {
        this.mContentFontSize = mContentFontSize;
    }

    public float getTitleFontSize() {
        return mTitleFontSize;
    }

    public void setTitleFontSize(float mTitleFontSize) {
        this.mTitleFontSize = mTitleFontSize;
    }

    public float getMinWidth() {
        return mMinWidth;
    }

    public void setMinWidth(float mMinWidth) {
        this.mMinWidth = mMinWidth;
    }

    public float getContentHeight() {
        return mContentHeight;
    }

    public void setContentHeight(float mContentHeight) {
        this.mContentHeight = mContentHeight;
    }

    public float getTitleHeight() {
        return mTitleHeight;
    }

    public void setTitleHeight(float mTitleHeight) {
        this.mTitleHeight = mTitleHeight;
    }

    public void setDialog(Dialog mDialog) {
        this.mDialog = mDialog;
    }

    public void setShowViewBHorizontalScrollBar(boolean showViewBHorizontalScrollBar) {
        this.showViewBHorizontalScrollBar = showViewBHorizontalScrollBar;
    }

    public void setWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public void setHeight(int mHeight) {
        this.mHeight = mHeight;
    }
}
