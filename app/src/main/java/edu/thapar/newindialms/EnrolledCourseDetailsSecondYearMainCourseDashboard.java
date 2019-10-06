package edu.thapar.newindialms;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDetailsSecondYearMainCourseDashboard extends AppCompatActivity {

    private String studentyear,studentid;
    private TextView course_details_coursename,studentpic_title;
    private Toolbar studentprofile_toolbar;

    //a List of type hero for holding list items
    List<EnrolledCourseDetailsSecondYearMainCourseDashboardListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details_dashboard);
        studentyear = getIntent().getStringExtra("studentyear");
        studentid = getIntent().getStringExtra("studentid");

        course_details_coursename = (TextView) findViewById(R.id.course_details_coursename);
        course_details_coursename.setText("My Course Dashboard");


        studentprofile_toolbar = (Toolbar) findViewById(R.id.studentprofile_toolbar);
        studentprofile_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(studentprofile_toolbar);
        studentpic_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        studentpic_title.setText("My Course Dashboard");
        studentprofile_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.course_details_ListView);

        ProgramScreenListItems pglist = new ProgramScreenListItems(studentid);
        pglist.setProgramname(studentid);

        heroList.add(new EnrolledCourseDetailsSecondYearMainCourseDashboardListItems(
                "Elective Courses", "Core Courses",studentid,studentyear));

        //creating the adapter
        EnrolledCourseDetailsSecondYearMainCourseDashboardAdapter adapter = new EnrolledCourseDetailsSecondYearMainCourseDashboardAdapter(this, R.layout.activity_secondyear_maincourse_dashboard_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle("");
    }
}
