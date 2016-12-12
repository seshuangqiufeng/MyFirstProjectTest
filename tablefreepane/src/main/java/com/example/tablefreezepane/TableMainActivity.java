package com.example.tablefreezepane;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Table.SampleObject;
import com.Table.TableMainLayout;
import com.util.EntityCommonInterface;
import com.view.ChildTableLayout;

import java.util.ArrayList;
import java.util.List;

public class TableMainActivity extends Activity implements TableMainLayout.TableComponentClickLisenter, View.OnClickListener {

	private static final String TAG = TableMainActivity.class.getName();
	ChildTableLayout mTableLayout;
	private Button mBTn;

	private List<SampleObject > ssss = new ArrayList<SampleObject>();
	private List<EntityCommonInterface> ss = new ArrayList<EntityCommonInterface>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablefreepane);
		mTableLayout = (ChildTableLayout) findViewById(R.id.tml_layout);
		mBTn = (Button) findViewById(R.id.btn_btn);
		mBTn.setOnClickListener(this);

		ss.add(new SampleObject("34", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("35", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("36", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("37", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("38", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("39", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("40", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("41", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("42", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("43", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("44", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("45", "haha2haha1", "haha3haha1", "haha4hahhaha3haha1haha3haha11", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("46", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("1", "haha2haha3haha1haha3haha1haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("2", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("3", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("4", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1", "haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("5", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));

		ss.add(new SampleObject("17", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("18", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("19", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("20", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("21", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("22", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("23", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("24", "haha2haha1", "haha3haha1", "haha4haha3haha1haha3haha1haha3haha1haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("25", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("26", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("27", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("28", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("29", "haha2haha1", "haha3haha1haha3haha1haha3haha1haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("30", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("31", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("32", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("33", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("6", "haha2haha1", "haha3haha1", "haha4haha1", "haha5hahhaha3haha1haha3haha1a1", "haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("7", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("8", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("9", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("10", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("11", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("12", "haha2haha1", "haha3haha1", "haha4haha1", "hahaha3haha1haha3haha1ha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("13", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("14", "haha2haha1", "hahaha3haha1haha3haha1ha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("15", "haha2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));
		ss.add(new SampleObject("16", "hahhaha3haha1haha3haha1a2haha1", "haha3haha1", "haha4haha1", "haha5haha1","haha1haha1", "haha2haha1", "haha3", "haha4"));

		ssss.add(new SampleObject("总额","","","","","","","",""));



		initTableLayout();
//		setContentView(mTableLayout);
//		mTableLayout = (TableMainLayout) findViewById(R.id.tml_layout);
	}

	private void initTableLayout(){

		mTableLayout.setSingleLine(true);//设置不可折行
		mTableLayout.setTopFixedRowCount(2);//设置上面锁定的表头行数
		mTableLayout.setLeftFixedColumnCount(1);//设置左侧锁定的列数
		mTableLayout.setTitleHeight(40f);//设置表头的单行高度
		mTableLayout.setContentHeight(33f);//设置内容行单行高度
		mTableLayout.setMinWidth(66.6f);//设置单元格最小宽度
		mTableLayout.setTitleFontSize(13f);//设置表头的字体大小
		mTableLayout.setContentFontSize(12f);//设置内容行字体大小
		mTableLayout.setTableCellPadding(6f);//设置单元格内补边距
		mTableLayout.setMatchWidth(true);//设置默认宽度是否充满屏幕显示

		//指定某一行固定列宽
		float tableCellsFixDpWidth[] = new float[1];
		tableCellsFixDpWidth[0] = 66.6f;
		mTableLayout.setTableCellsFixDpWidth(tableCellsFixDpWidth);

		//按照百分比固定列宽
//        int tableCellsFixPercentWidth[] = {1, 3};
//        mTableLayout.setTableCellsFixPercentWidth(tableCellsFixPercentWidth);
		initEvent();
	}

	/**
	 *  设置数据
	 */
	private void initEvent() {
//		mTableLayout.setHeaders(headers);
		mTableLayout.setHeaders(new String[]{"haha1haha1", "haha1", "haha3haha1", "haha4haha1", "haha5haha1", "haha1haha1", "haha2haha1"});
		mTableLayout.setDataList(ss);
		mTableLayout.handleInvalidate();
	}


	@Override
	public void onClick(TextView textView) {
		textView.setTextColor(getResources().getColor(R.color.shop_color_blue));
		textView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
	}

	@Override
	public void onClick(TextView textView, int index) {

	}
	boolean flag = false;
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btn_btn){
			if(!flag){
				flag = true;
				Toast.makeText(this,"12",Toast.LENGTH_SHORT).show();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}else{
				flag = false;
				Toast.makeText(this,"34",Toast.LENGTH_SHORT).show();
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
		}
	}
}