package com.example.thegoldenratio;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnBoardingMain extends AppCompatActivity {

    private ViewPager viewPager2;
    private LinearLayout linearLayout;

    private TextView[] mDots;

    private SliderAdapter sliderAdapter;
    private Button mNextButton;
    private Button mPrevButton;

    private int mCurrentInt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_main);

        viewPager2 = (ViewPager)findViewById(R.id.viewPager2);
        linearLayout = findViewById(R.id.linearLayout);

        mNextButton = findViewById(R.id.forward);
        mPrevButton = findViewById(R.id.backward);

        sliderAdapter = new SliderAdapter(this);
        viewPager2.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        viewPager2.addOnPageChangeListener(viewListener);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(mCurrentInt + 1);

            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(mCurrentInt - 1);

            }
        });

    }

    public void addDotsIndicator(int position2){
        linearLayout.removeAllViews();
        mDots = new TextView[8];

        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            linearLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position2].setTextColor(Color.WHITE);
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentInt = position;

            if(position == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("");
            }else if(position == mDots.length - 1){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Finish");
                mPrevButton.setText("Back");
            }else{
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);

                mNextButton.setText("Next");
                mPrevButton.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}