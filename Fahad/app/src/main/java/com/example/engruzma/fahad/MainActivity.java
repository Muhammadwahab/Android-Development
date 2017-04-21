package com.example.engruzma.fahad;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    TextView textViewOne,textViewTwo,textViewThree,textViewfour,root,textViewfive,textViewsix;
    Context context;
    int progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        seekBar=(SeekBar)findViewById(R.id.seek);
        textViewOne=(TextView)findViewById(R.id.one);
        textViewTwo=(TextView)findViewById(R.id.two);
        textViewThree=(TextView)findViewById(R.id.three);
        textViewfour=(TextView)findViewById(R.id.four);
        textViewfive=(TextView)findViewById(R.id.fivr);
        textViewsix=(TextView)findViewById(R.id.six);

       // textViewOne.setBackgroundColor(Color.rgb(111,111,222));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressBar=i;

                seekBar.setProgress(i);
                textViewOne.setBackgroundColor(Color.rgb(1+progressBar,22+progressBar,progressBar));
                textViewTwo.setBackgroundColor(Color.rgb(1+progressBar,progressBar+11,progressBar));
                textViewThree.setBackgroundColor(Color.rgb(progressBar+33,progressBar+11,198));
                textViewfour.setBackgroundColor(Color.rgb(111+progressBar,55+progressBar,progressBar));
                textViewfive.setBackgroundColor(Color.rgb(87+progressBar,55+progressBar,progressBar));
                textViewsix.setBackgroundColor(Color.rgb(22+progressBar,5+progressBar,11+progressBar));
              //  Toast.makeText(context,"progress "+progressBar,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"seekbar touch stop!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
