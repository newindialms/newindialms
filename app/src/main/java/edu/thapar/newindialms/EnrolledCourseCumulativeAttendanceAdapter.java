package edu.thapar.newindialms;

import android.content.Context;
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

public class EnrolledCourseCumulativeAttendanceAdapter extends ArrayAdapter<EnrolledCourseCumulativeAttendanceListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseCumulativeAttendanceListItems> enrolledCourseCumulativeAttendanceListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public EnrolledCourseCumulativeAttendanceAdapter(Context context, int resource, List<EnrolledCourseCumulativeAttendanceListItems> enrolledCourseCumulativeAttendanceListItems) {
        super(context, resource, enrolledCourseCumulativeAttendanceListItems);
        this.context = context;
        this.resource = resource;
        this.enrolledCourseCumulativeAttendanceListItems = enrolledCourseCumulativeAttendanceListItems;
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
        TextView enrollcourse_cumulative_display_total = (TextView) view.findViewById(R.id.enrollcourse_cumulative_display_total);
        TextView enrollcourse_cumulative_display_present = (TextView) view.findViewById(R.id.enrollcourse_cumulative_display_present);
        TextView enrollcourse_cumulative_display_absent = (TextView) view.findViewById(R.id.enrollcourse_cumulative_display_absent);
        TextView enrollcourse_cumulative_display_percentage = (TextView) view.findViewById(R.id.enrollcourse_cumulative_display_percentage);


        //getting the hero of the specified position
        final EnrolledCourseCumulativeAttendanceListItems hero = enrolledCourseCumulativeAttendanceListItems.get(position);

        //adding values to the list item
        enrollcourse_cumulative_display_total.setText(hero.getTotal_count());
        enrollcourse_cumulative_display_present.setText(hero.getPresent_count());
        enrollcourse_cumulative_display_absent.setText(hero.getAbsent_count());
        enrollcourse_cumulative_display_percentage.setText(hero.getPercentage());

        //finally returning the view
        return view;
    }
}