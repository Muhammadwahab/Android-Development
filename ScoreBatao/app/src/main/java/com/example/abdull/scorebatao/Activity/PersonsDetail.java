package com.example.abdull.scorebatao.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abdull.scorebatao.R;

public class PersonsDetail extends AppCompatActivity {
    String name[]={"wahab","wahab","wahab","wahab","wahab","wahab"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_detailed);

        ListView listView=(ListView)findViewById(R.id.addPerson);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);
        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"ADd number",Toast.LENGTH_LONG).show();
                Intent addPerson=new Intent(getApplicationContext(),AddPersons.class);
                startActivity(addPerson);
                finish();

            }
        });
    }
}
