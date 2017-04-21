package com.example.abdull.fetchscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.provider.Browser;
import android.widget.TextView;
import android.provider.Browser;


public class permisionActivity extends AppCompatActivity {


    Button getBook, getoDeng;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permision);

        getBook = (Button) findViewById(R.id.RecieveBookmark);
        textView = (TextView) findViewById(R.id.text);

        getoDeng = (Button) findViewById(R.id.gotoDengerous);

        getoDeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(permisionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        getBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("Wahab\nWahab\nWahab\nWahabchutya\nWahab\nWahab\nWahab\nWahab\nWahab\nWahab\nWahab\nWahab");


            }
        });

    }

//


}
