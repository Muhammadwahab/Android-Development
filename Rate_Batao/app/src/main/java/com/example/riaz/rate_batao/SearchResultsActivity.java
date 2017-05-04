package com.example.riaz.rate_batao;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {


    ListView listView;
    String Dummy[] = {"wahab", "wahab", "wahab", "wahab", "wahab"};
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;
    String searchData = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        handleIntent(getIntent());
        arrayList = new ArrayList();
        listView = (ListView) findViewById(R.id.inspirations);

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
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
            searchData = query;
            Toast.makeText(this, "" + searchData, Toast.LENGTH_SHORT).show();
            retrievePost();

            //use the query to search your data somehow
        }
    }

    public ArrayList retrievePost() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // database.setPersistenceEnabled(true);
        Intent intent = getIntent();
        int count = intent.getIntExtra("KEY", 1111);

        for (int i = 0; i < count; i++) {
            DatabaseReference databaseReference = database.getReference("users-" + i + "/identity/posts");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> childrenData = dataSnapshot.getChildren();
                    for (DataSnapshot child : childrenData) {
                        Iterable<DataSnapshot> postsData = child.getChildren();
                        // inner iteratable for post values
                        int childs = 0;
                        String ref = child.getRef().toString();
                        Post insertData = new Post();
                        for (DataSnapshot post : postsData) {

                            insertData.setPostReference(ref);

                            if (childs == 0) {
                                // DataSnapshot titleData=post.child("comments");
                                String value = post.getValue().toString();
                                insertData.setComments(value);
                                childs++;
                            } else if (childs == 1) {
//                                DataSnapshot titleData=post.child("discription");
                                String value = post.getValue().toString();
                                insertData.setPostDiscription(value);
                                childs++;
                            } else if (childs == 2) {
//                                DataSnapshot titleData=post.child("tile");
                                String titleRef = post.getRef().toString();
                                String value = post.getValue().toString();
                                insertData.setPostTitleReference(titleRef);
                                if (value.trim().equalsIgnoreCase(searchData.trim())) {
                                    arrayList.add(value);

                                }
                                childs++;
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return arrayList;
    }

}