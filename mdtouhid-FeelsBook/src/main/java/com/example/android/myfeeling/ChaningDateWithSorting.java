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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class ChaningDateWithSorting extends AppCompatActivity {

    ArrayList<Feeling> feelings;

    Feeling feel;

    int itemPosition;

    String date;

    String time;

    String date_time;

    boolean isTimePicked = false;

    boolean isDatePicked = false;



    private DatePickerDialog.OnDateSetListener mDateSetListerner;

    private TimePickerDialog.OnTimeSetListener mTimeSetListerner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chaning_date_with_sorting);

        Toast.makeText(getBaseContext(), "To Edit Date and Time : Just Click", Toast.LENGTH_LONG).show();

        Bundle bundle = getIntent().getExtras();

        feelings = (ArrayList<Feeling>)bundle.getSerializable("adapter");

        final FeelingsAdapter adapter = new FeelingsAdapter(this, feelings);

        final ListView listView = (ListView) findViewById(R.id.list_time);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView Clicked item index
                itemPosition = position;

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ChaningDateWithSorting.this, android.R.style.Theme_Dialog, mDateSetListerner,
                        year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.show();

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ChaningDateWithSorting.this, android.R.style.Theme_Dialog, mTimeSetListerner, hour, minute, true
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.show();

                // ListView Clicked item value
                //String  itemValue    = (String) listView.getItemAtPosition(position);


                // Show Alert

            }
        });

        mDateSetListerner = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                date = year + "-" + month + "-" + day;
                if (date != null && time != null){
                    date_time = date + "T" + time;
                    feelings.get(itemPosition).setFeelingTime(date_time);
                    feelings = sortArrayList(feelings);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                    Toast.makeText(getBaseContext(),"Position :"+itemPosition, Toast.LENGTH_LONG).show();
                }

            }

        };

        mTimeSetListerner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time = hourOfDay + ":" + minute + ":00";
            }
        };

    }

    private void saveInFile() {
        try{
            FileOutputStream fos = openFileOutput("feels.sav", Context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(feelings, output);

            //System.out.println(gson.toJson(feelings));

            //System.out.println(fos);

            Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();

            output.flush();

            fos.close();

            //Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromFile(){

        try{
            FileInputStream fis = openFileInput("feels.sav");

            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<EmptyNess>>(){}.getType();

            feelings = gson.fromJson(input, listType);

            System.out.println(feelings.size());

            System.out.println(gson.toJson(feelings));

            input.close();

            fis.close();

            Toast.makeText(getBaseContext(), "File was read!", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error reading file!", Toast.LENGTH_SHORT).show();
        }

    }

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
