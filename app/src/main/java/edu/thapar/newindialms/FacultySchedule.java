package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import solar.blaz.date.week.WeekDatePicker;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class FacultySchedule extends Fragment {

    private String faculty_employeeid;
    View rootView;
    private Button ScheduleButton;
    private String datevalue,yearval,month_name;
    private TextView monthval;
    private solar.blaz.date.week.WeekDatePicker calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_faculty_schedule, null);
        FacultyMenu activity = (FacultyMenu) getActivity();
        faculty_employeeid = activity.getEmployeeid();
        calendarView = (solar.blaz.date.week.WeekDatePicker) rootView.findViewById(R.id.schedule_calendarView);
        monthval=(TextView) rootView.findViewById(R.id.faculty_schedule_month);
        ScheduleButton = (Button) rootView.findViewById(R.id.ScheduleButton);

        Calendar cal= Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        month_name  = month_date.format(cal.getTime());
        yearval=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        monthval.setText(month_name+" "+yearval);

        calendarView.setOnDateSelectedListener(new WeekDatePicker.OnDateSelected() {
            @Override
            public void onDateSelected(LocalDate date) {
                datevalue=date.toString();
               // Toast.makeText(getContext(), datevalue, Toast.LENGTH_SHORT).show();
            }
        });
       /* calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue = i2 + "-" + (i1 + 1) + "-" + i;
            }
        });*/

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(getActivity(), FacultyScheduleDisplay.class);
                facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                facultyintent.putExtra("datevalue", datevalue);
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (datevalue != null && !datevalue.isEmpty()) {
                    //Toast.makeText(getContext(),"selected Date is "+datevalue,Toast.LENGTH_LONG).show();
                    // Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                    getActivity().startActivity(facultyintent);
                } else {
                    Toast.makeText(getActivity(), "Please Select a Date", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.faculty_schedule_title));

    }
}
