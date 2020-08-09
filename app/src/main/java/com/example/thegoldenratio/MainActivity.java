package com.example.thegoldenratio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //starts faceRecognition Intent
        Button mainActivityButton = findViewById(R.id.mainActivityButton);
        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faceRecognitionIntent = new Intent(MainActivity.this, faceRecognition.class);
                startActivity(faceRecognitionIntent);
            }
        });
    }
}
