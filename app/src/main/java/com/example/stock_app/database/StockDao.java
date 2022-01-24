package com.example.stock_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// interface to call sql queries from java code
@Dao
public interface StockDao {

    @Insert
    void insertItem(Stock... stock);

    @Delete
    void deleteItem(Stock stock);

    @Query("SELECT * FROM Stock")
    List<Stock> selectAll();

    @Query("SELECT * FROM Stock WHERE userID = :userID")
    List<Stock> stockList(int userID);

    @Query("DELETE FROM Stock")
    void deleteAll();

    @Query("SELECT * FROM Stock WHERE userID = :userID AND watchlist = :addedToWatchlist")
    List<Stock> stockList(int userID, boolean addedToWatchlist);
}
