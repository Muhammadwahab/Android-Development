package com.example.abdull.mapintroducction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap m_map;
    boolean mapReady=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mapReady)
                {
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                }

            }
        });
        findViewById(R.id.btnSatellite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mapReady)
                {
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }

            }
        });
        findViewById(R.id.btnHybrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mapReady)
                {
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                }

            }
        });

        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady=true;
        m_map=googleMap;

        LatLng latLng=new LatLng(25.003127,67.056843);
        CameraPosition cameraPosition=CameraPosition.builder().target(latLng).zoom(50).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
