package com.example.stock_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WatchlistDao {

    @Insert
    void insertItem(WatchlistItem...watchlistItem);

    @Delete
    void deleteItem(WatchlistItem watchlistItem);

    @Query("SELECT * FROM watchlistitem WHERE userID = :userID")
    List<WatchlistItem> watchlist(int userID);

    @Query("DELETE FROM watchlistitem")
    void deleteAll();
}
