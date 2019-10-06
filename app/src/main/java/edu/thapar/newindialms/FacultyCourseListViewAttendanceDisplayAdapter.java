package edu.thapar.newindialms;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListViewAttendanceDisplayAdapter extends ArrayAdapter<FacultyCourseListViewAttendanceDisplayListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListViewAttendanceDisplayListItems> facultyCourseListViewAttendanceDisplayListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyCourseListViewAttendanceDisplayAdapter(Context context, int resource, List<FacultyCourseListViewAttendanceDisplayListItems> facultyCourseListViewAttendanceDisplayListItems) {
        super(context, resource, facultyCourseListViewAttendanceDisplayListItems);
        this.context = context;
        this.resource = resource;
        this.facultyCourseListViewAttendanceDisplayListItems = facultyCourseListViewAttendanceDisplayListItems;
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
        TextView faculty_courselist_attendance_display_rollno = (TextView) view.findViewById(R.id.faculty_courselist_attendance_display_rollno);
        TextView faculty_courselist_attendance_display_name = (TextView) view.findViewById(R.id.faculty_courselist_attendance_display_name);
        TextView faculty_courselist_display_status = (TextView) view.findViewById(R.id.faculty_courselist_display_status);


        //getting the hero of the specified position
        final FacultyCourseListViewAttendanceDisplayListItems hero = facultyCourseListViewAttendanceDisplayListItems.get(position);

        //adding values to the list item
        faculty_courselist_attendance_display_name.setText(hero.getStudent_details());
        if (hero.getAttendance_status().equals("Present")) {

            faculty_courselist_display_status.setTextColor(Color.GREEN);
        }
        else if (hero.getAttendance_status().equals("Feedback Pending")) {

            faculty_courselist_display_status.setTextColor(Color.BLUE);
        }else {
            faculty_courselist_display_status.setTextColor(Color.RED);
        }
        faculty_courselist_display_status.setText(hero.getAttendance_status());
        faculty_courselist_attendance_display_rollno.setText(hero.getStudent_name());

        //finally returning the view
        return view;
    }
}