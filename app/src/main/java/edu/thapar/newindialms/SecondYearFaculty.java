package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
 * Created by kamalshree on 12/25/2018.
 */

public class SecondYearFaculty extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<SecondYearFacultyDetails> secondyearfacultyDetails;
    private Toolbar student_toolbar;
    private String coursename;
    private static final String FACULTYDETAILS_URL = "https://www.newindialms.com/get_second_year_faculty_name.php";
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_firstyear_facultydetails);
        coursename=getIntent().getStringExtra("coursename");
        student_toolbar = findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.student_enroll_toolbar_title);
        faculty_title.setText("Course Details");
        setSupportActionBar(student_toolbar);

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        secondyearfacultyDetails = new ArrayList<>();
        recyclerView = findViewById(R.id.faculty_firstyear_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadFacultyDetails();
    }

    private void LoadFacultyDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FACULTYDETAILS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject j = new JSONObject(response);
                            JSONArray array = j.getJSONArray("facultydetails");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                SecondYearFacultyDetails listItemProgramList = new SecondYearFacultyDetails(
                                        jsonObject1.getString("faculty_firstname"),
                                        jsonObject1.getString("faculty_lastname"),
                                        jsonObject1.getString("faculty_phone"),
                                        jsonObject1.getString("faculty_email"),
                                        jsonObject1.getString("course_credits")
                                );
                                secondyearfacultyDetails.add(listItemProgramList);

                            }
                            SecondYearFacultyDetailsAdapter adapter = new SecondYearFacultyDetailsAdapter(getApplicationContext(), secondyearfacultyDetails);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("coursename", coursename);
                return params;
            }
        };

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
