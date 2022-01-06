package com.example.stock_app.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
                                    parentColumns = "id",
                                    childColumns = "userID",
                                    onDelete = CASCADE))
public class Stock {

    @PrimaryKey(autoGenerate = true)
    public int stockID;

    @ColumnInfo(name = "last_update")
    public long lastUpdate;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @ColumnInfo(name = "symbol")
    public String symbol;

    @ColumnInfo(name = "userID")
    public int userID;

    @ColumnInfo(name = "close")
    public double close;

    @ColumnInfo(name = "open")
    public double open;

    @ColumnInfo(name = "high")
    public double high;

    @ColumnInfo(name = "low")
    public double low;

    @ColumnInfo(name = "daily_change")
    public double dailyChange;

    @ColumnInfo(name = "last_month")
    public double lastMonthData;

    @ColumnInfo(name = "watchlist")
    public boolean addedToWatchlist;
}
