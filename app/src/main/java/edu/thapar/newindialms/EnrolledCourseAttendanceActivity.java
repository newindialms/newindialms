package edu.thapar.newindialms;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
 * A simple {@link Fragment} subclass.
 */
public class EnrolledCourseAttendanceActivity extends AppCompatActivity {
    private String enrolled_courselist = "https://www.newindialms.com/listenrolledcourses.php";
    EnrolledCourseAttendanceAdapter adapter;
    private String studentid,studentyear;
    List<EnrolledCourseListItems> heroList;
    ListView listView;
    private Toolbar student_toolbar;
    private TextView student_toolbar_title;
    public SwipeRefreshLayout swipeRefreshLayout;

    public EnrolledCourseAttendanceActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_attendance);

        student_toolbar = findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(student_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        student_toolbar_title = findViewById(R.id.student_enroll_toolbar_title);
        student_toolbar_title.setText("My Attendance");

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(EnrolledCourseAttendanceActivity.this, StudentMenu.class);
                finish();
            }
        });

        Intent intent = getIntent();
        studentid = intent.getStringExtra("studentid");
        studentyear = intent.getStringExtra("studentyear");

        heroList = new ArrayList<>();
        listView = findViewById(R.id.enrolledcourselistView);
        loadRecyclerViewData();

        swipeRefreshLayout = findViewById(R.id.showfeedback_swipe);
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolled_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        EnrolledCourseListItems listItemProgramList = new EnrolledCourseListItems(
                                array.getString(i), studentid,studentyear
                        );
                        listItemProgramList.setStudentid(studentid);
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrolledCourseAttendanceAdapter(getApplicationContext(), R.layout.activity_enrolled_course_attendance_listitems, heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_rollno", studentid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
