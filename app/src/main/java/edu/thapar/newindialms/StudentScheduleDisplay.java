package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private String datevalue, student_specialization, studentid;
    private TextView Studentpic_program_title;
    private Toolbar studentpic_toolbar;
    private String schedule_url = "https://www.newindialms.com/get_student_schedule.php";
    StudentScheduleDisplayAdapter adapter;
    private AlertDialog.Builder builder;
    List<StudentScheduleDisplayListItems> heroList;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule_display);
        student_specialization = getIntent().getStringExtra("student_specialization");
        datevalue = getIntent().getStringExtra("datevalue");
        studentid = getIntent().getStringExtra("studentid");

        studentpic_toolbar = findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView student_title = findViewById(R.id.student_enroll_toolbar_title);
        student_title.setText("My Schedule");

        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_program_title = findViewById(R.id.studentschedule_display_title);
        Studentpic_program_title.setText("My schedule on " + datevalue);
        heroList = new ArrayList<>();
        listView = findViewById(R.id.studentcheduledisplaylist_ListView);
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
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("schedulelist");

                    if (array != null && array.length() > 0) {
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
                        adapter = new StudentScheduleDisplayAdapter(heroList, getApplicationContext());
                        listView.setAdapter(adapter);
                    } else {
                        //Toast.makeText(FacultyScheduleDisplay.this,"inside else",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(StudentScheduleDisplay.this, R.style.MyStudentAlertDialogStyle);
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
                params.put("student_specialization", student_specialization);
                params.put("studentid", studentid);
                params.put("datevalue", datevalue);
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