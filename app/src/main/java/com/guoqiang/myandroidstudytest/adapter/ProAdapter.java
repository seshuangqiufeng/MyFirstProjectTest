package com.guoqiang.myandroidstudytest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.entity.ProEntity;
import com.guoqiang.myandroidstudytest.packageinterface.ItemClickListener;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/8/8.
 */
public class ProAdapter extends BaseAdapter {


    private ItemClickListener itemClickListener;

    private int index = -1;

    private Context mContext;
    private ArrayList<ProEntity> proList = new ArrayList<ProEntity>();

    public ProAdapter(Context mContext, ArrayList<ProEntity> proList) {
        this.mContext = mContext;
        this.proList = proList;
    }

    @Override
    public int getCount() {
        return proList.size();
    }

    @Override
    public Object getItem(int position) {
        return proList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(null == convertView){
            convertView = View.inflate(mContext, R.layout.item_first,null);
            viewHolder = new ViewHolder();
            viewHolder.mFirstName = (TextView) convertView.findViewById(R.id.tv_first);
            viewHolder.mSecondList = (ListView) convertView.findViewById(R.id.lv_second);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ProEntity p = proList.get(position);

        viewHolder.mFirstName.setText(p.getName());

        if(index == position) {
            NameAdapter nameAdapter = new NameAdapter(mContext, p.getList());
            viewHolder.mSecondList.setAdapter(nameAdapter);
            nameAdapter.notifyDataSetChanged();
//            setListViewHeightBasedOnChildren(viewHolder.mSecondList);
        }else{
            viewHolder.mSecondList.setVisibility(View.GONE);
        }

        viewHolder.mFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(position);
            }
        });

        return convertView;
    }


    class ViewHolder {
        private TextView mFirstName;
        private ListView mSecondList;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

//    public static void setListViewHeightBasedOnChildren(ListView listView) {
//
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            // pre-condition
//            return;
//        }
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            // listItem.measure(0, 0);
//            listItem.measure(
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight
//                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }

    /**
     * 设置listView自适应高度
     */
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}
