package com.example.abdull.json_asignemtn;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

/**
 * Created by abdull on 4/14/17.
 */

public class customArrayAdapter extends ArrayAdapter {
    ArrayList<EarthQUickData> arrayList;
    ArrayList<EarthQUickData> List;


    public customArrayAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<EarthQUickData> arra) {
        super(context, resource, arra);
        List = arra;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_layout, parent, false);
        }


        TextView depth = (TextView) convertView.findViewById(R.id.Earth_depthID);
        TextView longitude = (TextView) convertView.findViewById(R.id.Earth_longID);
        TextView latitude = (TextView) convertView.findViewById(R.id.Earth_latID);
        TextView City = (TextView) convertView.findViewById(R.id.cityID);
        TextView magnitude = (TextView) convertView.findViewById(R.id.Earth_magID);
        TextView dateAndTime = (TextView) convertView.findViewById(R.id.Earth_dateTImeID);
        //get Data form array list using positon
        EarthQUickData data = List.get(position);
//        // set text to fields
        longitude.setText(data.getLongitude());
        depth.setText(data.getDepth());
        latitude.setText(data.getLatitude());
        City.setText(data.getCity());
        magnitude.setText(data.getMagnuttude());
        dateAndTime.setText(data.getDateTime());
//


        return convertView;

    }

    public void volleyRequest() {

        String responseLocal;

        Response.Listener listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                detData(response);
            }
        };


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //  String url ="http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&date=2015-06-08&username=mwahab";
        String url = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=mwahab&date=2015-06-08";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), (CharSequence) error, Toast.LENGTH_LONG).show();

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    public void detData(String response) {
        arrayList = new ArrayList();
        Toast.makeText(getContext(), "arraylis" + arrayList, Toast.LENGTH_SHORT).show();

        JSONTokener jsonTokener = new JSONTokener(response);

        try {
            JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
            JSONArray arrayOFEathQuakes = jsonObject.getJSONArray("earthquakes");

            Log.e("response", "array of object is " + arrayOFEathQuakes);
            Toast.makeText(getContext(), "jsonarray" + arrayOFEathQuakes, Toast.LENGTH_LONG).show();


            for (int i = 0; i < arrayOFEathQuakes.length(); i++) {
                JSONObject localData = (JSONObject) arrayOFEathQuakes.get(i);
                EarthQUickData data = new EarthQUickData();
                data.setCity(localData.getString("src"));
                data.setDateTime(localData.getString("datetime"));
                data.setDepth(localData.getDouble("depth") + "");
                data.setLongitude(localData.getDouble("lng") + "");
                data.setLatitude(localData.getDouble("lat") + "");
                data.setMagnuttude(localData.getDouble("magnitude") + "");


                arrayList.add(data);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
