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
 * Created by kamalshree on 11/10/2017.
 */

public class ProgramScreenCourseScheduleFirstyearAdapter extends ArrayAdapter<ProgramScreenCourseScheduleListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCourseScheduleListItems> programScreenCourseScheduleListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenCourseScheduleFirstyearAdapter(Context context, int resource, List<ProgramScreenCourseScheduleListItems> programScreenCourseScheduleListItems) {
        super(context, resource, programScreenCourseScheduleListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenCourseScheduleListItems = programScreenCourseScheduleListItems;
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
        TextView timetable_day = (TextView) view.findViewById(R.id.timetable_day);
        TextView timetable_date = (TextView) view.findViewById(R.id.timetable_date);
        TextView timetable_startdate = (TextView) view.findViewById(R.id.timetable_startdate);
        TextView timetable_enddate = (TextView) view.findViewById(R.id.timetable_enddate);
        TextView timetable_course = (TextView) view.findViewById(R.id.timetable_course);
        TextView textView13 = (TextView) view.findViewById(R.id.textView13);
        TextView timetable_faculty = (TextView) view.findViewById(R.id.timetable_faculty);
        TextView timetable_program = (TextView) view.findViewById(R.id.timetable_program);
        TextView timetable_program_group = (TextView) view.findViewById(R.id.timetable_program_group);
        TextView timetable_program_batch = (TextView) view.findViewById(R.id.timetable_program_batch);


        //getting the hero of the specified position
        final ProgramScreenCourseScheduleListItems hero = programScreenCourseScheduleListItems.get(position);

        //adding values to the list item
        timetable_day.setText(hero.getCourse_schedule_day());
        timetable_date.setText(hero.getCourse_date());
        timetable_startdate.setText("Time- " + hero.getCourse_schedule_time());


        timetable_enddate.setText("Classroom- " + hero.getCourse_classroom());
        timetable_course.setText(hero.getCourse_name());

        String textView13text = textView13.getText().toString();
        textView13.setText(textView13text);

        timetable_faculty.setText(hero.getFaculty_code());
        timetable_program.setText(hero.getTimetable_program());

        timetable_program_group.setText("Group- " + hero.getStudent_group());
        timetable_program_batch.setText("Section- " + hero.getStudent_batch());
        //finally returning the view
        return view;
    }
}