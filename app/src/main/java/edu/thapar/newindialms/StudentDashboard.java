package edu.thapar.newindialms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import static edu.thapar.newindialms.R.id.faculty_toolbar_title;

public class StudentDashboard extends AppCompatActivity {
    Toolbar toolbar_main_screen;
    MenuItem menuItem;
    String facultyname,facultyid;
    TextView faculty_toolbar_name,faculty_toolbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_menu);

    }



}
