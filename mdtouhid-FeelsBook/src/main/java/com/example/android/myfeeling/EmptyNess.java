/**
 * @author: Md Touhidul Islam
 * @date: 2018-10-07
 */

package com.example.android.myfeeling;

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
