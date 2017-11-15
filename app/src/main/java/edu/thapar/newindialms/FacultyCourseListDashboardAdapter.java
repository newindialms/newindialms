package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static edu.thapar.newindialms.R.id.faculty_courselist_rightarrow;
import static edu.thapar.newindialms.R.id.studentPicarrow1;
import static edu.thapar.newindialms.R.id.studentPicarrow2;
import static edu.thapar.newindialms.R.id.studentPicarrow3;
import static edu.thapar.newindialms.R.id.studentpic_programscreencorecourses;
import static edu.thapar.newindialms.R.id.studentpic_programscreenourse;
import static edu.thapar.newindialms.R.id.studentpic_programscreenspecialization;
import static edu.thapar.newindialms.R.id.studentpic_programscreenyear;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListDashboardAdapter extends ArrayAdapter<FacultyCourseListDashboardListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListDashboardListItems> facultyCourseListDashboardListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public FacultyCourseListDashboardAdapter(Context context, int resource, List<FacultyCourseListDashboardListItems> facultyCourseListDashboardListItems) {
        super(context, resource, facultyCourseListDashboardListItems);
        this.context = context;
        this.resource = resource;
        this.facultyCourseListDashboardListItems = facultyCourseListDashboardListItems;
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
        TextView faculty_courselist_dashboard_attendance = (TextView) view.findViewById(R.id.faculty_courselist_dashboard_attendance);
        ImageView faculty_courselist_rightarrow1 = (ImageView) view.findViewById(R.id.faculty_courselist_rightarrow1);
        TextView faculty_courselist_dashboard_feedback = (TextView) view.findViewById(R.id.faculty_courselist_dashboard_feedback);
        ImageView faculty_courselist_rightarrow2 = (ImageView) view.findViewById(R.id.faculty_courselist_rightarrow2);



        //getting the hero of the specified position
        final FacultyCourseListDashboardListItems hero = facultyCourseListDashboardListItems.get(position);

        //adding values to the list item
        faculty_courselist_dashboard_attendance.setText(hero.getAttendance());
        faculty_courselist_dashboard_feedback.setText(hero.getFeedback());
        faculty_courselist_rightarrow1.setImageResource(R.drawable.faculty_rightarrow);
        faculty_courselist_rightarrow2.setImageResource(R.drawable.faculty_rightarrow);

        faculty_courselist_rightarrow1.setOnClickListener(new View.OnClickListener() {

            String coursename=hero.getCoursename();
            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(context, FacultyCourseListAttendance.class);
                facultyintent.putExtra("coursename",coursename);
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);
            }
        });
        //finally returning the view
        return view;
    }
}