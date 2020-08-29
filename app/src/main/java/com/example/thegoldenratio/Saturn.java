package com.example.thegoldenratio;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.munon.turboimageview.TurboImageView;

public class Saturn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturn);

        TurboImageView turboImageView = findViewById(R.id.turboImageView2);
        turboImageView.addObject(this, R.drawable.goldenline);

        TypeWriter typeWriter = findViewById(R.id.saturnTypeWriter);
        typeWriter.setCharacterDelay(75);
        typeWriter.animateText("Did you know?");
        Utils.delay(3, () -> {
            typeWriter.animateText("The Divine proportion doesn't even spare Saturn");
            Utils.delay(6, ()  -> {
                typeWriter.animateText("Can you find it??");
                Utils.delay(4, () -> {
                    typeWriter.animateText("Drag the image from the top left corner and try to fit it in");
                    Utils.delay(8, () -> {
                        typeWriter.animateText("Click on the question mark to see the answer :)");
                    });
                });
            });
        });

        ImageView questionMark = findViewById(R.id.questionMark);
        questionMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView answer = findViewById(R.id.answer);
                answer.setVisibility(View.VISIBLE);
                turboImageView.setVisibility(View.INVISIBLE);
            }
        });


    }
}