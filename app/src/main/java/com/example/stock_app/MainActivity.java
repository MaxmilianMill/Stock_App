package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    // methods gets called when app is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // set the layout to the activity main xml file
        setContentView(R.layout.activity_main);
        // hide the upper app bar
        getSupportActionBar().hide();

        // Funktion ButtonOverview
        // new button that is connected to the xml button with following id
        Button buttonOverview = (Button) findViewById(R.id.button_overview);
        // set an on click listener to button
        buttonOverview.setOnClickListener(new View.OnClickListener() {
            // when button is clicked
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()){
                // when button is clicked --> call OverviewActivity class
                Intent i = new Intent(MainActivity.this, OverviewActivity.class);
                // start
                startActivity(i);
                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });

        // Funktion ButtonSearchStock
        // new button that is connected to the xml button with following id
        Button buttonSearchStock = (Button) findViewById(R.id.button_detailpage);
        // set an on click listener to button
        buttonSearchStock.setOnClickListener(new View.OnClickListener() {
            // when button is clicked
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()) {
                    // when button is clicked --> call SearchStockActivity class
                    Intent i = new Intent(MainActivity.this, SearchStockActivity.class);
                    // start
                    startActivity(i);
                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });

        // Funktion ButtonWatchlist
        // new button that is connected to the xml button with following id
        Button buttonWatchList = (Button) findViewById(R.id.button_watchlist);
        // set an on click listener to button
        buttonWatchList.setOnClickListener(new View.OnClickListener() {
            // when button is clicked
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()) {
                    // when button is clicked --> call SearchStockActivity class
                    Intent i = new Intent(MainActivity.this, WatchlistActivity.class);
                    // start
                    startActivity(i);
                } else {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            }
        });

    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

}