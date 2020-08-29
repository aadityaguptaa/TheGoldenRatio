package com.example.thegoldenratio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainActivityButton = findViewById(R.id.mainActivityButton);
        Button theGoldeGridButton = findViewById(R.id.TheGoldenGridButton);
        Button earthAndMoon = findViewById(R.id.earthAndMoonButton);
        Button saturnButton = findViewById(R.id.saturnButton);


        //starts faceRecognition Intent
        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceRecognitionIntent = new Intent(MainActivity.this, faceRecognition.class);
                startActivity(faceRecognitionIntent);
            }
        });

        //starts goldenGrid Intent
        theGoldeGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goldenGridIntent = new Intent(MainActivity.this, TheGoldenRuler.class);
                startActivity(goldenGridIntent);
            }
        });

        //starts EarthAndMoon Activity
        earthAndMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EarthAndMoon = new Intent(MainActivity.this, EarthAndMoon.class);
                startActivity(EarthAndMoon);
            }
        });

        //starts EarthAndMoon Activity
        saturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Saturn = new Intent(MainActivity.this, Saturn.class);
                startActivity(Saturn);
            }
        });
    }
}
