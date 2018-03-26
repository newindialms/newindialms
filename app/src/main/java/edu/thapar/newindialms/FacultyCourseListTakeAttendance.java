package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListTakeAttendance extends AppCompatActivity {
    public static final String enrolledstudent_url = "https://newindialms.000webhostapp.com/get_student_fulllist.php";
    public static final String saveattendance_URL = "https://newindialms.000webhostapp.com/saveattendance.php";
    private String coursename, faculty_employeeid,course_date,course_time;
    private TextView facultycourselist_program_title;
    private Toolbar faculty_toolbar;
    LayoutInflater layoutinflater;
    String[] arrayattendancelist;
    private AlertDialog.Builder builder;
    private String attendance_status;
    //a List of type hero for holding list items
    FacultyCourseListTakeAttendanceAdapter adapter;

    List<FacultyCourseListTakeAttendanceListItems> heroList;
    ListView listView;

    public static ArrayList<String> presentlist = new ArrayList<String>();
    public static ArrayList<String> absentlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_courselist_takeattendance);
        coursename = getIntent().getStringExtra("coursename");
        faculty_employeeid = getIntent().getStringExtra("faculty_employeeid");


        faculty_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(coursename);
        setSupportActionBar(faculty_toolbar);
        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        facultycourselist_program_title = (TextView) findViewById(R.id.facultycourselist_takeattendance_title);
        facultycourselist_program_title.setText(coursename + " Take Attendance");
        listView = (ListView) findViewById(R.id.facultycourselisttakeattendancelist_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();

        builder = new AlertDialog.Builder(this, R.style.MyFacultyAlertDialogStyle);
        layoutinflater = getLayoutInflater();

        ViewGroup footer = (ViewGroup) layoutinflater.inflate(R.layout.activity_faculty_takeattendance_footer, listView, false);
        listView.addFooterView(footer);
    }

    public void showResult(View v) {
        for (FacultyCourseListTakeAttendanceListItems p :adapter.getpresentattendanceDetails()) {
            presentlist.add(p.getStudentrollno());
        }
        for (FacultyCourseListTakeAttendanceListItems p :adapter.getabsentattendanceDetails()) {
            absentlist.add(p.getStudentrollno());
            attendance_status="Absent";
        }
        savepresentDetails();
        saveabsentDetails();
    }

public void savepresentDetails()
    {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        attendance_status="Present";
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, saveattendance_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            String message_time = jsonObject.getString("message_time");
                            String message_date = jsonObject.getString("message_date");
                            if (code.equals("Success")) {
                                loading.dismiss();
                                builder.setTitle(code);
                                builder.setMessage("Now Select the Feedback's");
                                displayAlert(message_time,message_date);

                            } else {
                                loading.dismiss();
                                builder.setTitle("failed");
                                builder.setMessage("Try again");
                               // displayAlert("input_error");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            builder.setTitle("Success");
                            builder.setMessage("Data saved successfully");
                           // displayAlert("input_success");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(FacultyCourseListTakeAttendance.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> numbers = new ArrayList<String>();
                arrayattendancelist = new String[adapter.getpresentattendanceDetails().size()];
                for (int i = 0; i < adapter.getpresentattendanceDetails().size(); i++) {
                    numbers.add(adapter.getpresentattendanceDetails().get(i).getStudentrollno());
                }

                int j=0;
                for(String object: numbers){
                    params.put("student_rollnno["+(j++)+"]", object);
                }

                params.put("faculty_employeeid", faculty_employeeid);
                params.put("course_details_name", coursename);
                params.put("attendance_status", attendance_status);
                return params;

            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void saveabsentDetails()
    {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        attendance_status="Absent";
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, saveattendance_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("Success")) {
                                loading.dismiss();
                                builder.setTitle(code);
                                builder.setMessage("Now Select the Feedback's");

                            } else {
                                loading.dismiss();
                                builder.setTitle("failed");
                                builder.setMessage("Try again");
                               // displayAlert("input_error");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //builder.setTitle("Success");
                           // builder.setMessage("Data saved successfully");
                            //displayAlert("input_success");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(FacultyCourseListTakeAttendance.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> numbers = new ArrayList<String>();
                arrayattendancelist = new String[adapter.getabsentattendanceDetails().size()];
                for (int i = 0; i < adapter.getabsentattendanceDetails().size(); i++) {
                    numbers.add(adapter.getabsentattendanceDetails().get(i).getStudentrollno());
                }

                int j=0;
                for(String object: numbers){
                    params.put("student_rollnno["+(j++)+"]", object);
                }

                params.put("faculty_employeeid", faculty_employeeid);
                params.put("course_details_name", coursename);
                params.put("attendance_status", attendance_status);
                return params;
            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolledstudent_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("studentlist");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        FacultyCourseListTakeAttendanceListItems listItemProgramList = new FacultyCourseListTakeAttendanceListItems(
                                jsonObject1.getString("student_name"),
                                jsonObject1.getString("student_rollnno")
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new FacultyCourseListTakeAttendanceAdapter(getApplicationContext(), R.layout.activity_faculty_courselist_takeattendance_listitems, R.layout.activity_faculty_takeattendance_footer, heroList);
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
                params.put("student_coursename", coursename);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert(final String code_time,final String code_date) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                course_date=code_date;
                course_time=code_time;
                finish();
                Intent feedbackScreenIntent=new Intent(FacultyCourseListTakeAttendance.this,FacultySelectFeedbackScreen.class);
                feedbackScreenIntent.putExtra("coursename",coursename);
                feedbackScreenIntent.putExtra("faculty_employeeid",faculty_employeeid);
                feedbackScreenIntent.putExtra("course_date",course_date);
                feedbackScreenIntent.putExtra("course_time",course_time);
                startActivity(feedbackScreenIntent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}