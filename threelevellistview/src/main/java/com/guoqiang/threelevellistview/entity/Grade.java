package com.guoqiang.threelevellistview.entity;

import java.util.ArrayList;

/**
 * Created by wangguoqiang on 2016/11/3.
 */
public class Grade {

    private String gradeName;
    private int colum;
    private ArrayList<NewClass> lists;


    public Grade() {
    }

    public Grade(String gradeName, int colum, ArrayList<NewClass> lists) {
        this.gradeName = gradeName;
        this.colum = colum;
        this.lists = lists;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getColum() {
        return colum;
    }

    public void setColum(int colum) {
        this.colum = colum;
    }

    public ArrayList<NewClass> getLists() {
        return lists;
    }

    public void setLists(ArrayList<NewClass> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeName='" + gradeName + '\'' +
                ", colum=" + colum +
                ", lists=" + lists +
                '}';
    }
}
