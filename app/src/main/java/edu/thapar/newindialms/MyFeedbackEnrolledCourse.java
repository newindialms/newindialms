package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by kamalshree on 3/3/2018.
 */

public class MyFeedbackEnrolledCourse extends AppCompatActivity {

    //private String enrolled_courselist = "https://www.newindialms.com/listenrolledcourses.php";
    List<MyfeedbackEnrolledCourseListItems> heroList;
    private TextView myfeedbackenrolledcourses_title;
    private Toolbar studentpic_toolbar;
    private String studentid, course_date, course_time, faculty_employeeid, coursename;
    TextView enrolledcourselist_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback_enrolled_course);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);

        studentid = getIntent().getStringExtra("studentid");
        course_date = getIntent().getStringExtra("course_date");
        course_time = getIntent().getStringExtra("course_time");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        coursename = getIntent().getStringExtra("coursename");

        myfeedbackenrolledcourses_title.setText("My Feedback");
        setSupportActionBar(studentpic_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MyFeedbackEnrolledCourse.this, StudentHome.class));
                finish();
            }
        });


        RelativeLayout relative1 = (RelativeLayout) findViewById(R.id.relative1);
        enrolledcourselist_name = (TextView) findViewById(R.id.myfeedbackenrolledcourselist_name);
        enrolledcourselist_name.setText(coursename);

        relative1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitfeedbackintent = new Intent(getApplicationContext(), SubmitFeedBackScreen.class);
                submitfeedbackintent.putExtra("corecoursename", coursename);
                submitfeedbackintent.putExtra("studentid", studentid);
                submitfeedbackintent.putExtra("course_date", course_date);
                submitfeedbackintent.putExtra("course_time", course_time);
                submitfeedbackintent.putExtra("faculty_employeeid", faculty_employeeid);
                submitfeedbackintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(submitfeedbackintent);
            }
        });
    }
}