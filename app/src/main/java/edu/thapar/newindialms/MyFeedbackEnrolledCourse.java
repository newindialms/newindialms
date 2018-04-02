package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
 * Created by kamalshree on 3/3/2018.
 */

public class MyFeedbackEnrolledCourse extends AppCompatActivity {

    private String enrolled_courselist = "https://newindialms.000webhostapp.com/listenrolledcourses.php";
    MyfeedbackEnrolledCourseAdapter adapter;
    List<MyfeedbackEnrolledCourseListItems> heroList;
    ListView listView;
    private TextView myfeedbackenrolledcourses_title;
    private Toolbar studentpic_toolbar;
    public SwipeRefreshLayout swipeRefreshLayout;
    private String studentid,course_date,course_time,faculty_employeeid,coursename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback_enrolled_course);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title=(TextView)findViewById(R.id.student_enroll_toolbar_title);

        studentid=getIntent().getStringExtra("studentid");
        course_date=getIntent().getStringExtra("course_date");
        course_time=getIntent().getStringExtra("course_time");
        faculty_employeeid=getIntent().getStringExtra("faculty_employeeid");
        coursename=getIntent().getStringExtra("coursename");

        myfeedbackenrolledcourses_title.setText("My Feedback");
        setSupportActionBar(studentpic_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MyFeedbackEnrolledCourse.this, StudentHome.class));
                finish();
            }
        });

        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.myfeedbackenrolledcourselistView);

        loadRecyclerViewData();

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.showfeedback_swipe);
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
        final ProgressDialog progressDialog = new ProgressDialog(MyFeedbackEnrolledCourse.this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolled_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        MyfeedbackEnrolledCourseListItems listItemProgramList = new MyfeedbackEnrolledCourseListItems(
                                array.getString(i),studentid,course_date,course_time,faculty_employeeid,coursename
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new MyfeedbackEnrolledCourseAdapter(MyFeedbackEnrolledCourse.this,R.layout.myfeedback_enrolled_course_listitems,heroList);
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
                Toast.makeText(MyFeedbackEnrolledCourse.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_rollno", studentid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MyFeedbackEnrolledCourse.this);
        requestQueue.add(stringRequest);
    }

}