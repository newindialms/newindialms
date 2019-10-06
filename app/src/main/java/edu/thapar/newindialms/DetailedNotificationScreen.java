package edu.thapar.newindialms;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class DetailedNotificationScreen extends AppCompatActivity {
    private String detailedTitle, detailedMessage, detailedTime;
    private Toolbar faculty_toolbar;
    private TextView notification_title, notification_msg, notification_time;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_notification_screen);

        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(" Notification");
        setSupportActionBar(faculty_toolbar);

        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
