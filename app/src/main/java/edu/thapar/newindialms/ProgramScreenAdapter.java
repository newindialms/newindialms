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

import static edu.thapar.newindialms.R.id.Studentpic_program_title;
import static edu.thapar.newindialms.R.id.studentPicarrow;
import static edu.thapar.newindialms.R.id.studentPicarrow1;
import static edu.thapar.newindialms.R.id.studentPicarrow2;
import static edu.thapar.newindialms.R.id.studentPicarrow3;
import static edu.thapar.newindialms.R.id.studentpic_programlist;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenAdapter extends ArrayAdapter<ProgramScreenListItems> {

    //the list values in the List of type hero
    List<ProgramScreenListItems> programScreenListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenAdapter(Context context, int resource, List<ProgramScreenListItems> programScreenListItems) {
        super(context, resource, programScreenListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenListItems = programScreenListItems;
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
        TextView studentpic_programscreenyear = (TextView) view.findViewById(R.id.studentpic_programscreenyear);
        TextView studentpic_programscreenspecialization = (TextView) view.findViewById(R.id.studentpic_programscreenspecialization);
        TextView studentpic_programscreenourse = (TextView) view.findViewById(R.id.studentpic_programscreenourse);
        ImageView studentPicarrow1 = (ImageView) view.findViewById(R.id.studentPicarrow1);
        ImageView studentPicarrow2 = (ImageView) view.findViewById(R.id.studentPicarrow2);
        ImageView studentPicarrow3 = (ImageView) view.findViewById(R.id.studentPicarrow3);


        //getting the hero of the specified position
        final ProgramScreenListItems hero = programScreenListItems.get(position);

        //adding values to the list item
        studentpic_programscreenyear.setText(hero.getYearofjoining());
        studentPicarrow1.setImageResource(R.drawable.ic_right);
        studentpic_programscreenspecialization.setText(hero.getYearofspecialization());
        studentPicarrow2.setImageResource(R.drawable.ic_right);
        studentpic_programscreenourse.setText(hero.getYearofcourses());
        studentPicarrow3.setImageResource(R.drawable.ic_right);


        studentpic_programscreenyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method

                String pgname=hero.getProgramname();
                String yearname=hero.getYearofjoining();

                Intent yearintent = new Intent(context, ProgramScreenYear.class);
                yearintent.putExtra("yearlist",yearname);
                yearintent.putExtra("programname",pgname);
                yearintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(yearintent);
            }
        });

        //finally returning the view
        return view;
    }
}