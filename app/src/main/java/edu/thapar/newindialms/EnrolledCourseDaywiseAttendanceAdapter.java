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

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDaywiseAttendanceAdapter extends ArrayAdapter<EnrolledCourseDaywiseAttendanceListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseDaywiseAttendanceListItems> enrolledCourseDaywiseAttendanceListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public EnrolledCourseDaywiseAttendanceAdapter(Context context, int resource, List<EnrolledCourseDaywiseAttendanceListItems> enrolledCourseDaywiseAttendanceListItems) {
        super(context, resource, enrolledCourseDaywiseAttendanceListItems);
        this.context = context;
        this.resource = resource;
        this.enrolledCourseDaywiseAttendanceListItems = enrolledCourseDaywiseAttendanceListItems;
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
        TextView enrollcourse_daywise_display_date = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_date);
        TextView enrollcourse_daywise_display_time = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_time);
        TextView enrollcourse_daywise_display_day = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_day);
        TextView enrollcourse_daywise_display_status = (TextView) view.findViewById(R.id.enrollcourse_daywise_display_status);




        //getting the hero of the specified position
        final EnrolledCourseDaywiseAttendanceListItems hero = enrolledCourseDaywiseAttendanceListItems.get(position);

        //adding values to the list item
        enrollcourse_daywise_display_status.setText(hero.getDaywise_status());
        if (hero.getDaywise_status().equals("Present")) {

            enrollcourse_daywise_display_status.setTextColor(Color.GREEN);
        }
        else{
            enrollcourse_daywise_display_status.setTextColor(Color.RED);
        }
        enrollcourse_daywise_display_date.setText(hero.getDaywise_date());
        enrollcourse_daywise_display_time.setText(hero.getDaywise_time());
        enrollcourse_daywise_display_day.setText(hero.getDaywise_day());

        //finally returning the view
        return view;
    }
}