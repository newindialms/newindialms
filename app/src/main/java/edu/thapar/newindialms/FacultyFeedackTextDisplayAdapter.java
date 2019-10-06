package edu.thapar.newindialms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedackTextDisplayAdapter extends ArrayAdapter<FacultyFeedbackTextDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackTextDisplayListItems> facultyFeedbackTextDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyFeedackTextDisplayAdapter(Context context, int resource, List<FacultyFeedbackTextDisplayListItems> facultyFeedbackTextDisplayListItems) {
        super(context, resource, facultyFeedbackTextDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyFeedbackTextDisplayListItems = facultyFeedbackTextDisplayListItems;
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
        TextView textbar = view.findViewById(R.id.text_rating);
        TextView feedback_question = view.findViewById(R.id.text_rating_questions);
        TextView enrollcourse_daywise_display_date = view.findViewById(R.id.enrollcourse_daywise_display_date);
        TextView enrollcourse_daywise_display_time = view.findViewById(R.id.enrollcourse_daywise_display_time);


        //getting the hero of the specified position
        final FacultyFeedbackTextDisplayListItems hero = facultyFeedbackTextDisplayListItems.get(position);

        //adding values to the list item
        enrollcourse_daywise_display_date.setText(hero.getDaywise_date());
        enrollcourse_daywise_display_time.setText(hero.getDaywise_time());

        textbar.setText(hero.getText());
        feedback_question.setText(hero.getQuestion());
        //finally returning the view
        return view;
    }
}