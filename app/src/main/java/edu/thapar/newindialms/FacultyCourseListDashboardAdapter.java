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

public class FacultyCourseListDashboardAdapter extends ArrayAdapter<FacultyCourseListDashboardListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListDashboardListItems> facultyCourseListDashboardListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

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
        RelativeLayout relative1 = view.findViewById(R.id.relative1);
        RelativeLayout relative2 = view.findViewById(R.id.relative2);
        TextView faculty_courselist_dashboard_attendance = view.findViewById(R.id.faculty_courselist_dashboard_attendance);
        ImageView faculty_courselist_rightarrow1 = view.findViewById(R.id.faculty_courselist_rightarrow1);
        TextView faculty_courselist_dashboard_feedback = view.findViewById(R.id.faculty_courselist_dashboard_feedback);
        ImageView faculty_courselist_rightarrow2 = view.findViewById(R.id.faculty_courselist_rightarrow2);


        //getting the hero of the specified position
        final FacultyCourseListDashboardListItems hero = facultyCourseListDashboardListItems.get(position);

        //adding values to the list item
        faculty_courselist_dashboard_attendance.setText(hero.getAttendance());
        faculty_courselist_dashboard_feedback.setText(hero.getFeedback());
        faculty_courselist_rightarrow1.setImageResource(R.drawable.faculty_rightarrow);
        faculty_courselist_rightarrow2.setImageResource(R.drawable.faculty_rightarrow);

        relative1.setOnClickListener(new View.OnClickListener() {

            String coursename = hero.getCoursename();

            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(context, FacultyCourseListAttendance.class);
                facultyintent.putExtra("coursename", coursename);
                facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                facultyintent.putExtra("coursetype", hero.getCoursetype());
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);
            }
        });

        relative2.setOnClickListener(new View.OnClickListener() {

            String coursename = hero.getCoursename();

            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(context, FacultyFeedbackWiseDashboard.class);
                facultyintent.putExtra("coursename", coursename);
                facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);
            }
        });
        //finally returning the view
        return view;
    }
}