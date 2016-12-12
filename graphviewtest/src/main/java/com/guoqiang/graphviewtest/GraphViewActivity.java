package com.guoqiang.graphviewtest;

import android.app.Activity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by wangguoqiang on 2016/8/9.
 */
public class GraphViewActivity extends Activity {

    private GraphView mGvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphview);
        initView();
        initData();
    }

    /**
     *
     */
    private void initView() {
        mGvView = (GraphView) findViewById(R.id.gv_view);
    }

    /**
     *
     */
    private void initData() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                new DataPoint(1, 2.43),//小米5
//                new DataPoint(2, 1.10),//小米Note
//                new DataPoint(3, 1.35),//红米3s
//                new DataPoint(4, 2.46),//红米Note3
//                new DataPoint(5, 3.33),//小米4c
//                new DataPoint(6, 2.46),//小米Max
//                new DataPoint(7, 3.33)//小米4

                new DataPoint(2.20, 1),//小米5
                new DataPoint(1.10, 2),//小米Note
                new DataPoint(1.35, 3),//红米3s
                new DataPoint(2.46, 4),//红米Note3
                new DataPoint(0.90, 5),//小米4c
                new DataPoint(0.30, 6),//小米Max
                new DataPoint(1.80, 7)//小米4

//                new DataPoint(2.43, 0),
//                new DataPoint(1.10, 1),
//                new DataPoint(1.35, 2),
//                new DataPoint(2.46, 3),
//                new DataPoint(3.33, 4)
        });

        mGvView.addSeries(series);

        mGvView.getViewport().setYAxisBoundsManual(true);
        mGvView.getViewport().setMinY(0);
//        mGvView.getViewport().setMaxY(8);



        StaticLabelsFormatter st = new StaticLabelsFormatter(mGvView);
//        String[] s = new String[]{"50%","100%","150%","200%","250%","300%"};
//        st.setHorizontalLabels(s);
        mGvView.getViewport().setXAxisBoundsManual(true);
        mGvView.getViewport().setMinX(0);
        mGvView.getViewport().setMaxX(3);
        st.setVerticalLabels(new String[]{"","小米5","小米Note","红米3s","红米Note3","小米4c","小米Max","小米4",""});

        mGvView.getGridLabelRenderer().setLabelFormatter(st);
    }

}
