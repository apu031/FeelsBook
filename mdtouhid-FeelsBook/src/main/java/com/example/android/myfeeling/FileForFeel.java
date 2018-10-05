package com.example.android.myfeeling;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileForFeel extends AppCompatActivity implements Serializable {

    public FileForFeel(){

    }
    public void loadFromFile(String fileName, ArrayList<Feeling> feelings){

        try{
            FileInputStream fis = openFileInput(fileName);

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

    public void saveInFile(String fileName, ArrayList<Feeling> feelings, int contextMode) {
        try{
            FileOutputStream fos = openFileOutput(fileName, contextMode);

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
}
