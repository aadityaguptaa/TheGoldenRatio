package com.example.thegoldenratio;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class FibonacciClock extends AppCompatActivity {

    int hour = -1, min = -1;                                                                        // initialise hour and min to -1; both invalid
    static int[] factor = {5, 3, 2, 1, 1};                                                          // initialise factors to 5,3,2,1 & 1. Fixed throughout program.
    static int[] hourFlag = {0, 0, 0, 0, 0};                                                        // initialise hour flag to zeroes.
    static int[] minFlag = {0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_clock);


        firstRun();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Calendar c = Calendar.getInstance();
                min = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);

                if (min % 5 == 0 && sec == 0) {

                    hour = c.get(Calendar.HOUR);
                    if (hour == 0) hour = 12;
                    min = c.get(Calendar.MINUTE);
                    while (min % 5 != 0)                                                            // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
                    {
                        min--;
                    }
                    min = min / 5;


                    for (int i = 0; i < 5; i++)                                                     // for checking presence of all factors: 5,3,2,1,1 is there in hour/min or not
                    {
                        if (hour - factor[i] >= 0)                                                  // if hour has a factor[i] present in it, set hourFlag[i] to show factor[i]'s presence
                        {
                            hourFlag[i] = 1;                                                        // set flag
                            hour = hour - factor[i];                                                // remove the factor of i, remaining factor be checked in next iterations
                        }
                        if (min - factor[i] >= 0)                                                   // if min has a factor[i] present in it, set minFlag[i] to show factor[i]'s presence
                        {
                            minFlag[i] = 1;                                                         // set flag
                            min = min - factor[i];                                                  // remove the factor of i, remaining factor be checked in next iterations
                        }
                    }

                    hour = c.get(Calendar.HOUR);
                    if (hour == 0) hour = 12;
                    min = c.get(Calendar.MINUTE);
                    while (min % 5 != 0)                                                            // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
                    {
                        min--;
                    }
                    min = min / 5;

                    runOnUiThread(new Runnable() {

                        public void run() {

                            imageChange();

                            resetTime();
                        }
                    });

                }

            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void firstRun() {
        Calendar c = Calendar.getInstance();                                                        // new object of type Calendar
        hour = c.get(Calendar.HOUR);                                                                // get hour (in 24-HR format) in int
        min = c.get(Calendar.MINUTE);                                                               // get minute in int

        if (hour == 0)
            hour = 12;
        while (min % 5 != 0)                                                                        // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
        {
            min--;
        }
        min = min / 5;                                                                              // min=min/5 for further computation

        calcColour();                                                                               // set hourFlag and minFlag arrays so that colour can be changed appropriately by imageChange method
        imageChange();                                                                              // change image to suitable colour
        resetTime();                                                                                // reset time variables hour & min to -1 to avoid unwanted scenarios, sets hourFlag and minFlag arrays to zeroes
    }

    public void calcColour()                                                                        // set hourFlag and minFlag arrays
    {
        for (int i = 0; i < 5; i++)                                                                 // for checking presence of all factors: 5,3,2,1,1 is there in hour/min or not
        {
            if (hour - factor[i] >= 0)                                                              // if hour has a factor[i] present in it, set hourFlag[i] to show factor[i]'s presence
            {
                hourFlag[i] = 1;                                                                    // set flag
                hour = hour - factor[i];                                                            // remove the factor of i, remaining factor be checked in next iterations
            }
            if (min - factor[i] >= 0)                                                               // if min has a factor[i] present in it, set minFlag[i] to show factor[i]'s presence
            {
                minFlag[i] = 1;                                                                     // set flag
                min = min - factor[i];                                                              // remove the factor of i, remaining factor be checked in next iterations
            }
        }
    }

    public void imageChange()                                                                       // change images in preview
    {

        ImageView box5x5 = (ImageView) findViewById(R.id.fivebyfive);                               // initialise box5x5 object of type ImageView, corresponding image is fivebyfive
        ImageView box3x3 = (ImageView) findViewById(R.id.threebythree);                               // initialise box3x3 object of type ImageView, corresponding image is threebythree
        ImageView box2x2 = (ImageView) findViewById(R.id.twobytwo);                               // initialise box2x2 object of type ImageView, corresponding image is twobytwo
        ImageView box1x1a = (ImageView) findViewById(R.id.onebyone);                              // initialise box1x1a object of type ImageView, corresponding image is onebyone
        ImageView box1x1b = (ImageView) findViewById(R.id.onebyonetwo);                              // initialise box1x1b object of type ImageView, corresponding image is onebyonetwo

        box5x5.setImageResource(android.R.color.transparent);                                       // initialise all boxes to transparent colour
        box3x3.setImageResource(android.R.color.transparent);
        box2x2.setImageResource(android.R.color.transparent);
        box1x1a.setImageResource(android.R.color.transparent);
        box1x1b.setImageResource(android.R.color.transparent);

        // 5x5 box                                                                                  // 5x5 box

        if (hourFlag[0] == 1 && minFlag[0] == 1)                                                    // if both hour and min have a factor of 5
            box5x5.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (hourFlag[0] == 1)                                                                  // if hour has a factor of 5
            box5x5.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (minFlag[0] == 1)                                                                   // if min has a factor of 5
            box5x5.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 3x3 box                                                                                  // 3x3 box

        if (hourFlag[1] == 1 && minFlag[1] == 1)                                                    // if both hour and min have a factor of 3
            box3x3.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (hourFlag[1] == 1)                                                                  // if hour has a factor of 3
            box3x3.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (minFlag[1] == 1)                                                                   // if min has a factor of 3
            box3x3.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 2x2 box                                                                                  // 2x2 box

        if (hourFlag[2] == 1 && minFlag[2] == 1)                                                    // if both hour and min have a factor of 2
            box2x2.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (hourFlag[2] == 1)                                                                  // if hour has a factor of 2
            box2x2.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (minFlag[2] == 1)                                                                   // if min has a factor of 2
            box2x2.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 1x1a box                                                                                 // 1x1a box

        if (hourFlag[3] == 1 && minFlag[3] == 1)                                                    // if both hour and min have a factor of 1
            box1x1a.setImageResource(android.R.color.holo_blue_dark);                              // then display blue
        else if (hourFlag[3] == 1)                                                                  // if hour has a factor of 1
            box1x1a.setImageResource(android.R.color.holo_red_dark);                               // then display red
        else if (minFlag[3] == 1)                                                                   // if min has a factor of 1
            box1x1a.setImageResource(android.R.color.holo_green_dark);                             // then display green

        // 1x1b box                                                                                 // 1x1b box

        if (hourFlag[4] == 1 && minFlag[4] == 1)                                                    // if both hour and min have a factor of 1
            box1x1b.setImageResource(android.R.color.holo_blue_dark);                              // then display blue
        else if (hourFlag[4] == 1)                                                                  // if hour has a factor of 1
            box1x1b.setImageResource(android.R.color.holo_red_dark);                               // then display red
        else if (minFlag[4] == 1)                                                                   // if min has a factor of 1
            box1x1b.setImageResource(android.R.color.holo_green_dark);                             // then display green

    }

    public void resetTime()                                                                         // reset time variables hour & min to -1 to avoid unwanted scenarios
    {                                                                                               // [IMPORTANT] also sets hourFlag and minFlag arrays to zeroes to start afresh, with no previous values altering current values
        hour = -1;
        min = -1;                                                                                   // hour and min set to -1
        for (int i = 0; i < 5; i++) {
            hourFlag[i] = 0;                                                                        // all elements in hourFlag set to 0
            minFlag[i] = 0;                                                                         // all elements in minFlag set to 0
        }
    }
}