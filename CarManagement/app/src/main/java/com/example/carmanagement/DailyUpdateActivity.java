package com.example.carmanagement;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class DailyUpdateActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        addPreferencesFromResource(R.layout.activity_daily_update);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean cbRefuel = prefs.getBoolean("cbRefuel", false);
        String etPayment = prefs.getString("etPayment", null);
        boolean sw = prefs.getBoolean("switch", false);
    }
}