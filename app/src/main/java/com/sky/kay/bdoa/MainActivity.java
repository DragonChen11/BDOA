package com.sky.kay.bdoa;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.kay.bdoa.fragament.HomeFragament;
import com.sky.kay.bdoa.fragament.MessageFragamet;
import com.sky.kay.bdoa.fragament.MoreFragament;
import com.sky.kay.bdoa.fragament.PersonFragament;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    FragmentManager manager;

    LinearLayout message_layout;
    LinearLayout person_layout;
    LinearLayout home_layout;
    LinearLayout commany_layout;
    LinearLayout more_layout;
    ImageView message_image;
    ImageView person_image;
    ImageView home_image;
    ImageView commany_image;
    ImageView more_image;
    TextView message_text;
    TextView person_text;
    TextView home_text;
    TextView commany_text;
    TextView more_text;
    TextView selected_text;
    EventBus eventBus;
    EventBus eventBus1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        eventBus1=new EventBus();
        eventBus1.register(this);
        initView();
    }

    private void initView() {
        message_layout = (LinearLayout) findViewById(R.id.message_layout);
        message_layout.setOnClickListener(this);
// commany_layout= (LinearLayout) findViewById(R.id.commany_layout);
// commany_layout.setOnClickListener(this);
        message_text = (TextView) findViewById(R.id.message_text);
        //    commany_text= (TextView) findViewById(R.id.commany_text);
        message_image = (ImageView) findViewById(R.id.message_image);
//        commany_image= (ImageView) findViewById(R.id.commany_image);
        manager = getFragmentManager();
        HomeFragament homeFragament = new HomeFragament();
        manager.beginTransaction().replace(R.id.content, homeFragament).commit();
        person_layout = (LinearLayout) findViewById(R.id.person_layout);
        home_layout = (LinearLayout) findViewById(R.id.home_layout);

        more_layout = (LinearLayout) findViewById(R.id.more_layout);

        person_layout.setOnClickListener(this);
        home_layout.setOnClickListener(this);

        more_layout.setOnClickListener(this);
        person_image = (ImageView) findViewById(R.id.person_image);
        home_image = (ImageView) findViewById(R.id.home_image);
        person_text = (TextView) findViewById(R.id.person_text);
        more_image = (ImageView) findViewById(R.id.more_image);
        home_text = (TextView) findViewById(R.id.home_text);
        more_text = (TextView) findViewById(R.id.more_text);
        home_image.setImageResource(R.mipmap.home2);
        selected_text = home_text;
        selected_text.setTextColor(getResources().getColor(R.color.cyan));

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        eventBus1.post(new Event(id));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Event event){
        int id=event.id;
        if(id==R.id.message_layout){
            message_image.setImageResource(R.mipmap.message2);
            int w=message_image.getWidth();
            person_image.setImageResource(R.mipmap.group1);
            home_image.setImageResource(R.mipmap.home1);
            //           commany_image.setImageResource(R.mipmap.commany_white);
            more_image.setImageResource(R.mipmap.more1);
            if(selected_text!=null){
                selected_text.setTextColor(getResources().getColor(R.color.cpb_gray));
            }
            selected_text=message_text;
            selected_text.setTextColor(getResources().getColor(R.color.cyan));
            MessageFragamet messageFragamet=new MessageFragamet();
            manager.beginTransaction().replace(R.id.content,messageFragamet).commit();
        }else if (id == R.id.home_layout) {
            message_image.setImageResource(R.mipmap.message1);
            person_image.setImageResource(R.mipmap.group1);
            home_image.setImageResource(R.mipmap.home2);
            //           commany_image.setImageResource(R.mipmap.commany_white);
            more_image.setImageResource(R.mipmap.more1);
            if (selected_text != null) {
                selected_text.setTextColor(getResources().getColor(R.color.cpb_gray));
            }
            selected_text = home_text;
            selected_text.setTextColor(getResources().getColor(R.color.cyan));
            HomeFragament homeFragament = new HomeFragament();
            manager.beginTransaction().replace(R.id.content, homeFragament).commit();
        } else if (id == R.id.person_layout) {
            message_image.setImageResource(R.mipmap.message1);
            person_image.setImageResource(R.mipmap.group2);
            home_image.setImageResource(R.mipmap.home1);
            //           commany_image.setImageResource(R.mipmap.commany_white);
            more_image.setImageResource(R.mipmap.more1);
            if (selected_text != null) {
                selected_text.setTextColor(getResources().getColor(R.color.cpb_gray));
            }
            selected_text = person_text;
            selected_text.setTextColor(getResources().getColor(R.color.cyan));
            PersonFragament personFragament = new PersonFragament();
            manager.beginTransaction().replace(R.id.content, personFragament).commit();
        }
//        else if(id==R.id.commany_layout){
//            message_image.setImageResource(R.mipmap.message_white);
//            person_image.setImageResource(R.mipmap.person_white);
//            home_image.setImageResource(R.mipmap.home_white);
//            commany_image.setImageResource(R.mipmap.commany_blue);
//            more_image.setImageResource(R.mipmap.more_gray);
//            if(selected_text!=null){
//                selected_text.setTextColor(getResources().getColor(R.color.cpb_gray));
//            }
//            selected_text=commany_text;
//            selected_text.setTextColor(getResources().getColor(R.color.sub_btn_normal));
//            CommanyFragament commanyFragament=new CommanyFragament();
//            manager.beginTransaction().replace(R.id.content,commanyFragament).commit();
//        }
        else if (id == R.id.more_layout) {
            message_image.setImageResource(R.mipmap.message1);
            person_image.setImageResource(R.mipmap.group1);
            home_image.setImageResource(R.mipmap.home1);
            //           commany_image.setImageResource(R.mipmap.commany_white);
            more_image.setImageResource(R.mipmap.more2);
            if (selected_text != null) {
                selected_text.setTextColor(getResources().getColor(R.color.cpb_gray));
            }
            selected_text = more_text;
            selected_text.setTextColor(getResources().getColor(R.color.cyan));
            MoreFragament moreFragament = new MoreFragament();
            manager.beginTransaction().replace(R.id.content, moreFragament).commit();
        }
    }

    class Event{
        int id;
        public Event(int id){
            this.id=id;
        }
    }
}
