package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackDashboardAdapter extends ArrayAdapter<FacultyFeedbackDashboardListItems> {

    //the list values in the List of type hero
    List<FacultyFeedbackDashboardListItems> facultyFeedbackDashboardListItems;
    Fragment fragment=null;
    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public FacultyFeedbackDashboardAdapter(Context context, int resource, List<FacultyFeedbackDashboardListItems> feedbackDashboardListItems) {
        super(context, resource, feedbackDashboardListItems);
        this.context = context;
        this.resource = resource;
        this.facultyFeedbackDashboardListItems = feedbackDashboardListItems;
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
        TextView faculty_feedback_dashboard_attendance = (TextView) view.findViewById(R.id.faculty_feedback_dashboard_type);
        ImageView faculty_feedback_rightarrow1 = (ImageView) view.findViewById(R.id.faculty_feedback_rightarrow1);



        //getting the hero of the specified position
        final FacultyFeedbackDashboardListItems hero = facultyFeedbackDashboardListItems.get(position);

        //adding values to the list item
        faculty_feedback_dashboard_attendance.setText(hero.getFeedbackType());
        faculty_feedback_rightarrow1.setImageResource(R.drawable.faculty_rightarrow);


        faculty_feedback_rightarrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String faculty_employeeid=hero.getFaculty_employeeid();
                String coursename=hero.getCoursename();
                String feedback_type=hero.getFeedbackType();
                Intent feedbackintent = new Intent(context, FacultyFeedbackScheduleDisplay.class);
                feedbackintent.putExtra("feedback_type",hero.getFeedbackType());
                feedbackintent.putExtra( "coursename", hero.getCoursename());
                feedbackintent.putExtra( "faculty_employeeid", hero.getFaculty_employeeid());
                //Toast.makeText(getContext(), "adapter"  + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();

                context.startActivity(feedbackintent);
            }
        });

        //finally returning the view
        return view;
    }
}