package edu.thapar.newindialms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kamalshree on 3/3/2018.
 */

public class MyFeedbackScreen extends AppCompatActivity {
    private static final String TAG=MyFeedbackScreen.class.getSimpleName();
    private Toolbar studentpic_toolbar;
    String studentid,course_date,course_time;
    TextView myfeedbackenrolledcourses_title,myfeedback_rollno;
    Button myfeedback_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback_screen);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title=(TextView)findViewById(R.id.student_enroll_toolbar_title);
        myfeedbackenrolledcourses_title.setText("My Feedback");


        if(getIntent().getExtras()!=null){

            for(String key:getIntent().getExtras().keySet()){

                if(key.equals("studentid")){
                    studentid=getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                if(key.equals("course_date")){
                    course_date=getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                if(key.equals("course_time")){
                    course_time=getIntent().getExtras().getString(key);
                    //Toast.makeText(MyFeedbackScreen.this,"Student Roll no"+studentid,Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(MyFeedbackScreen.this,"no key value",Toast.LENGTH_LONG).show();
                }
            }
        }

        myfeedback_button=(Button)findViewById(R.id.myfeedback_button);

        myfeedback_rollno=(EditText)findViewById(R.id.myfeedback_rollno);
       myfeedback_rollno.setText(studentid);

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
                Toast.makeText(MyFeedbackScreen.this,"Myfeedback"+studentid+course_date+course_time,Toast.LENGTH_LONG).show();
                Intent feedbackintent=new Intent(MyFeedbackScreen.this,MyFeedbackEnrolledCourse.class);
                feedbackintent.putExtra("studentid",studentid);
                feedbackintent.putExtra("course_date",course_date);
                feedbackintent.putExtra("course_time",course_time);
                startActivity(feedbackintent);
            }
        });
    }

}
