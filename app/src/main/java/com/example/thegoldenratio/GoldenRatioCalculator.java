package com.example.thegoldenratio;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class GoldenRatioCalculator extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextAplusB;
    private static DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golden_ratio_calculator);

        editTextA = findViewById(R.id.editTextNumberA);
        editTextB = findViewById(R.id.editTextNumberB);
        editTextAplusB = findViewById(R.id.editTextNumberAplusB);



    }
    public void onClick(View v){
        EditText a = findViewById(R.id.editTextNumberA);
        int aid = a.getId();
        EditText b = findViewById(R.id.editTextNumberB);
        int bid = b.getId();
        EditText apb = findViewById(R.id.editTextNumberAplusB);

        if(v.getId() == aid){
            b.setText(df.format(Double.parseDouble(a.getText().toString())/1.618));
            apb.setText(df.format(Double.parseDouble(a.getText().toString()) + Double.parseDouble(b.getText().toString())));
        }else if(v.getId() == bid){
            a.setText(df.format(Double.parseDouble(b.getText().toString())*1.618));
            apb.setText(df.format(Double.parseDouble(a.getText().toString()) + Double.parseDouble(b.getText().toString())));
        }
    }
}
