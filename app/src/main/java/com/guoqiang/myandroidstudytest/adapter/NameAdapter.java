package com.guoqiang.myandroidstudytest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.myandroidstudytest.R;
import com.guoqiang.myandroidstudytest.entity.NameEntity;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/8/8.
 */
public class NameAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NameEntity> nameList = new ArrayList<NameEntity>();

    public NameAdapter(Context mContext, ArrayList<NameEntity> nameList) {
        this.mContext = mContext;
        this.nameList = nameList;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int position) {
        return nameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_second,null);
            viewHolder = new ViewHolder();
            viewHolder.viewline1 = convertView.findViewById(R.id.view_line1);
            viewHolder.viewline2 = convertView.findViewById(R.id.view_line2);
            viewHolder.viewline3 = convertView.findViewById(R.id.view_line3);
            viewHolder.mSecondName = (TextView) convertView.findViewById(R.id.tv_second);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(position == 0){
            viewHolder.viewline1.setVisibility(View.GONE);
            viewHolder.viewline2.setVisibility(View.VISIBLE);
        }else if(position == (nameList.size()-1)){
            viewHolder.viewline2.setVisibility(View.GONE);
        }

        final NameEntity nameEntity = nameList.get(position);
        viewHolder.mSecondName.setText(nameEntity.getName1()+"("+nameEntity.getNum()+")");
        viewHolder.mSecondName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"item name == "+nameEntity.getName1() + "   position  == "+position,Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    class ViewHolder {
        private View viewline1;
        private View viewline2;
        private View viewline3;
        private TextView mSecondName;
    }

}
