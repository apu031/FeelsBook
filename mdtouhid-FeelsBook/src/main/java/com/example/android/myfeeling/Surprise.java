package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Surprise extends Feeling {
    @SerializedName("counter")
    private static int countSurprise;

    @SerializedName("feelingState")
    private String surprise;

    private int imageResourceID;

    public Surprise(){

        surprise = setFeeling();

        imageResourceID = setImageResourceID();

        countSurprise++;
    }

    @Override
    public void destructor() {
        countSurprise--;
    }

    @Override
    public String returnCounter() {
        return ""+countSurprise;
    }

    public int getCounter() {
        return countSurprise;
    }

    public void setCounter(int count) {
        countSurprise = count;
    }

    public String setFeeling(){
        return "I'm surprised.";
    }

    public String getFeeling(){
        return surprise;
    }

    public int setImageResourceID(){return R.drawable.surprise;}

    public int getImageResourceID(){return imageResourceID;}
}
