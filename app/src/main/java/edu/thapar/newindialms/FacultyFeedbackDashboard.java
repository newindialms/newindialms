package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
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
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackDashboard extends AppCompatActivity {
    public static final String feedack_type_url = "https://www.newindialms.com/get_feedbacktype.php";
    private String coursename, faculty_employeeid, datevalue;
    private TextView facultycourselist_program_title;
    private Toolbar faculty_toolbar;

    //a List of type hero for holding list items
    FacultyFeedbackDashboardAdapter adapter;
    List<FacultyFeedbackDashboardListItems> heroList;

    //the listview
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_feedback_dashboard);
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");
        datevalue = getIntent().getStringExtra("datevalue");

        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        facultycourselist_program_title = findViewById(R.id.faculty_feedback_dashboard_title);
        facultycourselist_program_title.setText("My Feedback");

        //initializing objects
        heroList = new ArrayList<>();
        listView = findViewById(R.id.facultyfeedbackdashboardlist_ListView);


        LoadFeedbackType();

    }

    private void LoadFeedbackType() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, feedack_type_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        FacultyFeedbackDashboardListItems listItemProgramList = new FacultyFeedbackDashboardListItems(
                                obj.getString("feedback_type"), coursename, faculty_employeeid, datevalue
                        );
                        heroList.add(listItemProgramList);

                    }
                    adapter = new FacultyFeedbackDashboardAdapter(FacultyFeedbackDashboard.this, R.layout.activity_faculty_feedback_dashboard_listitems, heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(FacultyFeedbackDashboard.this).add(stringRequest);
    }

}
