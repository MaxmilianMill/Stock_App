package com.example.stock_app.other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stock_app.R;
import com.example.stock_app.overview.OverviewActivity;
import com.example.stock_app.watchlist.WatchlistActivity;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    TextView greeting;
    MarketStackAPI api;

    // methods gets called when app is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // set the layout to the activity main xml file
        setContentView(R.layout.activity_main);
        // hide the upper app bar
        getSupportActionBar().hide();

        // allow certain settings that will enable the api to retrieve the data from the internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());


        // check if stock data was already updated in session
        if (!appData.alreadyUpdated) {

            // if it was not updated already --> update for every symbol
            for (int i = 0; i < appData.companySymbols.length; i++) {
                // create new object of api
                api = new MarketStackAPI();
                // call the connect method for every symbol in the array
                api.apiConnectDaily(appData.companySymbols[i]);
                // try the following statements
                try {
                    // add all data points into the arrays
                    appData.addPrice(String.valueOf(api.getClose()), i);
                    appData.addOpen(String.valueOf(api.getOpen()), i);
                    appData.addHigh(String.valueOf(api.getHigh()), i);
                    appData.addLow(String.valueOf(api.getLow()), i);
                    appData.addDailyChange(String.valueOf(api.getDailyChange()), i);
                    appData.addChartData(api.getChartData());

                    // handle json exceptions
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // if stock data is already downloaded --> don't reload in this session
            appData.alreadyUpdated = true;

            Log.d("LOAD STOCK DATA", "LOADING SUCCESSFUL");
        }

        greeting = findViewById(R.id.textHeader);

        greeting.setText("Willkommen, " + appData.firstName + "!");

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

        // Funktion ButtonMap
        // new button that is connected to the xml button with following id
        Button buttonMap = (Button) findViewById(R.id.button_map);
        // set an on click listener to button
        buttonMap.setOnClickListener(new View.OnClickListener() {
            // when button is clicked
            @Override
            public void onClick(View v) {
                if (isConnectedToInternet()) {
                    // when button is clicked --> call SearchStockActivity class
                    Intent i = new Intent(MainActivity.this, GoogleMaps_Activitiy.class);
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