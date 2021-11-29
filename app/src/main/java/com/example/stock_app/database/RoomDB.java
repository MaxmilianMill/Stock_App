package com.example.stock_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public abstract UserDao userDao();

    private static RoomDB INSTANCE;

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
