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

        for (Stock stock: watchlist) {
            
            for (int i = 0; i < appData.companySymbols.length; i++) {
                
                if (stock.symbol.equals(appData.companySymbols[i])) {

                    indexOfStock[watchlist.indexOf(stock)] = Arrays.asList(appData.companySymbols).indexOf(stock.symbol);
                    System.out.println(Arrays.asList(appData.companySymbols).indexOf(stock.symbol));

                    break;
                }
            }
        }

        recyclerView = findViewById(R.id.rv_watchlist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        watchlistRecyclerAdapter = new WatchlistRecyclerAdapter(this, appData.companyNames, appData.companyLogos,
                appData.price, appData.companySymbols, appData.dailyChange, appData.open, appData.high,
                appData.low, indexOfStock);

        recyclerView.setAdapter(watchlistRecyclerAdapter);
    }
}