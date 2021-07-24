package com.example.thegoldenratio.fibonacciClock;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thegoldenratio.R;
import com.example.thegoldenratio.TypeWriter;
import com.example.thegoldenratio.Utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class FibonacciClock extends AppCompatActivity {

    FibonacciClockFunctions fibonacciClockFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci_clock);

        fibonacciClockFunctions = new FibonacciClockFunctions();

        firstRun();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                Calendar c = Calendar.getInstance();
                fibonacciClockFunctions.min = c.get(Calendar.MINUTE);
                int sec = c.get(Calendar.SECOND);

                if (fibonacciClockFunctions.min % 5 == 0 && sec == 0) {

                    fibonacciClockFunctions.hour = c.get(Calendar.HOUR);
                    if (fibonacciClockFunctions.hour == 0) fibonacciClockFunctions.hour = 12;
                    fibonacciClockFunctions.min = c.get(Calendar.MINUTE);
                    while (fibonacciClockFunctions.min % 5 != 0)                                                            // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
                    {
                        fibonacciClockFunctions.min--;
                    }
                    fibonacciClockFunctions.min = fibonacciClockFunctions.min / 5;


                    for (int i = 0; i < 5; i++)                                                     // for checking presence of all factors: 5,3,2,1,1 is there in hour/min or not
                    {
                        if (fibonacciClockFunctions.hour - fibonacciClockFunctions.factor[i] >= 0)                                                  // if hour has a factor[i] present in it, set hourFlag[i] to show factor[i]'s presence
                        {
                            fibonacciClockFunctions.hourFlag[i] = 1;                                                        // set flag
                            fibonacciClockFunctions.hour = fibonacciClockFunctions.hour - fibonacciClockFunctions.factor[i];                                                // remove the factor of i, remaining factor be checked in next iterations
                        }
                        if (fibonacciClockFunctions.min - fibonacciClockFunctions.factor[i] >= 0)                                                   // if min has a factor[i] present in it, set minFlag[i] to show factor[i]'s presence
                        {
                            fibonacciClockFunctions.minFlag[i] = 1;                                                         // set flag
                            fibonacciClockFunctions.min = fibonacciClockFunctions.min - fibonacciClockFunctions.factor[i];                                                  // remove the factor of i, remaining factor be checked in next iterations
                        }
                    }

                    fibonacciClockFunctions.hour = c.get(Calendar.HOUR);
                    if (fibonacciClockFunctions.hour == 0) fibonacciClockFunctions.hour = 12;
                    fibonacciClockFunctions.min = c.get(Calendar.MINUTE);
                    while (fibonacciClockFunctions.min % 5 != 0)                                                            // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
                    {
                        fibonacciClockFunctions.min--;
                    }
                    fibonacciClockFunctions.min = fibonacciClockFunctions.min / 5;

                    runOnUiThread(new Runnable() {

                        public void run() {

                            imageChange();

                            fibonacciClockFunctions.resetTime();
                        }
                    });

                }

            }

        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void firstRun() {

        TypeWriter hourtw = findViewById(R.id.hourtw);
        TypeWriter minutetw = findViewById(R.id.minutetw);
        TypeWriter ignoretw = findViewById(R.id.ignoretw);
        minutetw.setCharacterDelay(75);
        ignoretw.setCharacterDelay(75);
        hourtw.setCharacterDelay(75);
        hourtw.animateText(fibonacciClockFunctions.stringList[0]);

        Utils.delay(5, () -> {
            hourtw.animateText(fibonacciClockFunctions.stringList[1]);
            Utils.delay(6, () -> {
                minutetw.animateText(fibonacciClockFunctions.stringList[2]);
                Utils.delay(9, () -> {
                    ignoretw.animateText(fibonacciClockFunctions.stringList[3]);
                });
            });
        });
        Calendar c = Calendar.getInstance();                                                        // new object of type Calendar
        fibonacciClockFunctions.hour = c.get(Calendar.HOUR);                                                                // get hour (in 24-HR format) in int
        fibonacciClockFunctions.min = c.get(Calendar.MINUTE);                                                               // get minute in int

        if (fibonacciClockFunctions.hour == 0)
            fibonacciClockFunctions.hour = 12;
        while (fibonacciClockFunctions.min % 5 != 0)                                                                        // checking for nearest lowest multiple of 5 in min. Ex: 54=50,59=55,etc.
        {
            fibonacciClockFunctions.min--;
        }
        fibonacciClockFunctions.min = fibonacciClockFunctions.min / 5;                                                                              // min=min/5 for further computation

        fibonacciClockFunctions.calcColour();                                                                               // set hourFlag and minFlag arrays so that colour can be changed appropriately by imageChange method
        imageChange();                                                                              // change image to suitable colour
        fibonacciClockFunctions.resetTime();                                                                                // reset time variables hour & min to -1 to avoid unwanted scenarios, sets hourFlag and minFlag arrays to zeroes
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

        if (fibonacciClockFunctions.hourFlag[0] == 1 && fibonacciClockFunctions.minFlag[0] == 1)                                                    // if both hour and min have a factor of 5
            box5x5.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (fibonacciClockFunctions.hourFlag[0] == 1)                                                                  // if hour has a factor of 5
            box5x5.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (fibonacciClockFunctions.minFlag[0] == 1)                                                                   // if min has a factor of 5
            box5x5.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 3x3 box                                                                                  // 3x3 box

        if (fibonacciClockFunctions.hourFlag[1] == 1 && fibonacciClockFunctions.minFlag[1] == 1)                                                    // if both hour and min have a factor of 3
            box3x3.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (fibonacciClockFunctions.hourFlag[1] == 1)                                                                  // if hour has a factor of 3
            box3x3.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (fibonacciClockFunctions.minFlag[1] == 1)                                                                   // if min has a factor of 3
            box3x3.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 2x2 box                                                                                  // 2x2 box

        if (fibonacciClockFunctions.hourFlag[2] == 1 && fibonacciClockFunctions.minFlag[2] == 1)                                                    // if both hour and min have a factor of 2
            box2x2.setImageResource(android.R.color.holo_blue_dark);                               // then display blue
        else if (fibonacciClockFunctions.hourFlag[2] == 1)                                                                  // if hour has a factor of 2
            box2x2.setImageResource(android.R.color.holo_red_dark);                                // then display red
        else if (fibonacciClockFunctions.minFlag[2] == 1)                                                                   // if min has a factor of 2
            box2x2.setImageResource(android.R.color.holo_green_dark);                              // then display green

        // 1x1a box                                                                                 // 1x1a box

        if (fibonacciClockFunctions.hourFlag[3] == 1 && fibonacciClockFunctions.minFlag[3] == 1)                                                    // if both hour and min have a factor of 1
            box1x1a.setImageResource(android.R.color.holo_blue_dark);                              // then display blue
        else if (fibonacciClockFunctions.hourFlag[3] == 1)                                                                  // if hour has a factor of 1
            box1x1a.setImageResource(android.R.color.holo_red_dark);                               // then display red
        else if (fibonacciClockFunctions.minFlag[3] == 1)                                                                   // if min has a factor of 1
            box1x1a.setImageResource(android.R.color.holo_green_dark);                             // then display green

        // 1x1b box                                                                                 // 1x1b box

        if (fibonacciClockFunctions.hourFlag[4] == 1 && fibonacciClockFunctions.minFlag[4] == 1)                                                    // if both hour and min have a factor of 1
            box1x1b.setImageResource(android.R.color.holo_blue_dark);                              // then display blue
        else if (fibonacciClockFunctions.hourFlag[4] == 1)                                                                  // if hour has a factor of 1
            box1x1b.setImageResource(android.R.color.holo_red_dark);                               // then display red
        else if (fibonacciClockFunctions.minFlag[4] == 1)                                                                   // if min has a factor of 1
            box1x1b.setImageResource(android.R.color.holo_green_dark);                             // then display green

    }


}