/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */
package com.example.android.myfeeling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public final String FILE_NAME = "feels.sav";

    FileForFeel file = new FileForFeel(this);

    //Creat an ArrayList of type Feeling
    ArrayList<Feeling> feelings = new ArrayList<Feeling>();

    //Comment EditText View: globally decleared
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFromFile1();

        Button historyButton = (Button)findViewById(R.id.history);

        historyButton.setOnClickListener(new OnClickListener() {

            //HistoryActivity historyActivity = new HistoryActivity(feelings, FILE_NAME);
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@Link History}
                Intent historyIntent = new Intent(MainActivity.this, HistoryActivity.class);

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

        //A new Love type object is being stored in the ArrayList of type Feelings
        feelings.add(love);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments
        editText.setText("");

        saveInFile();
        //file.saveInFile(this, FILE_NAME, feelings);
    }

    //Upon clicking Joy button onClicJoy will be executed
    public void onClickJoy(View view){
        //create a new joy object
        Joy joy = new Joy();

        //Adding the comments in the object member variable
        joy.setCommentOnTheFeeling(commentOnFeeling());

        //A new Joy type object is being stored in the ArrayList of type Feelings
        feelings.add(joy);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments
        editText.setText("");

        //file.saveInFile(this, FILE_NAME, feelings);
        saveInFile();
    }

    //Upon clicking Surprise button onClickSurprise will be executed
    public void onClickSurprise(View view){
        //create a new surprise object
        Surprise surprise = new Surprise();

        //Adding the comments in the object member variable
        surprise.setCommentOnTheFeeling(commentOnFeeling());

        //A new Surprise type object is being stored in the ArrayList of type Feelings
        feelings.add(surprise);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments

        editText.setText("");

        //file.saveInFile(this, FILE_NAME, feelings);

        saveInFile();
    }

    //Upon clicking Sad button onClickSad will be executed
    public void onClickSad(View view){
        //create a new sad object
        Sad sad = new Sad();

        //Adding the comments in the object member variable
        sad.setCommentOnTheFeeling(commentOnFeeling());

        //A new Sad type object is being stored in the ArrayList of type Feelings
        feelings.add(sad);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments
        editText.setText("");

        //file.saveInFile(this, FILE_NAME, feelings);

        saveInFile();
    }

    //Upon clicking Angry button onClickAngry will be executed
    public void onClickAngry(View view){
        //create a new angry object
        Angry angry = new Angry();

        //Adding the comments in the object member variable
        angry.setCommentOnTheFeeling(commentOnFeeling());

        //A new Angry type object is being stored in the ArrayList of type Feelings
        feelings.add(angry);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments
        editText.setText("");

        //file.saveInFile(this, FILE_NAME, feelings);
        saveInFile();
    }

    //Upon clicking Angry button onClickAngry will be executed
    public void onClickFear(View view){
        //create a new fear object
        Fear fear = new Fear();

        //Adding the comments in the object member variable
        fear.setCommentOnTheFeeling(commentOnFeeling());

        //A new Fear type object is being stored in the ArrayList of type Feelings
        feelings.add(fear);

        //The last ArrayList element is being sent for printing
        primaryDisplay(feelings.get(feelings.size()-1));

        //Clearing the text field for new comments
        editText.setText("");

        //file.saveInFile(this, FILE_NAME, feelings);
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
        //file.saveInFile(this, FILE_NAME, feelings);
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

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile1();
    }

    protected void loadFromFile1(){

        try{
            FileInputStream fis = openFileInput(FILE_NAME);

            BufferedReader input = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<EmptyNess>>(){}.getType();

            feelings = gson.fromJson(input, listType);

            System.out.println(feelings.size());

            System.out.println(gson.toJson(feelings));

            input.close();

            fis.close();

            Toast.makeText(this, "File was read!", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error reading file!", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveInFile() {
        try{
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(feelings, output);

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

            output.flush();

            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Error saving file!", Toast.LENGTH_SHORT).show();
        }
    }

}
