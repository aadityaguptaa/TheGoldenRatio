package com.example.thegoldenratio.fibonacciClock;

import com.example.thegoldenratio.fibonacciClock.FibonacciClockAbstract;

public class FibonacciClockFunctions extends FibonacciClockAbstract {

    int hour = -1, min = -1;                                                                        // initialise hour and min to -1; both invalid
    static int[] factor = {5, 3, 2, 1, 1};                                                          // initialise factors to 5,3,2,1 & 1. Fixed throughout program.
    static int[] hourFlag = {0, 0, 0, 0, 0};                                                        // initialise hour flag to zeroes.
    static int[] minFlag = {0, 0, 0, 0, 0};

    public String[] stringList = {"Here we have for you the Fibonacci Clock", "Hour = Sum of Fibonacci Numbers of blue and red boxes",
            "Minutes = Sum of Fibonacci Numbers of blue and green boxes * 5 ", "Ignore white boxes :)"};

    public void resetTime()                                                                         // reset time variables hour & min to -1 to avoid unwanted scenarios
    {                                                                                               // [IMPORTANT] also sets hourFlag and minFlag arrays to zeroes to start afresh, with no previous values altering current values
        hour = -1;
        min = -1;                                                                                   // hour and min set to -1
        for (int i = 0; i < 5; i++) {
            hourFlag[i] = 0;                                                                        // all elements in hourFlag set to 0
            minFlag[i] = 0;                                                                         // all elements in minFlag set to 0
        }
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
}
