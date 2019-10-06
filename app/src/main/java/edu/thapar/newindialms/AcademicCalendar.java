package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamalshree on 10/23/2017.
 */

public class AcademicCalendar extends AppCompatActivity {
    private Toolbar calendarToolbar;
    private String calendarDetails_Url = "https://www.newindialms.com/get_calendardetails.php";
    private LinearLayout fall_calendar_layout, spring_calendar_layout;
    private String CalendarVal;
    private TextView accademicCalendar, fallYear, fallSemesterSesion, springYear,
            springSemesterSesion,
            textViewClassesRegistrationFall,
            fallclassessemester3registrationtext,
            fallclassessemester3Registration,
            registrationtextfall,
            feefallSub1,
            feefallSub2,
            feefallSubcomment,
            textViewClassesRegistrationSpring,
            springclassessemester24registrationtext,
            springclassessemester24Registration,
            registrationtextspring,
            feespringSub1,
            feespringSub2,
            feespringSubcomment,
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
            springSummerBreak,
            sringcalendar,
            subj1,subj2,subj3,subj4,
            note1,note2,
            textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        CalendarVal = getIntent().getStringExtra("CalendarVal");
        calendarToolbar = findViewById(R.id.calendar_toolbar);
        calendarToolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(calendarToolbar);
        calendarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fall_calendar_layout = findViewById(R.id.fall_calendar_layout);
        spring_calendar_layout = findViewById(R.id.spring_calendar_layout);

        sringcalendar = findViewById(R.id.sringcalendar);
        textView5 = findViewById(R.id.textView5);

        accademicCalendar = findViewById(R.id.accademic_calendar);
        fallYear = findViewById(R.id.fall_year);
        fallSemesterSesion = findViewById(R.id.fall_semester_sesion);
        springYear = findViewById(R.id.spring_year);
        springSemesterSesion = findViewById(R.id.spring_semester_sesion);

        textViewClassesRegistrationFall = findViewById(R.id.textViewClassesRegistrationFall);
        springclassessemester24registrationtext = findViewById(R.id.spring_classes_semester24_registration_text);
        feefallSub1 = findViewById(R.id.fee_fall_sub_1);
        fallclassessemester3Registration = findViewById(R.id.fall_classes_semester3_registration);
        registrationtextfall = findViewById(R.id.registrationtextfall);
        feefallSub2 = findViewById(R.id.fee_fall_sub_2);
        feefallSubcomment = findViewById(R.id.fee_fall_sub_comment);

        textViewClassesRegistrationSpring = findViewById(R.id.textViewClassesRegistrationSpring);
        fallclassessemester3registrationtext = findViewById(R.id.fall_classes_semester3_registration_text);
        springclassessemester24Registration = findViewById(R.id.spring_classes_semester24_registration);
        registrationtextspring = findViewById(R.id.registrationtextspring);
        feespringSub1 = findViewById(R.id.fee_spring_sub_1);
        feespringSub2 = findViewById(R.id.fee_spring_sub_2);
        feespringSubcomment = findViewById(R.id.fee_spring_sub_comment);


        fallClassesSemester3 = findViewById(R.id.fall_classes_semester3);
        sprigClassesSemester24 = findViewById(R.id.sprig_classes_semester24);
        fallClassesSemester2 = findViewById(R.id.fall_classes_semester2);
        fallClassesSemester1 = findViewById(R.id.fall_classes_semester1);
        fallTeachingSemester3 = findViewById(R.id.fall_teaching_semester3);
        springTeachingSemester24 = findViewById(R.id.spring_teaching_semester24);
        fallTeachingSemester1 = findViewById(R.id.fall_teaching_semester1);
        fallMidendSemester13 = findViewById(R.id.fall_midend_semester13);
        springMidendSemester24 = findViewById(R.id.spring_midend_semester24);
        fallTeachingSemestersecond = findViewById(R.id.fall_teaching_semestersecond);
        springTeachingSemestersecond = findViewById(R.id.spring_teaching_semestersecond);
        fallBreakSemester13 = findViewById(R.id.fall_break_semester13);
        springBreakSemester24 = findViewById(R.id.spring_break_semester24);
        fallTeachingSemesterthird = findViewById(R.id.fall_teaching_semesterthird);
        springWeekendDays = findViewById(R.id.spring_weekend_days);
        fallendBreakSemester13 = findViewById(R.id.fallend_break_semester13);
        springClosingDays = findViewById(R.id.spring_closing_days);
        fallWeekendDays = findViewById(R.id.fall_weekend_days);
        internshipDays = findViewById(R.id.internship_days);
        fallClosingDays = findViewById(R.id.fall_closing_days);
        backlogDates = findViewById(R.id.backlog_dates);
        fallWinterBreak = findViewById(R.id.fall_winter_break);
        springSummerBreak = findViewById(R.id.spring_summer_break);
        subj1 = findViewById(R.id.sub1);
        subj2 = findViewById(R.id.sub2);
        subj3 = findViewById(R.id.sub3);
        subj4 = findViewById(R.id.sub4);
        note1 = findViewById(R.id.note_one);
        note2 = findViewById(R.id.note_two);



        if (CalendarVal.equals("Spring")) {
            loadSpringCalendarDetails();
            fall_calendar_layout.setVisibility(View.GONE);
            textView5.setVisibility(View.GONE);
        } else {
            loadFallCalendarDetails();
            spring_calendar_layout.setVisibility(View.GONE);
            sringcalendar.setVisibility(View.GONE);
        }

    }

    private void loadFallCalendarDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 0; i < jsonarray.length(); i++) {

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
                                String subject1 = jsonobject.getString("subject1");
                                String subject2 = jsonobject.getString("subject2");
                                String subject3 = jsonobject.getString("subject3");
                                String subject4 = jsonobject.getString("subject4");
                                String note_one = jsonobject.getString("note1");
                                String note_two = jsonobject.getString("note2");

                                String fallclassessemester3registration = jsonobject.getString("fall_classes_semester3_registration");
                                String feefallsub1 = jsonobject.getString("fee_fall_sub_1");
                                String feefallsub2 = jsonobject.getString("fee_fall_sub_2");
                                String fee_fall_sub_comment = jsonobject.getString("fee_spring_sub_comment");

                                accademicCalendar.setText(accademiccalendar);
                                fallYear.setText(fallyear);
                                fallSemesterSesion.setText(fallsemestersesion);
                                fallClassesSemester3.setText(fallclassessemester3);
                                fallClassesSemester2.setText(fallclassessemester2);
                                fallClassesSemester1.setText(fallclassessemester1);
                                fallTeachingSemester3.setText(fallteachingsemester3);
                                fallTeachingSemester1.setText(fallteachingsemester1);
                                fallMidendSemester13.setText(fallmidendsemester13);
                                fallTeachingSemestersecond.setText(fallteachingsemestersecond);
                                fallBreakSemester13.setText(fallbreaksemester13);
                                fallTeachingSemesterthird.setText(fallteachingsemesterthird);
                                fallendBreakSemester13.setText(fallendbreaksemester13);
                                fallWeekendDays.setText(fallweekenddays);
                                fallClosingDays.setText(fallclosingdays);
                                internshipDays.setText(internshipdays);
                                backlogDates.setText(backlogdates);
                                fallWinterBreak.setText(fallwinterbreak);
                                subj1.setText(subject1);
                                subj2.setText(subject2);
                                subj3.setText(subject3);
                                subj4.setText(subject4);
                                note1.setText(note_one);
                                note2.setText(note_two);

                                fallclassessemester3Registration.setText(fallclassessemester3registration);
                                feefallSub1.setText(feefallsub1);
                                feefallSub2.setText(feefallsub2);
                                feefallSubcomment.setText(fee_fall_sub_comment);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void loadSpringCalendarDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            for (int i = 0; i < jsonarray.length(); i++) {

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
                                String subject1 = jsonobject.getString("subject1");
                                String subject2 = jsonobject.getString("subject2");
                                String subject3 = jsonobject.getString("subject3");
                                String subject4 = jsonobject.getString("subject4");
                                String note_one = jsonobject.getString("note1");
                                String note_two = jsonobject.getString("note2");


                                String springclassessemester24registration = jsonobject.getString("spring_classes_semester24_registration");
                                String feespringsub1 = jsonobject.getString("fee_spring_sub_1");
                                String feespringsub2 = jsonobject.getString("fee_spring_sub_2");
                                String feespringsubcomment = jsonobject.getString("fee_spring_sub_comment");



                                accademicCalendar.setText(accademiccalendar);
                                springYear.setText(springyear);
                                springSemesterSesion.setText(springsemestersesion);
                                sprigClassesSemester24.setText(sprigclassessemester24);
                                springTeachingSemester24.setText(springteachingsemester24);
                                springMidendSemester24.setText(springmidendsemester24);
                                springTeachingSemestersecond.setText(springteachingsemestersecond);
                                springBreakSemester24.setText(springbreaksemester24);
                                springWeekendDays.setText(springweekenddays);
                                springClosingDays.setText(springclosingdays);
                                springSummerBreak.setText(springsummerbreak);
                                backlogDates.setText(backlogdates);
                                internshipDays.setText(internshipdays);

                                springclassessemester24Registration.setText(springclassessemester24registration);
                                feespringSub1.setText(feespringsub1);
                                feespringSub2.setText(feespringsub2);
                                feespringSubcomment.setText(feespringsubcomment);
                                subj1.setText(subject1);
                                subj2.setText(subject2);
                                subj3.setText(subject3);
                                subj4.setText(subject4);
                                note1.setText(note_one);
                                note2.setText(note_two);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}