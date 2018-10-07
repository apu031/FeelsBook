package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

public class Angry extends Feeling {

    @SerializedName("feelingState")
    private String angry;

    private int imageResourceID;

    public Angry() {

        angry = setFeeling();

        imageResourceID = setImageResourceID();

    }

    public String setFeeling(){
        return "I'm angry.";
    }

    public String getFeeling(){
        return angry;
    }

    public int setImageResourceID(){return R.drawable.angry;}

    public int getImageResourceID(){return imageResourceID;}

}
