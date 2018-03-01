package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by kamalshree on 2/21/2018.
 */

public class FacultySelectFeedbackScreen extends AppCompatActivity {

    Toolbar facultyToolbar;
    private static final String FEEDBACK_URL = "http://newindialms.000webhostapp.com/get_feedback.php";
    private static final String SELECTED_FEEDBACK_URL = "http://newindialms.000webhostapp.com/insert_selected_feedback.php";
    String notification_url = "https://newindialms.000webhostapp.com/send_selected_feedback_notification.php";
    private RecyclerView recyclerView;
    TextView facultyToolbarTitle;
    List<FacultySelectFeedbackScreenDetails> facultySelectFeedbackScreenDetails;
    FacultySelectFeedbackScreenAdapter adapter;
    public static ArrayList<String> feedbacklist = new ArrayList<String>();
    private String coursename,faculty_employeeid,course_time,course_date;
    String currentDate;
    Date date = new Date();
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Intent myFeedbackselect;
    String[] feedbacklistarray;
    private String title,message;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_select_feedbackscreen);

        facultyToolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        facultyToolbar.setNavigationIcon(R.drawable.ic_left);
        facultyToolbarTitle = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        facultyToolbarTitle.setText("Select Feedback");

        myFeedbackselect=getIntent();
        coursename = myFeedbackselect.getStringExtra("coursename");
        faculty_employeeid = myFeedbackselect.getStringExtra("faculty_employeeid");
        course_date = myFeedbackselect.getStringExtra("course_date");
        course_time = myFeedbackselect.getStringExtra("course_time");
        currentDate=sdf.format(date);

        setSupportActionBar(facultyToolbar);
        facultyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.faculty_select_feedbackscreen_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        facultySelectFeedbackScreenDetails = new ArrayList<>();
        builder = new AlertDialog.Builder(this, R.style.MyFacultyAlertDialogStyle);

        LoadFeedbackQuestions();
    }

    private void LoadFeedbackQuestions() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FEEDBACK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                FacultySelectFeedbackScreenDetails listItemProgramList = new FacultySelectFeedbackScreenDetails(
                                        obj.getString("id"),
                                        obj.getString("feedback_title"),
                                        obj.getString("feedback_question"),
                                        obj.getString("feedback_type")
                                );
                                facultySelectFeedbackScreenDetails.add(listItemProgramList);


                            }
                           adapter = new FacultySelectFeedbackScreenAdapter(FacultySelectFeedbackScreen.this, facultySelectFeedbackScreenDetails);
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
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(FacultySelectFeedbackScreen.this).add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_feedback_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.feedbacksubmit) {
            sendFeedbackQuestions();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendFeedbackQuestions() {
        showFeedback();
        InsertSelectedFeedback();
        //send notification to present list
        SendNotificationToPresentList();
    }


    public void showFeedback() {
        for (FacultySelectFeedbackScreenDetails p :adapter.getFeedbackDetails()) {
            feedbacklist.add(p.getFeedbackId());
          //  Toast.makeText(FacultySelectFeedbackScreen.this,p.getFeedbackId(),Toast.LENGTH_LONG).show();
           // Toast.makeText(FacultySelectFeedbackScreen.this,coursename+faculty_employeeid+currentDate,Toast.LENGTH_LONG).show();
        }

    }

    public void InsertSelectedFeedback()
    {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SELECTED_FEEDBACK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            if (code.equals("Success")) {
                                builder.setTitle("Success");
                                builder.setMessage("Notification sent Successfully");
                                displayAlert("input_success");
                            } else {
                                loading.dismiss();
                                builder.setTitle("failed");
                                builder.setMessage("Try again");
                                displayAlert("input_error");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(FacultySelectFeedbackScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> feedbackid = new ArrayList<String>();
                feedbacklistarray = new String[adapter.getFeedbackDetails().size()];
                for (int i = 0; i < adapter.getFeedbackDetails().size(); i++) {
                    feedbackid.add(adapter.getFeedbackDetails().get(i).getFeedbackId());
                }

                int j=0;
                for(String object: feedbackid){
                    params.put("feedback_id["+(j++)+"]", object);
                }

                params.put("faculty_rollno", faculty_employeeid);
                params.put("course_details", coursename);
                params.put("course_date", course_date);
                params.put("course_time", course_time);
                return params;

            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("input_success")) {
                    finish();
                    //
                } else if (code.equals("input_error")) {
                    //
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /* Send Notification to the present lsit
     *
     */

    public void SendNotificationToPresentList() {

        title = "Submit Feedback";
        message = "Please submit your feedback for "+coursename;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, notification_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", title);
                params.put("message", message);
                params.put("course_date", course_date);
                params.put("course_time", course_time);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
