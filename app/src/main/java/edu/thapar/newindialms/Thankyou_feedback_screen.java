package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

/**
 * Created by kamalshree on 10/27/2017.
 */

public class Thankyou_feedback_screen extends AppCompatActivity {

    private Button close_button;
    private Toolbar studentpic_toolbar;
    private TextView myfeedbackenrolledcourses_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanksfeedback_screen);
        close_button = findViewById(R.id.thankyoubutton);

        studentpic_toolbar = findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title = findViewById(R.id.student_enroll_toolbar_title);

        myfeedbackenrolledcourses_title.setText("Thank you");
        setSupportActionBar(studentpic_toolbar);

        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAffinity(Thankyou_feedback_screen.this);
            }
        });
    }
}