package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
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
 * Created by kamalshree on 11/2/2017.
 */

public class ProgramScreenAllCourses extends AppCompatActivity {
    private Toolbar studentpic_toolbar;
    private String coursesname;
    private TextView Studentpic_programstudentallcourselist_title;

    private String studentfulllist_url = "https://newindialms.000webhostapp.com/get_student_fulllist.php";
    ProgramScreenAllCoursesAdapter adapter;
    List<ProgramScreenAllCoursesListItems> heroList;
    ListView listView;
    private int Student_size;
    public SwipeRefreshLayout swipeRefreshLayout;
    ProgramScreenAllCoursesListItems arraycount = new ProgramScreenAllCoursesListItems();
    private TextView Studentpic_programstudentallcourselist_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_allcourse);
        coursesname = getIntent().getStringExtra("allcoursename");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title = (TextView) findViewById(R.id.studentpic_title);

        studentpic_title.setText(coursesname);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programstudentallcourselist_title = (TextView) findViewById(R.id.Studentpic_programstudentallcourselist_title);
        Studentpic_programstudentallcourselist_title.setText(coursesname);
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreenstudentallcourselist_ListView);

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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, studentfulllist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("studentlist");

                    Student_size = j.getInt("rowcount");
                    arraycount.setRowcount(Student_size);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenAllCoursesListItems listItemProgramList = new ProgramScreenAllCoursesListItems(
                                jsonObject1.getString("student_firstname"),
                                jsonObject1.getString("student_lastname"),
                                jsonObject1.getString("student_rollnno")


                        );
                        myarraycount(Student_size);
                        heroList.add(listItemProgramList);
                    }
                    adapter = new ProgramScreenAllCoursesAdapter(getApplicationContext(), R.layout.activity_program_screen_allcourselistitems, heroList, Student_size);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_coursename", coursesname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void myarraycount(int rowcount) {
        Studentpic_programstudentallcourselist_total = (TextView) findViewById(R.id.Studentpic_programstudentallcourselist_total);
        Studentpic_programstudentallcourselist_total.setText("Total Students : " + rowcount);
    }

}
