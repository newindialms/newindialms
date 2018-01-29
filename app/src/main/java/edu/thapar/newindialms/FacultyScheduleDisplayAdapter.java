package edu.thapar.newindialms;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static edu.thapar.newindialms.R.id.faculty_courselist_attendance_display_name;
import static edu.thapar.newindialms.R.id.faculty_courselist_attendance_display_rollno;
import static edu.thapar.newindialms.R.id.faculty_courselist_display_status;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyScheduleDisplayAdapter extends ArrayAdapter<FacultyScheduleDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyScheduleDisplayListItems> facultyScheduleDisplayListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public FacultyScheduleDisplayAdapter(Context context, int resource, List<FacultyScheduleDisplayListItems> facultyScheduleDisplayListItems) {
        super(context, resource, facultyScheduleDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyScheduleDisplayListItems = facultyScheduleDisplayListItems;
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
        TextView faculty_scheduledisplay_program = (TextView) view.findViewById(R.id.faculty_scheduledisplay_program);
        TextView faculty_scheduledisplay_starttime = (TextView) view.findViewById(R.id.faculty_scheduledisplay_starttime);
        TextView faculty_scheduledisplay_endtime = (TextView) view.findViewById(R.id.faculty_scheduledisplay_endtime);
        TextView faculty_scheduledisplay_course = (TextView) view.findViewById(R.id.faculty_scheduledisplay_course);




        //getting the hero of the specified position
        final FacultyScheduleDisplayListItems hero = facultyScheduleDisplayListItems.get(position);

        //adding values to the list item
        faculty_scheduledisplay_program.setText(hero.getProgram());
        faculty_scheduledisplay_starttime.setText(hero.getStarttime());
        faculty_scheduledisplay_endtime.setText(hero.getEndtime());
        faculty_scheduledisplay_course.setText(hero.getCourse());

        //finally returning the view
        return view;
    }
}