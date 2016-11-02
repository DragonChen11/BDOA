package com.sky.kay.bdoa.fragament;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sky.kay.bdoa.ImageHeadActivity;
import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.model.App;
import com.sky.kay.bdoa.model.ImageText;
import com.sky.kay.bdoa.tool.LoginSaveTools;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kay on 2016/7/11.
 */
public class MoreFragament extends Fragment {
    ArrayList<ImageText> list = new ArrayList<>();
    @Bind(R.id.image_head)
    ImageView imageHead;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_mail)
    TextView tvMail;
    @Bind(R.id.cancel)
    Button cancel;
    @Bind(R.id.exit)
    Button exit;
    LoginSaveTools ltool;
    App app;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        app= (App) getActivity().getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        list.add(new ImageText(R.mipmap.id, "我的资料"));
        list.add(new ImageText(R.mipmap.net_setup, "网络环境设置"));
        list.add(new ImageText(R.mipmap.pass_setup, "密码设置"));
        list.add(new ImageText(R.mipmap.delete, "清除缓存"));
        View view = inflater.inflate(R.layout.page_more, null);
        ButterKnife.bind(this,view);
        imageHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ImageHeadActivity.class);
                startActivity(intent);
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(new CommonAdapter<ImageText>(getActivity(), R.layout.listview_item_image_text, list) {
            @Override
            protected void convert(ViewHolder viewHolder, ImageText item, int position) {
                viewHolder.setImageResource(R.id.tag, item.image);
                viewHolder.setText(R.id.title, item.text);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ltool == null) {
                    ltool = new LoginSaveTools(getActivity());
                }
                ltool.removepwd(app.isLoginByVeh ? 0 : 1);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
