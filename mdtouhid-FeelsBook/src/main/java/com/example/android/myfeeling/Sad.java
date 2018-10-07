package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sad extends Feeling {

    @SerializedName("counter")
    private static int countSad;

    @SerializedName("feelingState")
    private String sad;

    private int imageResourceID;

    public Sad(){

        sad = setFeeling();

        imageResourceID = setImageResourceID();

    }


    public String setFeeling(){
            return "I'm sad.";
    }

    public String getFeeling(){
        return sad;
    }

    public int setImageResourceID(){return R.drawable.sad;}

    public int getImageResourceID(){return imageResourceID;}
}
