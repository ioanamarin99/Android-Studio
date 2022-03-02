package com.example.carmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class AddCarActivity extends AppCompatActivity {

    public static final String ADD_CAR = "addCar";

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        final EditText etName = findViewById(R.id.editTextName);


        //****************AUTOCOMPLETE*************************************8
        String[] brandName = getResources().getStringArray(R.array.brand_name);
        final AutoCompleteTextView etBrand = findViewById(R.id.autoCompleteTextBrand);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, brandName);
        etBrand.setAdapter(adapter1);
//*****************************************************************************
        final EditText etModel = findViewById(R.id.editTextModel);
        final EditText etDate = findViewById(R.id.editTextDate);

        //********SPINNER************************************************
        final Spinner spinnerMotorType = findViewById(R.id.spinnerMotorType);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.motor_type, R.layout.support_simple_spinner_dropdown_item);
        spinnerMotorType.setAdapter(adapter2);
//*********************************************************************************
        //*******************RADIOGROUP*****************************************8
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
//*****************************************************************************
        final EditText etPrice = findViewById(R.id.editTextPrice);
        final EditText etKilometers = findViewById(R.id.editTextKilometers);

        final Intent intent = getIntent();

//                             EDITARE
        if(intent.hasExtra(CarListActivity.EDIT_CODE)){
            Car car = (Car) intent.getSerializableExtra(CarListActivity.EDIT_CODE);
            etName.setText(car.getName());
            etBrand.setText(car.getBrand());
            etModel.setText(car.getModel());
            etDate.setText(car.getPurchaseDate());
            etPrice.setText(String.valueOf(car.getPrice()));
            //***************SPINNER*************************************
            ArrayAdapter<String> adaptor = (ArrayAdapter<String>)spinnerMotorType.getAdapter();
            for(int i=0;i<adaptor.getCount();i++)
                if(adaptor.getItem(i).equals(car.getMotorType()))
                {
                    spinnerMotorType.setSelection(i);
                    break;
                }
            //**********************RADIOGROUP**********************
            if(car.getTransmissionType().equals("Manual")){
                radioGroup.check(R.id.radioButton1);
            }
            if(car.getTransmissionType().equals("Automatic")){
                radioGroup.check(R.id.radioButton2);
            }
            etKilometers.setText(String.valueOf(car.getKilometers()));
        }

        database = FirebaseDatabase.getInstance();
//                    ADAUGARE
        FloatingActionButton btnSave = findViewById(R.id.floatingActionButtonSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty())
                    etName.setError("Insert a name for car!");
                else
                    if(etBrand.getText().toString().isEmpty())
                        etBrand.setError("Insert the car's brand");
                    else
                        if(etModel.getText().toString().isEmpty())
                            etModel.setError("Insert the car's model!");
                        else
                            if(etDate.getText().toString().isEmpty())
                                etDate.setError("Insert the date of car's purchase!");
                            else
                                if(etPrice.getText().toString().isEmpty())
                                    etPrice.setError("Insert the car's price!");
                                else
                                    if(etKilometers.getText().toString().isEmpty())
                                        etKilometers.setError("Insert the car's kilometers");
                                    else{


                                        String name = etName.getText().toString();

                                        String brand = etBrand.getText().toString();

                                        String model = etModel.getText().toString();

                                        String purchaseDate = etDate.getText().toString();

                                        int price = Integer.parseInt(etPrice.getText().toString());
//****************************************  SPINNER*************************************************************************
                                        String motorType = spinnerMotorType.getSelectedItem().toString();
//***********************************************RADIOGROUP************************************************************************************************
                                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                                        String transmissionType = radioButton.getText().toString();

                                        int kilometers = Integer.parseInt(etKilometers.getText().toString());

                                        CarsDB carsDB = CarsDB.getInstance(getApplicationContext());

                                        String[] services = getResources().getStringArray(R.array.services);
                                        String randomService = services[new Random().nextInt(services.length)];

                                        String[] dates = getResources().getStringArray(R.array.inspectionsDates);
                                        String randomDate = dates[new Random().nextInt(dates.length)];

                                        Random random = new Random();
                                        TechInspection inspection = new TechInspection(random.nextInt(99)+1, randomService, randomDate,500);

                                        Car car = new Car(name, brand, model, purchaseDate, price, motorType, transmissionType, kilometers);
                                        writeCarInFirebase(car);
                                        car.setIdInspection(inspection.getId());

                                        carsDB.getInspectionsDao().insert(inspection);
                                        carsDB.getCarsDao().insert(car);

                                        intent.putExtra(ADD_CAR, car);
                                        setResult(RESULT_OK, intent);
                                        finish();


                                    }
            }
        });
    }

    private void writeCarInFirebase(final Car car){
        final DatabaseReference myRef = database.getReference("carmanagement-3edbe-default-rtdb");
        myRef.keepSynced(true);

        myRef.child("carmanagement-3edbe-default-rtdb").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                car.setUid(myRef.child("carmanagement-3edbe-default-rtdb").push().getKey());
                myRef.child("carmanagement-3edbe-default-rtdb").child(car.getUid()).setValue(car);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
