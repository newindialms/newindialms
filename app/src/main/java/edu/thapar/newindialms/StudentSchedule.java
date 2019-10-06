package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class StudentSchedule extends Fragment {

    private String student_specialization, studentyear, studentid;
    View rootView;
    private Button ScheduleButton;
    private String datevalue,yearval,month_name;
    private CalendarView calendarView;
    private AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_schedule, null);
        studentyear = getArguments().getString("studentyear");
        studentid = getArguments().getString("studentid");
        student_specialization = getArguments().getString("student_specialization");
        //Toast.makeText(getContext(),"student year"+studentyear,Toast.LENGTH_LONG).show();
        calendarView = rootView.findViewById(R.id.schedule_calendarView);
        ScheduleButton = rootView.findViewById(R.id.ScheduleButton);


        Calendar cal= Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        month_name  = month_date.format(cal.getTime());
        yearval=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));


        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView calendarView, int yearval, int monthval, int dateval) {
                datevalue = dateval + "-" + (monthval + 1) + "-" + yearval;
            }
        });
        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (studentyear.equals("1")) {
                    Intent facultyintent = new Intent(getActivity(), StudentScheduleDisplayFirstYear.class);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("studentid", studentid);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getContext(), "selected Date is " + datevalue, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(), "student_specialization is " + student_specialization, Toast.LENGTH_LONG).show();
                        getActivity().startActivity(facultyintent);
                    } else {
                        //Toast.makeText(getActivity(), "Please Select a Date", Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(getContext(), R.style.MyStudentAlertDialogStyle);
                        builder.setTitle("Missing");
                        builder.setMessage("Please choose a Date.");
                        displayAlert();
                    }

                } else {
                    Intent facultyintent = new Intent(getActivity(), StudentScheduleDisplay.class);
                    facultyintent.putExtra("student_specialization", student_specialization);
                    facultyintent.putExtra("studentid", studentid);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getContext(), "selected Date is " + datevalue, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(), "student_specialization is " + student_specialization, Toast.LENGTH_LONG).show();
                        getActivity().startActivity(facultyintent);
                    } else {
                        //Toast.makeText(getActivity(), "Select Date", Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(getContext(), R.style.MyStudentAlertDialogStyle);
                        builder.setTitle("Missing");
                        builder.setMessage("Please choose a Date.");
                        displayAlert();
                    }
                }
            }
        });
        return rootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.faculty_schedule_title));
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}