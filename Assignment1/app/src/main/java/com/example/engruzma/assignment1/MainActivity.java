package com.example.engruzma.assignment1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    SeekBar seak1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(TextView)findViewById(R.id.text1);
        text2=(TextView)findViewById(R.id.text2);
        text3=(TextView)findViewById(R.id.text3);
        text4=(TextView)findViewById(R.id.text4);
        text5=(TextView)findViewById(R.id.text5);
        text6=(TextView)findViewById(R.id.text6);
        text7=(TextView)findViewById(R.id.text7);
        text8=(TextView)findViewById(R.id.text8);
        seak1=(SeekBar)findViewById(R.id.seak1);

//on black
        seak1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              // Toast.makeText(getApplicationContext(),"you progress"+i,Toast.LENGTH_SHORT).show();
                text1.setBackgroundColor(Color.rgb(i,i,i));
                text3.setBackgroundColor(Color.rgb(i,i,i));
                text6.setBackgroundColor(Color.rgb(i,i,i));
                text8.setBackgroundColor(Color.rgb(i,i,i));
                text2.setBackgroundColor(Color.rgb(255-i,255-i,255-i));
                text4.setBackgroundColor(Color.rgb(255-i,255-i,255-i));
                text7.setBackgroundColor(Color.rgb(255-i,255-i,255-i));
                text5.setBackgroundColor(Color.rgb(255-i,255-i,255-i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
            }
}