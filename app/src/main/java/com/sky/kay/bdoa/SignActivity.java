package com.sky.kay.bdoa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky.kay.bdoa.tool.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignActivity extends Activity {


    @Bind(R.id.topPanel_left)
    Button topPanelLeft;
    @Bind(R.id.tv_weekend)
    TextView tvWeekend;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.topPanel_center)
    TextView topPanelCenter;
    @Bind(R.id.button3)
    Button button3;
    @Bind(R.id.include)
    RelativeLayout include;
    @Bind(R.id.textView7)
    TextView textView7;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.imageView5)
    ImageView imageView5;
    @Bind(R.id.imageView6)
    ImageView imageView6;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.textView10)
    TextView textView10;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.button2)
    Button button2;
    @Bind(R.id.imageView7)
    ImageView imageView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        tvDate.setText(Tools.getToday());
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textView8.setText("上午");
        textView9.setText("8:30-12:00");
        textView10.setText("下午");
        textView11.setText("1:30-5:30");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("已打卡");
                button.setBackgroundResource(R.drawable.button_shape_press2);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("已打卡");
                button.setBackgroundResource(R.drawable.button_shape_press2);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
