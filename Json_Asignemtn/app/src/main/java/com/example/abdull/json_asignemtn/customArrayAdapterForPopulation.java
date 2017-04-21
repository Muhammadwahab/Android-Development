package com.example.abdull.json_asignemtn;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abdull on 4/15/17.
 */

public class customArrayAdapterForPopulation extends ArrayAdapter {

    ArrayList<populationData> List;
    public customArrayAdapterForPopulation(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<populationData> objects) {
        super(context, 0, objects);
        List=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    convertView= LayoutInflater.from(getContext()).inflate(R.layout.whether_listview_layout,parent,false);


        TextView nameCity= (TextView) convertView.findViewById(R.id.cityNameID);
        TextView fcodeName= (TextView) convertView.findViewById(R.id.fcodenameD);
        TextView population= (TextView) convertView.findViewById(R.id.populationID);

        //get Data form array list using positon
        populationData data= List.get(position);
//        // set text to fields
        nameCity.setText(data.getCityname());
        fcodeName.setText(data.getFcodename());
        population.setText(data.getPopulation());

//

        return  convertView;
    }
}
