package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class FacultyFeedbackScheduleDisplay extends AppCompatActivity {
    private TextView faculty_schedule_title;
    private Toolbar faculty_toolbar;
    private String faculty_employeeid, feedback_type, coursename;
    private Button ScheduleButton;
    private String datevalue;
    private CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faculty_feedback_schedule);

        //feedback_type=getIntent().getStringExtra("feedback_type");
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");

        faculty_schedule_title = findViewById(R.id.faculty_schedule_title);
        faculty_schedule_title.setText("Select a Date");

        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setVisibility(View.VISIBLE);

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

        //Toast.makeText(getApplicationContext(), "value is "  + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();

        calendarView = findViewById(R.id.schedule_calendarView);
        ScheduleButton = findViewById(R.id.ScheduleButton);
        datevalue = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue = i2 + "-" + (i1 + 1) + "-" + i;
            }
        });

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackintent = new Intent(getApplicationContext(), FacultyFeedbackDashboard.class);
                feedbackintent.putExtra("coursename", coursename);
                feedbackintent.putExtra("faculty_employeeid", faculty_employeeid);
                feedbackintent.putExtra("datevalue", datevalue);
                startActivity(feedbackintent);

                /*
                if(feedback_type.equals("Rate")) {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyFeedbackRateDisplay.class);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("feedback_type", feedback_type);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getApplicationContext(), "selected Date is " + datevalue + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                        startActivity(facultyintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Select a Date", Toast.LENGTH_LONG).show();
                    }
                }
                if(feedback_type.equals("Smiley")){
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyFeedbackSmileyDisplay.class);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("feedback_type", feedback_type);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getApplicationContext(), "selected Date is " + datevalue + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                        startActivity(facultyintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Select a Date", Toast.LENGTH_LONG).show();
                    }
                }
                if(feedback_type.equals("Text")){
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyFeedbackTextDisplay.class);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("feedback_type", feedback_type);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        //Toast.makeText(getApplicationContext(), "selected Date is " + datevalue + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                        startActivity(facultyintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Select a Date", Toast.LENGTH_LONG).show();
                    }
                }
                if(feedback_type.equals("Like")){
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyFeedbackLikeDisplay.class);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("feedback_type", feedback_type);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                       // Toast.makeText(getApplicationContext(), "selected Date is " + datevalue + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                        startActivity(facultyintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Select a Date", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    //
                }
                */


            }
        });
    }
}
/*}
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_faculty_schedule, null);
        FacultyMenu activity = (FacultyMenu) getActivity();
        faculty_employeeid = activity.getEmployeeid();
        feedback_type = getArguments().getString("feedback_type");
        coursename= getArguments().getString("coursename");
        calendarView= (CalendarView)rootView.findViewById(R.id.schedule_calendarView);
        ScheduleButton=(Button)rootView.findViewById(R.id.ScheduleButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue=i2+"-"+(i1+1)+"-"+i;
            }
        });

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facultyintent = new Intent(getActivity(), FacultyFeedbackRateDisplay.class);
                facultyintent.putExtra("faculty_employeeid",faculty_employeeid);
                facultyintent.putExtra("coursename",coursename);
                facultyintent.putExtra("datevalue",datevalue);
                facultyintent.putExtra("feedback_type",feedback_type);
                facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (datevalue != null && !datevalue.isEmpty()) {
                    //Toast.makeText(getContext(),"selected Date is "+datevalue+faculty_employeeid+coursename+feedback_type,Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                    getActivity().startActivity(facultyintent);
                }
                else{
                    //Toast.makeText(getActivity(),"Select Date",Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }*/
