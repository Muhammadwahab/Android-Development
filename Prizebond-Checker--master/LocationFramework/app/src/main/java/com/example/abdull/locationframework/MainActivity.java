package com.example.abdull.locationframework;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    private String Tag = "Location";
    TextView mlocation;
    private boolean isGPSEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlocation = (TextView) findViewById(R.id.location);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 3, this);
      //  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled;
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Toast.makeText(this, "Network "+isNetworkEnabled+lastKnownLocation.getLongitude(), Toast.LENGTH_LONG).show();


        int a=0;




    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(Tag,"onLocationChange");
        mlocation.setText(""+location.getProvider()+" \n"+ location.getLatitude()+"\n"+location.getLongitude());



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(Tag,"onStatus Change");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(Tag,"GPS Enable");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(Tag,"GPS Disable");

    }
}
