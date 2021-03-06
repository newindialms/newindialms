package edu.thapar.newindialms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.like.LikeButton;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedackLikeDisplayAdapter extends ArrayAdapter<FacultyFeedbackLikeDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackLikeDisplayListItems> facultyFeedbackLikeDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyFeedackLikeDisplayAdapter(Context context, int resource, List<FacultyFeedbackLikeDisplayListItems> facultyFeedbackLikeDisplayListItems) {
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
        LikeButton like_button = view.findViewById(R.id.like_button);
        ImageView dislike_button = view.findViewById(R.id.dislikeButton);
        TextView text_rating_questions = view.findViewById(R.id.text_rating_questions);
        TextView enrollcourse_daywise_display_date = view.findViewById(R.id.enrollcourse_daywise_display_date);
        TextView enrollcourse_daywise_display_time = view.findViewById(R.id.enrollcourse_daywise_display_time);


        //getting the hero of the specified position
        final FacultyFeedbackLikeDisplayListItems hero = facultyFeedbackLikeDisplayListItems.get(position);

        //adding values to the list item
        text_rating_questions.setText(hero.getQuestion());
        enrollcourse_daywise_display_date.setText(hero.getDaywise_date());
        enrollcourse_daywise_display_time.setText(hero.getDaywise_time());

        if (hero.getLike().equals("Like")) {
            like_button.setVisibility(View.VISIBLE);
            like_button.setLiked(true);
            dislike_button.setVisibility(View.INVISIBLE);
        } else {
            dislike_button.setVisibility(View.VISIBLE);
            like_button.setVisibility(View.INVISIBLE);
        }
        //finally returning the view
        return view;
    }
}