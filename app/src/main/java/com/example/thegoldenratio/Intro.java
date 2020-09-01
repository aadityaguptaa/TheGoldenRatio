package com.example.thegoldenratio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button mainActivityButton = findViewById(R.id.mainActivityButton);
        Button theGoldeGridButton = findViewById(R.id.TheGoldenGridButton);
        Button earthAndMoon = findViewById(R.id.earthAndMoonButton);
        Button saturnButton = findViewById(R.id.saturnButton);
        Button introButton = findViewById(R.id.introButton);
        Button fibclkButton = findViewById(R.id.fibonacciclock);



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
    }
}