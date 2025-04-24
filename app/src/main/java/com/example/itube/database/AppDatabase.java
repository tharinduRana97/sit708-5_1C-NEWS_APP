package com.example.itube.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.itube.models.User;
import com.example.itube.models.Video;

@Database(entities = {User.class, Video.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract VideoDao videoDao();
}
