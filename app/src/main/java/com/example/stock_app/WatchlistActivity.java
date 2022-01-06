package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.Stock;

import java.util.Arrays;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {

    public boolean[] inWatchlist;
    WatchlistRecyclerAdapter watchlistRecyclerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());

        RoomDB db = RoomDB.getDbInstance(this.getApplicationContext());

        // get all tickers that are on the watchlist
        List<Stock> watchlist = db.stockDao().stockList(appData.userID);

        inWatchlist = new boolean[appData.companySymbols.length];

        System.out.println(watchlist.size());

        if (!watchlist.isEmpty()) {

            for (Stock stock: watchlist) {

                // loop through appData symbols
                for (int i = 0; i < appData.companySymbols.length; i++) {

                    // if watchlist symbol equals appData symbol -> add a 1 in an array else do 0
                    inWatchlist[i] = appData.companySymbols[i].equals(stock.symbol);
                }
            }
        }
        Log.d("WATCHLIST CHECK", "Watchlist " + Arrays.toString(inWatchlist));

        recyclerView = findViewById(R.id.rv_watchlist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        watchlistRecyclerAdapter = new WatchlistRecyclerAdapter(this, appData.companyNames, appData.companyLogos,
                appData.price, appData.companySymbols, appData.dailyChange, appData.open, appData.high,
                appData.low, inWatchlist);

        recyclerView.setAdapter(watchlistRecyclerAdapter);
    }
}