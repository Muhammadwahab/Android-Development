package com.example.riaz.rate_batao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fb_login extends AppCompatActivity {

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_login);

        Button fbbtn= (Button) findViewById(R.id.fblogin);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot key=dataSnapshot.child("count");
                 count=Integer.parseInt(key.getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
      //  database.goOffline();
        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fbhomeintent=new Intent(Fb_login.this,HomeActivity.class);
                fbhomeintent.putExtra("count",count);
                startActivity(fbhomeintent);
            }
        });
    }
}
