package com.itf3.group10.grocerylistapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itf3.group10.grocerylistapplication.R;
import com.itf3.group10.grocerylistapplication.fragments.CalculatorFragment;
import com.itf3.group10.grocerylistapplication.fragments.ListFragment;
import com.itf3.group10.grocerylistapplication.fragments.SettingsFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navMenu;
    Fragment listFragment, calculatorFragment, settingsFragment, previousFragment, selectedFragment;
    FragmentManager fragmentManager;
    FrameLayout floFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greeting();

        floFragment = findViewById(R.id.floFragment);

        fragmentManager = getSupportFragmentManager();

        listFragment = new ListFragment();
        calculatorFragment = new CalculatorFragment();
        settingsFragment = new SettingsFragment();

        navMenu = findViewById(R.id.navMenu);
        navMenu.setOnNavigationItemSelectedListener(navListener);

        fragmentManager
                .beginTransaction()
                .add(floFragment.getId(), listFragment)
                .addToBackStack(null)
                .add(floFragment.getId(), calculatorFragment)
                .addToBackStack(null)
                .add(floFragment.getId(), settingsFragment)
                .addToBackStack(null)
                .hide(settingsFragment)
                .hide(calculatorFragment)
                .show(listFragment)
                .commit();
        selectedFragment = listFragment;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    previousFragment = selectedFragment;
                    switch(item.getItemId()){
                        case R.id.nav_list:
                            selectedFragment = listFragment;
                            break;
                        case R.id.nav_calculator:
                            selectedFragment = calculatorFragment;
                            break;
                        case R.id.nav_settings:
                            selectedFragment = settingsFragment;
                            break;
                    }
                    fragmentManager
                            .beginTransaction()
                            .hide(previousFragment)
                            .show(selectedFragment)
                            .commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onPause(){
        super.onPause();

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

    public void greeting(){
        String firstname = PreferenceManager.getDefaultSharedPreferences(this).getString("prefFirstname", null).trim();
        String surname = PreferenceManager.getDefaultSharedPreferences(this).getString("prefSurname", null).trim();

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if((!firstname.equals(null) || !surname.equals(null)) && (!firstname.equals("") || !surname.equals(""))) {
            if (timeOfDay >= 0 && timeOfDay < 12) {
                Toast.makeText(this, "Good Morning, " + firstname + " " + surname, Toast.LENGTH_SHORT).show();
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                Toast.makeText(this, "Good Afternoon, " + firstname + " " + surname, Toast.LENGTH_SHORT).show();
            } else if (timeOfDay >= 16 && timeOfDay < 24) {
                Toast.makeText(this, "Good Evening, " + firstname + " " + surname, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (timeOfDay >= 0 && timeOfDay < 12) {
                Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
            } else if (timeOfDay >= 12 && timeOfDay < 16) {
                Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
            } else if (timeOfDay >= 16 && timeOfDay < 24) {
                Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
            }
        }
    }
}