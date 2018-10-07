package com.example.android.myfeeling;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Locale;

public abstract class Feeling extends ISO8601_Time_Format implements Serializable {

    // When the emotion was felt
    @SerializedName("timeOfFelt")
    private String timeOfFelt;

    @SerializedName("commentOnTheFeeling")
    private String commentOnTheFeeling;

    public Feeling(){
        timeOfFelt = getTimeRightNow();
    }

    public abstract String getFeeling();

    public abstract int setImageResourceID();

    public abstract int getImageResourceID();

    public abstract String setFeeling();

    public String getFeelingTime(){
        return timeOfFelt;
    }

    public String setFeelingTime(String timeAdded) {return timeOfFelt = timeAdded;}

    public String setCommentOnTheFeeling(String comment){
        return commentOnTheFeeling = comment;
    }

    public String getCommentOnTheFeeling(){
        return commentOnTheFeeling;
    }

}
