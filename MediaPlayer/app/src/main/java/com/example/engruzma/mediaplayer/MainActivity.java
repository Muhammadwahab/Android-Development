package com.example.engruzma.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(MainActivity.this, R.raw.color_black);
        Button play=(Button)findViewById(R.id.play);
        Button pause=(Button)findViewById(R.id.pause);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer=MediaPlayer.create(MainActivity.this, R.raw.color_black);

                Log.v("IS run","Error");
                mediaPlayer.start();

            }
        });
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                mediaPlayer.release();
//            }
//        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mediaPlayer.pause();

            }
        });

    }

}
