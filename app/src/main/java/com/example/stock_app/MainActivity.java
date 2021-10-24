package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

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

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    MarketStackAPI api;

    String companyNames[] = {"Apple"};

    String companySymbols[] = {"AAPL"};

    int companyLogos[] = {R.drawable.apple};
    String[] price = new String[companySymbols.length];
    String[] dailyChange = new String[companySymbols.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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