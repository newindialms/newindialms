package edu.thapar.newindialms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedackRateDisplayAdapter extends ArrayAdapter<FacultyFeedbackRateDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackRateDisplayListItems> facultyFeedbackRateDisplayListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public FacultyFeedackRateDisplayAdapter(Context context, int resource, List<FacultyFeedbackRateDisplayListItems> facultyFeedbackRateDisplayListItems) {
        super(context, resource, facultyFeedbackRateDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyFeedbackRateDisplayListItems = facultyFeedbackRateDisplayListItems;
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
        RatingBar staratebar=(RatingBar)view.findViewById(R.id.submitfeedbackscreenlist_ratebar);
        TextView text_rating_questions = (TextView) view.findViewById(R.id.text_rating_questions);
        TextView enrollcourse_daywise_display_date = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_date);
        TextView enrollcourse_daywise_display_time = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_time);





        //getting the hero of the specified position
        final FacultyFeedbackRateDisplayListItems hero = facultyFeedbackRateDisplayListItems.get(position);

        //adding values to the list item
        text_rating_questions.setText(hero.getQuestion());
        enrollcourse_daywise_display_date.setText(hero.getDaywise_date());
        enrollcourse_daywise_display_time.setText(hero.getDaywise_time());
        int numberOfStars= (int) Math.round(hero.getStars());
        staratebar.setNumStars(numberOfStars);
        staratebar.setRating(numberOfStars);

        //finally returning the view
        return view;
    }
}