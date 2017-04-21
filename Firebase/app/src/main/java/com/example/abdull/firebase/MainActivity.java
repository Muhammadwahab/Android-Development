package com.example.abdull.firebase;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
// Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        final DatabaseReference myRef = database.getReference("Second Child");

        Button button=(Button)findViewById(R.id.database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // this is written data in firebase

               // myRef.child("First Child").setValue("wahab");
                myRef.child("Second Child").setValue("waqar");
                myRef.child("Third Child").setValue("waqas");




            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value= (String) dataSnapshot.getValue();
                Toast.makeText(getApplicationContext(),"data is "+value,Toast.LENGTH_LONG).show();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
