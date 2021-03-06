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

import static edu.thapar.newindialms.R.id.studentPicarrow;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenCoreCourseAdapter extends ArrayAdapter<ProgramScreenCoreCourseListItems> {

    //the list values in the List of type hero
    List<ProgramScreenCoreCourseListItems> programScreenCoreCourseListItems;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public ProgramScreenCoreCourseAdapter(Context context, int resource, List<ProgramScreenCoreCourseListItems> programScreenCoreCourseListItems) {
        super(context, resource, programScreenCoreCourseListItems);
        this.context = context;
        this.resource = resource;
        this.programScreenCoreCourseListItems = programScreenCoreCourseListItems;
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
        RelativeLayout relative1 = view.findViewById(R.id.relative1);
        TextView studentpic_programcorecourse = view.findViewById(R.id.studentpic_programcorecourse);
        TextView studentpic_programcorecoursecode = view.findViewById(R.id.studentpic_programcorecoursecode);
        ImageView studentpic_programlistarrow = view.findViewById(studentPicarrow);

        //getting the hero of the specified position
        final ProgramScreenCoreCourseListItems hero = programScreenCoreCourseListItems.get(position);

        //adding values to the list item
        studentpic_programcorecourse.setText(hero.getCoursedetails());
        studentpic_programcorecoursecode.setText(hero.getCore_code());
        studentpic_programlistarrow.setImageResource(R.drawable.ic_right);

        relative1.setOnClickListener(new View.OnClickListener() {

            String corecoursename = hero.getCoursedetails();

            @Override
            public void onClick(View view) {
                Intent studentintent = new Intent(context, ProgramScreenCoreCourseStudent.class);
                studentintent.putExtra("corecoursename", corecoursename);
                studentintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(studentintent);
            }
        });

        //finally returning the view
        return view;
    }
}