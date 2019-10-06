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

public class FacultyFeedackCumulativeDasboardAdapter extends ArrayAdapter<FacultyFeedbackCumulativeDashboardListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackCumulativeDashboardListItems> facultyFeedbackLikeDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyFeedackCumulativeDasboardAdapter(Context context, int resource, List<FacultyFeedbackCumulativeDashboardListItems> facultyFeedbackLikeDisplayListItems) {
        super(context, resource, facultyFeedbackLikeDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyFeedbackLikeDisplayListItems = facultyFeedbackLikeDisplayListItems;
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
        TextView text_rating_questions = view.findViewById(R.id.text_rating_questions);
        TextView average = view.findViewById(R.id.average);
        TextView median = view.findViewById(R.id.median);


        //getting the hero of the specified position
        final FacultyFeedbackCumulativeDashboardListItems hero = facultyFeedbackLikeDisplayListItems.get(position);

        //adding values to the list item

        if (hero.getType().equals("like")) {
            text_rating_questions.setText(hero.getQuestion());
            average.setText("Like Percentage : " + hero.getAverage() + "%");
            median.setVisibility(View.GONE);
        } else {
            text_rating_questions.setText(hero.getQuestion());
            average.setText("Average : " + hero.getAverage());
            median.setText("Median : " + hero.getMedian());
        }
        //finally returning the view
        return view;
    }
}