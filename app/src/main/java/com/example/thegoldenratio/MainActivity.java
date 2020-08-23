package com.example.thegoldenratio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainActivityButton = findViewById(R.id.mainActivityButton);
        Button theGoldeGridButton = findViewById(R.id.TheGoldenGridButton);

        //starts faceRecognition Intent
        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceRecognitionIntent = new Intent(MainActivity.this, faceRecognition.class);
                startActivity(faceRecognitionIntent);
            }
        });

        //starts goldenGrid Intent
        theGoldeGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goldenGridIntent = new Intent(MainActivity.this, TheGoldenRuler.class);
                startActivity(goldenGridIntent);
            }
        });
    }
}
