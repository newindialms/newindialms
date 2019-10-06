package edu.thapar.newindialms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by kamalshree on 11/10/2017.
 */

public class ProgramScreenCourseScheduleAdapter extends ArrayAdapter<ProgramScreenCourseScheduleListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCourseScheduleListItems> programScreenCourseScheduleListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenCourseScheduleAdapter(Context context, int resource, List<ProgramScreenCourseScheduleListItems> programScreenCourseScheduleListItems) {
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
        TextView timetable_day = view.findViewById(R.id.timetable_day);
        TextView timetable_date = view.findViewById(R.id.timetable_date);
        TextView timetable_startdate = view.findViewById(R.id.timetable_startdate);
        TextView textView10 = view.findViewById(R.id.textView10);
        TextView timetable_enddate = view.findViewById(R.id.timetable_enddate);
        TextView timetable_course = view.findViewById(R.id.timetable_course);
        TextView textView13 = view.findViewById(R.id.textView13);
        TextView timetable_faculty = view.findViewById(R.id.timetable_faculty);
        TextView timetable_program = view.findViewById(R.id.timetable_program);


        //getting the hero of the specified position
        final ProgramScreenCourseScheduleListItems hero = programScreenCourseScheduleListItems.get(position);

        //adding values to the list item
        timetable_day.setText(hero.getTimetable_day());
        timetable_date.setText(hero.getTimetable_date());
        timetable_startdate.setText(hero.getTimetable_startdate());

        String textView10text = textView10.getText().toString();
        textView10.setText(textView10text);

        timetable_enddate.setText(hero.getTimetable_enddate());
        timetable_course.setText(hero.getTimetable_course());

        String textView13text = textView13.getText().toString();
        textView13.setText(textView13text);

        timetable_faculty.setText(hero.getTimetable_faculty());
        timetable_program.setText(hero.getTimetable_program());

        //finally returning the view
        return view;
    }
}