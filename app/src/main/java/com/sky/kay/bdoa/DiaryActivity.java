package com.sky.kay.bdoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lib.homhomlib.design.SlidingLayout;

public class DiaryActivity extends FragmentActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.radio_receive)
    RadioButton radioReceive;
    @Bind(R.id.radio_send)
    RadioButton radioSend;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.slidingLayout)
    SlidingLayout slidingLayout;
    @Bind(R.id.iv_send)
    ImageView ivSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        ButterKnife.bind(this);
        initView();


    }

    private void initView() {
        radioReceive.setText("我的日志");
        radioSend.setText("他人日志");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.back, R.id.radio_receive, R.id.radio_send,R.id.iv_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.radio_receive:
                break;
            case R.id.radio_send:
                break;
            case R.id.iv_send:
             startActivity(new Intent(this,DiarySendActivity.class));
                break;
            default:
                break;
        }
    }
}
