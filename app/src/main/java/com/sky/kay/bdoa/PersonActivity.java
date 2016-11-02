package com.sky.kay.bdoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sky.kay.bdoa.model.PersonItem;
import com.sky.kay.bdoa.tool.Tools;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

public class PersonActivity extends AppCompatActivity {
    ArrayList<PersonItem> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initActionBar();
        initListView();
        initBottomView();


    }

    private void initBottomView() {
        ImageView image_left= (ImageView) findViewById(R.id.image_left);
        ImageView image_center=(ImageView)findViewById(R.id.image_center);
        ImageView image_right=(ImageView)findViewById(R.id.image_right);
        image_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.doSendSMSTo(PersonActivity.this,"10086");
            }
        });

        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.doCallPhone(PersonActivity.this,"10086");
            }
        });


    }

    private void initListView() {

        list.add(new PersonItem("姓名","张三"));
        list.add(new PersonItem("部门","销售部"));
        list.add(new PersonItem("座机号码","10086"));
        list.add(new PersonItem("手机号码","10086"));
        list.add(new PersonItem("邮箱","1111111@qq.com"));
        ListView listview= (ListView) findViewById(R.id.listView);
        listview.setAdapter(new CommonAdapter<PersonItem>(this,R.layout.listview_item_person,list) {
            @Override
            protected void convert(ViewHolder viewHolder, PersonItem item, int position) {
                viewHolder.setText(R.id.tv_key,item.key);
                viewHolder.setText(R.id.tv_value,item.value);
            }
        });

    }

    private void initActionBar() {
        getSupportActionBar().hide();
        Button topPanel_left= (Button) findViewById(R.id.topPanel_left);
        TextView topPanel_center=(TextView)findViewById(R.id.topPanel_center);
        topPanel_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topPanel_center.setText("详细信息");

    }


}
