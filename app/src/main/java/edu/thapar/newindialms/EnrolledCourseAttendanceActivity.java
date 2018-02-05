package edu.thapar.newindialms;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.support.v7.widget.Toolbar;

import static edu.thapar.newindialms.R.id.container;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrolledCourseAttendanceActivity extends AppCompatActivity {
    String enrolled_courselist = "https://newindialms.000webhostapp.com/listenrolledcourses.php";
    EnrolledCourseAttendanceAdapter adapter;
    String studentid;
    List<EnrolledCourseListItems> heroList;
    ListView listView;
    Toolbar student_toolbar;
    TextView student_toolbar_title;
    public EnrolledCourseAttendanceActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_attendance);

        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(student_toolbar);

        student_toolbar_title=(TextView) findViewById(R.id.student_enroll_toolbar_title);
        student_toolbar_title.setText("My Attendance");

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        Intent intent=getIntent();
        studentid =  intent.getStringExtra("studentid");

        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.enrolledcourselistView);
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolled_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        EnrolledCourseListItems listItemProgramList = new EnrolledCourseListItems(
                               array.getString(i)
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrolledCourseAttendanceAdapter(getApplicationContext(),R.layout.activity_enrolled_course_attendance_listitems,heroList);
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
        }){
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
