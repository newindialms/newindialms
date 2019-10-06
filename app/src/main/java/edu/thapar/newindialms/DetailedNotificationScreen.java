package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
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

        notification_title = findViewById(R.id.detailed_notification_title);
        notification_msg = findViewById(R.id.detailed_notification_msg);
        notification_time = findViewById(R.id.detailed_notification_time);

        notification_title.setText(detailedTitle);
        notification_msg.setText(detailedMessage);
        notification_time.setText(detailedTime);
    }
}
