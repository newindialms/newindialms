package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class FacultyFeedbackScheduleDisplay extends AppCompatActivity {
    TextView faculty_schedule_title;
    Toolbar faculty_toolbar;
    String faculty_employeeid, feedback_type, coursename;
    Button ScheduleButton;
    private String datevalue;
    private CalendarView calendarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faculty_schedule);

        feedback_type=getIntent().getStringExtra("feedback_type");
        coursename=getIntent().getStringExtra("coursename");
        faculty_employeeid=getIntent().getStringExtra("faculty_employeeid");

        faculty_schedule_title=(TextView)findViewById(R.id.faculty_schedule_title);
        faculty_schedule_title.setText("Select a Date");

        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setVisibility(View.VISIBLE);

        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title=(TextView)findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        //Toast.makeText(getApplicationContext(), "value is "  + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();

        calendarView = (CalendarView) findViewById(R.id.schedule_calendarView);
        ScheduleButton = (Button) findViewById(R.id.ScheduleButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                datevalue = i2 + "-" + (i1 + 1) + "-" + i;
            }
        });

        ScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(feedback_type.equals("Rate")) {
                    Intent facultyintent = new Intent(getApplicationContext(), FacultyFeedbackRateDisplay.class);
                    facultyintent.putExtra("faculty_employeeid", faculty_employeeid);
                    facultyintent.putExtra("coursename", coursename);
                    facultyintent.putExtra("datevalue", datevalue);
                    facultyintent.putExtra("feedback_type", feedback_type);
                    facultyintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    if (datevalue != null && !datevalue.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "selected Date is " + datevalue + faculty_employeeid + coursename + feedback_type, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                        startActivity(facultyintent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Select Date", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    //
                }
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
                    Toast.makeText(getContext(),"selected Date is "+datevalue+faculty_employeeid+coursename+feedback_type,Toast.LENGTH_LONG).show();
                    //Toast.makeText(getContext(),"Employee ID is "+faculty_employeeid,Toast.LENGTH_LONG).show();
                    getActivity().startActivity(facultyintent);
                }
                else{
                    Toast.makeText(getActivity(),"Select Date",Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }*/
