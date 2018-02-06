package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static edu.thapar.newindialms.R.id.Studentpic_program_title;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseAttendanceCommonScreen extends AppCompatActivity {

    String enrolledcoursename;

    Toolbar student_toolbar;
    TextView student_toolbar_title,student_title;

    //a List of type hero for holding list items
    List<EnrolledCourseAttendanceCommonScreenListItems> heroList;

    //the listview
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_attendance_commonscreen);
        enrolledcoursename = getIntent().getStringExtra("enrolledcoursename");

        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);

        student_title=(TextView)findViewById(R.id.enrolledcourses_title);

        student_title.setText("My Attendance " +enrolledcoursename);

        student_toolbar_title=(TextView) findViewById(R.id.student_enroll_toolbar_title);
        student_toolbar_title.setText(enrolledcoursename);

        setSupportActionBar(student_toolbar);
        student_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });


        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.enrolledcourselistView);

        EnrolledCourseAttendanceCommonScreenListItems pglist= new EnrolledCourseAttendanceCommonScreenListItems(enrolledcoursename);
        pglist.setEnrolledcoursename(enrolledcoursename);

        heroList.add(new EnrolledCourseAttendanceCommonScreenListItems("Daywise", "Cumulative",pglist.getEnrolledcoursename()));

        //creating the adapter
        EnrolledCourseAttendanceCommonScreenAdapter adapter = new EnrolledCourseAttendanceCommonScreenAdapter(this, R.layout.activity_enrolled_course_attendance_commonscreen_listitems, heroList);

        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
