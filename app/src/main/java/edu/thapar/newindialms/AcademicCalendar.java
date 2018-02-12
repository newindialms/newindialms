package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static edu.thapar.newindialms.R.id.backlog_dates;
import static edu.thapar.newindialms.R.id.fall_closing_days;
import static edu.thapar.newindialms.R.id.fall_weekend_days;
import static edu.thapar.newindialms.R.id.fall_winter_break;
import static edu.thapar.newindialms.R.id.spring_summer_break;

/**
 * Created by kamalshree on 10/23/2017.
 */

public class AcademicCalendar extends AppCompatActivity{
    Toolbar calendarToolbar;
    String calendarDetails_Url = "https://newindialms.000webhostapp.com/get_calendardetails.php";
    TextView accademicCalendar,fallYear,fallSemesterSesion,springYear,
      springSemesterSesion,
      fallClassesSemester3,
      sprigClassesSemester24,
      fallClassesSemester2,
      fallClassesSemester1,
      fallTeachingSemester3,
     springTeachingSemester24,
     fallTeachingSemester1,
    fallMidendSemester13,
    springMidendSemester24,
     fallTeachingSemestersecond,
     springTeachingSemestersecond,
   fallBreakSemester13,
    springBreakSemester24,
     fallTeachingSemesterthird,
     springWeekendDays,
   fallendBreakSemester13,
    springClosingDays,
     fallWeekendDays,
            internshipDays,
    fallClosingDays,
   backlogDates,
    fallWinterBreak,
    springSummerBreak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);

        calendarToolbar = (Toolbar) findViewById(R.id.calendar_toolbar);
        calendarToolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(calendarToolbar);
        calendarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

         accademicCalendar = (TextView) findViewById(R.id.accademic_calendar);
         fallYear = (TextView) findViewById(R.id.fall_year);
         fallSemesterSesion = (TextView) findViewById(R.id.fall_semester_sesion);
         springYear = (TextView) findViewById(R.id.spring_year);
         springSemesterSesion = (TextView) findViewById(R.id.spring_semester_sesion);
         fallClassesSemester3 = (TextView)findViewById(R.id.fall_classes_semester3);
         sprigClassesSemester24 = (TextView)findViewById(R.id.sprig_classes_semester24);
         fallClassesSemester2 = (TextView) findViewById(R.id.fall_classes_semester2);
         fallClassesSemester1 = (TextView)findViewById(R.id.fall_classes_semester1);
         fallTeachingSemester3 = (TextView) findViewById(R.id.fall_teaching_semester3);
         springTeachingSemester24 = (TextView) findViewById(R.id.spring_teaching_semester24);
         fallTeachingSemester1 = (TextView)findViewById(R.id.fall_teaching_semester1);
         fallMidendSemester13 = (TextView) findViewById(R.id.fall_midend_semester13);
         springMidendSemester24 = (TextView)findViewById(R.id.spring_midend_semester24);
         fallTeachingSemestersecond = (TextView)findViewById(R.id.fall_teaching_semestersecond);
         springTeachingSemestersecond = (TextView) findViewById(R.id.spring_teaching_semestersecond);
         fallBreakSemester13 = (TextView) findViewById(R.id.fall_break_semester13);
         springBreakSemester24 = (TextView) findViewById(R.id.spring_break_semester24);
         fallTeachingSemesterthird = (TextView)findViewById(R.id.fall_teaching_semesterthird);
         springWeekendDays = (TextView)findViewById(R.id.spring_weekend_days);
         fallendBreakSemester13 = (TextView)findViewById(R.id.fallend_break_semester13);
         springClosingDays = (TextView)findViewById(R.id.spring_closing_days);
         fallWeekendDays = (TextView) findViewById(R.id.fall_weekend_days);
        internshipDays = (TextView) findViewById(R.id.internship_days);
         fallClosingDays = (TextView) findViewById(R.id.fall_closing_days);
         backlogDates = (TextView) findViewById(R.id.backlog_dates);
         fallWinterBreak = (TextView)findViewById(R.id.fall_winter_break);
         springSummerBreak = (TextView) findViewById(R.id.spring_summer_break);

        loadCalendarDetails();
    }
    private void loadCalendarDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String accademiccalendar = jsonobject.getString("accademic_calendar");
                                String fallyear = jsonobject.getString("fall_year");
                                String fallsemestersesion = jsonobject.getString("fall_semester_sesion");
                                String springyear = jsonobject.getString("spring_year");
                                String springsemestersesion = jsonobject.getString("spring_semester_sesion");
                                String fallclassessemester3 = jsonobject.getString("fall_classes_semester3");
                                String sprigclassessemester24 = jsonobject.getString("sprig_classes_semester24");
                                String fallclassessemester2 = jsonobject.getString("fall_classes_semester2");
                                String fallclassessemester1 = jsonobject.getString("fall_classes_semester1");
                                String fallteachingsemester3 = jsonobject.getString("fall_teaching_semester3");
                                String springteachingsemester24 = jsonobject.getString("spring_teaching_semester24");
                                String fallteachingsemester1 = jsonobject.getString("fall_teaching_semester1");
                                String fallmidendsemester13 = jsonobject.getString("fall_midend_semester13");
                                String springmidendsemester24 = jsonobject.getString("spring_midend_semester24");
                                String fallteachingsemestersecond = jsonobject.getString("fall_teaching_semestersecond");
                                String springteachingsemestersecond = jsonobject.getString("spring_teaching_semestersecond");
                                String fallbreaksemester13 = jsonobject.getString("fall_break_semester13");
                                String springbreaksemester24 = jsonobject.getString("spring_break_semester24");
                                String fallteachingsemesterthird = jsonobject.getString("fall_teaching_semesterthird");
                                String springweekenddays = jsonobject.getString("spring_weekend_days");
                                String fallendbreaksemester13 = jsonobject.getString("fallend_break_semester13");
                                String springclosingdays = jsonobject.getString("spring_closing_days");
                                String fallweekenddays = jsonobject.getString("fall_weekend_days");
                                String internshipdays = jsonobject.getString("internship_days");
                                String fallclosingdays = jsonobject.getString("fall_closing_days");
                                String backlogdates = jsonobject.getString("backlog_dates");
                                String fallwinterbreak = jsonobject.getString("fall_winter_break");
                                String springsummerbreak = jsonobject.getString("spring_summer_break");

                                accademicCalendar.setText(accademiccalendar);
                                fallYear.setText(fallyear);
                                fallSemesterSesion.setText(fallsemestersesion);
                                springYear.setText(springyear);
                                springSemesterSesion.setText(springsemestersesion);
                                fallClassesSemester3.setText(fallclassessemester3);
                                sprigClassesSemester24.setText(sprigclassessemester24);
                                fallClassesSemester2.setText(fallclassessemester2);
                                fallClassesSemester1.setText(fallclassessemester1);
                                fallTeachingSemester3.setText(fallteachingsemester3);
                                springTeachingSemester24.setText(springteachingsemester24);
                                fallTeachingSemester1.setText(fallteachingsemester1);
                                fallMidendSemester13.setText(fallmidendsemester13);
                                springMidendSemester24.setText(springmidendsemester24);
                                fallTeachingSemestersecond.setText(fallteachingsemestersecond);
                                springTeachingSemestersecond.setText(springteachingsemestersecond);
                                fallBreakSemester13.setText(fallbreaksemester13);
                                springBreakSemester24.setText(springbreaksemester24);
                                fallTeachingSemesterthird.setText(fallteachingsemesterthird);
                                springWeekendDays.setText(springweekenddays);
                                fallendBreakSemester13.setText(fallendbreaksemester13);
                                springClosingDays.setText(springclosingdays);
                                fallWeekendDays.setText(fallweekenddays);
                                internshipDays.setText(internshipdays);
                                fallClosingDays.setText(fallclosingdays);
                                backlogDates.setText(backlogdates);
                                fallWinterBreak.setText(fallwinterbreak);
                                springSummerBreak.setText(springsummerbreak);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}