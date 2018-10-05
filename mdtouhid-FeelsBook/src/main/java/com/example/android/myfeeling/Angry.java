package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

public class Angry extends Feeling {

    @SerializedName("counter")
    private static int countAnger;

    @SerializedName("feelingState")
    private String angry;

    private int imageResourceID;

    public Angry(){

        angry = setFeeling();

        imageResourceID = setImageResourceID();

        countAnger++;
    }

    @Override
    public void destructor() {
        countAnger--;
    }

    @Override
    public String returnCounter() {
        return ""+countAnger;
    }

    public int getCounter() {
        return countAnger;
    }

    public void setCounter(int count) {
        countAnger = count;
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
