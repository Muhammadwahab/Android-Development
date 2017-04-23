package com.example.abdull.database_asignment;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    ListView listView;
    String Dummy[]={"wahab","wahab","wahab","wahab","wahab"};
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    helper helper;
    String searchData="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        handleIntent(getIntent());

        listView= (ListView) findViewById(R.id.inspirations);
        helper=new helper(getApplicationContext());
        arrayList=helper.searchRecoid(searchData);

        arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
        Toast.makeText(getApplicationContext(), "On new intnent", Toast.LENGTH_LONG).show();

    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), "Search activity" + query, Toast.LENGTH_LONG).show();
            searchData=query;

            //use the query to search your data somehow
        }
    }

}