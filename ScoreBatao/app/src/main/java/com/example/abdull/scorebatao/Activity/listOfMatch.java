package com.example.abdull.scorebatao.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abdull.scorebatao.R;

import Fragment.currentMatch;

public class listOfMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_match);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.currentMatch,new currentMatch());
        fragmentTransaction.commit();


        //getSupportFragmentManager().beginTransaction().add().commit();
    }
}
