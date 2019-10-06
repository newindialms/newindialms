package edu.thapar.newindialms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrollcourseAdapter extends ArrayAdapter<EnrollcourseListItems> {

    //the list values in the List of type hero
    List<EnrollcourseListItems> enrollcourseListItemses;

    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    //constructor initializing the values
    public EnrollcourseAdapter(Context context, int resource, List<EnrollcourseListItems> enrollcourseListItemses) {
        super(context, resource, enrollcourseListItemses);
        this.context = context;
        this.resource = resource;
        this.enrollcourseListItemses = enrollcourseListItemses;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
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
        TextView studentenrollcourse_name = view.findViewById(R.id.studentenrollcourse_name);
        TextView studentenrollcourse_code = view.findViewById(R.id.studentenrollcourse_code);
        TextView studentenrollcourse_faculty = view.findViewById(R.id.studentenrollcourse_faculty);
        TextView studentenrollcourse_credits = view.findViewById(R.id.studentenrollcourse_credits);
        CheckBox studentenrollcourse_checkbox = view.findViewById(R.id.enrollcheckBox);
        //Button enrollButton=(Button)view1.findViewById(R.id.EnrollButton);
        //getting the hero of the specified position
        final EnrollcourseListItems hero1 = enrollcourseListItemses.get(position);

        //adding values to the list item
        studentenrollcourse_name.setText(hero1.getCoursedetails_name());
        studentenrollcourse_code.setText(hero1.getCoursedetails_code());
        studentenrollcourse_faculty.setText(hero1.getCoursedetails_faculty());
        studentenrollcourse_credits.setText(hero1.getCoursedetails_credits());

        studentenrollcourse_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (ischecked) {
                    hero1.setSelected(ischecked);
                    //selectedStrings.add(hero1.getCoursedetails_name());
                }
                if (!ischecked) {
                    hero1.setSelected(false);
                    // selectedStrings.remove(hero1.getCoursedetails_name());
                }
                // Toast.makeText(context,selectedStrings+"Selected Strings",Toast.LENGTH_LONG).show();
            }
        });
        studentenrollcourse_checkbox.setChecked(hero1.isSelected());
        //finally returning the view
        return view;
    }
}