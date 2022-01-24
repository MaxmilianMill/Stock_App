package com.example.stock_app.other;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.stock_app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

public class GoogleMaps_Activitiy extends FragmentActivity implements OnMapReadyCallback {

    boolean isPersmissionGranted;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_activitiy);
        //Funktionsaufruf
        checkMyPermission();
        //sobald bolean = true
        if (isPersmissionGranted) {
            //Aufruf xml fragment der map
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            //initializes the maps system and the view
            mapFragment.getMapAsync(this);
        }
    }

    private void checkMyPermission() {
        //call the dexter library that simplifies the process of requesting permissions at runtime
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                //if Permission granted, show text
                Toast.makeText(GoogleMaps_Activitiy.this, "Permission granted", Toast.LENGTH_SHORT).show();
                isPersmissionGranted = true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
            //open permission settings if denied/not accepted
                //start new intent
                Intent intent = new Intent();
                //open permission settings
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                //Creates an opaque Uri from the given components
                Uri uri = Uri.fromParts("package", getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
                //Permission Anfrage soll erneut gestellt werden
                permissionToken.continuePermissionRequest();
            }

        }).check();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);


    //Marker für Microsoft setzen
        //Längen-,Breitengrade initiieren
        LatLng Microsoft = new LatLng(50.92915063920853, 6.963219498306297);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(Microsoft).title("Microsoft"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(Microsoft));

    //Marker für Apple setzen
        //Längen-,Breitengrade initiieren
        LatLng Apple = new LatLng(48.14311149683813, 11.56481558289294);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(Apple).title("Apple"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(Apple));

    //Marker für Amazon setzen
        //Längen-,Breitengrade initiieren
        LatLng Amazon = new LatLng(48.183169776597346, 11.595640115487832);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(Amazon).title("Amazon"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(Amazon));

    //Marker für Tesla setzen
        //Längen-,Breitengrade initiieren
        LatLng Tesla = new LatLng(52.392658635258364, 13.541609300192011);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(Tesla).title("Tesla"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(Tesla));

    //Marker für NVIDIA setzen
        //Längen-,Breitengrade initiieren
        LatLng NVIDIA = new LatLng(50.80517583171322, 6.152707068259437);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(NVIDIA).title("NVIDIA"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(NVIDIA));

    //Marker für JPMorgan Chase setzen
        //Längen-,Breitengrade initiieren
        LatLng JPMorgan = new LatLng(50.110723267034686, 8.673333569449413);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(JPMorgan).title("JPMorgan Chase"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(JPMorgan));

    //Marker für VISA setzen
        //Längen-,Breitengrade initiieren
        LatLng VISA = new LatLng(50.11369838362454, 8.671767664197299);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(VISA).title("VISA"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(VISA));

    //Marker für PayPal setzen
        //Längen-,Breitengrade initiieren
        LatLng PayPal = new LatLng(52.40651378398821, 13.187217317846539);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(PayPal).title("PayPal"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(PayPal));

    //Marker für Procter & Gamble setzen
        //Längen-,Breitengrade initiieren
        LatLng PandG = new LatLng(50.143876657943025, 8.532740501436438);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(PandG).title("Procter & Gamble"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(PandG));

    //Marker für Mastercard setzen
        //Längen-,Breitengrade initiieren
        LatLng Mastercard = new LatLng(50.71073372717604, 4.40929293246353);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(Mastercard).title("Mastercard"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(Mastercard));

    //Marker für Walt Disney setzen
        //Längen-,Breitengrade initiieren
        LatLng WaltDisney = new LatLng(48.144539790579806, 11.53766533891783);
        //Marker auf der Map setzen für die Position
        map.addMarker(new MarkerOptions().position(WaltDisney).title("Walt Disney"));
        //Kamera bei Klick zentrieren
        map.moveCamera(CameraUpdateFactory.newLatLng(WaltDisney));
    }
}