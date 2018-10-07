/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */

package com.example.android.myfeeling;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601_Time_Format {

    //A string variable that will store the formatted time
    private String timeRightNow;

    // A string variable that will store the timeZone information
    final String TIME_ZONE = "America/Edmonton";


    // Constructor of the class that will work as a setter to set the time in timeRightNow

    public ISO8601_Time_Format(){
        // A date object of type Date that will get current date
        Date date = new Date();

        /*
         A dateFormat object of type DateFormat that will call its superclass constructor
         SimpleDateFormat with
         @pattern: "yyyy-MM-dd'T'HH:mm:ss'Z'" as the 1st parameter and the locale of the area as the
         2nd parameter
          */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CANADA);
        // setTimeZone will set the timeZone in current date format
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        // The formatted date is going to be stored in timeRightNow
        timeRightNow = dateFormat.format(date);
    }

    // Method for getting the time
    public String getTimeRightNow(){
        return timeRightNow;
    }
}
