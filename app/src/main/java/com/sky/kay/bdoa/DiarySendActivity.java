package com.sky.kay.bdoa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiarySendActivity extends Activity {

    @Bind(R.id.topPanel_left)
    Button topPanelLeft;
    @Bind(R.id.topPanel_right)
    Button topPanelRight;
    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.textView17)
    TextView textView17;
    @Bind(R.id.textView18)
    TextView textView18;
    @Bind(R.id.editText)
    EditText editText;
    @Bind(R.id.textView16)
    TextView textView16;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.imageView2)
    ImageView imageView2;
    @Bind(R.id.imageView3)
    ImageView imageView3;
    @Bind(R.id.imageView4)
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_send);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        topPanelLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topPanelRight.setBackgroundResource(R.mipmap.choose_person_checked);
        topPanelRight.setVisibility(View.VISIBLE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String date = sdf.format(d);
        textView14.setText(date + "工作日志");
        textView16.setText(date);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
