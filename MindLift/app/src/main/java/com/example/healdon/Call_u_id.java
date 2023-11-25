package com.example.healdon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import static android.provider.Telephony.Mms.Part.TEXT;
import static com.example.healdon.Signup.SHARED_PREFS;


public class Call_u_id extends AppCompatActivity {
    String u_id;
    public String get_u_id()
    {
       //SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        u_id=sharedPreferences.getString("id", "");
        return u_id;
    }
}
