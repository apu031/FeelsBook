package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Love extends Feeling {

    @SerializedName("feelingState")
    private String love;

    private int imageResourceID;

    public Love(){
        love = setFeeling();

        imageResourceID = setImageResourceID();

    }

    public String setFeeling(){
        return "I'm in LOVE.";
    }

    public String getFeeling(){
        return love;
    }

    public int setImageResourceID(){ return R.drawable.love;}

    public int getImageResourceID(){return imageResourceID;}
}
