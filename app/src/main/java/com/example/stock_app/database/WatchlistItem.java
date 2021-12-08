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
public class WatchlistItem {

    @PrimaryKey(autoGenerate = true)
    public int watchlistID;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @ColumnInfo(name = "symbol")
    public String symbol;

    @ColumnInfo(name = "userID")
    public int userID;

}
