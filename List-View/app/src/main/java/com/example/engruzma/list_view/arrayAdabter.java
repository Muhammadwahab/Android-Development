package com.example.engruzma.list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Engr.Uzma on 10/03/2017.
 */
public class arrayAdabter extends ArrayAdapter {


    public arrayAdabter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent,false);
        }
        return super.getView(position, convertView, parent);
    }
}
