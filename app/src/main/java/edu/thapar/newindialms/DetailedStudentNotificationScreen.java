package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class DetailedStudentNotificationScreen extends AppCompatActivity {
    private String detailedTitle, detailedMessage, detailedTime;
    private Toolbar student_toolbar;
    private TextView notification_title, notification_msg, notification_time;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_student_notification_screen);

        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title=(TextView)findViewById(R.id.student_enroll_toolbar_title);
        faculty_title.setText(" Notification");
        setSupportActionBar(student_toolbar);

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailedTitle = getIntent().getExtras().getString("notification_title");
        detailedMessage = getIntent().getExtras().getString("notification_msg");
        detailedTime = getIntent().getExtras().getString("notification_date");

        notification_title = (TextView) findViewById(R.id.detailed_notification_title);
        notification_msg = (TextView) findViewById(R.id.detailed_notification_msg);
        notification_time = (TextView) findViewById(R.id.detailed_notification_time);

        notification_title.setText(detailedTitle);
        notification_msg.setText(detailedMessage);
        notification_time.setText(detailedTime);
    }
}
