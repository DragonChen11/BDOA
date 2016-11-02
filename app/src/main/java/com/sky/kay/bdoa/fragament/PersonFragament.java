package com.sky.kay.bdoa.fragament;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.sky.kay.bdoa.PersonActivity;
import com.sky.kay.bdoa.R;
import com.sky.kay.bdoa.adapter.MyExpandAdapter;

/**
 * Created by kay on 2016/7/11.
 */
public class PersonFragament extends Fragment {
    ExpandableListView expandableListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.page_person,null);
        expandableListView= (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListView.setGroupIndicator(this.getResources().getDrawable(R.drawable.selector_dropdown));
        MyExpandAdapter myExpandAdapter=new MyExpandAdapter(getActivity());
        expandableListView.setAdapter(myExpandAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent=new Intent(getActivity(), PersonActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return  view;
    }
}
