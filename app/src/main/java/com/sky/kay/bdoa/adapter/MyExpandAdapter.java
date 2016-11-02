package com.sky.kay.bdoa.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.model.Department;
import com.sky.kay.bdoa.model.Employee;

import java.util.ArrayList;

/**
 * Created by kay on 2016/7/12.
 */
public class MyExpandAdapter implements ExpandableListAdapter {
    private Context context;
    LayoutInflater inflater;
    public MyExpandAdapter(Context context){
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initDatas();
    }

    private ArrayList<Department> departments=new ArrayList<>();

    private void initDatas() {
        Department department1=new Department("000001","技术部");
        Employee employee1=new Employee("name1","10086","10086","销售部","00001","11111@gdbds.net","技术部");
        Employee employee2=new Employee("name2","10086","10086","销售部","00001","11111@gdbds.net","技术部");
        Employee employee3=new Employee("name3","10086","10086","销售部","00001","11111@gdbds.net","技术部");
        Employee employee4=new Employee("name4","10086","10086","销售部","00001","11111@gdbds.net","技术部");
        ArrayList<Employee> employees=new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
    }

    int[] logos = new int[]{
//            R.drawable.word,
//            R.drawable.excel,
//            R.drawable.email,
//            R.drawable.ppt
    };
    private String[] armTypes = new String[]{
            "总经办", "管理部", "企划部", "采购部", "财务部", "营销部", "运营部", "软件研发部", "无线创新事业部", "系统集成部"
    };
    private String[][] arms = new String[][]{
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"},
            {"员工1", "员工2", "员工3", "员工4"}
    };



    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return armTypes.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arms[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return armTypes[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arms[groupPosition][childPosition];
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView=inflater.inflate(R.layout.expand_group,null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView
                    .findViewById(R.id.tv_group);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTextView .setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView=inflater.inflate(R.layout.expand_child,null);
            viewHolder = new ChildViewHolder();
            viewHolder.title = (TextView) convertView
                    .findViewById(R.id.title);
            viewHolder.tag=(ImageView)convertView.findViewById(R.id.tag);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }

        viewHolder.title .setText(getChild(groupPosition, childPosition).toString());
        viewHolder.tag.setImageResource(R.mipmap.id);
        return convertView;
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

    private TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(36, 0, 0, 0);
        textView.setTextSize(20);
        return textView;
    }

    private final class ViewHolder
    {
        TextView mTextView;
    }

    private final class ChildViewHolder{
        ImageView tag;
        TextView title;
    }

}
