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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class FacultyCourseListAdapter extends ArrayAdapter<FacultyListItemCourseList> {

    //the list values in the List of type hero
    List<FacultyListItemCourseList> facultyListItemCourseLists;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public FacultyCourseListAdapter(Context context, int resource, List<FacultyListItemCourseList> facultyListItemCourseLists) {
        super(context, resource, facultyListItemCourseLists);
        this.context = context;
        this.resource = resource;
        this.facultyListItemCourseLists = facultyListItemCourseLists;
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
        RelativeLayout relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        TextView faculty_cardviewCourselistTitle = (TextView) view.findViewById(R.id.faculty_cardviewCourselistTitle);
        TextView faculty_cardviewCourselistCode = (TextView) view.findViewById(R.id.faculty_cardviewCourselistCode);
        ImageView faculty_courselist_rightarrow = (ImageView) view.findViewById(R.id.faculty_courselist_rightarrow);

        //getting the hero of the specified position
        final FacultyListItemCourseList hero = facultyListItemCourseLists.get(position);

        //adding values to the list item
        faculty_cardviewCourselistTitle.setText(hero.getCourseListTitle());
        faculty_cardviewCourselistCode.setText(hero.getCoursecode());
        faculty_courselist_rightarrow.setImageResource(R.drawable.faculty_rightarrow);


        relative1.setOnClickListener(new View.OnClickListener() {

            String coursename = hero.getCourseListTitle();
            String coursetype = hero.getCourse_type();

            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(context, FacultyCourseListDashboard.class);
                facultyintent.putExtra("coursename", coursename);
                facultyintent.putExtra("coursetype", coursetype);
                facultyintent.putExtra("faculty_employeeid", hero.getFaculty_employeeid());
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(facultyintent);
            }
        });
        //finally returning the view
        return view;
    }
}
