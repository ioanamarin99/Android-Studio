 package com.example.carmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

 public class RateOurApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_our_app);

        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        Button btnRateUs = findViewById(R.id.buttonRateUs);

        btnRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = String.valueOf(ratingBar.getRating());
                Toast.makeText(getApplicationContext(), rating + " stars", Toast.LENGTH_LONG).show();
                try{
                    writeInFile("RatingFile", rating);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

    }
    private void writeInFile(String nameFile, String rating)
            throws IOException {
        FileOutputStream file = openFileOutput(nameFile, Activity.MODE_PRIVATE);
        DataOutputStream dos = new DataOutputStream(file);
        dos.writeUTF(rating);
        dos.flush();
        file.close();
    }

}