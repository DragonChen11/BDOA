package com.sky.kay.bdoa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import lib.homhomlib.design.SlidingLayout;

public class ExamineActivity extends Activity {

    ArrayList<String> datas = new ArrayList<>();
    @Bind(R.id.topPanel_left)
    Button topPanelLeft;
    @Bind(R.id.topPanel_center)
    TextView topPanelCenter;
    @Bind(R.id.topPanel_right)
    Button topPanelRight;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.slidingLayout)
    SlidingLayout slidingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        ButterKnife.bind(this);
        initDatas();
        initView();

    }

    private void initView() {
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topPanelCenter.setText("流程申批");
        listview.setAdapter(new CommonAdapter<String>(this,R.layout.list_item_text_right,datas) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.getView(R.id.imageView11).setVisibility(View.VISIBLE);
                viewHolder.setText(R.id.imageView11,position+"");
                viewHolder.setText(R.id.tv_item,item);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ExamineActivity.this,FlowItemActivity.class);
                intent.putExtra("type",datas.get(i));
                startActivity(intent);
            }
        });
    }

    private void initDatas() {
        datas.add("请假审批");
        datas.add("加班申批");
        datas.add("出差申批");
        datas.add("报销申批");
        datas.add("转正申批");
        datas.add("住宿申批");
        datas.add("调职申批");
        datas.add("招聘申批");
        datas.add("会议申批");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
