package edu.thapar.newindialms;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SpecializationScreenAdapter extends ArrayAdapter<SpecializationScreenListItems> {

    //the list values in the List of type hero
    List<SpecializationScreenListItems> specializationScreenListItemses;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public SpecializationScreenAdapter(Context context, int resource, List<SpecializationScreenListItems> specializationScreenListItems) {
        super(context, resource, specializationScreenListItems);
        this.context = context;
        this.resource = resource;
        this.specializationScreenListItemses = specializationScreenListItems;
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
        TextView studentpic_programscreenspecializationlist = (TextView) view.findViewById(R.id.enrolledcourselist_name);

        //getting the hero of the specified position
        final SpecializationScreenListItems hero = specializationScreenListItemses.get(position);

        //adding values to the list item
        studentpic_programscreenspecializationlist.setText(hero.getSpecializationdetails());


        //finally returning the view
        return view;
    }
}