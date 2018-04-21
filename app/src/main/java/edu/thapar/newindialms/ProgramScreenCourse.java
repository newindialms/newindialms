package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenCourse extends AppCompatActivity {
    private String ProgramName;
    private TextView Studentpic_programCourse_title;
    private Toolbar studentpic_toolbar;
    private String coursespecificlist_url = "https://newindialms.000webhostapp.com/get_course_specific.php";
    ProgramScreenCourseAdapter adapter;
    public SwipeRefreshLayout swipeRefreshLayout;
    List<ProgramScreenCourseListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_course);
        ProgramName = getIntent().getStringExtra("programname");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title = (TextView) findViewById(R.id.studentpic_title);
        studentpic_title.setText(ProgramName);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programCourse_title = (TextView) findViewById(R.id.Studentpic_programcourse_title);
        studentpic_title = (TextView) findViewById(R.id.studentpic_title);
        studentpic_title.setText("Courses");
        Studentpic_programCourse_title.setText("Courses");
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreencourselist_ListView);

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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, coursespecificlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("courses");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenCourseListItems listItemProgramList = new ProgramScreenCourseListItems(
                                jsonObject1.getString("course_details_name"),
                                jsonObject1.getString("course_details_code")
                        );
                        heroList.add(listItemProgramList);


                    }
                    adapter = new ProgramScreenCourseAdapter(getApplicationContext(), R.layout.activity_program_screencourselistitems, heroList);
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
        this.setTitle("Courses");
    }

}