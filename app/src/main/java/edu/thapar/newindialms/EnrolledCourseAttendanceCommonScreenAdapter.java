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
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseAttendanceCommonScreenAdapter extends ArrayAdapter<EnrolledCourseAttendanceCommonScreenListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseAttendanceCommonScreenListItems> enrolledCourseAttendanceCommonScreenListItemses;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public EnrolledCourseAttendanceCommonScreenAdapter(Context context, int resource, List<EnrolledCourseAttendanceCommonScreenListItems> enrolledCourseAttendanceCommonScreenListItems) {
        super(context, resource, enrolledCourseAttendanceCommonScreenListItems);
        this.context = context;
        this.resource = resource;
        this.enrolledCourseAttendanceCommonScreenListItemses = enrolledCourseAttendanceCommonScreenListItems;
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
        TextView enrolledcourselist_daywise = (TextView) view.findViewById(R.id.enrolledcourselist_daywise);
        TextView enrolledcourselist_cumulative = (TextView) view.findViewById(R.id.enrolledcourselist_cumulative);

        ImageView studentPicarrow1 = (ImageView) view.findViewById(R.id.studentPicarrow1);
        ImageView studentPicarrow2 = (ImageView) view.findViewById(R.id.studentPicarrow2);



        //getting the hero of the specified position
        final EnrolledCourseAttendanceCommonScreenListItems hero = enrolledCourseAttendanceCommonScreenListItemses.get(position);

        //adding values to the list item
        enrolledcourselist_daywise.setText(hero.getDaywise());
        studentPicarrow1.setImageResource(R.drawable.student_right_arrow);
        enrolledcourselist_cumulative.setText(hero.getCumulative());
        studentPicarrow2.setImageResource(R.drawable.student_right_arrow);

        studentPicarrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course_details_name=hero.getEnrolledcoursename();
                String student_rollnno=hero.getStudentrollno();

                Intent daywiseintent = new Intent(context, EnrolledCourseDaywiseAttendanceActivity.class);
                daywiseintent.putExtra("course_details_name",course_details_name);
                daywiseintent.putExtra("student_rollnno",student_rollnno);
                daywiseintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(daywiseintent);
            }
        });

        studentPicarrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course_details_name=hero.getEnrolledcoursename();
                String student_rollnno=hero.getStudentrollno();

                Intent daywiseintent = new Intent(context, EnrolledCourseCumulativeAttendanceActivity.class);
                daywiseintent.putExtra("course_details_name",course_details_name);
                daywiseintent.putExtra("student_rollnno",student_rollnno);
                daywiseintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(daywiseintent);
            }
        });

        //finally returning the view
        return view;
    }
}