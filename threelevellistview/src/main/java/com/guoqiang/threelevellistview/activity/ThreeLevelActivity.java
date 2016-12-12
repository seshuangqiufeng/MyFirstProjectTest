package com.guoqiang.threelevellistview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.guoqiang.threelevellistview.R;
import com.guoqiang.threelevellistview.adapter.GradeAdapter;
import com.guoqiang.threelevellistview.entity.Grade;
import com.guoqiang.threelevellistview.entity.NewClass;
import com.guoqiang.threelevellistview.entity.Student;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/11/3.
 */
public class ThreeLevelActivity extends Activity {

    private static final String TAG = ThreeLevelActivity.class.getSimpleName();
    ListView mExpandableListView;

    GradeAdapter mGradeAdapter;

    ArrayList<Grade> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threelevel);
        initView();
    }

    /**
     *
     */
    private void initView() {
        mExpandableListView = (ListView) this.findViewById(R.id.elv_listView);
        mGradeAdapter = new GradeAdapter(this,R.layout.item_firstlevel,getGradeList());
        mExpandableListView.setAdapter(mGradeAdapter);
        mGradeAdapter.notifyDataSetChanged();
    }



    public ArrayList<Grade> getGradeList(){

        lists.clear();

        for(int i = 0;i < 10;i++){
            ArrayList<NewClass> lll = new ArrayList<>();
            for(int j = 0; j< 3; j++){
                ArrayList<Student> students = new ArrayList<>();
                for(int k = 0;k<5;k++){
                    Student student = new Student("xiaoqiang"+k,"男",16+i);
                    students.add(student);
                }
                NewClass newClass = new NewClass("高三"+j+"班",students);
                lll.add(newClass);
            }
            Grade grade = new Grade("高三年级",i,lll);
            lists.add(grade);
        }

        Log.d(TAG,lists.toString());

        return lists;
    }

}
