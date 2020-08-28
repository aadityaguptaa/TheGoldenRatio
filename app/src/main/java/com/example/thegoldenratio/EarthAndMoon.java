package com.example.thegoldenratio;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.droidsonroids.gif.GifImageView;

public class EarthAndMoon extends AppCompatActivity {
    private int verticalsButtonsCount = 0;
    ImageView verticalLine;
    ImageView horizontalLine;
    GifImageView earthView;
    GifImageView moonView;
    TypeWriter mainone;
    FloatingActionButton earthCenter;
    FloatingActionButton earthCorner;
    FloatingActionButton moonCenter;



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
        final String[] st = new String[6];
        st[0] = "Did you know??";
        st[1] = "The dimensions of the Earth and Moon are in Phi relationship, forming a Triangle based on 1.618.";
        st[2] = "Don't believe me??";
        st[3] = "Lemme show you!!";
        st[4] = "first, let us draw the radius of earth";
        st[5] = "tap on the plus signs visible";

        //Add a character every 150ms
         // Delay in seconds
        mainone = findViewById(R.id.typeWriter);
        mainone.setCharacterDelay(75);
        mainone.animateText(st[0]);

        Utils.delay(3, new Utils.DelayCallback() {
            @Override
            public void afterDelay() {
                mainone.animateText(st[1]);
                Utils.delay(11, new Utils.DelayCallback() {
                    @Override
                    public void afterDelay() {
                        mainone.animateText(st[2]);
                        Utils.delay(3, new Utils.DelayCallback() {
                            @Override
                            public void afterDelay() {
                                mainone.animateText(st[3]);
                                Utils.delay(3, new Utils.DelayCallback() {
                                    @Override
                                    public void afterDelay() {
                                        mainone.animateText(st[4]);
                                        Utils.delay(6, new Utils.DelayCallback() {
                                            @Override
                                            public void afterDelay() {
                                                earthCenter = findViewById(R.id.earthCenterFloat);
                                                earthCorner = findViewById(R.id.earthRightFloat2);
                                                earthCenter.setVisibility(View.VISIBLE);
                                                earthCorner.setVisibility(View.VISIBLE);
                                                mainone.animateText(st[5]);
                                            }
                                        });
                                    }
                                });
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
            horizontalLine.setVisibility(View.VISIBLE);
            TextView re = findViewById(R.id.Re);
            re.setVisibility(View.VISIBLE);
            TypeWriter earthRadius = findViewById(R.id.earthRadius);
            earthRadius.setCharacterDelay(75);
            earthRadius.animateText("-> Let the radius of the Earth be 1");
            Utils.delay(5, () -> {
                TypeWriter moonRadius = findViewById(R.id.moonRadius);
                moonRadius.setCharacterDelay(75);
                moonRadius.animateText("-> the ratio of radius of Earth and Moon is 1 : 0.272");
                Utils.delay(6, () -> {
                    moonRadius.animateText("-> i.e. Radius of moon = 0.272 ");
                    Utils.delay(3, () ->{
                        earthCorner.setVisibility(View.INVISIBLE);
                        moonCenter = findViewById(R.id.moonCenterFloat);
                        moonCenter.setVisibility(View.VISIBLE);
                        mainone.animateText("now, let us join center of earth and center of moon");
                        Utils.delay(6, () -> {
                            mainone.animateText("Again, tap on the plus signs Visible");
                        });
                    });
                });
            });
        }else if(verticalsButtonsCount == 4){
            verticalLine.setVisibility(View.VISIBLE);
            TextView rem = findViewById(R.id.rem);
            rem.setVisibility(View.VISIBLE);
            TypeWriter earthMoonDist = findViewById(R.id.earthAndMoonDist);
            earthMoonDist.setCharacterDelay(75);
            earthMoonDist.animateText("-> Re + Rm = 1.272 == √Φ ");
            TypeWriter hypo = findViewById(R.id.hypoTextDisc);

                Utils.delay(6, () -> {
                    mainone.animateText("time to draw hypotenuse!!");
                    earthCenter.setVisibility(View.INVISIBLE);
                    earthCorner.setVisibility(View.VISIBLE);
                    Utils.delay(4, ()->{
                        mainone.animateText("tap on the signs visible");
                    });
                });
            
        }else if(verticalsButtonsCount == 6){
            ImageView earthMoonHypo = findViewById(R.id.earthMoonHypo);
            TextView remh = findViewById(R.id.remh);
            TypeWriter hypo = findViewById(R.id.hypoTextDisc);
            hypo.setCharacterDelay(75);
            hypo.animateText("-> now, the hypotenuse according to pythagoras theorem is,  ");
            Utils.delay(6, ()->{
                hypo.animateText("-> the Golden Ratio Φ  :)  ");
            });
            remh.setVisibility(View.VISIBLE);
            earthMoonHypo.setVisibility(View.VISIBLE);
        }
    }
}