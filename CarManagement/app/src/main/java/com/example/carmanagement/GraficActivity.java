package com.example.carmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

public class GraficActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);

        Intent intent = getIntent();
        ArrayList<Car> listCarsManualTr = (ArrayList<Car>)intent.getSerializableExtra("listCarsManualTr");
        ArrayList<Car> listCarsAutomaticTr = (ArrayList<Car>)intent.getSerializableExtra("listCarsAutomaticTr");

        List<Double> lstPriceManualCars = new ArrayList<>();
        List<Double> lstPriceAutomaticCars = new ArrayList<>();

        for(Car car: listCarsManualTr)
            lstPriceManualCars.add(Double.valueOf(car.getPrice()));

        for(Car car: listCarsAutomaticTr)
            lstPriceAutomaticCars.add(Double.valueOf(car.getPrice()));

        XYPlot plot = findViewById(R.id.mySimpleXYPlot);

        plot.setTitle("Investments");

        XYSeries series1 = new SimpleXYSeries(lstPriceManualCars, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Manual");
        plot.addSeries(series1, new LineAndPointFormatter(getApplicationContext(), R.layout.f1));

        XYSeries series2 = new SimpleXYSeries(lstPriceAutomaticCars, SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Automatic");
        plot.addSeries(series2, new LineAndPointFormatter(getApplicationContext(), R.layout.f2));
    }
}