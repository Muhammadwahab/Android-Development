package com.example.abdull.glidelibrary;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String imgUrl = "http://www.thenewstribe.com/wp-content/uploads/2012/08/Shahid-Afridi-Ducks.jpg";
        ImageView imageView = (ImageView) findViewById(R.id.thumbnail);
        Glide.with(getApplicationContext()).load(imgUrl)
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        imgUrl = "https://en.dailypakistan.com.pk/wp-content/uploads/2017/04/640x437x227919.jpg.pagespeed.ic.vGytMSIUiM.jpg";
        imageView = (ImageView) findViewById(R.id.image_two);
        Glide.with(getApplicationContext()).load(imgUrl)
                .thumbnail(0.1f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
