package com.example.carmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class LvCarAdapter extends ArrayAdapter<Car> {

    private Context context;
    private int resource;
    private List<Car> carList;
    private LayoutInflater layoutInflater;


    public LvCarAdapter(@NonNull Context context, int resource, @NonNull List<Car> carList, LayoutInflater layoutInflater) {
        super(context, resource, carList);
        this.context = context;
        this.resource = resource;
        this.carList = carList;
        this.layoutInflater = layoutInflater;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Car car = carList.get(position);

        if(car != null){
            TextView tv1 = view.findViewById(R.id.name);
            tv1.setText("NAME: " + car.getName());

            TextView tv2 = view.findViewById(R.id.brand);
            tv2.setText("BRAND: " + car.getBrand());

            TextView tv3 = view.findViewById(R.id.model);
            tv3.setText("MODEL: " + car.getModel());

            TextView tv4 = view.findViewById(R.id.purchaseDate);

            tv4.setText("PURCH. DATE: " + car.getPurchaseDate());

            TextView tv5 = view.findViewById(R.id.price);
            tv5.setText("PRICE: " + String.valueOf(car.getPrice()) + " EUR");

            TextView tv6 = view.findViewById(R.id.motorType);
            tv6.setText("MOTOR: " + car.getMotorType());

            TextView tv7 = view.findViewById(R.id.transmissionType);
            tv7.setText("TRANSMISSION: " +car.getTransmissionType());

            TextView tv8 = view.findViewById(R.id.kilometers);
            tv8.setText("KM: " + String.valueOf(car.getKilometers()));
        }
        return view;
    }
}
