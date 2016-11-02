package com.sky.kay.bdoa.fragament;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.tool.Tools;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by kay on 2016/7/11.
 */
public class MessageFragamet extends Fragment {
    ListView listView;
    ArrayList<MessageItem> list=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_message, null);
        listView= (ListView) view.findViewById(R.id.listview);
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        list.add(new MessageItem());
        listView.setAdapter(new CommonAdapter<MessageItem>(getActivity(),R.layout.page_message_item,list) {
            @Override
            protected void convert(ViewHolder viewHolder, MessageItem item, int position) {
                viewHolder.setText(R.id.textView,"test");
                viewHolder.setText(R.id.textView2,"test");
                viewHolder.setText(R.id.textView3,(position+90)+"");
                Date date= new Date();
                Date date1=new Date(2016,0,2,12,24);
                Date date2=new Date(2016,7,10,8,24);
                String time="";
                if(position % 2==0){
                    time= Tools.showTime(date1);
                }else if(position % 2==1){
                    time= Tools.showTime(date2);
                }
                viewHolder.setText(R.id.textView4,time);
            }
        });
        return  view;
    }
    class MessageItem {
        public String title;
        public String subTitle;
        public String number;
    }
}
