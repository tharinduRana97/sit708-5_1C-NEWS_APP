package com.example.newsapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.newsapp.models.NewsItem;
import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_items")
    List<NewsItem> getAllNews();

    @Query("SELECT * FROM news_items WHERE category = :category")
    List<NewsItem> getNewsByCategory(String category);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<NewsItem> newsItems);

    @Query("DELETE FROM news_items")
    void deleteAll();
}

