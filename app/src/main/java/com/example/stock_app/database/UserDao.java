package com.example.stock_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// interface to call sql queries from java code
@Dao
public interface UserDao {

    @Insert
    void insertUser(User...user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user WHERE :email = email")
    List<User> selectFromEmail(String email);
}
