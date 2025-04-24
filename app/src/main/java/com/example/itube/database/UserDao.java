package com.example.itube.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.itube.models.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User login(String username, String password);
}