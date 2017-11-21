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
import android.widget.CalendarView;

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
 * Created by kamalshree on 11/20/2017.
 */

public class FacultySchedule extends Fragment {
    TextView faculty_schedule_title;
    Toolbar studentpic_toolbar;
    String faculty_employeeid;
    View rootView;
    Button ScheduleButton;
    private String datevalue;
    private android.widget.CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_faculty_schedule, null);
        FacultyMenu activity = (FacultyMenu) getActivity();
        faculty_employeeid = activity.getEmployeeid();
        calendarView= (android.widget.CalendarView)rootView.findViewById(R.id.schedule_calendarView);
        ScheduleButton=(Button)rootView.findViewById(R.id.ScheduleButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue=i2+"-"+(i1+1)+"-"+i;
            }
        });

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(getActivity(), FacultyScheduleDisplay.class);
                facultyintent.putExtra("faculty_employeeid",faculty_employeeid);
                facultyintent.putExtra("datevalue",datevalue);
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (datevalue != null && !datevalue.isEmpty()) {
                    Toast.makeText(getContext(),"selected Date is "+datevalue,Toast.LENGTH_LONG).show();
                    Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                    getActivity().startActivity(facultyintent);
                }
                else{
                    Toast.makeText(getActivity(),"Select Date",Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}
