package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentDashboard extends AppCompatActivity {
    private Toolbar toolbar_main_screen;
    private MenuItem menuItem;
    private String facultyname,facultyid;
    private TextView faculty_toolbar_name,faculty_toolbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_menu);
    }


}
