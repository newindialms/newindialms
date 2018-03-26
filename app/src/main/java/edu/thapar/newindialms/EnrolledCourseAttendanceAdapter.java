package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrolledCourseAttendanceAdapter extends ArrayAdapter<EnrolledCourseListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseListItems> enrolledCourseListItemses;
    //activity context
    private  Context context;

    //the layout resource file for the list items
    int resource;


    //constructor initializing the values
    public EnrolledCourseAttendanceAdapter(Context context, int resource, List<EnrolledCourseListItems> enrolledCourseListItems) {
        super(context, resource, enrolledCourseListItems);
        this.context = context;
        this.resource = resource;
        this.enrolledCourseListItemses = enrolledCourseListItems;
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
        TextView enrolledcourselist_name = (TextView) view.findViewById(R.id.enrolledcourselist_name);
        ImageView enrollattendance_arrow= (ImageView) view.findViewById(R.id.studentPicarrow) ;

        //getting the hero of the specified position
        final EnrolledCourseListItems hero = enrolledCourseListItemses.get(position);

        //adding values to the list item
        enrolledcourselist_name.setText(hero.getEnrolledcoursename());
        enrollattendance_arrow.setImageResource(R.drawable.student_right_arrow);


        enrollattendance_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enrolledcoursename=hero.getEnrolledcoursename();
                String studentid=hero.getStudentid();

                Intent yearintent = new Intent(context, EnrolledCourseAttendanceCommonScreen.class);
                yearintent.putExtra("enrolledcoursename",enrolledcoursename);
                yearintent.putExtra("student_rollnno",studentid);

                yearintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(yearintent);
            }
        });

        //finally returning the view
        return view;
    }
}