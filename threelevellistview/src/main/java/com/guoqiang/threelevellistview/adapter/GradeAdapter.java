package com.guoqiang.threelevellistview.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoqiang.threelevellistview.R;
import com.guoqiang.threelevellistview.entity.Grade;
import com.guoqiang.threelevellistview.entity.NewClass;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/11/3.
 */
public class GradeAdapter extends QuickAdapter<Grade> {

    private Context mContext;
    private ArrayList<Grade> lists = new ArrayList<>();


    public GradeAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public GradeAdapter(Context context, int layoutResId, ArrayList<Grade> data) {
        super(context, layoutResId, data);
        this.mContext = context;
        this.lists.clear();
        lists.addAll(data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, Grade item) {
        final ExpandableListView mSecondListView = helper.getView(R.id.elv_second);
        TextView mGradeName = helper.getView(R.id.tv_gradeName);
        TextView mColum = helper.getView(R.id.tv_colum);
        mGradeName.setText(item.getGradeName());
        mColum.setText(""+item.getColum());
        final ArrayList<NewClass> secondList = item.getLists();
        final StudentAdaper studentAdaper = new StudentAdaper(mContext,secondList);
        mSecondListView.setAdapter(studentAdaper);
        for (int i = 0; i < secondList.size(); i++) {
            mSecondListView.expandGroup(i);
        }
        mSecondListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        (secondList.size()+1)*50 + 10);
                mSecondListView.setLayoutParams(lp);
            }
        });
    }


}
