package com.sky.kay.bdoa.fragament;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sky.kay.bdoa.DiaryActivity;
import com.sky.kay.bdoa.ExamineActivity;
import com.sky.kay.bdoa.FlowActivity;
import com.sky.kay.bdoa.NoticeActivity;
import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.SignActivity;
import com.sky.kay.bdoa.model.ImageText;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

/**
 * Created by kay on 2016/7/11.
 */
public class HomeFragament extends Fragment {
    ArrayList<ImageText> mDatas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatas=new ArrayList<ImageText>();
        mDatas.add(new ImageText(R.mipmap.workdiary1,"工作日志"));
        mDatas.add(new ImageText(R.mipmap.apply1,"我的申请"));
        mDatas.add(new ImageText(R.mipmap.mail1,"我的邮件"));
        mDatas.add(new ImageText(R.mipmap.register1,"移动考勤"));
        mDatas.add(new ImageText(R.mipmap.check1,"审批事项"));
        mDatas.add(new ImageText(R.mipmap.announcement1,"系统公告"));
        mDatas.add(new ImageText(R.mipmap.node1,"记事本"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.page_home,null);
        GridView gridView= (GridView) view.findViewById(R.id.gridView1);
        gridView.setAdapter(new CommonAdapter<ImageText>(this.getActivity(), R.layout.grid_item, mDatas) {


            @Override
            protected void convert(ViewHolder viewHolder, ImageText item, int position) {

                viewHolder.setImageBitmap(R.id.image_griditem, BitmapFactory.decodeResource(getResources(),item.image));
                viewHolder.setText(R.id.text_griditem,item.text);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mDatas.get(i).text.equalsIgnoreCase("工作日志")){
                    startActivity(new Intent(getActivity(), DiaryActivity.class));

                }else if(mDatas.get(i).text.equalsIgnoreCase("我的申请")){
                    startActivity(new Intent(getActivity(), FlowActivity.class));
                }else if(mDatas.get(i).text.equalsIgnoreCase("我的邮件")){
//                    startActivity(new Intent(getActivity(), MailActivity.class));
                }else if(mDatas.get(i).text.equalsIgnoreCase("移动考勤")){
                    startActivity(new Intent(getActivity(),SignActivity.class));
                }else if(mDatas.get(i).text.equalsIgnoreCase("审批事项")){
                    startActivity(new Intent(getActivity(),ExamineActivity.class));
                }else if(mDatas.get(i).text.equalsIgnoreCase("系统公告")){
                    startActivity(new Intent(getActivity(), NoticeActivity.class));
                }else if(mDatas.get(i).text.equalsIgnoreCase("记事本")){

                }

            }
        });
        return view;

    }
}
