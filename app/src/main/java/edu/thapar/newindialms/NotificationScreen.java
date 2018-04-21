package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by kamalshree on 10/28/2017.
 */

public class NotificationScreen extends AppCompatActivity {
    private Toolbar toolbar_all_notiifcation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);

        toolbar_all_notiifcation = (Toolbar) findViewById(R.id.toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationIcon(R.drawable.ic_left);

        setSupportActionBar(toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void GoToAccademicCalendar(View v) {
        Intent intent = new Intent(getApplicationContext(), OpenCalendar.class);
        startActivity(intent);
    }

    public void SendFacultyNotification(View v) {
        Intent intent = new Intent(getApplicationContext(), SendFacultyNotificaton.class);
        startActivity(intent);
    }

    public void SendStudentNotification(View v) {
        Intent intent = new Intent(getApplicationContext(), SendStudentNotificaton.class);
        startActivity(intent);
    }
}