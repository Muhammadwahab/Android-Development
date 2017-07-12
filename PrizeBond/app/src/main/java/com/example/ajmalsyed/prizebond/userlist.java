package com.example.ajmalsyed.prizebond;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {

    userDB userDB=new userDB(this);

    ArrayList Arraylist;
    Adapter userlistAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);


        Arraylist = userDB.DataDisplay();

        userlistAdapter = new Adapter(this, R.layout.usersequence, Arraylist);
        listView = (ListView) findViewById(R.id.userlistview);
        listView.setAdapter(userlistAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {



                AlertDialog.Builder builder = new AlertDialog.Builder(userlist.this);
                builder.setTitle("OPTION");
                builder.setIcon(R.drawable.deleteicon);
                builder.setMessage("Are You Delete This ?");
                builder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        userDB.DeleteSelectedlist(id);
                        Arraylist = userDB.DataDisplay();
                        userlistAdapter.clear();
                        userlistAdapter.addAll(Arraylist);
                        userlistAdapter.notifyDataSetChanged();
                        //  recreate();


                       // Toast.makeText(userlist.this, "ID is " + id, Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //for not delete
                    }
                });
                AlertDialog option = builder.create();
                option.show();
                return false;
            }


        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        switch (item.getItemId()){
            case R.id.deleteAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(userlist.this);
                builder.setTitle("DELETED ALL");
                builder.setIcon(R.drawable.deleteicon);
                builder.setMessage("Are you Sure to delete ?");
                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        userDB.deleteAlldata();
                        userlistAdapter.clear();
                    }
                });

                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //for not delete all
                    }
                });
                AlertDialog option = builder.create();
                option.show();
                // NavUtils.navigateUpFromSameTask(this);
                }
        return super.onOptionsItemSelected(item);
    }
}

