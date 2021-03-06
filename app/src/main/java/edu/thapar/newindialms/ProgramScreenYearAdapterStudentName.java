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
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearAdapterStudentName extends ArrayAdapter<ProgramScreenYearStudentNameListItems> {

    //the list values in the List of type hero
    List<ProgramScreenYearStudentNameListItems> programScreenYearStudentNameListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenYearAdapterStudentName(Context context, int resource, List<ProgramScreenYearStudentNameListItems> programScreenYearStudentNameListItems) {
        super(context, resource, programScreenYearStudentNameListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenYearStudentNameListItems = programScreenYearStudentNameListItems;
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
        TextView studentpic_programscreenyearlist = view.findViewById(R.id.studentpic_programscreenstudentnamelist);
        TextView studentpic_programscreenstudentrollnolist = view.findViewById(R.id.studentpic_programscreenstudentrollnolist);
        TextView studentpic_programscreenstudentspecializationlist = view.findViewById(R.id.studentpic_programscreenstudentspecializationlist);

        //getting the hero of the specified position
        final ProgramScreenYearStudentNameListItems hero = programScreenYearStudentNameListItems.get(position);

        //adding values to the list item
        studentpic_programscreenyearlist.setText(hero.getStudentname() + " " + hero.getStudnetfirstname());
        studentpic_programscreenstudentrollnolist.setText(hero.getStudentrollno());
        if (hero.getStudentspecialization().equals("General")) {
            studentpic_programscreenstudentspecializationlist.setVisibility(View.GONE);
        } else {
            studentpic_programscreenstudentspecializationlist.setText(hero.getStudentspecialization());
        }
        //finally returning the view
        return view;
    }
}