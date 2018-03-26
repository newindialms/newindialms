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

public class MyfeedbackEnrolledCourseAdapter extends ArrayAdapter<MyfeedbackEnrolledCourseListItems> {

    //the list values in the List of type hero
    List<MyfeedbackEnrolledCourseListItems> myfeedbackEnrolledCourseListItems;
    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;


    //constructor initializing the values
    public MyfeedbackEnrolledCourseAdapter(Context context, int resource, List<MyfeedbackEnrolledCourseListItems> myenrolledCourseListItems) {
        super(context, resource, myenrolledCourseListItems);
        this.context = context;
        this.resource = resource;
        this.myfeedbackEnrolledCourseListItems = myenrolledCourseListItems;
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
        TextView enrolledcourselist_name = (TextView) view.findViewById(R.id.myfeedbackenrolledcourselist_name);
        ImageView studentpic_programlistarrow = (ImageView)view.findViewById(R.id.myfeedback_rightarrow);

        //getting the hero of the specified position
        final MyfeedbackEnrolledCourseListItems hero = myfeedbackEnrolledCourseListItems.get(position);

        //adding values to the list item
        enrolledcourselist_name.setText(hero.getEnrolledcoursename());

        studentpic_programlistarrow.setOnClickListener(new View.OnClickListener() {
            String corecoursename=hero.getEnrolledcoursename();
            String studentid=hero.getStudentid();
            String course_date=hero.getCourse_date();
            String course_time=hero.getCourse_time();
            String faculty_employeeid=hero.getFaculty_employeeid();

            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(),"Adapter"+studentid+course_date+course_time,Toast.LENGTH_LONG).show();
                Intent submitfeedbackitent = new Intent(context, SubmitFeedBackScreen.class);
                submitfeedbackitent.putExtra("corecoursename",corecoursename);
                submitfeedbackitent.putExtra("studentid",studentid);
                submitfeedbackitent.putExtra("course_date",course_date);
                submitfeedbackitent.putExtra("course_time",course_time);
                submitfeedbackitent.putExtra("faculty_employeeid",faculty_employeeid);
                submitfeedbackitent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(submitfeedbackitent);
            }
        });



        //finally returning the view
        return view;
    }
}