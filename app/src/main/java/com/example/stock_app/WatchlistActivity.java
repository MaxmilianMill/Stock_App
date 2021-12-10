package com.example.stock_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.WatchlistItem;

import java.util.List;

public class WatchlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);
        // access the ApplicationData class where all important values are stored
        ApplicationData appData = ((ApplicationData) getApplicationContext());

        RoomDB db = RoomDB.getDbInstance(this.getApplicationContext());

        List<WatchlistItem> watchlist = db.watchlistDao().watchlist(appData.userID);

        System.out.println("Size: " + watchlist.size());
        System.out.println("Size App: " + appData.companySymbols.length);




    }
}