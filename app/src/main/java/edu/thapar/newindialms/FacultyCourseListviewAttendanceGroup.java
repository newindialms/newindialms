package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListviewAttendanceGroup extends AppCompatActivity {

    private String coursename, faculty_employeeid, coursetype, attendance_date, course_details_name, groupname, sectionname;
    private TextView facultycourselist_program_title;
    private Toolbar faculty_toolbar;
    private String groupval, sectionval;
    private Button submitButton;
    AlertDialog.Builder builder;

    //a List of type hero for holding list items
    List<FacultyCourseListDashboardListItems> heroList;

    //the listview
    ListView listView;
    Spinner groupSpinner, sectionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_group);
        coursename = getIntent().getStringExtra("coursename");
        attendance_date = getIntent().getStringExtra("attendance_date");
        course_details_name = getIntent().getStringExtra("course_details_name");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        groupname = getIntent().getStringExtra("groupname");
        sectionname = getIntent().getStringExtra("sectionname");
        coursetype = getIntent().getStringExtra("coursetype");
        builder = new AlertDialog.Builder(this, R.style.MyFacultyAlertDialogStyle);

        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(course_details_name);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        facultycourselist_program_title = findViewById(R.id.facultycourselist_program_title);
        facultycourselist_program_title.setText(course_details_name);


        groupSpinner = findViewById(R.id.spinner1);
        sectionSpinner = findViewById(R.id.spinner2);

        if(course_details_name.equals("Communication and Consultative Problem Solving-I") || course_details_name.equals("Communication and Consultative Problem Solving-II")) {
            groupSpinner.setVisibility(View.VISIBLE);
            sectionSpinner.setVisibility(View.GONE);
            sectionval="0";
        }
        else{
            groupSpinner.setVisibility(View.GONE);
            sectionSpinner.setVisibility(View.VISIBLE);
            groupval="0";
        }


        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                groupval = groupSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(FacultyCourseListviewAttendanceGroup.this, "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sectionval = sectionSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(FacultyCourseListviewAttendanceGroup.this, "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        submitButton = findViewById(R.id.group_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groupval.equals("Select Group") || sectionval.equals("Select Section")) {
                    builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
                    builder.setMessage(getResources().getString(R.string.registration_error_missingspinner_text));
                    displayAlert("missing_fields");
                } else {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyCourseListViewAttendanceDisplay.class);
                    //Toast.makeText(getApplication(),groupval+sectionval+coursename+faculty_employeeid,Toast.LENGTH_LONG).show();
                    facultyintent.putExtra("attendance_date", attendance_date);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("course_details_name", course_details_name);
                    facultyintent.putExtra("groupname", groupval);
                    facultyintent.putExtra("sectionname", sectionval);
                    facultyintent.putExtra("coursetype", coursetype);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(facultyintent);
                }
            }
        });


    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("missing_fields")) {
                    dialoginterface.dismiss();
                    //
                }
                if (code.equals("Success")) {
                    dialoginterface.dismiss();
                    Intent feedbackintent = new Intent(getApplicationContext(), FeedbackTab.class);
                    feedbackintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(feedbackintent);

                }
                if (code.equals("Fail")) {
                    dialoginterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
