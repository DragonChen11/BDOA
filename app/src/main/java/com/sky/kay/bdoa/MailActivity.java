package com.sky.kay.bdoa;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.sky.kay.bdoa.model.App;
import com.sky.kay.bdoa.model.Mail;
import com.sky.kay.bdoa.model.MultiMailsender;
import com.sky.kay.bdoa.tool.EmailUtil;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MailActivity extends AppCompatActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.radio_receive)
    RadioButton radioReceive;
    @Bind(R.id.radio_send)
    RadioButton radioSend;
    @Bind(R.id.listView_mail)
    ListView listViewMail;



    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        app= (App) getApplication();
        ButterKnife.bind(this);
        radioReceive.setSelected(true);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        initUI();
        EmailUtil emailUtil=new EmailUtil(this);
        emailUtil.getImapEmail();

        if(app.mails.size()>0){
            listViewMail.setAdapter(new CommonAdapter<Mail>(this,R.layout.mail_item_receive,app.mails) {
                @Override
                protected void convert(ViewHolder viewHolder, Mail item, int position) {
                    viewHolder.setText(R.id.tv_sender_name,item.sender);
                    viewHolder.setText(R.id.tv_date,item.date);
                    viewHolder.setText(R.id.tv_title,item.title);
                }
            });
        }
        listViewMail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MailActivity.this,ReceiveDetailActivity.class);
                intent.putExtra("position",i);
                startActivity(intent);


            }
        });
   //     emailUtil.getEmail();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initUI() {

    }

    @OnClick({R.id.back, R.id.radio_receive, R.id.radio_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.radio_receive:
                break;
            case R.id.radio_send:
                break;
            default:
                break;
        }
    }

    private void sendEmail() {

            MultiMailsender.MultiMailSenderInfo mailInfo = new MultiMailsender.MultiMailSenderInfo();

            mailInfo.setMailServerHost("smtp.163.com");

            mailInfo.setMailServerPort("25");

            mailInfo.setValidate(true);

            mailInfo.setUserName("xxx@163.com");

            mailInfo.setPassword("**********");//您的邮箱密码

            mailInfo.setFromAddress("xxx@163.com");

            mailInfo.setToAddress("xxx@163.com");

            mailInfo.setSubject("设置邮箱标题");

            mailInfo.setContent("设置邮箱内容");

            String[] receivers = new String[]{"***@163.com", "***@tom.com"};

            String[] ccs = receivers; mailInfo.setReceivers(receivers);

            mailInfo.setCcs(ccs);

            //这个类主要来发送邮件

            MultiMailsender sms = new MultiMailsender();

            sms.sendTextMail(mailInfo);//发送文体格式

     //       MultiMailsender.sendHtmlMail(mailInfo);//发送html格式

            MultiMailsender.sendMailtoMultiCC(mailInfo);//发送抄送
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
