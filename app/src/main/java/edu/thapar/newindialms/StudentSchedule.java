package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class StudentSchedule extends Fragment{

    TextView faculty_schedule_title;
    Toolbar studentpic_toolbar;
    String student_specialization,studentyear,studentid;
    View rootView;
    Button ScheduleButton;
    private String datevalue;
    private android.widget.CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_schedule, null);
        studentyear = getArguments().getString("studentyear");
        studentid = getArguments().getString("studentid");
        student_specialization = getArguments().getString("student_specialization");
        //Toast.makeText(getContext(),"student year"+studentyear,Toast.LENGTH_LONG).show();
        calendarView= (android.widget.CalendarView)rootView.findViewById(R.id.schedule_calendarView);
        ScheduleButton=(Button)rootView.findViewById(R.id.ScheduleButton);

        calendarView.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull android.widget.CalendarView calendarView, int yearval, int monthval, int dateval) {
                datevalue=dateval+"-"+(monthval+1)+"-"+yearval;
            }
        });

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(studentyear.equals("1")) {
                    Intent facultyintent = new Intent(getActivity(), StudentScheduleDisplayFirstYear.class);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("studentid", studentid);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getContext(), "selected Date is " + datevalue, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(), "student_specialization is " + student_specialization, Toast.LENGTH_LONG).show();
                        getActivity().startActivity(facultyintent);
                    } else {
                        Toast.makeText(getActivity(), "Please Select a Date", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Intent facultyintent = new Intent(getActivity(), StudentScheduleDisplay.class);
                    facultyintent.putExtra("student_specialization", student_specialization);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                    if (datevalue != null && !datevalue.isEmpty()) {
                        Toast.makeText(getContext(), "selected Date is " + datevalue, Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(), "student_specialization is " + student_specialization, Toast.LENGTH_LONG).show();
                        getActivity().startActivity(facultyintent);
                    } else {
                        Toast.makeText(getActivity(), "Select Date", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;
    }
}