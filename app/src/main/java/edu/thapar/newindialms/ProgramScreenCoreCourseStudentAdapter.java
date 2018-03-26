package edu.thapar.newindialms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenCoreCourseStudentAdapter extends ArrayAdapter<ProgramScreenCoreCourseStudentListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCoreCourseStudentListItems> programScreenCoreCourseStudentListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenCoreCourseStudentAdapter(Context context, int resource, List<ProgramScreenCoreCourseStudentListItems> programScreenCoreCourseStudentListItems, int rowcount) {
        super(context, resource, programScreenCoreCourseStudentListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenCoreCourseStudentListItems = programScreenCoreCourseStudentListItems;
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
        TextView Studentpic_programstudentcorecourselist_name = (TextView)view.findViewById(R.id.Studentpic_programstudentcorecourselist_name);
        TextView Studentpic_programstudentcorecourselist_rollno = (TextView)view.findViewById(R.id.Studentpic_programstudentcorecourselist_rollno);


        //getting the hero of the specified position
        final ProgramScreenCoreCourseStudentListItems hero = programScreenCoreCourseStudentListItems.get(position);
        //adding values to the list item
        Studentpic_programstudentcorecourselist_name.setText(hero.getName());
        Studentpic_programstudentcorecourselist_rollno.setText(hero.getRollno());

        //finally returning the view
        return view;
    }
}