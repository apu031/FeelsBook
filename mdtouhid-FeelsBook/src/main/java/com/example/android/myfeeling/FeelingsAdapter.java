package com.example.android.myfeeling;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.myfeeling.Feeling;
import com.example.android.myfeeling.R;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class FeelingsAdapter extends ArrayAdapter<Feeling> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param differentFeelings A List of Word objects to display in a list
     */

    public FeelingsAdapter(Activity context, ArrayList<Feeling> differentFeelings){
        super (context, 0, differentFeelings);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the {@link Word} object located at this position in the list
        final Feeling currentFeel = getItem(position);


        TextView feelTextView = (TextView)listItemView.findViewById(R.id.feeling_text_view);

        feelTextView.setText(currentFeel.getFeeling());


        TextView timeTextView = (TextView)listItemView.findViewById(R.id.time_text_view);

        timeTextView.setText(currentFeel.getFeelingTime());

        TextView commentTextView = (TextView)listItemView.findViewById(R.id.comment_text_view);

        commentTextView.setText(currentFeel.getCommentOnTheFeeling());

        ImageView iconView = (ImageView)listItemView.findViewById(R.id.image_emoticon);

        iconView.setImageResource(currentFeel.getImageResourceID());

//        final ImageView iconView1 = (ImageView)listItemView.findViewById(R.id.image_del);
//
//        //iconView1.setImageResource(R.drawable.delete);
//        iconView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return listItemView;
    }
}
