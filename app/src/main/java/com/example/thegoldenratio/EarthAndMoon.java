package com.example.thegoldenratio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class EarthAndMoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_and_moon);

        GifImageView gifImageView = findViewById(R.id.earthGif);

    }
}