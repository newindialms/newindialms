package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ExpandableListView;
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
 * Created by kamalshree on 11/20/2017.
 */

public class StudentScheduleDisplay extends AppCompatActivity {
    String datevalue,student_specialization;
    TextView Studentpic_program_title;
    Toolbar studentpic_toolbar;
    String schedule_url = "https://newindialms.000webhostapp.com/get_student_schedule.php";
    StudentScheduleDisplayAdapter adapter;

    List<StudentScheduleDisplayListItems> heroList;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule_display);
        student_specialization = getIntent().getStringExtra("student_specialization");
        datevalue = getIntent().getStringExtra("datevalue");

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView student_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        student_title.setText("My Schedule");

        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_program_title = (TextView) findViewById(R.id.studentschedule_display_title);
        Studentpic_program_title.setText("My schedule on "+datevalue);
        heroList = new ArrayList<>();
        listView = (RecyclerView) findViewById(R.id.studentcheduledisplaylist_ListView);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, schedule_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("schedulelist");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        StudentScheduleDisplayListItems listItemProgramList = new StudentScheduleDisplayListItems(
                                jsonObject1.getString("course_schedule_program"),
                                jsonObject1.getString("course_schedule_starttime"),
                                jsonObject1.getString("course_schedule_endtime"),
                                jsonObject1.getString("course_schedule_course"),
                                jsonObject1.getString("course_schedule_faculty")
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new StudentScheduleDisplayAdapter(heroList,getApplicationContext());
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
                params.put("student_specialization", student_specialization);
                params.put("datevalue", datevalue);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
