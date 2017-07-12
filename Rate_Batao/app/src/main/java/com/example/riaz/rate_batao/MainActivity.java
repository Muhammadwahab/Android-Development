package com.example.riaz.rate_batao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fbbtn= (Button) findViewById(R.id.fb);
        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbintent=new Intent(MainActivity.this,Fb_login.class);
                startActivity(fbintent);
            }
        });
        Button gmbtn=(Button)findViewById(R.id.gmail);
        gmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmintent=new Intent(MainActivity.this,Gmail_login.class);
                startActivity(gmintent);

            }
        });
        Button dbtn= (Button) findViewById(R.id.dailogbtn);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogclass dailog=new Dialogclass();
                dailog.show(getSupportFragmentManager(),"Posts");

            }
        });

    }
}
