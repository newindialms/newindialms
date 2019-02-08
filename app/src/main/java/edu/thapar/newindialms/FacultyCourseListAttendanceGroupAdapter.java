package edu.thapar.newindialms;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 1/18/2019.
 */

public class FacultyCourseListAttendanceGroupAdapter extends RecyclerView.Adapter<FacultyCourseListAttendanceGroupAdapter.FacultyCourseListTakeAttendanceViewHolder> {

    //the list values in the List of type hero
    List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems;

    //activity context
    private Context context;
    //the layout resource file for the list items
    int resource;
    private String status = "present";
    List<String> absentlist, presentlist;
    View view;
    LayoutInflater layoutinflater;


    //constructor initializing the values
    public FacultyCourseListAttendanceGroupAdapter(Context context,List<FacultyCourseListTakeAttendanceListItems> facultyCourseListTakeAttendanceListItems) {
        this.context = context;
        this.facultyCourseListTakeAttendanceListItems = facultyCourseListTakeAttendanceListItems;
    }

    @Override
    public FacultyCourseListAttendanceGroupAdapter.FacultyCourseListTakeAttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_faculty_courselist_takeattendance_group_listitems, null);
        return new FacultyCourseListTakeAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FacultyCourseListTakeAttendanceViewHolder holder, int position) {
        final FacultyCourseListTakeAttendanceListItems hero = facultyCourseListTakeAttendanceListItems.get(position);
        absentlist = new ArrayList<>();
        presentlist = new ArrayList<>();

        holder.faculty_courselist_attendance_take.setText(hero.getStudentname() + " " + hero.getFname());
        holder.faculty_courselist_attendance_take_roolno.setText(hero.getStudentrollno());
        holder.attendance_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    status = "Present";
                    presentlist.add(hero.getStudentrollno().toString());
                    hero.setStatus(false);
                } else {
                    status = "Absent";
                    absentlist.add(hero.getStudentrollno().toString());
                    hero.setStatus(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultyCourseListTakeAttendanceListItems.size();
    }

    class FacultyCourseListTakeAttendanceViewHolder extends RecyclerView.ViewHolder {

        TextView faculty_courselist_attendance_take, faculty_courselist_attendance_take_roolno;
        CheckBox attendance_switch;

        public FacultyCourseListTakeAttendanceViewHolder(View itemView) {
            super(itemView);

            //getting the view elements of the list from the view
            faculty_courselist_attendance_take = (TextView) itemView.findViewById(R.id.faculty_courselist_attendance_take);
            faculty_courselist_attendance_take_roolno = (TextView) itemView.findViewById(R.id.faculty_courselist_attendance_take_roolno);
            attendance_switch = (CheckBox) itemView.findViewById(R.id.simpleSwitchtakeattendance);
        }

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