package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class ProgramScreenCourseModuleAdapter extends ArrayAdapter<ProgramScreenCourseModuleListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCourseModuleListItems> programScreenCourseModuleListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenCourseModuleAdapter(Context context, int resource, List<ProgramScreenCourseModuleListItems> programScreenCourseModuleListItems) {
        super(context, resource, programScreenCourseModuleListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenCourseModuleListItems = programScreenCourseModuleListItems;
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
        TextView studentpic_programscreencoursemodulelist = view.findViewById(R.id.studentpic_programscreencoursemodulelist);
        RelativeLayout relative1 = view.findViewById(R.id.relative1);
        TextView studentpic_programscreencoursemodulecode = view.findViewById(R.id.studentpic_programscreencoursemodulelistcode);
        ImageView studentpic_programlistarrow = view.findViewById(R.id.studentPicarrow);

        //getting the hero of the specified position
        final ProgramScreenCourseModuleListItems hero1 = programScreenCourseModuleListItems.get(position);

        //adding values to the list item
        studentpic_programscreencoursemodulelist.setText(hero1.getCoursemodulelist());
        studentpic_programscreencoursemodulecode.setText(hero1.getCoursecode());
        studentpic_programlistarrow.setImageResource(R.drawable.ic_right);


        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method

                String szname = hero1.getCoursemodulelist();

                Intent studentintent = new Intent(context, ProgramScreenStudentFullList.class);
                studentintent.putExtra("coursesname", szname);
                studentintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(studentintent);

            }
        });


        //finally returning the view
        return view;
    }
}