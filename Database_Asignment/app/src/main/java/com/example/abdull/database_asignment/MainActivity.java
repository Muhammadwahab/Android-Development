package com.example.abdull.database_asignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, yourInspiration;
    Button insert, showRecord;
    String nameData, inspirationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameID);

        yourInspiration = (EditText) findViewById(R.id.inspirrationID);
        insert = (Button) findViewById(R.id.Insert);
        showRecord = (Button) findViewById(R.id.showRecord);
        showRecord.setOnClickListener(this);
        insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        helper helper = new helper(getApplicationContext());
        if (v.getId() == insert.getId()) {
            nameData = name.getText().toString();
            inspirationData = yourInspiration.getText().toString();

            Toast.makeText(getApplicationContext(), "Name is " + nameData + "\n Inpiration is " + inspirationData, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "id is " + helper.addInspiration(nameData, inspirationData), Toast.LENGTH_LONG).show();

        } else

        {
            startActivity(new Intent(getApplicationContext(), inspirationList.class));
            finish();
        }
    }
}
