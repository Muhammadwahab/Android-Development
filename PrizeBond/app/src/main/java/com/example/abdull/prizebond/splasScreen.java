package com.example.abdull.prizebond;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splasScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas_screen);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
