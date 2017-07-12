package com.example.ajmalsyed.prizebond;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ajmal Syed on 6/8/2017.
 */
public class Adapter extends ArrayAdapter<UserInstance> {
    ArrayList<UserInstance> userarraylist;
    public Adapter(Context context, int resource, ArrayList<UserInstance> userarraylist) {
        super(context,resource,userarraylist);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitem=convertView;

        if(listitem==null){
            listitem= LayoutInflater.from(getContext()).inflate(R.layout.usersequence,parent,false);
        }

        UserInstance currentlist=getItem(position);

        TextView name= (TextView) listitem.findViewById(R.id.nametext);
        name.setText(currentlist.getName());
        TextView number= (TextView) listitem.findViewById(R.id.numbertext);
        number.setText(currentlist.getNumber());
        TextView email= (TextView) listitem.findViewById(R.id.drawnumbertext);
        email.setText(currentlist.getDrawnumber());


        return listitem;
    }}