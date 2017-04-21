package com.example.abdull.fragment_practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class first_fragment extends Fragment {

    String name[]={"wahab","wahab","wahab","wahab","wahab","wahab","wahab","wahab"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =LayoutInflater.from(getActivity()).inflate(R.layout.fragment,container,false);

        ListView listView=(ListView)view.findViewById(R.id.listItem);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);
        return view;
    }
}
