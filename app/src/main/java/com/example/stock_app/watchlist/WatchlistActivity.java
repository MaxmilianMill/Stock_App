package com.example.stock_app.watchlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stock_app.other.ApplicationData;
import com.example.stock_app.R;
import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.Stock;

import java.util.Arrays;
import java.util.List;

public class WatchlistActivity extends AppCompatActivity {

    public Integer[] indexOfStock;
    WatchlistRecyclerAdapter watchlistRecyclerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        // hide upper app bar
        getSupportActionBar().hide();
        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());

        RoomDB db = RoomDB.getDbInstance(this.getApplicationContext());

        // get all tickers that are on the watchlist
        List<Stock> watchlist = db.stockDao().stockList(appData.userID, true);

        indexOfStock = new Integer[watchlist.size()];

        // for the stocks on watchlist --> check which index they have in application data class
        for (Stock stock: watchlist) {

            // compare watchlist stock with every stock in app data class
            for (int i = 0; i < appData.companySymbols.length; i++) {

                // if its equal --> add index to array and stop iteration
                if (stock.symbol.equals(appData.companySymbols[i])) {

                    indexOfStock[watchlist.indexOf(stock)] = Arrays.asList(appData.companySymbols).indexOf(stock.symbol);
                    System.out.println(Arrays.asList(appData.companySymbols).indexOf(stock.symbol));

                    break;
                }
            }
        }

        // initialize the recycler view
        recyclerView = findViewById(R.id.rv_watchlist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        watchlistRecyclerAdapter = new WatchlistRecyclerAdapter(this, appData.companyNames, appData.companyLogos,
                appData.price, appData.companySymbols, appData.dailyChange, appData.open, appData.high,
                appData.low, indexOfStock);

        recyclerView.setAdapter(watchlistRecyclerAdapter);
    }
}