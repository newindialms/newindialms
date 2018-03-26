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

/**
 * Created by kamalshree on 10/20/2017.
 */

//we need to extend the ArrayAdapter class as we are building an adapter
public class StudentPicProgramAdapter extends ArrayAdapter<StudentPicListItems> {

    //the list values in the List of type hero
    List<StudentPicListItems> studentPicListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public StudentPicProgramAdapter(Context context, int resource, List<StudentPicListItems> studentPicListItems) {
        super(context, resource, studentPicListItems);
        this.context = context;
        this.resource = resource;
        this.studentPicListItems = studentPicListItems;
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
        TextView studentpic_programlist = (TextView)view.findViewById(R.id.studentpic_programlist);
        ImageView studentpic_programlistarrow = (ImageView)view.findViewById(studentPicarrow);

        //getting the hero of the specified position
        final StudentPicListItems hero = studentPicListItems.get(position);

        //adding values to the list item
        studentpic_programlist.setText(hero.getProgramName());
        studentpic_programlistarrow.setImageResource(R.drawable.ic_right);


        studentpic_programlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                String pgname=hero.getProgramName();
                Intent programintent = new Intent(context, ProgramScreen.class);
                programintent.putExtra("programname",pgname);
                programintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(programintent);
            }
        });

        //finally returning the view
        return view;
    }
}