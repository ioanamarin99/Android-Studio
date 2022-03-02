package com.example.carmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_car_list:
                Intent intent1 = new Intent(getApplicationContext(), CarListActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_view_from_database:
                Intent intent2 = new Intent(getApplicationContext(), ViewFromDatabase.class);
                startActivity(intent2);
                break;
            case R.id.nav_auto_services_map:
                Intent intent3 = new Intent(getApplicationContext(), AutoServicesMap.class);
                startActivity(intent3);
                break;
            case R.id.nav_grafic:
                CarsDB instance = CarsDB.getInstance(getApplicationContext());
                List<Car> carList = instance.getCarsDao().getAll();

                ArrayList<Car> listCarsManualTr = new ArrayList<>();
                ArrayList<Car> listCarsAutomaticTr = new ArrayList<>();
                for(Car car:carList)
                    if(car.getTransmissionType().equals("Manual"))
                        listCarsManualTr.add(car);
                    else
                        listCarsAutomaticTr.add(car);

                Intent intent6 = new Intent(getApplicationContext(), GraficActivity.class);
                intent6.putExtra("listCarsManualTr", listCarsManualTr);
                intent6.putExtra("listCarsAutomaticTr", listCarsAutomaticTr);
                startActivity(intent6);
                break;
            case R.id.nav_daily_update:
                Intent intent4 = new Intent(getApplicationContext(), DailyUpdateActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_rate_us:
                Intent intent5 = new Intent(getApplicationContext(), RateOurApp.class);
                startActivity(intent5);
                break;
            case R.id.nav_exit:
                System.exit(0);
        }
        return true;
    }
}
