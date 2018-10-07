/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */



package com.example.android.myfeeling;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.myfeeling.EmptyNess;
import com.example.android.myfeeling.Feeling;
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

public class FileForFeel extends AppCompatActivity {

    private Context context;

    public FileForFeel(Context context){

        context = context;

    }
    public ArrayList<Feeling> loadFromFile(Context context, String fileName, ArrayList<Feeling> feelings){

        try{
            FileInputStream fis = context.openFileInput(fileName);

            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<EmptyNess>>(){}.getType();

            feelings = gson.fromJson(input, listType);

            System.out.println(feelings.size());

            System.out.println(gson.toJson(feelings));

            input.close();

            fis.close();

            Toast.makeText(context, "File was read!", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error reading file!", Toast.LENGTH_SHORT).show();
        }

        return feelings;

    }

    public void saveInFile(Context context, String fileName, ArrayList<Feeling> feelings) {
        try{
            FileOutputStream fos = context.openFileOutput(fileName, context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(feelings, output);

            //System.out.println(gson.toJson(feelings));

            //System.out.println(fos);

            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();

            output.flush();

            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }
}
