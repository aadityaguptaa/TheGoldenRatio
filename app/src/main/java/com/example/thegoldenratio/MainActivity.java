package com.example.thegoldenratio;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.delay(6, () -> {
            Intent intent = new Intent(MainActivity.this, Intro.class);
            startActivity(intent);
        });
    }
}
