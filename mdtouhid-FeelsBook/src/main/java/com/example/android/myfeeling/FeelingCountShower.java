/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */

package com.example.android.myfeeling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeelingCountShower extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_count_shower);

        ArrayList<Feeling> feelings = new ArrayList<Feeling>();
        int[] arraycount = new int[6];

        FileForFeel file = new FileForFeel(this);

        try {
            feelings = file.loadFromFile(this,"feels.sav", feelings);
        }catch(NullPointerException e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error reading file!", Toast.LENGTH_SHORT).show();
        }

        FeelingCounter countAll = new FeelingCounter(feelings, arraycount);

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

}
