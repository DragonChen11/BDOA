package com.sky.kay.bdoa;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.sky.kay.bdoa.tool.Tools;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.MultiItemTypeAdapter;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import lib.homhomlib.design.SlidingLayout;

public class FlowItemActivity extends FragmentActivity {

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
    ArrayList<String> datas = new ArrayList<>();
    String type="";
 ArrayList<String> holidayType=new ArrayList<>();

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            // Do something with the date. This Date object contains
            // the date and time that the user has selected.
        }

        @Override
        public void onDateTimeCancel()
        {
            // Overriding onDateTimeCancel() is optional.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_item);
        ButterKnife.bind(this);
        type=getIntent().getStringExtra("type");
        initDatas();
        initListView();
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initListView() {
        if(type!=null){
            topPanelCenter.setText(type);
        }

        final String today= Tools.getToday2();
        listview.setAdapter(new CommonAdapter<String>(this, R.layout.list_item_text_edit, datas) {
            @Override
            public MultiItemTypeAdapter addItemViewDelegate(ItemViewDelegate<String> itemViewDelegate) {
                return super.addItemViewDelegate(itemViewDelegate);
            }

            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                if(item.indexOf("审批")!=-1){
                    viewHolder.getView(R.id.checkBox).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.editText2).setVisibility(View.GONE);
                    viewHolder.setText(R.id.tv_item, item);
                }else if(item.indexOf("日期")!=-1){
                    viewHolder.setText(R.id.tv_item, item);
                    viewHolder.getView(R.id.tv_date).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.tv_date,today);
                    viewHolder.getView(R.id.editText2).setVisibility(View.GONE);
                    viewHolder.getView(R.id.tv_date).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new SlideDateTimePicker.Builder(getSupportFragmentManager())
                                    .setListener(listener)
                                    .setInitialDate(new Date())
                                    .build()
                                    .show();
                        }
                    });
                }
                else if(item.indexOf("类别")!=-1){
                    viewHolder.setText(R.id.tv_item, item);
                    viewHolder.getView(R.id.spinner).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.editText2).setVisibility(View.GONE);
                    Spinner spinner=viewHolder.getView(R.id.spinner);
                    BaseAdapter adapter=new CommonAdapter<String>(FlowItemActivity.this,R.layout.list_item_text,holidayType) {
                        @Override
                        protected void convert(ViewHolder viewHolder, String item, int position) {
                           viewHolder.setText(R.id.tv_title,item);
                        }
                    };
                    spinner.setAdapter(adapter);

                }
                else {
                    viewHolder.setText(R.id.tv_item, item);
                }

            }
        });
    }

    private void initDatas() {
        datas.add("申请人");
        datas.add("职位");
        datas.add("所属部门");
        datas.add("职位代理人");
        datas.add("起始日期");
        datas.add("终止日期");
        datas.add("休假类别");
        datas.add("休假理由");
        datas.add("管理部审批");
        datas.add("部门经理审批");
        datas.add("分管领导审批");
        datas.add("执行副总审批");
        datas.add("总经理审批");

       holidayType.add("事假");
       holidayType.add("病假");
       holidayType.add("年假");
       holidayType.add("婚假");
       holidayType.add("调休");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
