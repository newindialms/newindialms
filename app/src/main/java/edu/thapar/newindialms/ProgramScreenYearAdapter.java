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
import android.widget.Toast;

import java.util.List;

import static edu.thapar.newindialms.R.id.studentPicarrow;
import static edu.thapar.newindialms.R.id.studentpic_programlist;
import static edu.thapar.newindialms.R.id.studentpic_programscreenyear;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearAdapter extends ArrayAdapter<ProgramScreenYearListItems> {

    //the list values in the List of type hero
    List<ProgramScreenYearListItems> programScreenYearListItems;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public ProgramScreenYearAdapter(Context context, int resource, List<ProgramScreenYearListItems> programScreenYearListItems) {
        super(context, resource, programScreenYearListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenYearListItems = programScreenYearListItems;
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
        TextView studentpic_programscreenyearlist = (TextView)view.findViewById(R.id.studentpic_programscreenyearlist);
        ImageView studentpic_programlistarrow = (ImageView)view.findViewById(studentPicarrow);

        //getting the hero of the specified position
        final ProgramScreenYearListItems hero = programScreenYearListItems.get(position);

        //adding values to the list item
        studentpic_programscreenyearlist.setText(hero.getYeardetails());
        studentpic_programlistarrow.setImageResource(R.drawable.ic_right);

        studentpic_programscreenyearlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method

                String pgname=hero.getProgramname();
                String yearname=hero.getYeardetails();

                Intent yearintent = new Intent(context, ProgramScreenYearStudentName.class);
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