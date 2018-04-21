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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListAttendanceAdapter extends ArrayAdapter<FacultyCourseListAttendanceListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListAttendanceListItems> facultyCourseListAttendanceListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public FacultyCourseListAttendanceAdapter(Context context, int resource, List<FacultyCourseListAttendanceListItems> facultyCourseListAttendanceListItems) {
        super(context, resource, facultyCourseListAttendanceListItems);
        this.context = context;
        this.resource = resource;
        this.facultyCourseListAttendanceListItems = facultyCourseListAttendanceListItems;
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
        RelativeLayout relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        RelativeLayout relative2 = (RelativeLayout) view.findViewById(R.id.relative2);
        TextView faculty_courselist_attendance_take = (TextView) view.findViewById(R.id.faculty_courselist_attendance_take);
        ImageView faculty_courselist_rightarrow1 = (ImageView) view.findViewById(R.id.faculty_courselist_rightarrow1);
        TextView faculty_courselist_attendance_see = (TextView) view.findViewById(R.id.faculty_courselist_attendance_see);
        ImageView faculty_courselist_rightarrow2 = (ImageView) view.findViewById(R.id.faculty_courselist_rightarrow2);


        //getting the hero of the specified position
        final FacultyCourseListAttendanceListItems hero = facultyCourseListAttendanceListItems.get(position);

        //adding values to the list item
        faculty_courselist_attendance_take.setText(hero.getTakeattendance());
        faculty_courselist_attendance_see.setText(hero.getSeeattendance());
        faculty_courselist_rightarrow1.setImageResource(R.drawable.faculty_rightarrow);
        faculty_courselist_rightarrow2.setImageResource(R.drawable.faculty_rightarrow);

        relative1.setOnClickListener(new View.OnClickListener() {

            String coursename = hero.getCoursename();
            String coursetype = hero.getCoursetype();

            @Override
            public void onClick(View view) {
                // Intent facultyintent = new Intent(context, FacultyCourseListTakeAttendance.class);
                if (coursetype.equals("1")) {
                    Intent facultyintent = new Intent(context, FacultyCourseListGroup.class);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("coursetype", coursetype);
                    facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(facultyintent);
                } else {
                    Intent facultyintent = new Intent(context, FacultyCourseListTakeAttendance.class);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("coursetype", coursetype);
                    facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(facultyintent);
                }

            }
        });

        relative2.setOnClickListener(new View.OnClickListener() {

            String coursename = hero.getCoursename();
            String coursetype = hero.getCoursetype();

            @Override
            public void onClick(View view) {

                Intent facultyintent = new Intent(context, FacultyCourseListViewAttendance.class);
                facultyintent.putExtra("coursename", coursename);
                facultyintent.putExtra("coursetype", coursetype);
                facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);


            }
        });
        //finally returning the view
        return view;
    }
}