package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDetailsFirstYearDashboard extends AppCompatActivity {

    private String CourseName;
    private TextView course_details_coursename,studentpic_title;
    private Toolbar studentprofile_toolbar;

    //a List of type hero for holding list items
    List<EnrolledCourseDetailsFirstYearDashboardListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details_dashboard);
        CourseName = getIntent().getStringExtra("enrolledcoursename");

        course_details_coursename = findViewById(R.id.course_details_coursename);
        course_details_coursename.setText(CourseName);

        studentprofile_toolbar = findViewById(R.id.studentprofile_toolbar);
        studentprofile_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(studentprofile_toolbar);
        studentpic_title = findViewById(R.id.student_enroll_toolbar_title);
        studentpic_title.setText(CourseName);
        studentprofile_toolbar.setTitle("My Course Dashboard");
        studentprofile_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        //initializing objects
        heroList = new ArrayList<>();
        listView = findViewById(R.id.course_details_ListView);

        ProgramScreenListItems pglist = new ProgramScreenListItems(CourseName);
        pglist.setProgramname(CourseName);

        heroList.add(new EnrolledCourseDetailsFirstYearDashboardListItems(
                "Class Schedule", "Course Details",
                "Notifications", "Marks",
                CourseName));

        //creating the adapter
        EnrolledCourseDetailsFirstYearDashboardAdapter adapter = new EnrolledCourseDetailsFirstYearDashboardAdapter(this, R.layout.activity_course_details_dashboard_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(CourseName);
    }
}
