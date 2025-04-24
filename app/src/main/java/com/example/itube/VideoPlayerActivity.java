package com.example.itube;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.itube.R;

public class VideoPlayerActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Video Player Activity");
        webView = findViewById(R.id.youtube_webview);

        // Enable JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true); // optional but helpful

        // Ensure it opens in WebView, not browser
        webView.setWebViewClient(new WebViewClient());

        // Get YouTube URL
        String youtubeUrl = getIntent().getStringExtra("youtube_url");
        String videoId = extractYoutubeId(youtubeUrl);

        if (videoId != null) {
            String html = "<html><body style='margin:0;padding:0'>" +
                    "<iframe width='100%' height='100%' src='https://www.youtube.com/embed/" + videoId + "' " +
                    "frameborder='0' allowfullscreen></iframe>" +
                    "</body></html>";

            webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }

    private String extractYoutubeId(String url) {
        String pattern = "(?<=watch\\?v=|youtu.be/|embed/)[^#&?\\n]+";
        java.util.regex.Pattern compiledPattern = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = compiledPattern.matcher(url);
        return matcher.find() ? matcher.group() : null;
    }
}
