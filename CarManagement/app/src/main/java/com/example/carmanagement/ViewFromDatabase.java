package com.example.carmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewFromDatabase extends AppCompatActivity {

    private ListView listViewDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_from_database);

        listViewDatabase = findViewById(R.id.listViewDatabase);

        CarsDB instance = CarsDB.getInstance(getApplicationContext());
        List<CarInspection> carInspectionsFromDatabase = instance.getCarsInspectionsDao().getCarsWithInspections();
        LvCarInspectionsAdapter adapter = new LvCarInspectionsAdapter(getApplicationContext(), R.layout.elem_listview_vfd, carInspectionsFromDatabase, getLayoutInflater());
        listViewDatabase.setAdapter(adapter);
    }
}