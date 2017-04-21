package com.example.engruzma.mivoktranslation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView view=(TextView) findViewById(R.id.numbers);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, NumberActivity.class);
                startActivity(intent);

            }
        });
        view=(TextView) findViewById(R.id.colors);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);

            }
        });
        view=(TextView) findViewById(R.id.phrases);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, PhraseActivity.class);
                startActivity(intent);

            }
        });
        view=(TextView) findViewById(R.id.family);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(intent);

            }
        });

    }

}
