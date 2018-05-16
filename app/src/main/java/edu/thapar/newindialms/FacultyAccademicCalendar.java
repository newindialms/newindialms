package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class FacultyAccademicCalendar extends Fragment {

    private String calendatdetails_url = "https://www.newindialms.com/get_calendardetails.php";
    private TextView accademic_calendar, fall_year, fall_semester_sesion, spring_year,
            spring_semester_sesion,
            fall_classes_semester3,
            sprig_classes_semester24,
            fall_classes_semester2,
            fall_classes_semester1,
            fall_teaching_semester3,
            spring_teaching_semester24,
            fall_teaching_semester1,
            fall_midend_semester13,
            spring_midend_semester24,
            fall_teaching_semestersecond,
            spring_teaching_semestersecond,
            fall_break_semester13,
            spring_break_semester24,
            fall_teaching_semesterthird,
            spring_weekend_days,
            fallend_break_semester13,
            spring_closing_days,
            fall_weekend_days,
            internship_days,
            fall_closing_days,
            backlog_dates,
            fall_winter_break,
            spring_summer_break;
    private View rootview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.activity_academic_calendar_faculty, container, false);
        accademic_calendar = (TextView) rootview.findViewById(R.id.accademic_calendar);
        fall_year = (TextView) rootview.findViewById(R.id.fall_year);
        fall_semester_sesion = (TextView) rootview.findViewById(R.id.fall_semester_sesion);
        spring_year = (TextView) rootview.findViewById(R.id.spring_year);
        spring_semester_sesion = (TextView) rootview.findViewById(R.id.spring_semester_sesion);
        fall_classes_semester3 = (TextView) rootview.findViewById(R.id.fall_classes_semester3);
        sprig_classes_semester24 = (TextView) rootview.findViewById(R.id.sprig_classes_semester24);
        fall_classes_semester2 = (TextView) rootview.findViewById(R.id.fall_classes_semester2);
        fall_classes_semester1 = (TextView) rootview.findViewById(R.id.fall_classes_semester1);
        fall_teaching_semester3 = (TextView) rootview.findViewById(R.id.fall_teaching_semester3);
        spring_teaching_semester24 = (TextView) rootview.findViewById(R.id.spring_teaching_semester24);
        fall_teaching_semester1 = (TextView) rootview.findViewById(R.id.fall_teaching_semester1);
        fall_midend_semester13 = (TextView) rootview.findViewById(R.id.fall_midend_semester13);
        spring_midend_semester24 = (TextView) rootview.findViewById(R.id.spring_midend_semester24);
        fall_teaching_semestersecond = (TextView) rootview.findViewById(R.id.fall_teaching_semestersecond);
        spring_teaching_semestersecond = (TextView) rootview.findViewById(R.id.spring_teaching_semestersecond);
        fall_break_semester13 = (TextView) rootview.findViewById(R.id.fall_break_semester13);
        spring_break_semester24 = (TextView) rootview.findViewById(R.id.spring_break_semester24);
        fall_teaching_semesterthird = (TextView) rootview.findViewById(R.id.fall_teaching_semesterthird);
        spring_weekend_days = (TextView) rootview.findViewById(R.id.spring_weekend_days);
        fallend_break_semester13 = (TextView) rootview.findViewById(R.id.fallend_break_semester13);
        spring_closing_days = (TextView) rootview.findViewById(R.id.spring_closing_days);
        fall_weekend_days = (TextView) rootview.findViewById(R.id.fall_weekend_days);
        internship_days = (TextView) rootview.findViewById(R.id.internship_days);
        fall_closing_days = (TextView) rootview.findViewById(R.id.fall_closing_days);
        backlog_dates = (TextView) rootview.findViewById(R.id.backlog_dates);
        fall_winter_break = (TextView) rootview.findViewById(R.id.fall_winter_break);
        spring_summer_break = (TextView) rootview.findViewById(R.id.spring_summer_break);
        loadCalendarDetails();
        return rootview;
    }

    private void loadCalendarDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendatdetails_url,
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

                                accademic_calendar.setText(accademiccalendar);
                                fall_year.setText(fallyear);
                                fall_semester_sesion.setText(fallsemestersesion);
                                spring_year.setText(springyear);
                                spring_semester_sesion.setText(springsemestersesion);
                                fall_classes_semester3.setText(fallclassessemester3);
                                sprig_classes_semester24.setText(sprigclassessemester24);
                                fall_classes_semester2.setText(fallclassessemester2);
                                fall_classes_semester1.setText(fallclassessemester1);
                                fall_teaching_semester3.setText(fallteachingsemester3);
                                spring_teaching_semester24.setText(springteachingsemester24);
                                fall_teaching_semester1.setText(fallteachingsemester1);
                                fall_midend_semester13.setText(fallmidendsemester13);
                                spring_midend_semester24.setText(springmidendsemester24);
                                fall_teaching_semestersecond.setText(fallteachingsemestersecond);
                                spring_teaching_semestersecond.setText(springteachingsemestersecond);
                                fall_break_semester13.setText(fallbreaksemester13);
                                spring_break_semester24.setText(springbreaksemester24);
                                fall_teaching_semesterthird.setText(fallteachingsemesterthird);
                                spring_weekend_days.setText(springweekenddays);
                                fallend_break_semester13.setText(fallendbreaksemester13);
                                spring_closing_days.setText(springclosingdays);
                                fall_weekend_days.setText(fallweekenddays);
                                internship_days.setText(internshipdays);
                                fall_closing_days.setText(fallclosingdays);
                                backlog_dates.setText(backlogdates);
                                fall_winter_break.setText(fallwinterbreak);
                                spring_summer_break.setText(springsummerbreak);
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

                            Toast.makeText(getContext(), "Something went wrong Try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_calendar));

    }
}
