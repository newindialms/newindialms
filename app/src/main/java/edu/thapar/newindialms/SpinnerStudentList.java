package edu.thapar.newindialms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SpinnerStudentList extends AppCompatActivity {
    String program,specialization;
    int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_student_list);
        year = getIntent().getIntExtra("year",1);
        program = getIntent().getStringExtra("program");
        specialization = getIntent().getStringExtra("specialization");


    }
}
