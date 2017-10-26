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

import static edu.thapar.newindialms.R.id.studentPicarrow;
import static edu.thapar.newindialms.R.id.studentpic_programscreenyearlist;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class ProgramScreenSpecializationAdapter extends ArrayAdapter<ProgramScreenSpecializationListItems> {

    //the list values in the List of type hero
    List<ProgramScreenSpecializationListItems> programScreenSpecializationListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenSpecializationAdapter(Context context, int resource, List<ProgramScreenSpecializationListItems> programScreenSpecializationListItems) {
        super(context, resource, programScreenSpecializationListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenSpecializationListItems = programScreenSpecializationListItems;
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
        TextView studentpic_programscreenspecializationlist = (TextView)view.findViewById(R.id.studentpic_programscreenspecializationlist);
        ImageView studentpic_programlistarrow = (ImageView)view.findViewById(studentPicarrow);

        //getting the hero of the specified position
        final ProgramScreenSpecializationListItems hero = programScreenSpecializationListItems.get(position);

        //adding values to the list item
        studentpic_programscreenspecializationlist.setText(hero.getSpecializationdetails());
        studentpic_programlistarrow.setImageResource(R.drawable.ic_right);


        studentpic_programscreenspecializationlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method

                String specializationname=hero.getSpecializationdetails();

                Intent specializationintent = new Intent(context, ProgramScreenSpecializationModule.class);
                specializationintent.putExtra("specializationname",specializationname);
                specializationintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(specializationintent);

            }
        });
        //finally returning the view
        return view;
    }
}