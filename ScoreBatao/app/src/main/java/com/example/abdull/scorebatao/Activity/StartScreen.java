package com.example.abdull.scorebatao.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.abdull.scorebatao.R;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {

    Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        LoginButton=(Button)findViewById(R.id.loginID);
        LoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.equals(LoginButton))
        {
            Intent ListOFScreen=new Intent(StartScreen.this,listOfMatch.class);
            startActivity(ListOFScreen);
            finish();
            //Toast.makeText(getApplicationContext(),"Login Appear",Toast.LENGTH_LONG).show();
        }

    }
}
