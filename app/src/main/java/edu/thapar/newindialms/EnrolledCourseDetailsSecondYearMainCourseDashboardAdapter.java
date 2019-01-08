package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class EnrolledCourseDetailsSecondYearMainCourseDashboardAdapter extends ArrayAdapter<EnrolledCourseDetailsSecondYearMainCourseDashboardListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseDetailsSecondYearMainCourseDashboardListItems> programScreenListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public EnrolledCourseDetailsSecondYearMainCourseDashboardAdapter(Context context, int resource, List<EnrolledCourseDetailsSecondYearMainCourseDashboardListItems> programScreenListItems) {
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

        RelativeLayout course_elective = (RelativeLayout) view.findViewById(R.id.relative_elective);
        RelativeLayout course_core = (RelativeLayout) view.findViewById(R.id.relative_core);


        //getting the hero of the specified position
        final EnrolledCourseDetailsSecondYearMainCourseDashboardListItems hero = programScreenListItems.get(position);

        //adding values to the list item

        course_elective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electiveintent = new Intent(context, EnrolledCourseActivity.class);
                electiveintent.putExtra("studentid", hero.getStudentid());
                electiveintent.putExtra("studentyear", hero.getStudentyear());
                electiveintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(electiveintent);

            }
        });

        course_core.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent coreintent = new Intent(context, EnrolledCoreCourseActivity.class);
                coreintent.putExtra("studentid", hero.getStudentid());
                coreintent.putExtra("studentyear", hero.getStudentyear());
                coreintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(coreintent);

            }
        });

        //finally returning the view
        return view;
    }
}