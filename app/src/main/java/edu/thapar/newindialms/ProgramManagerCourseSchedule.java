package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamalshree on 11/9/2017.
 */

public class ProgramManagerCourseSchedule extends AppCompatActivity {
String issue_details,day_details,semester_details;
    private Toolbar studentpic_toolbar;
    private TextView timetable_title3,timetable_title2,timetable_title5,timetable_title6,timetable_title4;

    private String course_schedule_issue_url = "https://newindialms.000webhostapp.com/get_course_schedule_issue.php";
    private String time_table_issue_url = "https://newindialms.000webhostapp.com/get_timetable.php";
    List<ProgramScreenCourseScheduleListItems> heroList;
    ProgramScreenCourseScheduleAdapter adapter;
    ListView listView;
    LayoutInflater layoutinflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_courseschedule);
        issue_details = getIntent().getStringExtra("issue_details");
        day_details = getIntent().getStringExtra("day_details");
        semester_details = getIntent().getStringExtra("semester_details");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView studentpic_title=(TextView)findViewById(R.id.studentpic_title);
        studentpic_title.setText("MBA Program Time Table");

        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.programscreen_courseschedulelist_ListView);
        loadRecyclerViewData();
        loadRecyclerTimeTable();

        layoutinflater = getLayoutInflater();

        ViewGroup footer = (ViewGroup)layoutinflater.inflate(R.layout.activity_timetable_footer,listView,false);
        listView.addFooterView(footer);

        TextView timetable_table=(TextView)findViewById(R.id.timetable_table);
        timetable_table.setPaintFlags(timetable_table.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        timetable_table.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(Intent.ACTION_VIEW);
               i.setData(Uri.parse("https://newindialms.000webhostapp.com/timetable.php"));
               startActivity(i);

           }
        });
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, course_schedule_issue_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("Issue");
                    JSONObject jsonObject1 = array.getJSONObject(0);
                        myarraycount(jsonObject1.getString("course_schedule_from"), jsonObject1.getString("course_schedule_to"),jsonObject1.getString("course_schedule_duration"));

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("course_schedule_issue", issue_details);
                params.put("course_schedule_issue_semester", semester_details);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }



    public void myarraycount(String from,String to,String Duration){
        timetable_title3 = (TextView)findViewById(R.id.timetable_title3);
        timetable_title2 = (TextView)findViewById(R.id.timetable_title2);

        timetable_title5 = (TextView)findViewById(R.id.timetable_title5);
        timetable_title6 = (TextView)findViewById(R.id.timetable_title6);
        timetable_title4 = (TextView)findViewById(R.id.timetable_title4);

        timetable_title3.setText("Issue: "+issue_details);
        timetable_title2.setText(semester_details);
        timetable_title6.setText(from);
        timetable_title5.setText(to);
        timetable_title4.setText(Duration);
    }

    private void loadRecyclerTimeTable() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, time_table_issue_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("timetable");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenCourseScheduleListItems listItemProgramList = new ProgramScreenCourseScheduleListItems(
                                jsonObject1.getString("course_schedule_day"),
                                jsonObject1.getString("course_schedule_date"),
                                jsonObject1.getString("course_schedule_starttime"),
                                jsonObject1.getString("course_schedule_endtime"),
                                jsonObject1.getString("course_schedule_course"),
                                jsonObject1.getString("course_schedule_faculty"),
                                jsonObject1.getString("course_schedule_program")
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new ProgramScreenCourseScheduleAdapter(getApplicationContext(),R.layout.activity_program_screencourseschedulelistitems,heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("course_schedule_issue", issue_details);
                params.put("course_schedule_day", day_details);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
