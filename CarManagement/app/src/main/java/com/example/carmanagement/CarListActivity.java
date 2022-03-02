package com.example.carmanagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 200;

    private ListView lvCars;
    List<Car> carList = new ArrayList<Car>();

    public int poz;
    public static final int REQUEST_EDIT_CODE = 300;
    public static final String EDIT_CODE = "editCar";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_car_list);

        lvCars = findViewById(R.id.listViewCars);
//           EDITARE
        lvCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                intent.putExtra(EDIT_CODE, carList.get(position));
                startActivityForResult(intent, REQUEST_EDIT_CODE);
            }
        });
//                    STERGERE
        lvCars.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Car car = carList.get(position);
                final LvCarAdapter adapter = (LvCarAdapter) lvCars.getAdapter();

                AlertDialog alertDialog = new AlertDialog.Builder(CarListActivity.this)
                        .setTitle("Delete confirmation")
                        .setMessage("Are you sure?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "No car deleted!", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                carList.remove(car);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                               DatabaseReference myRef = database.getReference("carmanagement-3edbe-default-rtdb");
                               myRef.child("carmanagement-3edbe-default-rtdb").child(car.getUid()).removeValue();

                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "The car was deleted succsessfully!", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .create();

                alertDialog.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_car_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                //ADAUGAREE
                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.item2:
                ExtractCarsJSON extractCarsJSON = new ExtractCarsJSON(){

                    @Override
                    protected void onPostExecute(String s) {
                        carList.addAll(ExtractCarsJSON.carsList);

                        LvCarAdapter adapter = new LvCarAdapter(getApplicationContext(), R.layout.elem_listview_car, carList, getLayoutInflater());

                        lvCars.setAdapter(adapter);
                    }
                };
                try {
                    extractCarsJSON.execute(new URL("https://pastebin.com/raw/BMsHcSpH"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.item3:
                Intent intent2 = new Intent(getApplicationContext(), ViewFromDatabase.class);
                startActivity(intent2);
                break;
            case R.id.item4:
                AlertDialog alertDialog = new AlertDialog.Builder(CarListActivity.this)
                        .setTitle("Delete confirmation")
                        .setMessage("Are you sure?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "No data deleted from local database!", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CarsDB instance = CarsDB.getInstance(getApplicationContext());
                                instance.getInspectionsDao().deleteAll();
                                instance.getCarsDao().deleteAll();

                                Toast.makeText(getApplicationContext(), "All data from local database was deleted succsessfully!", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        })
                        .create();

                alertDialog.show();
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data != null){
            Car car = (Car)data.getSerializableExtra(AddCarActivity.ADD_CAR);
            if(car != null){
                carList.add(car);

                //ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(getApplicationContext(), android.R.layout.simple_list_item_1, carList);
                LvCarAdapter adapter = new LvCarAdapter(getApplicationContext(), R.layout.elem_listview_car, carList, getLayoutInflater());

                lvCars.setAdapter(adapter);
            }
        }

        if(requestCode==REQUEST_EDIT_CODE && resultCode==RESULT_OK && data != null){
            Car car = (Car)data.getSerializableExtra(AddCarActivity.ADD_CAR);
            if(car != null){
                carList.get(poz).setName(car.getName());
                carList.get(poz).setBrand(car.getBrand());
                carList.get(poz).setModel(car.getModel());
                carList.get(poz).setPurchaseDate(car.getPurchaseDate());
                carList.get(poz).setPrice(car.getPrice());
                carList.get(poz).setMotorType(car.getMotorType());
                carList.get(poz).setTransmissionType(car.getTransmissionType());
                carList.get(poz).setKilometers(car.getKilometers());

                LvCarAdapter adapter = new LvCarAdapter(getApplicationContext(), R.layout.elem_listview_car, carList, getLayoutInflater());

                lvCars.setAdapter(adapter);
            }
        }
    }

    protected void onStart() {
        super.onStart();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("carmanagement-3edbe-default-rtdb");
        myRef.keepSynced(true);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    carList.clear();
                    for(DataSnapshot dn: snapshot.getChildren()){
                        Car car = dn.getValue(Car.class);
                        carList.add(car);
                    }
                }
                LvCarAdapter adapter = new LvCarAdapter(getApplicationContext(), R.layout.elem_listview_car, carList, getLayoutInflater());

                lvCars.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.child("carmanagement-3edbe-default-rtdb").addValueEventListener(listener);
    }
}
