package com.example.abdull.json_asignemtn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
 * Created by abdull on 4/15/17.
 */

public class Population extends Fragment {

    String Dummy[]={"wahab"};
    ListView listView;
    ArrayList<populationData> arrayList;
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.from(getContext()).inflate(R.layout.pupulationactivity,container,false);
        volleyRequest();



        return view;
    }
    public void volleyRequest(){

        String responseLocal;

        Response.Listener listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.


                listView= (ListView) view.findViewById(R.id.whetherList);
                // ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,Dummy);
                customArrayAdapterForPopulation customArrayAdapter=new customArrayAdapterForPopulation(getActivity(),0,detData(response));
                listView.setAdapter(customArrayAdapter);
            }
        };


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        //  String url ="http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&date=2015-06-08&username=mwahab";
        String url="http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&date=2015-06-08&username=mwahab";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,listener
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), (CharSequence) error,Toast.LENGTH_LONG).show();

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public ArrayList detData(String response)
    {
        arrayList=new ArrayList();

        JSONTokener jsonTokener=new JSONTokener(response);

        try {
            JSONObject jsonObject= (JSONObject) jsonTokener.nextValue();
            JSONArray arrayOFEathQuakes=jsonObject.getJSONArray("geonames");
            Log.e("response","array of object is "+arrayOFEathQuakes);
            for (int i=0;i<arrayOFEathQuakes.length();i++)
            {
                JSONObject localData= (JSONObject) arrayOFEathQuakes.get(i);
                populationData data = new populationData();
                data.setCityname(localData.getString("name"));
                data.setFcodename(localData.getString("fcodeName"));
                data.setPopulation(localData.getLong("population")+"");
                arrayList.add(data);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
