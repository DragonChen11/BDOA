package com.sky.kay.bdoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.sky.kay.bdoa.model.App;
import com.sky.kay.bdoa.model.ReceiveMail;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class ReceiveDetailActivity extends AppCompatActivity {
    App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_receive_detail);
        app= (App) getApplication();
        Intent intent=getIntent();
        int position=intent.getIntExtra("position",0);
        Message message=app.messages[position];
        String content= null;
        try {


//            content = message.getContent().toString();
//

            ReceiveMail mail=new ReceiveMail((MimeMessage) message);
            MimeMessage mes=(MimeMessage)message;
            mail.readContent(mes);
//            String type=mes.getContentType();
//            Part part=mes;
//            mail.getMailContent(part);
//            WebView webView= (WebView) findViewById(R.id.webView);
//            Log.v("AAA",mail.getBodyText());
//            webView.loadData(mail.getBodyText(),"text/html", "utf-8");
//            mail.mailReceiver(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
