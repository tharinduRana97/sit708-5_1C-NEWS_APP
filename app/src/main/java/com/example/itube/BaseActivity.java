package com.example.itube;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.itube.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base); // wraps everything

        // Inflate the child layout first
        getLayoutInflater().inflate(getLayoutId(), findViewById(R.id.base_content));

        // THEN bind the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (enableBackButton()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }


    // Let child classes supply the layout and back behavior
    protected abstract int getLayoutId();
    protected abstract boolean enableBackButton();
}
