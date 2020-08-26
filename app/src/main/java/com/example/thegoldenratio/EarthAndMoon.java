package com.example.thegoldenratio;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class EarthAndMoon extends AppCompatActivity {
    private int verticalsButtonsCount = 0;
    ImageView verticalLine;
    ImageView horizontalLine;
    GifImageView earthView;
    GifImageView moonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_and_moon);
        verticalLine = findViewById(R.id.verticalLine);
        horizontalLine = findViewById(R.id.horizontalLine);
        earthView = findViewById(R.id.earthGif);
        moonView = findViewById(R.id.moonGif);

        float earthViewHeight = earthView.getHeight();
        float earthViewWidth = earthView.getWidth();
        float moonViewHeight = moonView.getHeight();

        verticalLine.getLayoutParams().height = (int) ((earthViewHeight/2) + (moonViewHeight/2));
        horizontalLine.getLayoutParams().width = (int)(earthViewWidth/2);
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
    public void onClickHorizontal(View view){
        verticalsButtonsCount+=1;
        if(verticalsButtonsCount == 2){
            verticalLine.setVisibility(View.VISIBLE);
        }else if(verticalsButtonsCount == 3){
            horizontalLine.setVisibility(View.VISIBLE);
        }
    }
}