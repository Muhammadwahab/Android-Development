package com.example.riaz.rate_batao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Gmail_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_login);
        Button gmbtn= (Button) findViewById(R.id.gmlogin);
        gmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmhomeintent=new Intent(Gmail_login.this,HomeActivity.class);
                startActivity(gmhomeintent);
            }
        });
    }
}
