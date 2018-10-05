package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Joy extends Feeling {

    @SerializedName("counter")
    private static int countJoy;

    @SerializedName("feelingState")
    private String joy;

    private int imageResourceID;

    public Joy(){

        joy = setFeeling();

        imageResourceID = setImageResourceID();

        countJoy++;
    }

    @Override
    public void destructor() {
        countJoy--;
    }

    @Override
    public String returnCounter() {
        return ""+countJoy;
    }

    @Override
    public int getCounter() {
        return countJoy;
    }

    @Override
    public void setCounter(int count) {
        countJoy = count;
    }

    public String setFeeling(){
        return "I'm happy.";
    }

    public String getFeeling(){
        return joy;
    }

    public int setImageResourceID(){return R.drawable.happy;}

    public int getImageResourceID(){return imageResourceID;}

}
