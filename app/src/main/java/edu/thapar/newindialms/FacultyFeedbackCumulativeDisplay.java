package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

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
 * Created by kamalshree on 4/13/2018.
 */

public class FacultyFeedbackCumulativeDisplay extends AppCompatActivity {

    public static final String rate_url = "https://www.newindialms.com/get_faculty_feedbackquestions.php";
    private String faculty_id, coursename, feedback_sent_date;
    private Toolbar rate_toolbar;
    FacultyFeedackCumulativeDisplayAdapter adapter;
    private AlertDialog.Builder builder;

    List<FacultyFeedbackCumulativeDisplayListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_cumulative_question);

        faculty_id = getIntent().getStringExtra("faculty_employeeid");
        coursename = getIntent().getStringExtra("coursename");
        feedback_sent_date = getIntent().getStringExtra("datevalue");


        rate_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        rate_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView daywise_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        daywise_title.setText("Feedback Questions");
        setSupportActionBar(rate_toolbar);

        rate_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.like_list_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();

    }


    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, rate_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("questions");
                    if (array != null && array.length() > 0) {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject1 = array.getJSONObject(i);
                            FacultyFeedbackCumulativeDisplayListItems listItemProgramList = new FacultyFeedbackCumulativeDisplayListItems(
                                    jsonObject1.getString("feedback_question"), jsonObject1.getString("feedback_question_type"), faculty_id, coursename, feedback_sent_date


                            );
                            heroList.add(listItemProgramList);
                        }
                        adapter = new FacultyFeedackCumulativeDisplayAdapter(getApplicationContext(), R.layout.activity_faculty_cumulative_question_listitems, heroList);
                        listView.setAdapter(adapter);
                    } else {
                        //Toast.makeText(FacultyScheduleDisplay.this,"inside else",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(FacultyFeedbackCumulativeDisplay.this, R.style.MyFacultyAlertDialogStyle);
                        builder.setTitle("Feedback");
                        builder.setMessage("No Records available for the selected search.");
                        displayAlert();
                    }

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
                params.put("faculty_id", faculty_id);
                params.put("coursename", coursename);
                params.put("feedback_sent_date", feedback_sent_date);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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