package com.example.carmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LvCarInspectionsAdapter extends ArrayAdapter<CarInspection> {

    private Context context;
    private int resource;
    private List<CarInspection> carsInspectionsList;
    private LayoutInflater layoutInflater;

    public LvCarInspectionsAdapter(@NonNull Context context, int resource, List<CarInspection> carsInspectionsList, LayoutInflater layoutInflater){
        super(context, resource, carsInspectionsList);
        this.context = context;
        this.resource = resource;
        this.carsInspectionsList = carsInspectionsList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = layoutInflater.inflate(resource, parent, false);
        CarInspection carInspection= carsInspectionsList.get(position);

        if(carInspection != null){
            TextView tv1 = view.findViewById(R.id.name);
            tv1.setText("NAME: " + carInspection.getName());

            TextView tv2 = view.findViewById(R.id.brand);
            tv2.setText("BRAND: " + carInspection.getBrand());

            TextView tv3 = view.findViewById(R.id.model);
            tv3.setText("MODEL: " + carInspection.getModel());

            TextView tv4 = view.findViewById(R.id.purchaseDate);
            tv4.setText("PURCH. DATE: " + carInspection.getPurchaseDate());

            TextView tv5 = view.findViewById(R.id.price);
            tv5.setText("PRICE: " + String.valueOf(carInspection.getPrice()) + " EUR");

            TextView tv6 = view.findViewById(R.id.motorType);
            tv6.setText("MOTOR: " + carInspection.getMotorType());

            TextView tv7 = view.findViewById(R.id.transmissionType);
            tv7.setText("TRANSMISSION: " +carInspection.getTransmissionType());

            TextView tv8 = view.findViewById(R.id.kilometers);
            tv8.setText("KM: " + String.valueOf(carInspection.getKilometers()));

            TextView tv9 = view.findViewById(R.id.idCar);
            tv9.setText("CAR ID: " + String.valueOf(carInspection.getIdCar()));

            TextView tv10 = view.findViewById(R.id.idInspection);
            tv10.setText("INSPECTION ID: " + String.valueOf(carInspection.getIdInspection()));

            TextView tv11 = view.findViewById(R.id.serviceName);
            tv11.setText("SERVICE NAME: " + carInspection.getServiceName());

            TextView tv12 = view.findViewById(R.id.date);
            tv12.setText("DATE: " + carInspection.getDate());

            TextView tv13 = view.findViewById(R.id.priceInspection);
            tv13.setText("INSPECTION PRICE: " + String.valueOf(carInspection.getPriceInspection()));
        }

        return view;
    }
}
