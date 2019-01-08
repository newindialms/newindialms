package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
 * A simple {@link Fragment} subclass.
 */
public class EnrolledCoreCourseActivity extends AppCompatActivity {
    private String enrolled_core_courselist = "https://www.newindialms.com/listcorecourses.php";
    EnrolledCourseAdapter adapter;
    private String studentid, studentyear;
    List<EnrolledCourseListItems> heroList;
    ListView listView;
    private AlertDialog.Builder builder;
    private Toolbar studentprofile_toolbar;
    private TextView studentpic_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enrolled_course_activity);
       studentid = getIntent().getExtras().getString("studentid");
        studentyear = getIntent().getExtras().getString("studentyear");

        studentprofile_toolbar = (Toolbar) findViewById(R.id.studentprofile_toolbar);
        studentprofile_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(studentprofile_toolbar);
        studentpic_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        studentpic_title.setText("My Core Courses");
        studentprofile_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.enrolledcourselistView);
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(EnrolledCoreCourseActivity.this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, enrolled_core_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);

                    JSONArray array = j.getJSONArray("course_details");
                    for (int i = 0; i < array.length(); i++) {

                        EnrolledCourseListItems listItemProgramList = new EnrolledCourseListItems(
                                array.getString(i)
                        );
                        heroList.add(listItemProgramList);

                    }
                    adapter = new EnrolledCourseAdapter(getApplicationContext(), R.layout.fragment_enrolled_course_listitems, heroList, studentyear);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
