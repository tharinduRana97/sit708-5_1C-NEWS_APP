package com.example.itube.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveUserId(int id) {
        editor.putInt("user_id", id);
        editor.apply();
    }

    public int getUserId() {
        return prefs.getInt("user_id", -1);
    }
}

