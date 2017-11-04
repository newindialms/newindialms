package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static edu.thapar.newindialms.R.id.Studentpic_programstudentfulllist_total;


/**
 * Created by kamalshree on 11/2/2017.
 */

public class ProgramScreenStudentFullListAdapter extends ArrayAdapter<ProgramScreenStudentFullListListItems> {

    //the list values in the List of type hero
    List<ProgramScreenStudentFullListListItems> programScreenStudentFullListListItems;
    int rowvalue;
    ListView lv;
    //activity context
    Context context;
    TextView Studentpic_programstudentfulllist_total;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenStudentFullListAdapter(Context context, int resource, List<ProgramScreenStudentFullListListItems> programScreenStudentFullListListItems, int rowcount) {
        super(context, resource, programScreenStudentFullListListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenStudentFullListListItems = programScreenStudentFullListListItems;
        this.rowvalue=rowcount;
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
        TextView Studentpic_programstudentfulllist_name = (TextView)view.findViewById(R.id.Studentpic_programstudentfulllist_name);
        TextView Studentpic_programstudentfulllist_rollno = (TextView)view.findViewById(R.id.Studentpic_programstudentfulllist_rollno);


        //getting the hero of the specified position
        final ProgramScreenStudentFullListListItems hero = programScreenStudentFullListListItems.get(position);
        //adding values to the list item
        Studentpic_programstudentfulllist_name.setText(hero.getName());
        Studentpic_programstudentfulllist_rollno.setText(hero.getRollno());

        //finally returning the view
        return view;
    }
}