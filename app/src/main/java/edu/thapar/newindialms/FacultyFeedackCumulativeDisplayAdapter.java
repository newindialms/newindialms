package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedackCumulativeDisplayAdapter extends ArrayAdapter<FacultyFeedbackCumulativeDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackCumulativeDisplayListItems> facultyFeedbackLikeDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyFeedackCumulativeDisplayAdapter(Context context, int resource, List<FacultyFeedbackCumulativeDisplayListItems> facultyFeedbackLikeDisplayListItems) {
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
        RelativeLayout relative1 = view.findViewById(R.id.relative1);
        TextView text_rating_questions = view.findViewById(R.id.text_rating_questions);
        ImageView right_arrow = view.findViewById(R.id.faculty_courselist_rightarrow2);


        //getting the hero of the specified position
        final FacultyFeedbackCumulativeDisplayListItems hero = facultyFeedbackLikeDisplayListItems.get(position);

        //adding values to the list item
        text_rating_questions.setText(hero.getQuestion());
        right_arrow.setImageResource(R.drawable.faculty_rightarrow);

        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(context, FacultyFeedbackCummulativeDashboard.class);
                facultyintent.putExtra("coursename", hero.getCoursename());
                facultyintent.putExtra("question", hero.getQuestion());
                facultyintent.putExtra("type", hero.getType());
                facultyintent.putExtra("faculty_employeeid", hero.getFacultyid());
                facultyintent.putExtra("feedback_sent_date", hero.getDateval());
                // Toast.makeText(getContext(),hero.getCoursename()+hero.getQuestion()+hero.getType()+hero.getFacultyid()+hero.getDateval(),Toast.LENGTH_LONG).show();
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);
            }
        });

        //finally returning the view
        return view;
    }
}