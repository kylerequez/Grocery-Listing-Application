package com.itf3.group10.grocerylistapplication.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.itf3.group10.grocerylistapplication.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String PREF_FIRSTNAME = "prefFirstname";
    public static final String PREF_SURNAME = "prefSurname";
    public static final String PREF_THEME = "prefTheme";

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_settings);

        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals(PREF_FIRSTNAME)){
                    Preference prefFname = findPreference(key);

                    String firstName = getPreferenceScreen().getSharedPreferences().getString(PREF_FIRSTNAME, null).trim();
                    if(firstName.equals(null) || firstName.equals("")){
                        prefFname.setSummary("Please enter your first name");
                    } else {
                        prefFname.setSummary("Your first name is: " + firstName);
                    }
                }

                if(key.equals(PREF_SURNAME)){
                    Preference prefSname = findPreference(key);

                    String lastName = getPreferenceScreen().getSharedPreferences().getString(PREF_SURNAME, null).trim();
                    if(lastName.equals(null) || lastName.equals("")){
                        prefSname.setSummary("Please enter your surname");
                    } else {
                        prefSname.setSummary("Your surname is: " + lastName);
                    }
                }

                if(key.equals(PREF_THEME)){
                    Preference prefTheme = findPreference(key);

                    String theme = getPreferenceScreen().getSharedPreferences().getString(PREF_THEME, null);
                    if(theme.equals(null) || theme.equals("")){
                        prefTheme.setSummary("Please select a theme");
                    } else {
                        prefTheme.setSummary("The current theme is: " + theme);
                        loadTheme();
                    }
                }
            }
        };
    }

    @Override
    public void onResume(){
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference prefFname = findPreference(PREF_FIRSTNAME);
        String firstName = getPreferenceScreen().getSharedPreferences().getString(PREF_FIRSTNAME, null).trim();
        if(firstName.equals(null) || firstName.equals("")){
            prefFname.setSummary("Please enter your first name");
        } else {
            prefFname.setSummary("Your first name is: " + firstName);
        }

        Preference prefSname = findPreference(PREF_SURNAME);
        String lastName = getPreferenceScreen().getSharedPreferences().getString(PREF_SURNAME, null).trim();
        if(lastName.equals(null) || lastName.equals("")){
            prefSname.setSummary("Please enter your surname");
        } else {
            prefSname.setSummary("Your surname is: " + lastName);
        }

        Preference prefTheme = findPreference(PREF_THEME);

        String theme = getPreferenceScreen().getSharedPreferences().getString(PREF_THEME, null);
        if(theme.equals(null) || theme.equals("")){
            prefTheme.setSummary("Please select a theme");
        } else {
            prefTheme.setSummary("The current theme is: " + theme);
            loadTheme();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    public void loadTheme(){
        String theme = PreferenceManager.getDefaultSharedPreferences(this.getContext()).getString("prefTheme", "LIGHT");

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
