package com.itf3.group10.grocerylistapplication.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.itf3.group10.grocerylistapplication.R;
import com.itf3.group10.grocerylistapplication.fragments.TestRecyclerView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hides the Action Bar of the Activity
        ActionBar actBar = getSupportActionBar();
        actBar.hide();

        // Redirects to specific activity after 1 second/s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Main Activity Redirect
                Intent intApp = new Intent(getApplicationContext(),  MainActivity.class);
                startActivity(intApp);
            }
        }, 1000);
    }

    @Override
    public void onBackPressed(){
        finish();
    }

    public void loadTheme(){
        String theme = PreferenceManager.getDefaultSharedPreferences(this).getString("prefTheme", "LIGHT");

        switch(theme){
            case "LIGHT":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "DARK":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
}