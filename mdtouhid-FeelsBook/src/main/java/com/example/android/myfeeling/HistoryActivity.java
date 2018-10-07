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

    private ArrayList<Feeling> feelings = new ArrayList<Feeling>();

    FileForFeel file = new FileForFeel(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toast.makeText(getBaseContext(), "To Delete: Just Click", Toast.LENGTH_LONG).show();

        feelings = file.loadFromFile(this, "feels.sav", feelings);

        final FeelingsAdapter adapter = new FeelingsAdapter(this, feelings);

        final ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                //String  itemValue    = (String) listView.getItemAtPosition(position);
                feelings.remove(itemPosition);

                sortArrayList();

                adapter.notifyDataSetChanged();

                file.saveInFile(getBaseContext(), "feels.sav", feelings);

                // Show Alert
                Toast.makeText(getBaseContext(),"Position :"+itemPosition, Toast.LENGTH_LONG).show();
            }
        });
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
