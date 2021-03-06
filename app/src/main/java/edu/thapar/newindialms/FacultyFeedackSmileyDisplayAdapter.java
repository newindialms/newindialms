package edu.thapar.newindialms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hsalf.smilerating.SmileRating;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedackSmileyDisplayAdapter extends ArrayAdapter<FacultyFeedbackSmileyDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackSmileyDisplayListItems> facultyFeedbackSmileyDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyFeedackSmileyDisplayAdapter(Context context, int resource, List<FacultyFeedbackSmileyDisplayListItems> facultyFeedbackSmileyDisplayListItems) {
        super(context, resource, facultyFeedbackSmileyDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyFeedbackSmileyDisplayListItems = facultyFeedbackSmileyDisplayListItems;
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
        SmileRating smileybar = view.findViewById(R.id.smile_rating);
        TextView smileyquestion = view.findViewById(R.id.text_rating_questions);
        TextView enrollcourse_daywise_display_date = view.findViewById(R.id.enrollcourse_daywise_display_date);
        TextView enrollcourse_daywise_display_time = view.findViewById(R.id.enrollcourse_daywise_display_time);


        //getting the hero of the specified position
        final FacultyFeedbackSmileyDisplayListItems hero = facultyFeedbackSmileyDisplayListItems.get(position);

        //adding values to the list item
        smileyquestion.setText(hero.getQuestion());
        enrollcourse_daywise_display_date.setText(hero.getDaywise_date());
        enrollcourse_daywise_display_time.setText(hero.getDaywise_time());

        int smiley = Integer.parseInt(hero.getSmiley());
        // Toast.makeText(context,smiley,Toast.LENGTH_LONG).show();
        int smileyRating = 0;
        switch (smiley) {
            case 0:
                smileyRating = 0;
                break;
            case 1:
                smileyRating = 1;
                break;
            case 2:
                smileyRating = 2;
                break;
            case 3:
                smileyRating = 3;
                break;
            case 4:
                smileyRating = 4;
                break;
        }
        smileybar.setSelectedSmile(smileyRating, false);
        //finally returning the view
        return view;
    }
}