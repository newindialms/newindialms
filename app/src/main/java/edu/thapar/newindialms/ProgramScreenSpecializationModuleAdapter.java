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
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenSpecializationModuleAdapter extends ArrayAdapter<ProgramScreenSpecializationModuleListItems> {

    //the list values in the List of type hero
    List<ProgramScreenSpecializationModuleListItems> programScreenSpecializationModuleListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenSpecializationModuleAdapter(Context context, int resource, List<ProgramScreenSpecializationModuleListItems> programScreenSpecializationModuleListItems) {
        super(context, resource, programScreenSpecializationModuleListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenSpecializationModuleListItems = programScreenSpecializationModuleListItems;
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
        TextView studentpic_programscreenspecializationmodule = (TextView) view.findViewById(R.id.studentpic_programscreenspecializationmodule);
        ImageView studentPicarrow = (ImageView) view.findViewById(R.id.studentPicarrow);


        //getting the hero of the specified position
        final ProgramScreenSpecializationModuleListItems hero1 = programScreenSpecializationModuleListItems.get(position);

        //adding values to the list item
        studentpic_programscreenspecializationmodule.setText(hero1.getCoursename());
        studentPicarrow.setImageResource(R.drawable.ic_right);


        studentPicarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method

                String specializationname=hero1.getSpecializationname();
                Intent specializationintent = new Intent(context, ProgramScreenCourseModule.class);
                specializationintent.putExtra("specializationname",specializationname);
                specializationintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(specializationintent);

            }
        });

        //finally returning the view
        return view;
    }
}