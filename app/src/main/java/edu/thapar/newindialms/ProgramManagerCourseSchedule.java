package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kamalshree on 11/9/2017.
 */

public class ProgramManagerCourseSchedule extends AppCompatActivity {
String issue_details,day_details;
    Toolbar studentpic_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_courseschedule);
        issue_details = getIntent().getStringExtra("issue_details");
        day_details = getIntent().getStringExtra("day_details");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);

        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
