package com.example.newsapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news_items")
public class NewsItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public int imageRes;
    public String category;

    public NewsItem(String title, String description, int imageRes, String category) {
        this.title = title;
        this.description = description;
        this.imageRes = imageRes;
        this.category = category;
    }
}

