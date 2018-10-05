package com.example.android.myfeeling;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeelingCountShower extends AppCompatActivity {

    public static final String SHARED_PREFERENCE = "counterPrefs";

    public static final String JOY_COUNT = "joyCounter";

    public static final String LOVE_COUNT = "loveCounter";

    public static final String SAD_COUNT = "sadCounter";

    public static final String SURPRISE_COUNT = "surpriseCounter";

    public static final String ANGRY_COUNT = "angryCounter";

    public static final String FEAR_COUNT = "fearCounter";

    Love love = new Love();

    Joy joy = new Joy();

    Surprise surprise = new Surprise();

    Sad sad = new Sad();

    Angry angry = new Angry();

    Fear fear = new Fear();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_count_shower);

        //loadFromSharedPreference(LOVE_COUNT, love);
//        loadFromSharedPreference(JOY_COUNT, joy);
//        loadFromSharedPreference(SURPRISE_COUNT, surprise);
//        loadFromSharedPreference(SAD_COUNT, sad);
//        loadFromSharedPreference(ANGRY_COUNT, angry);
//        loadFromSharedPreference(FEAR_COUNT, fear);
        Bundle bundle = getIntent().getExtras();

        int[] arraycount = (int[])bundle.getSerializable("counter");
        TextView loveCountView = (TextView) findViewById(R.id.love_number);
        loveCountView.setText(String.valueOf(arraycount[0]));

        TextView joyCountView = (TextView) findViewById(R.id.joy_number);
        joyCountView.setText(String.valueOf(arraycount[1]));

        TextView surpriseCountView = (TextView) findViewById(R.id.surprise_number);
        surpriseCountView.setText(String.valueOf(arraycount[2]));

        TextView sadCountView = (TextView) findViewById(R.id.sad_number);
        sadCountView.setText(String.valueOf(arraycount[3]));

        TextView angryCountView = (TextView) findViewById(R.id.angry_number);
        angryCountView.setText(String.valueOf(arraycount[4]));

        TextView fearCountView = (TextView) findViewById(R.id.fear_number);
        fearCountView.setText(String.valueOf(arraycount[5]));

    }

    public void loadFromSharedPreference(String counter, Feeling feel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        feel.setCounter(sharedPreferences.getInt(counter, 0));

        System.out.println(sharedPreferences.getInt(counter, 0));
    }

}
