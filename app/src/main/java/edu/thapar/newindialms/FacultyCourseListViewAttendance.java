package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by kamalshree on 11/18/2017.
 */

public class FacultyCourseListViewAttendance extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar faculty_toolbar;
    private android.widget.CalendarView calendarView;
    private Button ShowButton;
    private String datevalue;
    private String coursename, faculty_employeeid, coursetype;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_viewattendance);
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        coursetype = getIntent().getStringExtra("coursetype");
        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        calendarView = findViewById(R.id.calendarView);
        ShowButton = findViewById(R.id.ChooseButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int yearval, int monthval, int dateval) {
                datevalue = dateval + "-" + (monthval + 1) + "-" + yearval; //date-month-year
                //  Toast.makeText(FacultyCourseListViewAttendance.this,"The selected Date is... "+datevalue,Toast.LENGTH_LONG).show();
            }
        });

        ShowButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (coursetype.equals("1")) {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyCourseListviewAttendanceGroup.class);
                    facultyintent.putExtra("attendance_date", datevalue);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursetype", coursetype);
                    facultyintent.putExtra("course_details_name", coursename);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        getApplicationContext().startActivity(facultyintent);
                    } else {
                        //Toast.makeText(FacultyCourseListViewAttendance.this,"Please Select a Date",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(FacultyCourseListViewAttendance.this, R.style.MyFacultyAlertDialogStyle);
                        builder.setTitle("Missing");
                        builder.setMessage("Please choose a Date.");
                        displayAlert();
                    }
                } else {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyCourseListViewAttendanceDisplay.class);
                    facultyintent.putExtra("attendance_date", datevalue);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("course_details_name", coursename);
                    facultyintent.putExtra("coursetype", coursetype);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        getApplicationContext().startActivity(facultyintent);
                    } else {
                        //Toast.makeText(FacultyCourseListViewAttendance.this,"Please Select a Date",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(FacultyCourseListViewAttendance.this, R.style.MyFacultyAlertDialogStyle);
                        builder.setTitle("Missing");
                        builder.setMessage("Please choose a Date.");
                        displayAlert();
                    }
                }
            }
        });

    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
