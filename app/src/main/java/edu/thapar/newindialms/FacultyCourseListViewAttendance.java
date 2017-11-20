package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.View;
import android.widget.*;
import android.widget.CalendarView;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static edu.thapar.newindialms.AddCourseFragment.facultyspinner_URL;
import static edu.thapar.newindialms.FacultyCourseListTakeAttendance.absentlist;
import static edu.thapar.newindialms.R.id.facultyspinner;

/**
 * Created by kamalshree on 11/18/2017.
 */

public class FacultyCourseListViewAttendance extends AppCompatActivity {
    android.support.v7.widget.Toolbar faculty_toolbar;
    private android.widget.CalendarView calendarView;
    private Button ShowButton;
    private JSONArray resultcourse;
    private ArrayList<String> Courselist;
    public static final String coursepsinner_URL = "https://newindialms.000webhostapp.com/attendance_courselist.php";
    private Spinner coursespinner;
    private String datevalue;
    private String course_details_faculty;
    private String coursename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_viewattendance);
        coursename = getIntent().getStringExtra("coursename");
        faculty_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        coursespinner = (Spinner) findViewById(R.id.facultycourselistspinner);
        calendarView= (CalendarView)findViewById(R.id.calendarView);
        ShowButton=(Button)findViewById(R.id.ChooseButton);
        addListenerOnCourseSpinnerItemSelection();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue=(i1+1)+"-"+i2+"-"+i;
            }
        });

        ShowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                course_details_faculty=coursespinner.getSelectedItem().toString();
                Toast.makeText(FacultyCourseListViewAttendance.this," Selected date is "+datevalue,Toast.LENGTH_LONG).show();
                Toast.makeText(FacultyCourseListViewAttendance.this," Selected course is "+course_details_faculty,Toast.LENGTH_LONG).show();
                Intent facultyintent = new Intent(getApplicationContext(), FacultyCourseListViewAttendanceDisplay.class);
                facultyintent.putExtra("attendance_date",datevalue);
                facultyintent.putExtra("course_details_name",course_details_faculty);
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(facultyintent);

            }
        });

    }

    public void addListenerOnCourseSpinnerItemSelection() {
        Courselist = new ArrayList<String>();
        coursespinner = (Spinner) findViewById(R.id.facultycourselistspinner);
        getCourseData();

    }

    private void getCourseData(){

        coursespinner = (Spinner) findViewById(R.id.facultycourselistspinner);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(coursepsinner_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultcourse = j.getJSONArray("course_name");

                            //Calling method getStudents to get the students from the JSON Array
                            getCourseName(resultcourse);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getCourseName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                Courselist.add(json.getString("course_details_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        coursespinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Courselist));
    }
}
