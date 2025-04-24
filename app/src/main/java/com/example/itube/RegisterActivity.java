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

public class RegisterActivity extends BaseActivity {

    private EditText edtFullName, edtUsername, edtPassword, edtConfirmPassword;
    private Button btnCreateAccount;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Register");

        edtFullName = findViewById(R.id.edt_fullname);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        btnCreateAccount = findViewById(R.id.btn_create_account);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "itube-db")
                .allowMainThreadQueries()
                .build();

        btnCreateAccount.setOnClickListener(v -> {
            String fullName = edtFullName.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();

            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.fullName = fullName;
            user.username = username;
            user.password = password;

            db.userDao().insert(user);
            Toast.makeText(this, "Account created!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected boolean enableBackButton() {
        return true;
    }
}
