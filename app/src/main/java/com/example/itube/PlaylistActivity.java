package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.itube.R;
import com.example.itube.adapters.PlaylistAdapter;
import com.example.itube.database.AppDatabase;
import com.example.itube.models.Video;
import com.example.itube.utils.SessionManager;

import java.util.List;

public class PlaylistActivity extends BaseActivity implements PlaylistAdapter.OnVideoClickListener {

    private RecyclerView recyclerView;
    private AppDatabase db;
    private SessionManager sessionManager;
    private PlaylistAdapter adapter;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("My Playlist");

        // Handle back button click
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        recyclerView = findViewById(R.id.recycler_playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db")
                .allowMainThreadQueries()
                .build();

        sessionManager = new SessionManager(this);
        userId = sessionManager.getUserId();

        List<Video> videoList = db.videoDao().getVideosForUser(userId);

        if (videoList.isEmpty()) {
            Toast.makeText(this, "No videos in playlist", Toast.LENGTH_SHORT).show();
        }

        adapter = new PlaylistAdapter(videoList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_playlist;
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }

    @Override
    public void onVideoClick(String url) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        intent.putExtra("youtube_url", url);
        startActivity(intent);
    }
}
