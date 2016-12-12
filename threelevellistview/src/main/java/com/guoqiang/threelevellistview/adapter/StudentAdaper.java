package com.guoqiang.threelevellistview.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.guoqiang.threelevellistview.R;
import com.guoqiang.threelevellistview.entity.NewClass;
import com.guoqiang.threelevellistview.entity.Student;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/11/3.
 */
public class StudentAdaper implements ExpandableListAdapter {

    private Context mContext;
    private ArrayList<NewClass> secondlist = new ArrayList<>();

    public StudentAdaper(Context mContext, ArrayList<NewClass> secondlist) {
        this.mContext = mContext;
        this.secondlist.clear();
        this.secondlist.addAll(secondlist);
    }


    //    public StudentAdaper(Context context, int layoutResId) {
//        super(context, layoutResId);
//    }
//
//    public StudentAdaper(Context context, int layoutResId, ArrayList<Student> data) {
//        super(context, layoutResId, data);
//        this.mContext = context;
//        this.secondlist.clear();
//        this.secondlist.addAll(data);
//    }
//
//    @Override
//    protected void convert(BaseAdapterHelper helper, Student item) {
//        TextView mName = helper.getView(R.id.tv_name);
//        TextView mAge = helper.getView(R.id.tv_age);
//        TextView mSex = helper.getView(R.id.tv_sex);
//        mName.setText(item.getName());
//        mAge.setText(item.getAge());
//        mSex.setText(item.getSex());
//    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return secondlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return secondlist.get(groupPosition).getStudents().size();
    }

    @Override
    public NewClass getGroup(int groupPosition) {
        return secondlist.get(groupPosition);
    }

    @Override
    public Student getChild(int groupPosition, int childPosition) {
        return secondlist.get(groupPosition).getStudents().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        NewClassViewHolder newClassViewHolder;
        if(convertView == null){
            newClassViewHolder = new NewClassViewHolder();
            convertView = View.inflate(mContext, R.layout.item_secondlevel,null);
            newClassViewHolder.mNewClass = (TextView) convertView.findViewById(R.id.tv_classname);
            convertView.setTag(newClassViewHolder);
        }else{
            newClassViewHolder = (NewClassViewHolder) convertView.getTag();
        }

        newClassViewHolder.mNewClass.setText(secondlist.get(groupPosition).getClassName());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBus.getDefault().post("ssssssss");
//            }
//        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        StudentViewHolder studentViewHolder;
        if(convertView == null){
            studentViewHolder = new StudentViewHolder();
            convertView = View.inflate(mContext, R.layout.item_secondlevel,null);
            studentViewHolder.mName = (TextView) convertView.findViewById(R.id.tv_name);
            studentViewHolder.mAge = (TextView) convertView.findViewById(R.id.tv_age);
            studentViewHolder.mSex = (TextView) convertView.findViewById(R.id.tv_sex);
            convertView.setTag(studentViewHolder);
        }else{
            studentViewHolder = (StudentViewHolder) convertView.getTag();
        }

        Student student = secondlist.get(groupPosition).getStudents().get(childPosition);

        studentViewHolder.mName.setText(student.getName());
        studentViewHolder.mAge.setText(""+student.getAge());
        studentViewHolder.mSex.setText(student.getSex());
        return convertView;
    }

    class NewClassViewHolder {
         TextView mNewClass;
    }

    class StudentViewHolder{
        TextView mName;
        TextView mAge;
        TextView mSex;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
