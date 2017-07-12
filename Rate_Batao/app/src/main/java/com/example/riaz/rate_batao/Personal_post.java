package com.example.riaz.rate_batao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by riaz on 5/1/17.
 */

public class Personal_post extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_post);

        Intent intent=getIntent();
        String title=intent.getStringExtra("titlte");

        TextView titleText= (TextView) findViewById(R.id.personalpost_id);
        titleText.setText(title);

        EditText editText= (EditText) findViewById(R.id.personalcomment_id);
        String comment=editText.getText().toString();

    }
}
