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

public class EnrolledCourseAttendanceCommonScreen extends AppCompatActivity {

    private String enrolledcoursename, student_rollnno,studentyear;

    private Toolbar student_toolbar;
    private TextView student_toolbar_title, student_title;

    //a List of type hero for holding list items
    List<EnrolledCourseAttendanceCommonScreenListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_attendance_commonscreen);
        enrolledcoursename = getIntent().getStringExtra("enrolledcoursename");
        student_rollnno = getIntent().getStringExtra("student_rollnno");
        studentyear = getIntent().getStringExtra("studentyear");

        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);

        student_title = (TextView) findViewById(R.id.enrolledcourses_title);

        student_title.setText("My Attendance " + enrolledcoursename);

        student_toolbar_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        student_toolbar_title.setText(enrolledcoursename);

        setSupportActionBar(student_toolbar);
        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.enrolledcourselistView);

        EnrolledCourseAttendanceCommonScreenListItems pglist = new EnrolledCourseAttendanceCommonScreenListItems(enrolledcoursename);
        pglist.setEnrolledcoursename(enrolledcoursename);
        pglist.setStudentrollno(student_rollnno);

        heroList.add(new EnrolledCourseAttendanceCommonScreenListItems("Daywise", "Cumulative", pglist.getEnrolledcoursename(), pglist.getStudentrollno(),studentyear));

        //creating the adapter
        EnrolledCourseAttendanceCommonScreenAdapter adapter = new EnrolledCourseAttendanceCommonScreenAdapter(this, R.layout.activity_enrolled_course_attendance_commonscreen_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
}
