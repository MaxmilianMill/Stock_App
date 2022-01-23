package com.example.stock_app.overview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stock_app.other.ApplicationData;
import com.example.stock_app.R;

public class OverviewActivity extends AppCompatActivity {
    // new recyclerview object
    RecyclerView recyclerView;
    // new adapter object
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // set layout to overview activity
        setContentView(R.layout.overview_activity);
        // hide upper app bar
        getSupportActionBar().hide();

        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());

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