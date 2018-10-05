package com.example.android.myfeeling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final String FILE_NAME = "feels.sav";

    public final String FILE_NAME1 = "arrayc.sav";

    public static final String SHARED_PREFERENCE = "counterPrefs";

    public static final String JOY_COUNT = "joyCounter";

    public static final String LOVE_COUNT = "loveCounter";

    public static final String SAD_COUNT = "sadCounter";

    public static final String SURPRISE_COUNT = "surpriseCounter";

    public static final String ANGRY_COUNT = "angryCounter";

    public static final String FEAR_COUNT = "fearCounter";

    final int[] arraycounta = new int[6];


    //Creat an ArrayList of type Feeling
    ArrayList<Feeling> feelings = new ArrayList<Feeling>();

    //Comment EditText View: globally decleared
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadFromSharedPreference(LOVE_COUNT, love);
//        loadFromSharedPreference(JOY_COUNT, joy);
//        loadFromSharedPreference(SURPRISE_COUNT, surprise);
//        loadFromSharedPreference(SAD_COUNT, sad);
//        loadFromSharedPreference(ANGRY_COUNT, angry);
//        loadFromSharedPreference(FEAR_COUNT, fear);
        loadFromFile();
        //arraycounta = feelingCounter();

        Button historyButton = (Button)findViewById(R.id.history);

        historyButton.setOnClickListener(new OnClickListener() {

            //HistoryActivity historyActivity = new HistoryActivity(feelings, FILE_NAME);
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@Link History}
                Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("adapter", feelings);

                historyIntent.putExtras(bundle);

                startActivity(historyIntent);
            }
        });

        Button editFeelingDateButton = (Button)findViewById(R.id.feeling_date_changer);

        editFeelingDateButton.setOnClickListener(new OnClickListener() {

            //HistoryActivity historyActivity = new HistoryActivity(feelings, FILE_NAME);
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@Link History}
                Intent editDateIntent = new Intent(MainActivity.this, ChaningDateWithSorting.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("adapter", feelings);

                editDateIntent.putExtras(bundle);

                startActivity(editDateIntent);
            }
        });

        Button data_summary = (Button)findViewById(R.id.count_Shower);

        data_summary.setOnClickListener(new OnClickListener() {

            //HistoryActivity historyActivity = new HistoryActivity(feelings, FILE_NAME);
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@Link History}
                Intent dataSummaryIntent = new Intent(MainActivity.this, FeelingCountShower.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("counter", arraycounta);

                dataSummaryIntent.putExtras(bundle);

                startActivity(dataSummaryIntent);
            }
        });
    }

    //Upon clicking Love button onClickLove will be executed
    public void onClickLove(View view){
        //create a new love object
        Love love = new Love();
        //Adding the comments in the object member variable
        love.setCommentOnTheFeeling(commentOnFeeling());
        saveToSharedPreference(love);
        arraycounta[0] = love.getCounter();
        //A new Love type object is being stored in the ArrayList of type Feelings
        feelings.add(love);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");

        saveInFile();
    }

    //Upon clicking Joy button onClicJoy will be executed
    public void onClickJoy(View view){
        //create a new joy object
        Joy joy = new Joy();
        //Adding the comments in the object member variable
        joy.setCommentOnTheFeeling(commentOnFeeling());
        saveToSharedPreference(joy);
        arraycounta[1] = joy.getCounter();
        //A new Joy type object is being stored in the ArrayList of type Feelings
        feelings.add(joy);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");

        saveInFile();
    }

    //Upon clicking Surprise button onClickSurprise will be executed
    public void onClickSurprise(View view){
        //create a new surprise object
        Surprise surprise = new Surprise();
        //Adding the comments in the object member variable
        surprise.setCommentOnTheFeeling(commentOnFeeling());
        //A new Surprise type object is being stored in the ArrayList of type Feelings
        saveToSharedPreference(surprise);
        arraycounta[2] = surprise.getCounter();
        feelings.add(surprise);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");
        saveInFile();
    }

    //Upon clicking Sad button onClickSad will be executed
    public void onClickSad(View view){
        //create a new sad object
        Sad sad = new Sad();
        //Adding the comments in the object member variable
        sad.setCommentOnTheFeeling(commentOnFeeling());
        //A new Sad type object is being stored in the ArrayList of type Feelings
        saveToSharedPreference(sad);
        arraycounta[3] = sad.getCounter();
        feelings.add(sad);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");
        saveInFile();
    }

    //Upon clicking Angry button onClickAngry will be executed
    public void onClickAngry(View view){
        //create a new angry object
        Angry angry = new Angry();
        //Adding the comments in the object member variable
        angry.setCommentOnTheFeeling(commentOnFeeling());
        saveToSharedPreference(angry);
        arraycounta[4] = angry.getCounter();
        //A new Angry type object is being stored in the ArrayList of type Feelings
        feelings.add(angry);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");
        saveInFile();
    }

    //Upon clicking Angry button onClickAngry will be executed
    public void onClickFear(View view){
        //create a new fear object
        Fear fear = new Fear();
        //Adding the comments in the object member variable
        fear.setCommentOnTheFeeling(commentOnFeeling());
        saveToSharedPreference(fear);
        arraycounta[5] = fear.getCounter();
        //A new Fear type object is being stored in the ArrayList of type Feelings
        feelings.add(fear);
        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));
        //Clearing the text field for new comments
        editText.setText("");
        saveInFile();
    }

    public String commentOnFeeling(){
        //EditTextView for showing comment about the feeling
        editText = (EditText) findViewById(R.id.comment_about_the_feeling);
        return editText.getText().toString();
    }

    //Upon clicking Delete All button the removeAll method will be excuted
    public void removeAll(View view){
        // Clears the whole ArrayList of Type Feelings
        feelings.clear();
        primaryDisplay();
        saveInFile();
    }

    //Primarily shows the data on the app
    public void primaryDisplay(Feeling feel){
        //feel_message_text_view is being stored in feelingMessageView of TextView type
        TextView feelingMessageView = (TextView) findViewById(R.id.feel_message_text_view);
        //The text is being set in the feelingMessageView
        feelingMessageView.setText(feel.getFeeling());

        //feel_date_text_view is being stored in feelingDateView of TextView type
        TextView feelingDateView = (TextView) findViewById(R.id.feel_date_text_view);
        //The text is being set in the feelingMessageView
        feelingDateView.setText(feel.getFeelingTime());

        //feel_comment_text_view is being stored in feelingCommentView of TextView type
        TextView feelingCommentView = (TextView) findViewById(R.id.comment_text_view);
        //The text is being set in the feelingMessageView
        feelingCommentView.setText(feel.getCommentOnTheFeeling());

    }

    //Primarily shows the data on the app
    public void primaryDisplay(){
        //feel_message_text_view is being stored in feelingMessageView of TextView type
        TextView feelingMessageView = (TextView) findViewById(R.id.feel_message_text_view);
        //The text is being set in the feelingMessageView
        feelingMessageView.setText("");

        //feel_date_text_view is being stored in feelingDateView of TextView type
        TextView feelingDateView = (TextView) findViewById(R.id.feel_date_text_view);
        //The text is being set in the feelingMessageView
        feelingDateView.setText("");

        //feel_comment_text_view is being stored in feelingCommentView of TextView type
        TextView feelingCommentView = (TextView) findViewById(R.id.comment_text_view);
        //The text is being set in the feelingMessageView
        feelingCommentView.setText("");

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

    private void saveInFile() {
        try{
            FileOutputStream fos = openFileOutput("feels.sav", Context.MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(feelings, output);

            System.out.println(gson.toJson(feelings));

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

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
    }

    public void saveToSharedPreference(Feeling feel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences.edit();

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();

        SharedPreferences sharedPreferences4 = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();

        SharedPreferences sharedPreferences5 = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();

        SharedPreferences sharedPreferences6 = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor6 = sharedPreferences6.edit();

        editor1.putInt(LOVE_COUNT, feel.getCounter());
        editor2.putInt(JOY_COUNT, feel.getCounter());
        editor3.putInt(SURPRISE_COUNT, feel.getCounter());
        editor4.putInt(SAD_COUNT, feel.getCounter());
        editor5.putInt(ANGRY_COUNT, feel.getCounter());
        editor6.putInt(FEAR_COUNT, feel.getCounter());
    }

    public void loadFromSharedPreference(String counter, Feeling feel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        feel.setCounter(sharedPreferences.getInt(counter, 0));

        System.out.println(sharedPreferences.getInt(counter, 0));
    }

    public void clearSharedPreference(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public  int[] feelingCounter(){
        int [] arrayCount = new int[7];
        for (int i = 0; i < feelings.size(); i++){
            System.out.println(feelings.get(i).getFeeling());
            if(feelings.get(i).getFeeling() == "I'm in LOVE."){
                //Counting love feeling
                arrayCount[0]++;
                Log.v("In LOVE COUNT", "IM IN LOVE COUNT");
                System.out.println("PRINTAAAAAAAAAAAAAAAAAA"+arrayCount[0]);
            }
            if(feelings.get(i).getFeeling() == "I'm happy."){
                //Counting joy feeling
                arrayCount[1]++;
            }
            if(feelings.get(i).getFeeling() == "I'm surprised."){
                //Counting surprise feeling
                arrayCount[2]++;
            }
            if(feelings.get(i).getFeeling() == "I'm sad."){
                //Counting sad feeling
                arrayCount[3]++;
            }
            if(feelings.get(i).getFeeling() == "I'm angry."){
                //Counting angry feeling
                arrayCount[4]++;
            }
            if(feelings.get(i).getFeeling() == "I'm frightened."){
                //Counting fear feeling
                arrayCount[5]++;
            }
        }
        //counting total feeling
        arrayCount[6] = arrayCount[0] + arrayCount[1] + arrayCount[2] + arrayCount[3] + arrayCount[4] + arrayCount[5];
        System.out.println(arrayCount[6]);

        return arrayCount;
    }

}
