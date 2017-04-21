package com.example.abdull.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_login extends AppCompatActivity implements View.OnClickListener {

    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_login);
        login= (Button) findViewById(R.id.loginButton);
        signup= (Button) findViewById(R.id.sigupButton);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==login.getId())
        {

        }
        else if(v.getId()==signup.getId())
        {
            startActivity(new Intent(getApplicationContext(),signup.class));

        }

    }
}
