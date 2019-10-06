package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDetailsFirstYearDashboardAdapter extends ArrayAdapter<EnrolledCourseDetailsFirstYearDashboardListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseDetailsFirstYearDashboardListItems> programScreenListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public EnrolledCourseDetailsFirstYearDashboardAdapter(Context context, int resource, List<EnrolledCourseDetailsFirstYearDashboardListItems> programScreenListItems) {
        super(context, resource, programScreenListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenListItems = programScreenListItems;
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
        RelativeLayout relative_details = (RelativeLayout) view.findViewById(R.id.relative_details);
        TextView course_details_schedule = (TextView) view.findViewById(R.id.course_details_schedule);
        TextView course_details_details = (TextView) view.findViewById(R.id.course_details_details);
        TextView course_details_notification = (TextView) view.findViewById(R.id.course_details_notification);
        TextView course_details_marks = (TextView) view.findViewById(R.id.course_details_marks);



        //getting the hero of the specified position
        final EnrolledCourseDetailsFirstYearDashboardListItems hero = programScreenListItems.get(position);

        course_details_schedule.setText(hero.getCourseDetailsSchedule());
        course_details_details.setText(hero.getCourseDetailsDetails());
        course_details_notification.setText(hero.getCourseDetailsNotification());
        course_details_marks.setText(hero.getCourseDetailsMarks());
        //adding values to the list item

        course_details_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent facultyintent = new Intent(context, FirstYearFaculty.class);
                    facultyintent.putExtra("coursename", hero.getProgramname());
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(facultyintent);

            }
        });

        //finally returning the view
        return view;
    }
}