package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import org.json.JSONException;

public class OverviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    MarketStackAPI api;

    String companyNames[] = {"Amazon"}; // getResources().getStringArray(R.array.company_name);

    String companySymbols[] = {"AMZN"}; // getResources().getStringArray(R.array.ticker);

    int companyLogos[] = {R.drawable.amazon};
    String[] price = new String[companySymbols.length];
    String[] dailyChange = new String[companySymbols.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview_activity);
        getSupportActionBar().hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        for (int i = 0; i < companySymbols.length; i++) {

            api = new MarketStackAPI();
            api.apiConnectDaily(companySymbols[i]);
            try {
                price[i] = String.valueOf(api.getClose());
                dailyChange[i] = String.valueOf(api.getDailyChange());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView = findViewById(R.id.rv_overview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter(this, companyNames, companyLogos,
                price, companySymbols, dailyChange);
        recyclerView.setAdapter(adapter);
    }
}