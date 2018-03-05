package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SubmitFeedBackScreenAdapter extends ArrayAdapter<SubmitFeedbackScreenListItems> {

    //the list values in the List of type hero
    List<SubmitFeedbackScreenListItems> submitFeedbackScreenListItems;
    AlertDialog.Builder builder;
    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;


    //constructor initializing the values
    public SubmitFeedBackScreenAdapter(Context context, int resource, List<SubmitFeedbackScreenListItems> submitFeedbackScreenListItems) {
        super(context, resource, submitFeedbackScreenListItems);
        this.context = context;
        this.resource = resource;
        this.submitFeedbackScreenListItems = submitFeedbackScreenListItems;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView feedbackquestion = (TextView) view.findViewById(R.id.submitfeedbackscreenlist_question);


        //getting the hero of the specified position
        final SubmitFeedbackScreenListItems hero = submitFeedbackScreenListItems.get(position);

        //adding values to the list item
        feedbackquestion.setText(hero.getFeedbackquestion());
        //finally returning the view
        return view;
    }
}