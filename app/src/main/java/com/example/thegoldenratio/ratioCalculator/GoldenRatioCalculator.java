package com.example.thegoldenratio.ratioCalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.thegoldenratio.R;
import com.example.thegoldenratio.databinding.ActivityGoldenRatioCalculatorBinding;

import java.text.DecimalFormat;

public class GoldenRatioCalculator extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextAplusB;
    private static DecimalFormat df = new DecimalFormat("0.00");

    ActivityGoldenRatioCalculatorBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_golden_ratio_calculator);

        editTextA = findViewById(R.id.editTextNumberA);
        editTextB = findViewById(R.id.editTextNumberB);
        editTextAplusB = findViewById(R.id.editTextNumberAplusB);

        DecimalFormat df = new DecimalFormat("0.00");
        RatioCalculatorFunction ratioCalculatorFunction = new RatioCalculatorFunction();

        binding.ratioCalculatorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try{
                    Double getB = Double.parseDouble(binding.editTextNumberB.getText().toString());
                    Double getA = ratioCalculatorFunction.calculateA(getB);
                    Double getAplusB = ratioCalculatorFunction.calculateAplusB(getA, getB);


                    binding.editTextNumberA.setText(df.format(getA));
                    binding.editTextNumberAplusB.setText(df.format(getAplusB));

                }catch(Exception e){
                    Log.i("exception", e.toString());
                }
            }
        });



    }

}
