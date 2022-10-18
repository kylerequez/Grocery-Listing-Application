package com.itf3.group10.grocerylistapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.itf3.group10.grocerylistapplication.R;

public class AboutActivity extends AppCompatActivity {
    private Button btnAboutThis, btnTargetUsers , btnVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //Action Bar Editor
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("About");
        //Button Caller/import
        btnAboutThis = findViewById(R.id.btnAboutThis);
        btnTargetUsers = findViewById(R.id.btnTargetUsers);
        btnVersion = findViewById(R.id.btnVersion);
        //OnClick methods
        btnAboutThis.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intAboutThisApp = new Intent(getApplicationContext(),  AboutThisApplicationActivity.class);
                        startActivity(intAboutThisApp);
                    }
                }
        );
        btnTargetUsers.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intAboutTargetUsers = new Intent(getApplicationContext(),  AboutTargetUsersActivity.class);
                        startActivity(intAboutTargetUsers);
                    }
                }
        );
        btnVersion.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intAboutVersion = new Intent(getApplicationContext(),  AboutVersionActivity.class);
                        startActivity(intAboutVersion);
                    }
                }
        );
    }
}