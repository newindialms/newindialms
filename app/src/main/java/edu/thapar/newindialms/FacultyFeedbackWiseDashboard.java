package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 4/13/2018.
 */

public class FacultyFeedbackWiseDashboard extends AppCompatActivity {

    private String coursename, faculty_employeeid;
    private TextView facultycourselist_program_title;
    private Toolbar faculty_toolbar;

    //a List of type hero for holding list items
    List<FacultyCourseListDashboardWiseListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_dashboardwise);
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");

        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        facultycourselist_program_title = findViewById(R.id.facultycourselist_program_title);
        facultycourselist_program_title.setText(coursename);

        //initializing objects
        heroList = new ArrayList<>();
        listView = findViewById(R.id.facultycourselistdashboardlist_ListView);

        FacultyCourseListDashboardWiseListItems pglist = new FacultyCourseListDashboardWiseListItems(coursename);
        pglist.setCoursename(coursename);

        heroList.add(new FacultyCourseListDashboardWiseListItems("Daywise/Raw Data", "Average/Median Score", pglist.getCoursename(), faculty_employeeid));

        //creating the adapter
        FacultyCourseListDashboardWiseAdapter adapter = new FacultyCourseListDashboardWiseAdapter(this, R.layout.activity_faculty_courselist_dashboardwise_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
}