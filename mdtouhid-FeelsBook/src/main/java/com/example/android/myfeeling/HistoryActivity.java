package com.example.android.myfeeling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<Feeling> feelings;

    int itemPosition;

    public static final String SHARED_PREFERENCE = "counterPrefs";

    public static final String JOY_COUNT = "joyCounter";

    public static final String LOVE_COUNT = "loveCounter";

    public static final String SAD_COUNT = "sadCounter";

    public static final String SURPRISE_COUNT = "surpriseCounter";

    public static final String ANGRY_COUNT = "angryCounter";

    Love love = new Love();

    Joy joy = new Joy();

    Surprise surprise = new Surprise();

    Sad sad = new Sad();

    Angry angry = new Angry();

    Fear fear = new Fear();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //ArrayList<Feeling> feel = new ArrayList<Feeling>();

        //loadFromFile(feel);

        loadFromSharedPreference(LOVE_COUNT, love);

        Toast.makeText(getBaseContext(), "To Delete: Just Click", Toast.LENGTH_LONG).show();

        Bundle bundle = getIntent().getExtras();

        feelings = (ArrayList<Feeling>)bundle.getSerializable("adapter");


        final FeelingsAdapter adapter = new FeelingsAdapter(this, feelings);

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView Clicked item index
                itemPosition = position;

                // ListView Clicked item value
                //String  itemValue    = (String) listView.getItemAtPosition(position);
                feelings.get(itemPosition).destructor();
                feelings.remove(itemPosition);

                sortArrayList();
                adapter.notifyDataSetChanged();

                saveInFile();

                // Show Alert
                Toast.makeText(getBaseContext(),"Position :"+itemPosition, Toast.LENGTH_LONG).show();
            }
        });
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

    public void saveToSharedPreference(String counter, Feeling feel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(counter, feel.getCounter());
    }

    public void loadFromSharedPreference(String counter, Feeling feel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        feel.setCounter(sharedPreferences.getInt(counter, 0));

        System.out.println(sharedPreferences.getInt(counter, 0));
    }

    public void sortArrayList(){
        Collections.sort(feelings, new Comparator<Feeling>() {
            @Override
            public int compare(Feeling o1, Feeling o2) {
                return o1.getFeelingTime().compareTo(o2.getFeelingTime());
            }
        });
    }
}
