package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import org.json.JSONException;

public class OverviewActivity extends AppCompatActivity {
    // new recyclerview object
    RecyclerView recyclerView;
    // new adapter object
    RecyclerAdapter adapter;
    // new API object
    MarketStackAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // set layout to overview activity
        setContentView(R.layout.overview_activity);
        // hide upper app bar
        getSupportActionBar().hide();
        // allow certain settings that will enable the api to retrieve the data from the internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData)getApplicationContext());

        // do this for every symbol
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

        // find the recyclerview by the id in the xml file
        recyclerView = findViewById(R.id.rv_overview);
        // set layout manager for recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // create new adapter for recyclerview and insert the required arrays in it
        adapter = new RecyclerAdapter(this, appData.companyNames, appData.companyLogos,
                appData.price, appData.companySymbols, appData.dailyChange, appData.open, appData.high,
                appData.low);
        // set the adapter
        recyclerView.setAdapter(adapter);
    }
}