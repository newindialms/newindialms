package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kamalshree on 3/3/2018.
 */

public class MyFeedbackScreen extends AppCompatActivity {
    private static final String TAG = MyFeedbackScreen.class.getSimpleName();
    private Toolbar studentpic_toolbar;
    private String studentid, course_date, course_time, faculty_employeeid, feedback_course_details;
    private TextView myfeedbackenrolledcourses_title, myfeedback_rollno;
    private Button myfeedback_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback_screen);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        myfeedbackenrolledcourses_title.setText("My Feedback ");


        if (getIntent().getExtras() != null) {

            for (String key : getIntent().getExtras().keySet()) {

                //Toast.makeText(MyFeedbackScreen.this,getIntent().getExtras().getString(key),Toast.LENGTH_LONG).show();
                if (key.equals("feedback_course_details")) {
                    feedback_course_details = getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"coursename"+coursename,Toast.LENGTH_LONG).show();
                }
                if (key.equals("studentid")) {
                    studentid = getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                if (key.equals("course_date")) {
                    course_date = getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                if (key.equals("course_time")) {
                    course_time = getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                if (key.equals("faculty_employeeid")) {
                    faculty_employeeid = getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(MyFeedbackScreen.this,"no key value",Toast.LENGTH_LONG).show();
                }
            }
        }

        myfeedback_button = (Button) findViewById(R.id.myfeedback_button);

        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        myfeedback_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MyFeedbackScreen.this,"Myfeedback"+feedback_course_details+faculty_employeeid, Toast.LENGTH_LONG).show();
                Intent feedbackintent = new Intent(MyFeedbackScreen.this, MyFeedbackEnrolledCourse.class);
                feedbackintent.putExtra("studentid", studentid);
                feedbackintent.putExtra("course_date", course_date);
                feedbackintent.putExtra("course_time", course_time);
                feedbackintent.putExtra("faculty_employeeid", faculty_employeeid);
                feedbackintent.putExtra("coursename", feedback_course_details);
                startActivity(feedbackintent);
            }
        });
    }

}
