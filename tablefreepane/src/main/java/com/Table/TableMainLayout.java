package com.Table;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TableMainLayout extends RelativeLayout {

    public final String TAG = "TableMainLayout.java";

    // set the header titles
//	String headers[] = {
//		"Header 1 \n multi-lines",
//		"Header 2",
//		"Header 3",
//		"Header 4",
//		"Header 5",
//		"Header 6",
//		"Header 7",
//		"Header 8",
//		"Header 9"
//	};

    private Handler mHandler;

    String headers[] = {};

    private TableComponentClickLisenter mm;


    TableLayout tableA;

    public TableLayout getTableA() {
        return tableA;
    }

    public void setTableA(TableLayout tableA) {
        this.tableA = tableA;
    }

    public TableLayout getTableB() {
        return tableB;
    }

    public void setTableB(TableLayout tableB) {
        this.tableB = tableB;
    }

    public TableLayout getTableC() {
        return tableC;
    }

    public void setTableC(TableLayout tableC) {
        this.tableC = tableC;
    }

    public TableLayout getTableD() {
        return tableD;
    }

    public void setTableD(TableLayout tableD) {
        this.tableD = tableD;
    }

    public HorizontalScrollView getHorizontalScrollViewB() {
        return horizontalScrollViewB;
    }

    public void setHorizontalScrollViewB(HorizontalScrollView horizontalScrollViewB) {
        this.horizontalScrollViewB = horizontalScrollViewB;
    }

    public HorizontalScrollView getHorizontalScrollViewD() {
        return horizontalScrollViewD;
    }

    public void setHorizontalScrollViewD(HorizontalScrollView horizontalScrollViewD) {
        this.horizontalScrollViewD = horizontalScrollViewD;
    }

    public ScrollView getScrollViewC() {
        return scrollViewC;
    }

    public void setScrollViewC(ScrollView scrollViewC) {
        this.scrollViewC = scrollViewC;
    }

    public ScrollView getScrollViewD() {
        return scrollViewD;
    }

    public void setScrollViewD(ScrollView scrollViewD) {
        this.scrollViewD = scrollViewD;
    }

    public Handler getmHandler() {
        return mHandler;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    TableLayout tableB;
    TableLayout tableC;
    TableLayout tableD;

    HorizontalScrollView horizontalScrollViewB;
    HorizontalScrollView horizontalScrollViewD;

    ScrollView scrollViewC;
    ScrollView scrollViewD;

    Context context;

    List<SampleObject> sampleObjects = new ArrayList<SampleObject>();

    int headerCellsWidth[];

    public TableMainLayout(Context context) {

        super(context);

        init(context);

    }

    public TableMainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

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
        this.setBackgroundColor(Color.RED);

    }

    /**
     * 手动？
     */
    public void handleInvilate() {

        // add some table rows

        this.addTableRowToTableA();
        this.addTableRowToTableB();
        this.resizeHeaderHeight();
        this.getTableRowHeaderCellWidth();
        this.generateTableC_AndTable_B();
        this.resizeBodyTableRowHeight();
        invalidate();
    }

    public void handleInvilate1() {

        this.getTableRowHeaderCellWidth();

        this.generateTableC_AndTable_B();

        this.resizeBodyTableRowHeight();
        invalidate();
    }


    // this is just the sample data
    List<SampleObject> sampleObjects() {

//		List<SampleObject> sampleObjects = new ArrayList<SampleObject>();
//
//		for(int x=1; x<=20; x++){
//
//			SampleObject sampleObject = new SampleObject(
//				"Col 1, Row " + x,
//				"Col 2, Row " + x + " - multi-lines",
//				"Col 3, Row " + x,
//				"Col 4, Row " + x,
//				"Col 5, Row " + x,
//				"Col 6, Row " + x,
//				"Col 7, Row " + x,
//				"Col 8, Row " + x,
//				"Col 9, Row " + x
//			);
//
//			sampleObjects.add(sampleObject);
//		}

        return sampleObjects;

    }

    // initalized components
    private void initComponents() {

        this.tableA = new TableLayout(this.context);
        this.tableB = new TableLayout(this.context);
        this.tableC = new TableLayout(this.context);
        this.tableD = new TableLayout(this.context);

        this.horizontalScrollViewB = new MyHorizontalScrollView(this.context);
        this.horizontalScrollViewD = new MyHorizontalScrollView(this.context);

        this.scrollViewC = new MyScrollView(this.context);
        this.scrollViewD = new MyScrollView(this.context);

        this.tableA.setBackgroundColor(Color.GREEN);
        this.horizontalScrollViewB.setBackgroundColor(Color.LTGRAY);

    }

    // set essential component IDs
    private void setComponentsId() {
        this.tableA.setId(1);
        this.horizontalScrollViewB.setId(2);
        this.scrollViewC.setId(3);
        this.scrollViewD.setId(4);
    }

    // set tags for some horizontal and vertical scroll view
    private void setScrollViewAndHorizontalScrollViewTag() {

        this.horizontalScrollViewB.setTag("horizontal scroll view b");
        this.horizontalScrollViewD.setTag("horizontal scroll view d");

        this.scrollViewC.setTag("scroll view c");
        this.scrollViewD.setTag("scroll view d");
    }

    // we add the components here in our TableMainLayout
    private void addComponentToMainLayout() {

        // RelativeLayout params were very useful here
        // the addRule method is the key to arrange the components properly
        LayoutParams componentB_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.tableA.getId());

        LayoutParams componentC_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentC_Params.addRule(RelativeLayout.BELOW, this.tableA.getId());

        LayoutParams componentD_Params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewC.getId());
        componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollViewB.getId());

        // 'this' is a relative layout,
        // we extend this table layout as relative layout as seen during the creation of this class
        this.addView(this.tableA);
        this.addView(this.horizontalScrollViewB, componentB_Params);
        this.addView(this.scrollViewC, componentC_Params);
        this.addView(this.scrollViewD, componentD_Params);

    }


    private void addTableRowToTableA() {
        this.tableA.addView(this.componentATableRow());
    }

    private void addTableRowToTableB() {
        this.tableB.addView(this.componentBTableRow());
    }

    // generate table row of table A
    TableRow componentATableRow() {

        TableRow componentATableRow = new TableRow(this.context);
        TextView textView = this.headerTextView(this.headers[0]);
        textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        componentATableRow.addView(textView);
        return componentATableRow;
    }

    // generate table row of table B
    TableRow componentBTableRow() {

        TableRow componentBTableRow = new TableRow(this.context);
        int headerFieldCount = this.headers.length;

        for (int x = 0; x < (headerFieldCount - 1); x++) {
            int width = getMaxWidthfromRow(x+1);
            TableRow.LayoutParams params = new TableRow.LayoutParams(width, 120);//this.headerCellsWidth[x+1]
            System.out.println("每行宽度：   "+width + "x == " + x);
            params.setMargins(2, 0, 0, 0);
            final TextView textView = this.headerTextView(this.headers[x + 1]);
//			textView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            textView.setLayoutParams(params);
//			textView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            textView.setTag(x);
            componentBTableRow.addView(textView);
            final int finalX = x;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//					Toast.makeText(context,""+ finalX + " === "+TableMainLayout.this.headers[finalX+1],Toast.LENGTH_SHORT).show();
                    Message msg = new Message();
                    msg.obj = TableMainLayout.this.headers[finalX + 1];
                    msg.what = 12;
                    mHandler.sendMessage(msg);
                    mm.onClick(textView);
                }
            });
        }

        return componentBTableRow;
    }

    // generate table row of table C and table D
    private void generateTableC_AndTable_B() {

        // just seeing some header cell width
        for (int x = 0; x < this.headerCellsWidth.length; x++) {
            Log.v("TableMainLayout.java", this.headerCellsWidth[x] + "");
        }

        for (SampleObject sampleObject : this.sampleObjects) {

            TableRow tableRowForTableC = this.tableRowForTableC(sampleObject);
            TableRow taleRowForTableD = this.taleRowForTableD(sampleObject);

            tableRowForTableC.setBackgroundColor(Color.LTGRAY);
            taleRowForTableD.setBackgroundColor(Color.LTGRAY);

            this.tableC.addView(tableRowForTableC);
            this.tableD.addView(taleRowForTableD);

        }
    }

    // a TableRow for table C
    TableRow tableRowForTableC(SampleObject sampleObject) {

        TableRow.LayoutParams params = new TableRow.LayoutParams(this.headerCellsWidth[0], LayoutParams.MATCH_PARENT);
        params.setMargins(0, 2, 0, 0);

        TableRow tableRowForTableC = new TableRow(this.context);
        final TextView textView = this.bodyTextView(sampleObject.header1);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mm.onClick(textView);
            }
        });
        tableRowForTableC.addView(textView, params);

        return tableRowForTableC;
    }

    TableRow taleRowForTableD(SampleObject sampleObject) {

        TableRow taleRowForTableD = new TableRow(this.context);

        int loopCount = ((TableRow) this.tableB.getChildAt(0)).getChildCount();
        String info[] = {
                sampleObject.header2,
                sampleObject.header3,
                sampleObject.header4,
                sampleObject.header5,
                sampleObject.header6,
                sampleObject.header7,
//			sampleObject.header8,
//			sampleObject.header9
        };

        for (int x = 0; x < loopCount; x++) {
            TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);//headerCellsWidth[x + 1]
            params.setMargins(2, 2, 0, 0);
            TextView textViewB = this.bodyTextView(info[x]);
            if(x != 0) {
                this.headerCellsWidth[x] = viewWidth(textViewB);
            }
            taleRowForTableD.addView(textViewB, params);
        }

        return taleRowForTableD;

    }

    // table cell standard TextView
    TextView bodyTextView(String label) {

        TextView bodyTextView = new TextView(this.context);
        bodyTextView.setBackgroundColor(Color.WHITE);
        bodyTextView.setText(label);
        bodyTextView.setGravity(Gravity.CENTER);
        bodyTextView.setPadding(5, 5, 5, 5);

        return bodyTextView;
    }

    // header standard TextView
    TextView headerTextView(String label) {

        TextView headerTextView = new TextView(this.context);
        headerTextView.setBackgroundColor(Color.WHITE);
        headerTextView.setText(label);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setPadding(5, 5, 5, 5);

        return headerTextView;
    }

    // resizing TableRow height starts here
    void resizeHeaderHeight() {

        TableRow productNameHeaderTableRow = (TableRow) this.tableA.getChildAt(0);
        TableRow productInfoTableRow = (TableRow) this.tableB.getChildAt(0);

        int rowAHeight = this.viewHeight(productNameHeaderTableRow);
        int rowBHeight = this.viewHeight(productInfoTableRow);

        TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
        int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

        this.matchLayoutHeight(tableRow, finalHeight);
    }

    void getTableRowHeaderCellWidth() {

        int tableAChildCount = ((TableRow) this.tableA.getChildAt(0)).getChildCount();
        int tableBChildCount = ((TableRow) this.tableB.getChildAt(0)).getChildCount();

        for (int x = 0; x < (tableAChildCount + tableBChildCount); x++) {
            if (x == 0) {
                this.headerCellsWidth[x] = this.viewWidth(((TableRow) this.tableA.getChildAt(0)).getChildAt(x));
            } else {
                //在这里获取每一个列的数据即
//                this.headerCellsWidth[x] =  getMaxWidthfromRow();
                this.headerCellsWidth[x] = this.viewWidth(((TableRow) this.tableB.getChildAt(0)).getChildAt(x - 1));
            }
        }
    }

    /**
     *
     */
    private int getMaxWidthfromRow(int x) {
        int temp = 0;
        String flagString = "";
        String[] str = new String[sampleObjects.size()];
        for (int i = 0; i < sampleObjects.size(); i++) {
            switch (x) {
                case 1:
                    String s1 = sampleObjects.get(i).getHeader2().toString();
                    str[i] = s1;
                    break;
                case 2:
                    String s2 = sampleObjects.get(i).getHeader3().toString();
                    str[i] = s2;
                    break;
                case 3:
                    String s3 = sampleObjects.get(i).getHeader4().toString();
                    str[i] = s3;
                    break;
                case 4:
                    String s4 = sampleObjects.get(i).getHeader5().toString();
                    str[i] = s4;
                    break;
                case 5:
                    String s5 = sampleObjects.get(i).getHeader6().toString();
                    str[i] = s5;
                    break;
                case 6:
                    String s6 = sampleObjects.get(i).getHeader7().toString();
                    str[i] = s6;
                    break;
            }
        }

        for(int i = 0;i<str.length;i++){
            if(str[i].length() > temp){
                temp = str[i].length();
                flagString = str[i];
            }
        }

        for (int i = 0; i < str.length; i++) {
            if(x==2) {
            System.out.println("str[i] == "+str[i] + "   =======   "+x);
            }
        }

        for (int i = 0; i < sampleObjects.size(); i++) {
            switch (x) {
                case 1:
                    if (flagString.equals(sampleObjects.get(i).getHeader2())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
                case 2:
                    if (flagString.equals(sampleObjects.get(i).getHeader3())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
                case 3:
                    if (flagString.equals(sampleObjects.get(i).getHeader4())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
                case 4:
                    if (flagString.equals(sampleObjects.get(i).getHeader5())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
                case 5:
                    if (flagString.equals(sampleObjects.get(i).getHeader6())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
                case 6:
                    if (flagString.equals(sampleObjects.get(i).getHeader7())) {
                        TextView textView = bodyTextView(flagString);
                        return this.viewWidth(textView);
                    }
                    break;
            }

        }
        return 0;
    }

    // resize body table row height
    void resizeBodyTableRowHeight() {

        int tableC_ChildCount = this.tableC.getChildCount();

        for (int x = 0; x < tableC_ChildCount; x++) {

            TableRow productNameHeaderTableRow = (TableRow) this.tableC.getChildAt(x);
            TableRow productInfoTableRow = (TableRow) this.tableD.getChildAt(x);

            int rowAHeight = this.viewHeight(productNameHeaderTableRow);
            int rowBHeight = this.viewHeight(productInfoTableRow);

            TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
            int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

            this.matchLayoutHeight(tableRow, finalHeight);
        }

    }

    // match all height in a table row
    // to make a standard TableRow height
    private void matchLayoutHeight(TableRow tableRow, int height) {

        int tableRowChildCount = tableRow.getChildCount();

        // if a TableRow has only 1 child
        if (tableRow.getChildCount() == 1) {

            View view = tableRow.getChildAt(0);
            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();
            params.height = height - (params.bottomMargin + params.topMargin);

            return;
        }

        // if a TableRow has more than 1 child
        for (int x = 0; x < tableRowChildCount; x++) {

            View view = tableRow.getChildAt(x);

            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();

            if (!isTheHeighestLayout(tableRow, x)) {
                params.height = height - (params.bottomMargin + params.topMargin);
                return;
            }
        }

    }

    // check if the view has the highest height in a TableRow
    private boolean isTheHeighestLayout(TableRow tableRow, int layoutPosition) {

        int tableRowChildCount = tableRow.getChildCount();
        int heighestViewPosition = -1;
        int viewHeight = 0;

        for (int x = 0; x < tableRowChildCount; x++) {
            View view = tableRow.getChildAt(x);
            int height = this.viewHeight(view);

            if (viewHeight < height) {
                heighestViewPosition = x;
                viewHeight = height;
            }
        }

        return heighestViewPosition == layoutPosition;
    }

    // read a view's height
    private int viewHeight(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight();
    }

    // read a view's width
    private int viewWidth(View view) {
        view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    // horizontal scroll view custom class
    class MyHorizontalScrollView extends HorizontalScrollView {

        public MyHorizontalScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            String tag = (String) this.getTag();

            if (tag.equalsIgnoreCase("horizontal scroll view b")) {
                horizontalScrollViewD.scrollTo(l, 0);
            } else {
                horizontalScrollViewB.scrollTo(l, 0);
            }
        }

    }

    // scroll view custom class
    class MyScrollView extends ScrollView {

        public MyScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {

            String tag = (String) this.getTag();

            if (tag.equalsIgnoreCase("scroll view c")) {
                scrollViewD.scrollTo(0, t);
            } else {
                scrollViewC.scrollTo(0, t);
            }
        }
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
        headerCellsWidth = new int[headers.length];
    }

    public List<SampleObject> getSampleObjects() {
        return sampleObjects;
    }

    public void setSampleObjects(List<SampleObject> sampleObjects) {
        this.sampleObjects.clear();
        this.tableC.removeAllViews();
        this.tableD.removeAllViews();
//		this.sampleObjects = new ArrayList<SampleObject>();
        this.sampleObjects.addAll(sampleObjects);
    }


    public interface TableComponentClickLisenter {
        void onClick(TextView textView);
        void onClick(TextView textView,int index);
    }

    public void setTableComponentClickListener(TableComponentClickLisenter m) {
        mm = m;
    }



}
