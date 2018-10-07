package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

public class Fear extends Feeling {

    @SerializedName("counter")
    private static int countFear;

    @SerializedName("feelingState")
    private String fear;

    private int imageResourceID;

    public Fear() {

        fear = setFeeling();

        imageResourceID = setImageResourceID();

    }

    public String setFeeling(){
        return "I'm frightened.";
    }

    public String getFeeling(){
        return fear;
    }

    public int setImageResourceID(){return R.drawable.fear;}

    public int getImageResourceID(){return imageResourceID;}
}
