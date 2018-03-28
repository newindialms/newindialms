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

public class ProgramScreenCourseAdapter  extends ArrayAdapter<ProgramScreenCourseListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCourseListItems> programScreenCourseListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenCourseAdapter(Context context, int resource, List<ProgramScreenCourseListItems> programScreenCourseListItems) {
        super(context, resource, programScreenCourseListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenCourseListItems = programScreenCourseListItems;
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
        TextView studentpic_programscreencourselist = (TextView)view.findViewById(R.id.studentpic_programscreencourselist);
        ImageView studentPicarrow = (ImageView)view.findViewById(R.id.studentPicarrow);

        //getting the hero of the specified position
        final ProgramScreenCourseListItems hero = programScreenCourseListItems.get(position);

        //adding values to the list item
        studentpic_programscreencourselist.setText(hero.getCoursename());
        studentPicarrow.setImageResource(R.drawable.ic_right);

        studentPicarrow.setOnClickListener(new View.OnClickListener() {

            String allcoursename=hero.getCoursename();
            @Override
            public void onClick(View view) {
                Intent allcourseintent = new Intent(context, ProgramScreenAllCourses.class);
                allcourseintent.putExtra("allcoursename",allcoursename);
                allcourseintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(allcourseintent);
            }
        });
        //finally returning the view
        return view;
    }
}