package com.sky.kay.bdoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sky.kay.bdoa.model.Notice;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoticeActivity extends Activity {

    @Bind(R.id.topPanel_left)
    Button topPanelLeft;
    @Bind(R.id.topPanel_center)
    TextView topPanelCenter;
    @Bind(R.id.topPanel_right)
    Button topPanelRight;
    @Bind(R.id.listView2)
    ListView listView2;

    ArrayList<Notice> list=new ArrayList<>();
    BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        initView();
        initDatas();


    }

    private void initDatas() {
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topPanelCenter.setText("公告栏");
        Notice data1=new Notice();
        data1.time="2016-06-21 10:00";
        data1.title="表扬通告";
        data1.sender="管理部邮箱";
        data1.content="昨天下暴雨时，xxx第一时间冒着大雨，奋不顾身。。。";
        Notice data2=new Notice();
        data2.time="2016-06-21 10:00";
        data2.title="表扬通告";
        data2.sender="管理部邮箱";
        data2.content="昨天下暴雨时，xxx第一时间冒着大雨，奋不顾身。。。";
        Notice data3=new Notice();
        data3.time="2016-06-22 10:00";
        data3.title="表扬通告";
        data3.sender="管理部邮箱";
        data3.content="昨天下暴雨时，xxx第一时间冒着大雨，奋不顾身。。。";
        Notice data4=new Notice();
        data4.time="2016-06-23 10:00";
        data4.title="表扬通告";
        data4.sender="管理部邮箱";
        data4.content="昨天下暴雨时，xxx第一时间冒着大雨，奋不顾身。。。";
        Notice data5=new Notice();
        data5.time="2016-08-19 10:00";
        data5.title="表扬通告";
        data5.sender="管理部邮箱";
        data5.content="昨天下暴雨时，xxx第一时间冒着大雨，奋不顾身。。。";
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        adapter.notifyDataSetChanged();

    }

    private void initView() {

         adapter=new CommonAdapter<Notice>(this,R.layout.list_item_notice,list) {
            @Override
            protected void convert(ViewHolder viewHolder, Notice item, int position) {
                viewHolder.setText(R.id.textView5,item.time);
                viewHolder.setText(R.id.textView6,"新公告");
                viewHolder.setText(R.id.textView12,"标题");
                viewHolder.setText(R.id.textView19,item.title);
                viewHolder.setText(R.id.textView20,"发布人");
                viewHolder.setText(R.id.textView21,item.sender);
                viewHolder.setText(R.id.textView22,item.content);
            }
        };
        listView2.setAdapter(adapter);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notice data=list.get(i);
                Intent intent=new Intent(NoticeActivity.this,NoticeDetailActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }



}
