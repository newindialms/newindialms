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

public class ProgramScreenAllCoursesAdapter extends ArrayAdapter<ProgramScreenAllCoursesListItems> {

    //the list values in the List of type hero
    List<ProgramScreenAllCoursesListItems> programScreenAllCoursesListItems;
    //activity context
    private Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenAllCoursesAdapter(Context context, int resource, List<ProgramScreenAllCoursesListItems> programScreenAllCoursesListItems, int rowcount) {
        super(context, resource, programScreenAllCoursesListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenAllCoursesListItems = programScreenAllCoursesListItems;

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
        TextView Studentpic_programstudentallcourselist_name = (TextView)view.findViewById(R.id.Studentpic_programstudentallcourselist_name);
        TextView Studentpic_programstudentallcourselist_rollno = (TextView)view.findViewById(R.id.Studentpic_programstudentallcourselist_rollno);


        //getting the hero of the specified position
        final ProgramScreenAllCoursesListItems hero = programScreenAllCoursesListItems.get(position);
        //adding values to the list item
        Studentpic_programstudentallcourselist_name.setText(hero.getName());
        Studentpic_programstudentallcourselist_rollno.setText(hero.getRollno());

        //finally returning the view
        return view;
    }
}