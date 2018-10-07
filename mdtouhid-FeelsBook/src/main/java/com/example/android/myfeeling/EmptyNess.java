package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmptyNess extends Feeling {

    private String feelingState;

    private int imageResourceID;

    public EmptyNess(){
        feelingState = setFeeling();

        imageResourceID = setImageResourceID();
    }

    public String setFeeling(){
        return "";
    }

    public String getFeeling(){
        return feelingState;
    }

    public int setImageResourceID(){ return R.drawable.love;}

    public int getImageResourceID(){return imageResourceID;}
}
