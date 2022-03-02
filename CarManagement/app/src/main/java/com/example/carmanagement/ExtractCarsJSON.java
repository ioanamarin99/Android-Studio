package com.example.carmanagement;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractCarsJSON extends AsyncTask<URL, Void, String> {

    public static List<Car> carsList = new ArrayList<>();

    JSONArray cars = null;

    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream ist = conn.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            String sbuf ="";
            while ((line = br.readLine()) != null){
                sbuf += line;
            }

            loadJSON(sbuf);

            return sbuf;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadJSON(String jsonStr){
        if(jsonStr != null){
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(jsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                cars = jsonObject.getJSONArray("cars");

                for(int i=0; i<cars.length();i++){
                    JSONObject c = cars.getJSONObject(i);

                    String name = c.getString("Name");
                    String brand = c.getString("Brand");
                    String model = c.getString("Model");
                    //Date purchaseDate = new Date(c.getString("PurchaseDate"));
                    String purchaseDate = c.getString("PurchaseDate");
                    int price = Integer.parseInt(c.getString("Price"));
                    String motorType = c.getString("MotorType");
                    String transmissionType = c.getString("TransmissionType");
                    int kilometers = Integer.parseInt(c.getString("Kilometers"));

                    Car car = new Car(name, brand, model, purchaseDate, price, motorType, transmissionType, kilometers);

                    carsList.add(car);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.e("loadJSON", "JSON object is null");
        }
    }
}
