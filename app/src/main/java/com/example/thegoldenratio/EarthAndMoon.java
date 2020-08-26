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

        final String[] st = new String[4];
        st[0] = "Did you know??";
        st[1] = "The dimensions of the Earth and Moon are in Phi relationship, forming a Triangle based on 1.618.";
        st[2] = "Don't believe me??";
        st[3] = "Lemme show you!!";

        //Add a character every 150ms
         // Delay in seconds
        final TypeWriter writer = findViewById(R.id.typeWriter);
        writer.setCharacterDelay(75);
        writer.animateText(st[0]);

        Utils.delay(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                writer.animateText(st[1]);
                Utils.delay(11, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        writer.animateText(st[2]);
                        Utils.delay(3, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                writer.animateText(st[3]);
                            }
                        });
                    }
                });
            }
        });

    }
}