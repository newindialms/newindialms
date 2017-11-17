package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static edu.thapar.newindialms.R.id.faculty_courselist_attendance_take_roolno;
import static edu.thapar.newindialms.R.id.faculty_courselist_rightarrow1;
import static edu.thapar.newindialms.R.id.faculty_courselist_rightarrow2;
import static edu.thapar.newindialms.R.id.linearLayout;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListTakeAttendanceAdapter extends ArrayAdapter<FacultyCourseListTakeAttendanceListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems;

    //activity context
    Context context;
    //the layout resource file for the list items
    int resource, resource1;
    String status = "present";
    List<String> absentlist;
    View view;
    LayoutInflater layoutinflater;
    TextView faculty_courselist_attendance_take, faculty_courselist_attendance_take_roolno;
    Switch attendance_switch;
    Button submitbutton;

    //constructor initializing the values
    public FacultyCourseListTakeAttendanceAdapter(Context context, int resource, int resource1, List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems) {
        super(context, resource, facultyCourseListTakeAttendanceListItems);
        this.context = context;
        this.resource = resource;
        this.resource1 = resource1;
        this.facultyCourseListTakeAttendanceListItems = facultyCourseListTakeAttendanceListItems;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (convertView == null) {

            view = layoutInflater.inflate(resource, null, false);

        }

        //getting the view elements of the list from the view
        faculty_courselist_attendance_take = (TextView) view.findViewById(R.id.faculty_courselist_attendance_take);
        faculty_courselist_attendance_take_roolno = (TextView) view.findViewById(R.id.faculty_courselist_attendance_take_roolno);
        attendance_switch = (Switch) view.findViewById(R.id.simpleSwitchtakeattendance);


        //getting the hero of the specified position
        final FacultyCourseListTakeAttendanceListItems hero = facultyCourseListTakeAttendanceListItems.get(position);

        //adding values to the list item
        faculty_courselist_attendance_take.setText(hero.getStudentname());
        faculty_courselist_attendance_take_roolno.setText(hero.getStudentrollno());
        attendance_switch.setChecked(true);
        absentlist = new ArrayList<>();
        attendance_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    status = "Present";
                } else {
                    status = "Absent";//edit here
                    Toast.makeText(getContext(), hero.getStudentrollno().toString(), Toast.LENGTH_LONG).show();
                    absentlist.add(hero.getStudentrollno().toString());
                    hero.setStatus(true);
                }

            }
        });

        //finally returning the view
        return view;
    }

    ArrayList<FacultyCourseListTakeAttendanceListItems> getattendanceDetails() {
        ArrayList<FacultyCourseListTakeAttendanceListItems> attendance = new ArrayList<>();
        for (FacultyCourseListTakeAttendanceListItems attendancevalue : facultyCourseListTakeAttendanceListItems) {
            if (attendancevalue.isStatus())
                attendance.add(attendancevalue);
        }
        return attendance;
    }



}