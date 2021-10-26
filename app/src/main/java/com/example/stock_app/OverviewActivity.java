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
    // String Array with all company names
    String companyNames[] = {"Amazon"}; // getResources().getStringArray(R.array.company_name);
    // String Array with all company symbols
    String companySymbols[] = {"AMZN"}; // getResources().getStringArray(R.array.ticker);
    // new int Array to store all logos
    int companyLogos[] = {R.drawable.amazon};
    // new String for the stock price with length of the symbols array
    String[] price = new String[companySymbols.length];
    // new String Array for the daily change in price with length of symbols array
    String[] dailyChange = new String[companySymbols.length];

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

        // do this for every symbol
        for (int i = 0; i < companySymbols.length; i++) {
            // create new object of api
            api = new MarketStackAPI();
            // call the connect method
            api.apiConnectDaily(companySymbols[i]);
            // try the following statements
            try {
                // retrieve the close price and store it in the price array
                price[i] = String.valueOf(api.getClose());
                // retrieve the daily change and store it in the price array
                dailyChange[i] = String.valueOf(api.getDailyChange());
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
        adapter = new RecyclerAdapter(this, companyNames, companyLogos,
                price, companySymbols, dailyChange);
        // set the adapter
        recyclerView.setAdapter(adapter);
    }
}