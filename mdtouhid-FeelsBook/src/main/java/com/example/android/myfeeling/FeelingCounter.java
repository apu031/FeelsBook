package com.example.android.myfeeling;

import android.util.Log;

import java.util.ArrayList;

public class FeelingCounter {

    FeelingCounter(ArrayList<Feeling> feel, int[] arrayForCount){
        feelingCounter(feel, arrayForCount);
    }

    private void feelingCounter(ArrayList<Feeling> feelings, int[] arrayCount) {
        for (int i = 0; i < feelings.size(); i++) {
//            System.out.println(feelings.get(i).getClass());
//            System.out.println(feelings.get(i) instanceof Love);
            System.out.println(R.drawable.angry);
            if (feelings.get(i).getImageResourceID() == R.drawable.love) {
                //Counting love feeling
                arrayCount[0]++;
            }
            if (feelings.get(i).getImageResourceID() == R.drawable.happy) {
                //Counting joy feeling
                arrayCount[1]++;
            }
            if (feelings.get(i).getImageResourceID() == R.drawable.surprise) {
                //Counting surprise feeling
                arrayCount[2]++;
            }
            if (feelings.get(i).getImageResourceID() == R.drawable.sad) {
                //Counting sad feeling
                arrayCount[3]++;
            }
            if (feelings.get(i).getImageResourceID() == R.drawable.angry) {
                //Counting angry feeling
                arrayCount[4]++;
            }
            if (feelings.get(i).getImageResourceID() == R.drawable.fear) {
                //Counting fear feeling
                arrayCount[5]++;
            }
        }
    }
}
