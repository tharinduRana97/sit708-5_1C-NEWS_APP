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
import com.example.itube.models.User;
import com.example.itube.utils.SessionManager;

public class LoginActivity extends BaseActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnSignup;
    private AppDatabase db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("iTube");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db")
                .allowMainThreadQueries()
                .build();

        sessionManager = new SessionManager(this);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            User user = db.userDao().login(username, password);

            if (user != null) {
                sessionManager.saveUserId(user.id);
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignup.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean enableBackButton() {
        return false; // no back on login screen
    }
}
