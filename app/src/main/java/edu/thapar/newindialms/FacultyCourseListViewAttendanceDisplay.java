package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
 * Created by kamalshree on 11/19/2017.
 */

public class FacultyCourseListViewAttendanceDisplay extends AppCompatActivity {
    public static final String attendancedetails_url = "https://www.newindialms.com/get_attendancedetails.php";
    String attendance_date, course_details_name, faculty_employeeid, groupname, sectionname, coursetype;
    TextView facultycourselist_attendancedisplay_title;
    Toolbar faculty_toolbar;
    FacultyCourseListViewAttendanceDisplayAdapter adapter;
    private AlertDialog.Builder builder;
    public SwipeRefreshLayout swipeRefreshLayout;

    List<FacultyCourseListViewAttendanceDisplayListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_attendancedisplay);
        attendance_date = getIntent().getStringExtra("attendance_date");
        course_details_name = getIntent().getStringExtra("course_details_name");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");

        coursetype = getIntent().getStringExtra("coursetype");
        if (coursetype.equals("1")) {
            groupname = getIntent().getStringExtra("groupname");
            sectionname = getIntent().getStringExtra("sectionname");
        } else {
            groupname = "0";
            sectionname = "0";
        }

        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(course_details_name);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        facultycourselist_attendancedisplay_title = (TextView) findViewById(R.id.facultycourselist_attendancedisplay_title);
        facultycourselist_attendancedisplay_title.setText(course_details_name + " Attendance");
        listView = (ListView) findViewById(R.id.facultycourselistattendancedisplaylist_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.showfeedback_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                heroList.clear();

                loadRecyclerViewData();
            }
        });

    }


    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, attendancedetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("attendancelist");

                    if (array != null && array.length() > 0) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject1 = array.getJSONObject(i);
                            String rollno = jsonObject1.getString("student_rollnno");
                            if (rollno.equals("0"))
                                break;
                            String[] items = rollno.split(",");
                            for (int k = 0; k < items.length; k++) {
                                FacultyCourseListViewAttendanceDisplayListItems listItemProgramList = new FacultyCourseListViewAttendanceDisplayListItems(
                                        items[k],
                                        jsonObject1.getString("attendance_status"),
                                        jsonObject1.getString("student_firstname")
                                );
                                heroList.add(listItemProgramList);
                            }
                        }
                        adapter = new FacultyCourseListViewAttendanceDisplayAdapter(getApplicationContext(), R.layout.activity_faculty_courselist_attendancedisplay_listitems, heroList);
                        listView.setAdapter(adapter);
                    } else {
                        //Toast.makeText(FacultyScheduleDisplay.this,"inside else",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(FacultyCourseListViewAttendanceDisplay.this, R.style.MyFacultyAlertDialogStyle);
                        builder.setTitle("Records");
                        builder.setMessage("No Records available for the selected Day.");
                        displayAlert();
                    }

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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("attendance_date", attendance_date);
                params.put("course_details_name", course_details_name);
                params.put("faculty_employeeid", faculty_employeeid);
                params.put("groupname", groupname);
                params.put("sectionname", sectionname);
                params.put("coursetype", coursetype);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
