package com.sky.kay.bdoa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sky.kay.bdoa.model.Notice;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoticeDetailActivity extends Activity {

    @Bind(R.id.topPanel_left)
    Button topPanelLeft;
    @Bind(R.id.topPanel_center)
    TextView topPanelCenter;
    @Bind(R.id.topPanel_right)
    Button topPanelRight;
    @Bind(R.id.textView23)
    TextView textView23;
    @Bind(R.id.textView24)
    TextView textView24;
    @Bind(R.id.imageView9)
    ImageView imageView9;
    @Bind(R.id.textView25)
    TextView textView25;
    @Bind(R.id.textView26)
    TextView textView26;
    @Bind(R.id.imageView10)
    ImageView imageView10;
    @Bind(R.id.textView27)
    TextView textView27;

   Notice data=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);
        initView();
        initDatas();
    }

    private void initDatas() {
        data=(Notice)getIntent().getSerializableExtra("data");
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topPanelCenter.setText("公告详细");
        textView23.setText("发件人");
        textView24.setText(data.sender);
        textView25.setText(data.title);
        textView26.setText(data.time);
        textView27.setText(data.content);
    }

    private void initView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
