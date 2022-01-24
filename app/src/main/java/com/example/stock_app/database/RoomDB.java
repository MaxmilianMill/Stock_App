package com.example.stock_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// create Room database
@Database(entities = {User.class, Stock.class}, version = 6, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    // call data accessing objects
    public abstract UserDao userDao();

    public abstract StockDao stockDao();

    // create instance of db
    private static RoomDB INSTANCE;

    // get existing instance or create new one
    public static RoomDB getDbInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, "DATABASE")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
