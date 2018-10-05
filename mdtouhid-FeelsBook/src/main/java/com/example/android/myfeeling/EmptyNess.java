package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmptyNess extends Feeling {

    private static int counter;

    private String feelingState;

    private int imageResourceID;

    public EmptyNess(){
        feelingState = setFeeling();

        imageResourceID = setImageResourceID();
    }

    @Override
    public String returnCounter() {
        return ""+counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int count) {
        counter = count;
    }

    public String setFeeling(){
        return "";
    }

    public void destructor(){counter--;}

    public String getFeeling(){
        return feelingState;
    }

    public int setImageResourceID(){ return R.drawable.love;}

    public int getImageResourceID(){return imageResourceID;}
}
