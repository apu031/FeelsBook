package com.example.android.myfeeling;

import com.google.gson.annotations.SerializedName;

public class Fear extends Feeling {

    @SerializedName("counter")
    private static int countFear;

    @SerializedName("feelingState")
    private String fear;

    private int imageResourceID;

    public Fear(){

        fear = setFeeling();

        imageResourceID = setImageResourceID();

        countFear++;
    }

    @Override
    public String returnCounter() {
        return ""+countFear;
    }

    @Override
    public int getCounter() {
        return countFear;
    }

    @Override
    public void setCounter(int count) {
        countFear = count;
    }

    public void destructor(){countFear--;}

    public String setFeeling(){
        return "I'm frightened.";
    }

    public String getFeeling(){
        return fear;
    }

    public int setImageResourceID(){return R.drawable.fear;}

    public int getImageResourceID(){return imageResourceID;}
}
