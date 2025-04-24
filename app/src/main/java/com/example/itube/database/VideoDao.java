package com.example.itube.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.itube.models.Video;

import java.util.List;

@Dao
public interface VideoDao {
    @Insert
    void insert(Video video);

    @Query("SELECT * FROM Video WHERE user_Id = :userId")
    List<Video> getVideosForUser(int userId);
}
