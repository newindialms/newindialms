package edu.thapar.newindialms;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListTakeAttendanceAdapter extends ArrayAdapter<FacultyCourseListTakeAttendanceListItems> {

    //the list values in the List of type hero
    List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems;

    //activity context
    Context context;
    //the layout resource file for the list items
    int resource;
    private String status = "present";
    List<String> absentlist, presentlist;
    View view;
    LayoutInflater layoutinflater;
    TextView faculty_courselist_attendance_take, faculty_courselist_attendance_take_roolno;
    Switch attendance_switch;

    //constructor initializing the values
    public FacultyCourseListTakeAttendanceAdapter(Context context, int resource, List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems) {
        super(context, resource, facultyCourseListTakeAttendanceListItems);
        this.context = context;
        this.resource = resource;
        this.facultyCourseListTakeAttendanceListItems = facultyCourseListTakeAttendanceListItems;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        view = layoutInflater.inflate(resource, null, false);


        //getting the view elements of the list from the view
        faculty_courselist_attendance_take = (TextView) view.findViewById(R.id.faculty_courselist_attendance_take);
        faculty_courselist_attendance_take_roolno = (TextView) view.findViewById(R.id.faculty_courselist_attendance_take_roolno);
        attendance_switch = (Switch) view.findViewById(R.id.simpleSwitchtakeattendance);


        //getting the hero of the specified position
        final FacultyCourseListTakeAttendanceListItems hero = facultyCourseListTakeAttendanceListItems.get(position);

        //adding values to the list item
        faculty_courselist_attendance_take.setText(hero.getStudentname() + " " + hero.getFname());
        faculty_courselist_attendance_take_roolno.setText(hero.getStudentrollno());
        attendance_switch.setChecked(true);
        absentlist = new ArrayList<>();
        presentlist = new ArrayList<>();
        attendance_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    status = "Present";
                    presentlist.add(hero.getStudentrollno().toString());
                } else {
                    status = "Absent";//edit here
                    //Toast.makeText(getContext(), hero.getStudentrollno().toString(), Toast.LENGTH_LONG).show();
                    absentlist.add(hero.getStudentrollno().toString());
                    hero.setStatus(true);
                }

            }
        });

        //finally returning the view
        return view;
    }

    ArrayList<FacultyCourseListTakeAttendanceListItems> getabsentattendanceDetails() {
        ArrayList<FacultyCourseListTakeAttendanceListItems> attendance = new ArrayList<>();
        for (FacultyCourseListTakeAttendanceListItems attendancevalue : facultyCourseListTakeAttendanceListItems) {
            if (attendancevalue.isStatus())
                attendance.add(attendancevalue);
        }
        return attendance;
    }

    ArrayList<FacultyCourseListTakeAttendanceListItems> getpresentattendanceDetails() {
        ArrayList<FacultyCourseListTakeAttendanceListItems> attendance = new ArrayList<>();
        for (FacultyCourseListTakeAttendanceListItems attendancevalue : facultyCourseListTakeAttendanceListItems) {
            if (!attendancevalue.isStatus())
                attendance.add(attendancevalue);
        }
        return attendance;
    }


}