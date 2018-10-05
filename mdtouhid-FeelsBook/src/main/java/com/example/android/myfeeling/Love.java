package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Love extends Feeling {

    private static int countLove;

    @SerializedName("feelingState")
    private String love;

    private int imageResourceID;

    public Love(){
        love = setFeeling();

        imageResourceID = setImageResourceID();

        countLove++;
    }

    @Override
    public void destructor() {
        countLove--;
    }

    @Override
    public String returnCounter() {
        return String.valueOf(countLove);
    }

    public int getCounter() {
        return countLove;
    }

    public void setCounter(int count) {
        countLove = count;
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
