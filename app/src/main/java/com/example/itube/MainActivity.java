package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.itube.R;
import com.example.itube.database.AppDatabase;
import com.example.itube.models.Video;
import com.example.itube.utils.SessionManager;

public class MainActivity extends BaseActivity {

    private EditText edtYoutubeUrl;
    private Button btnPlay, btnAddToPlaylist, btnMyPlaylist;

    private AppDatabase db;
    private SessionManager sessionManager;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("iTube");
        edtYoutubeUrl = findViewById(R.id.edt_youtube_url);
        btnPlay = findViewById(R.id.btn_play);
        btnAddToPlaylist = findViewById(R.id.btn_add_to_playlist);
        btnMyPlaylist = findViewById(R.id.btn_my_playlist);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db")
                .allowMainThreadQueries()
                .build();

        sessionManager = new SessionManager(this);
        userId = sessionManager.getUserId();

        btnPlay.setOnClickListener(v -> {
            String url = edtYoutubeUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                Intent intent = new Intent(this, VideoPlayerActivity.class);
                intent.putExtra("youtube_url", url);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter a YouTube URL", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddToPlaylist.setOnClickListener(v -> {
            String url = edtYoutubeUrl.getText().toString().trim();
            if (!url.isEmpty()) {
                Video video = new Video();
                video.url = url;
                video.userId = userId;
                db.videoDao().insert(video);
                Toast.makeText(this, "Added to playlist!", Toast.LENGTH_SHORT).show();
                edtYoutubeUrl.setText("");
            } else {
                Toast.makeText(this, "URL cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        btnMyPlaylist.setOnClickListener(v -> {
            startActivity(new Intent(this, PlaylistActivity.class));
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }
}
