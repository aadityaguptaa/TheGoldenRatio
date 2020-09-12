package com.example.thegoldenratio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        CardView mainActivityButton = findViewById(R.id.goldenFaceButton);
        CardView theGoldeGridButton = findViewById(R.id.gridButton);
        CardView earthAndMoon = findViewById(R.id.earthButton);
        CardView saturnButton = findViewById(R.id.saturnLogoButton);
        CardView introButton = findViewById(R.id.homeButton);
        CardView fibclkButton = findViewById(R.id.fibonacciboxButton);
        CardView ratioCalculatorButton = findViewById(R.id.calculatorButton);
        CardView theMenuButton = findViewById(R.id.logoButton);





        //starts faceRecognition Intent
        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceRecognitionIntent = new Intent(Intro.this, faceRecognition.class);
                startActivity(faceRecognitionIntent);
            }
        });

        //starts goldenGrid Intent
        theGoldeGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goldenGridIntent = new Intent(Intro.this, TheGoldenRuler.class);
                startActivity(goldenGridIntent);
            }
        });

        //starts EarthAndMoon Activity
        earthAndMoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent EarthAndMoon = new Intent(Intro.this, EarthAndMoon.class);
                startActivity(EarthAndMoon);
            }
        });

        //starts EarthAndMoon Activity
        saturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Saturn = new Intent(Intro.this, Saturn.class);
                startActivity(Saturn);
            }
        });

        //starts EarthAndMoon Activity
        introButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro = new Intent(Intro.this, OnBoardingMain.class);
                startActivity(intro);
            }
        });

        fibclkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro = new Intent(Intro.this, FibonacciClock.class);
                startActivity(intro);
            }
        });

        ratioCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro = new Intent(Intro.this, GoldenRatioCalculator.class);
                startActivity(intro);
            }
        });

        theMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro = new Intent(Intro.this, Logos.class);
                startActivity(intro);
            }
        });
    }
}