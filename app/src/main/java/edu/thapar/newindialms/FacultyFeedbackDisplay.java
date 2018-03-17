package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackDisplay extends AppCompatActivity {

    String coursename,faculty_employeeid;
    TextView facultycourselist_program_title;
    Toolbar faculty_toolbar;

    //a List of type hero for holding list items
    List<FacultyCourseListDashboardListItems> heroList;

    //the listview
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_dashboard);
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        coursename = getIntent().getStringExtra("coursename");

        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title=(TextView)findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }
}
