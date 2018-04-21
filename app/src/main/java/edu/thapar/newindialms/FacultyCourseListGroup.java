package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListGroup extends AppCompatActivity {

    private String coursename, faculty_employeeid, coursetype;
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
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        coursetype = getIntent().getStringExtra("coursetype");

        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        builder = new AlertDialog.Builder(this, R.style.MyFacultyAlertDialogStyle);

        facultycourselist_program_title = (TextView) findViewById(R.id.facultycourselist_program_title);
        facultycourselist_program_title.setText(coursename);


        groupSpinner = (Spinner) findViewById(R.id.spinner1);
        sectionSpinner = (Spinner) findViewById(R.id.spinner2);


        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                groupval = groupSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(FacultyCourseListGroup.this, "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sectionval = sectionSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(FacultyCourseListGroup.this, "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

        submitButton = (Button) findViewById(R.id.group_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (groupval.equals("") || sectionval.equals("")) {
                    builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
                    builder.setMessage(getResources().getString(R.string.registration_error_missingspinner_text));
                    displayAlert("missing_fields");
                } else {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyCourseListTakeAttendanceGroup.class);
                    //Toast.makeText(getApplication(),groupval+sectionval+coursename+faculty_employeeid,Toast.LENGTH_LONG).show();
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
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
