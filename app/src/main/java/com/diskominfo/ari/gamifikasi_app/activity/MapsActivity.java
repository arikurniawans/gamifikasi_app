package com.diskominfo.ari.gamifikasi_app.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.diskominfo.ari.gamifikasi_app.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity {
    private GoogleMap mMap;
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    Location location;
    LocationManager locationManager;
    FloatingActionButton menu1,menu2,menu3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if ((ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

        }
        location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        current_position();

        menu1 = (FloatingActionButton)findViewById(R.id.subFloatingMenu1) ;
        menu2 = (FloatingActionButton)findViewById(R.id.subFloatingMenu2) ;
        menu3 = (FloatingActionButton)findViewById(R.id.subFloatingMenu3) ;

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this , " Alarm Icon clicked ", Toast.LENGTH_LONG).show();

            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this , "BackUp Icon clicked", Toast.LENGTH_LONG).show();

            }
        });

        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this , "Settings Icon clicked", Toast.LENGTH_LONG).show();

            }
        });
    }


    void current_position(){
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-5.397140, 105.266789), 12.0f));

                Criteria criteria = new Criteria();
                if ((ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
                    mMap.setMyLocationEnabled(true);
                }

                Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                if (location!=null){
                    LatLng MyLocation = new LatLng(location.getLatitude(), location.getLongitude());

                        mMap.getUiSettings().setZoomControlsEnabled(true);
                        mMap.getUiSettings().setZoomGesturesEnabled(true);
                        mMap.addMarker(new MarkerOptions().position(MyLocation).title("Posisi Anda").snippet(""+location.getLatitude()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MyLocation,13));



                }else{
                    Toast.makeText(getApplication(), "Lokasi Tidak Di temukan", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
