package com.example.thegoldenratio;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingMain extends AppCompatActivity {

    private ViewPager viewPager2;
    private LinearLayout linearLayout;

    private SliderAdapter sliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_main);

        viewPager2 = (ViewPager)findViewById(R.id.viewPager2);
        linearLayout = findViewById(R.id.linearLayout);

        sliderAdapter = new SliderAdapter(this);
        viewPager2.setAdapter(sliderAdapter);

    }
}