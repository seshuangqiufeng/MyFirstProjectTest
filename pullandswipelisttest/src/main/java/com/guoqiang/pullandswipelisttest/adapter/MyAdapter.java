package com.guoqiang.pullandswipelisttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.guoqiang.pullandswipelisttest.R;
import com.guoqiang.pullandswipelisttest.entity.UserSelf;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/12/1.
 */
public class MyAdapter extends BaseAdapter {

    private Context mCotext;
    private ArrayList<UserSelf> lists = new ArrayList<>();
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<UserSelf> lists) {
        this.mCotext = context;
        this.lists.clear();
        this.lists.addAll(lists);
        inflater = LayoutInflater.from(context);
    }

//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }

    @Override
    public int getViewTypeCount() {
        // menu type count
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        // current menu type
        return position % 3;
    }

    @Override
    public int getCount() {
        return this.lists.size();
    }

    @Override
    public UserSelf getItem(int i) {
        return this.lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null ;
        if(null == view){
            view = inflater.inflate(R.layout.item_userself,null);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) view.findViewById(R.id.item_name);
            viewHolder.mSchool = (TextView) view.findViewById(R.id.item_schoolname);
            viewHolder.mSex = (TextView) view.findViewById(R.id.item_sex);
            viewHolder.mAge = (TextView) view.findViewById(R.id.item_age);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        UserSelf userSelf = lists.get(i);
        viewHolder.mName.setText(userSelf.getName());
        viewHolder.mSchool.setText(userSelf.getSchoolName());
        viewHolder.mSex.setText(userSelf.getSex());
        viewHolder.mAge.setText(""+userSelf.getAge());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCotext,"item点击",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class ViewHolder {
        TextView mName;
        TextView mSchool;
        TextView mSex;
        TextView mAge;
    }
}
