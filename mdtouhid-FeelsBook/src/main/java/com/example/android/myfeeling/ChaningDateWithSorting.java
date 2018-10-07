/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */

package com.example.android.myfeeling;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.TimeZone;

public class ChaningDateWithSorting extends AppCompatActivity {

    private ArrayList<Feeling> feelings = new ArrayList<Feeling>();

    FileForFeel file = new FileForFeel(this);

    int itemPosition;

    private DatePickerDialog.OnDateSetListener mDateSetListerner;

    private TimePickerDialog.OnTimeSetListener mTimeSetListerner;

    Calendar calendar = Calendar.getInstance();

    String time;

    String date;

    String date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaning_date_with_sorting);

        Toast.makeText(getBaseContext(), "To Edit Date and Time : Just Click", Toast.LENGTH_LONG).show();

        feelings = file.loadFromFile(this, "feels.sav", feelings);

        final FeelingsAdapter adapter = new FeelingsAdapter(this, feelings);

        final ListView listView = (ListView) findViewById(R.id.list_time);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView Clicked item index
                itemPosition = position;

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ChaningDateWithSorting.this, android.R.style.Theme_Dialog, mDateSetListerner,
                        year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.show();

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ChaningDateWithSorting.this, android.R.style.Theme_Dialog, mTimeSetListerner, hour , minute, true
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.show();

            }
        });

        mDateSetListerner = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                month = month + 1;
                String yearS = String.valueOf(year);
                String monthS = String.valueOf(month);
                String dayS = String.valueOf(day);

                if (monthS.length() != 2){
                    monthS = "0" + monthS;
                }

                if (dayS.length() != 2){
                    dayS = "0" + dayS;

                }

                date = yearS + "-" + monthS + "-" + dayS;

                if (date != null && time != null){
                    date_time = date + 'T' + time;

                    feelings.get(itemPosition).setFeelingTime(date_time);
                    feelings = sortArrayList(feelings);
                    adapter.notifyDataSetChanged();
                    file.saveInFile(getBaseContext(), "feels.sav", feelings);
                    Toast.makeText(getBaseContext(),"Position :"+itemPosition, Toast.LENGTH_LONG).show();
                }

            }

        };

        mTimeSetListerner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hourS = "" + hourOfDay;
                String minuteS = "" + minute;
                if (hourS.length() != 2){
                    hourS = "0" + hourS;
                }

                if (minuteS.length() != 2){
                    minuteS ="0" + minuteS;
                }
                time = hourS + ":" + minuteS + ":00";
            }
        };

    }

    /**
     * Source of Idea: https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
     */

    public ArrayList<Feeling> sortArrayList(ArrayList<Feeling> feelings){
        Collections.sort(feelings, new Comparator<Feeling>() {
            @Override
            public int compare(Feeling o1, Feeling o2) {
                return o1.getFeelingTime().compareTo(o2.getFeelingTime());
            }
        });

        return feelings;
    }

}
