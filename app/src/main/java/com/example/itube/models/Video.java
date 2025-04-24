package com.example.itube.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String url;

    @ColumnInfo(name = "user_id")
    public int userId;
}

