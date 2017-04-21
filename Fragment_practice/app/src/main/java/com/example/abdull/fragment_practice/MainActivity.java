package com.example.abdull.fragment_practice;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment1,new first_fragment()).commit();

         fragmentManager=getSupportFragmentManager();
         fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment2,new first_fragment()).commit();
    }
}
