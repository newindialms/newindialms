package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamalshree on 3/3/2018.
 */

public class SubmitFeedBackScreen extends AppCompatActivity {

    private String feedbacklist = "https://www.newindialms.com/getFeedbacklist.php";

    private String submitratefeedbacklist = "https://www.newindialms.com/submitRateFeedbacklist.php";
    private String submitlikefeedbacklist = "https://www.newindialms.com/submitLikeFeedbacklist.php";
    private String submittextfeedbacklist = "https://www.newindialms.com/submitTextFeedbacklist.php";
    private String submitsmileyfeedbacklist = "https://www.newindialms.com/submitSmileyFeedbacklist.php";
    private String submit_attendance_after_feedback = "https://www.newindialms.com/submit_attendance_after_feedback.php";

    SubmitFeedBackScreenAdapter adapter;
    List<SubmitFeedbackScreenListItems> heroList;
    ListView listView;
    private TextView myfeedbackenrolledcourses_title;
    private Toolbar studentpic_toolbar;
    private String course_name, student_id, course_date, course_time, faculty_id;
    AlertDialog.Builder builder;
    LayoutInflater layoutinflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_feedback_screen);


        studentpic_toolbar = findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title = findViewById(R.id.student_enroll_toolbar_title);


        course_name = getIntent().getStringExtra("corecoursename");
        student_id = getIntent().getStringExtra("studentid");
        course_date = getIntent().getStringExtra("course_date");
        course_time = getIntent().getStringExtra("course_time");
        faculty_id = getIntent().getStringExtra("faculty_employeeid");


        //Toast.makeText(SubmitFeedBackScreen.this,"Submitfeedback"+course_name+course_date+course_time+faculty_id,Toast.LENGTH_LONG).show();

        myfeedbackenrolledcourses_title.setText("My Feedback");
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        heroList = new ArrayList<>();
        listView = findViewById(R.id.submitfeedbackscreenlistView);
        builder = new AlertDialog.Builder(this, R.style.MyStudentAlertDialogStyle);
        loadRecyclerViewData();

        layoutinflater = getLayoutInflater();

        ViewGroup footer = (ViewGroup) layoutinflater.inflate(R.layout.submit_feedback_button, listView, false);
        listView.addFooterView(footer);
    }

    public void submitfeedbackfunction(View v) {
        /*Toast.makeText(this,"Rate array"+adapter.getRateSubmittedFeedbackDetails().size(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Like array"+adapter.getLikeSubmittedFeedbackDetails().size(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Smiley array"+adapter.getSmileySubmittedFeedbackDetails().size(),Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Text array"+adapter.getTextSubmittedFeedbackDetails().size(),Toast.LENGTH_LONG).show();*/
        //SubmitFeedBack();
        if (adapter.getRateSubmittedFeedbackDetails().size() != 0) {
            SubmitRateFeedBack();
        }
        if (adapter.getLikeSubmittedFeedbackDetails().size() != 0) {
            SubmitLikeFeedBack();
        }
        if (adapter.getSmileySubmittedFeedbackDetails().size() != 0) {
            SubmitSmileyFeedBack();
        }
        if (adapter.getTextSubmittedFeedbackDetails().size() != 0) {
            SubmitTextFeedBack();
        }
        SubmitAttendanceAfterFeedback();
        Intent thankyoufeedbackintent = new Intent(getApplicationContext(), Thankyou_feedback_screen.class);
        startActivity(thankyoufeedbackintent);
    }

    private void SubmitAttendanceAfterFeedback() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_attendance_after_feedback, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONObject array = jsonArray.getJSONObject(0);
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("course_name", course_name);
                params.put("student_id", student_id);
                params.put("course_date", course_date);
                params.put("course_time", course_time);
                params.put("faculty_id", faculty_id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, feedbacklist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("feedback");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        SubmitFeedbackScreenListItems listItemProgramList = new SubmitFeedbackScreenListItems(
                                jsonObject1.getString("feedback_title"),
                                jsonObject1.getString("feedback_question"),
                                jsonObject1.getString("feedback_type")

                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new SubmitFeedBackScreenAdapter(getApplicationContext(), R.layout.submit_feedback_screen_listitems, heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("course_name", course_name);
                params.put("course_date", course_date);
                params.put("course_time", course_time);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    public void SubmitRateFeedBack() {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submitratefeedbacklist,
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
                                builder.setMessage("Feedback Submitted Successfully");
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
                        Toast.makeText(SubmitFeedBackScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> feedbackresponses = new ArrayList<String>();
                ArrayList<String> feedbackquestions = new ArrayList<String>();
                // feedbacklistarray = new String[adapter.getSubmittedFeedbackDetails().size()];

                Set set2 = adapter.getRateSubmittedFeedbackDetails().entrySet();
                Iterator iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    Map.Entry mentry2 = (Map.Entry) iterator2.next();
                    feedbackresponses.add(mentry2.getKey().toString());
                    feedbackquestions.add(mentry2.getValue().toString());
                }

                String joinedresponses= TextUtils.join(",", feedbackresponses);
                String joinedquestions= TextUtils.join(", ", feedbackquestions);

                Log.d("My response", joinedresponses);
                Log.d("My question", joinedquestions);
               /* for (int i = 0; i < adapter.getRateSubmittedFeedbackDetails().size(); i++) {
                    feedbackresponses.add(adapter.getRateSubmittedFeedbackDetails().get(i));
                }

                int j = 0;
                for (String object : feedbackresponses) {
                    params.put("feedback_response[" + (j++) + "]", object);
                }
                int k = 0;
                for (String object : feedbackquestions) {
                    params.put("feedback_question[" + (k++) + "]", object);
                }*/

                params.put("feedback_response",joinedresponses);
                params.put("feedback_question",joinedquestions);
                params.put("faculty_id", faculty_id);
                params.put("coursename", course_name);
                params.put("feedback_sent_date", course_date);
                params.put("feedback_sent_time", course_time);
                params.put("feedback_submit_date", course_date);
                params.put("feedback_submit_time", course_time);
                params.put("student_id", student_id);
                return params;

            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void SubmitLikeFeedBack() {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submitlikefeedbacklist,
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
                                builder.setMessage("Feedback Submitted Successfully");
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
                        Toast.makeText(SubmitFeedBackScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> feedbackresponses = new ArrayList<String>();
                ArrayList<String> feedbackquestions = new ArrayList<String>();
                // feedbacklistarray = new String[adapter.getSubmittedFeedbackDetails().size()];
                Set set2 = adapter.getLikeSubmittedFeedbackDetails().entrySet();
                Iterator iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    Map.Entry mentry2 = (Map.Entry) iterator2.next();
                    feedbackresponses.add(mentry2.getKey().toString());
                    feedbackquestions.add(mentry2.getValue().toString());
                }

                String joinedresponses= TextUtils.join(",", feedbackresponses);
                String joinedquestions= TextUtils.join(", ", feedbackquestions);

                Log.d("My response", joinedresponses);
                Log.d("My question", joinedquestions);
              /*  for (int i = 0; i < adapter.getLikeSubmittedFeedbackDetails().size(); i++) {
                    feedbackresponses.add(adapter.getLikeSubmittedFeedbackDetails().get(i));
                }

                int j = 0;
                for (String object : feedbackresponses) {
                    params.put("feedback_response[" + (j++) + "]", object);
                }
                int k = 0;
                for (String object : feedbackquestions) {
                    params.put("feedback_question[" + (k++) + "]", object);
                }*/

                params.put("feedback_response",joinedresponses);
                params.put("feedback_question",joinedquestions);
                params.put("faculty_id", faculty_id);
                params.put("coursename", course_name);
                params.put("feedback_sent_date", course_date);
                params.put("feedback_sent_time", course_time);
                params.put("feedback_submit_date", course_date);
                params.put("feedback_submit_time", course_time);
                params.put("student_id", student_id);
                return params;

            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void SubmitSmileyFeedBack() {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submitsmileyfeedbacklist,
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
                                builder.setMessage("Feedback Submitted Successfully");
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
                        Toast.makeText(SubmitFeedBackScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> feedbackresponses = new ArrayList<String>();
                ArrayList<String> feedbackquestions = new ArrayList<String>();
                // feedbacklistarray = new String[adapter.getSubmittedFeedbackDetails().size()];

                Set set2 = adapter.getSmileySubmittedFeedbackDetails().entrySet();
                Iterator iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    Map.Entry mentry2 = (Map.Entry) iterator2.next();
                    feedbackresponses.add(mentry2.getKey().toString());
                    feedbackquestions.add(mentry2.getValue().toString());
                }

                String joinedresponses= TextUtils.join(",", feedbackresponses);
                String joinedquestions= TextUtils.join(", ", feedbackquestions);

                Log.d("My response", joinedresponses);
                Log.d("My question", joinedquestions);
              /*  for (int i = 0; i < adapter.getSmileySubmittedFeedbackDetails().size(); i++) {
                    feedbackresponses.add(adapter.getSmileySubmittedFeedbackDetails().get(i));
                }
                int j = 0;
                for (String object : feedbackresponses) {
                    params.put("feedback_response[" + (j++) + "]", object);
                }
                int k = 0;
                for (String object : feedbackquestions) {
                    params.put("feedback_question[" + (k++) + "]", object);
                }*/
                params.put("feedback_response",joinedresponses);
                params.put("feedback_question",joinedquestions);
                params.put("faculty_id", faculty_id);
                params.put("coursename", course_name);
                params.put("feedback_sent_date", course_date);
                params.put("feedback_sent_time", course_time);
                params.put("feedback_submit_date", course_date);
                params.put("feedback_submit_time", course_time);
                params.put("student_id", student_id);
                return params;

            }
        };

        //Adding request the the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void SubmitTextFeedBack() {
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Saving Details", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submittextfeedbacklist,
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
                                builder.setMessage("Feedback Submitted Successfully");
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
                        Toast.makeText(SubmitFeedBackScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                ArrayList<String> feedbackresponses = new ArrayList<String>();
                ArrayList<String> feedbackquestions = new ArrayList<String>();
                // feedbacklistarray = new String[adapter.getSubmittedFeedbackDetails().size()];

                Set set2 = adapter.getTextSubmittedFeedbackDetails().entrySet();
                Iterator iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    Map.Entry mentry2 = (Map.Entry) iterator2.next();
                    feedbackresponses.add(mentry2.getKey().toString());
                    feedbackquestions.add(mentry2.getValue().toString());
                }
                String joinedresponses= TextUtils.join(", ", feedbackresponses);
                String joinedquestions= TextUtils.join(", ", feedbackquestions);

                Log.d("My response", joinedresponses);
                Log.d("My question", joinedquestions);
               /* for (int i = 0; i < adapter.getTextSubmittedFeedbackDetails().size(); i++) {
                    feedbackresponses.add(adapter.getTextSubmittedFeedbackDetails().get(i));
                }

                int j = 0;
                for (String object : feedbackresponses) {
                    params.put("feedback_response[" + (j++) + "]", object);
                }

                int k = 0;
                for (String object : feedbackquestions) {
                    params.put("feedback_question[" + (k++) + "]", object);
                }*/
                params.put("feedback_response",joinedresponses);
                params.put("feedback_question",joinedquestions);
                params.put("faculty_id", faculty_id);
                params.put("coursename", course_name);
                params.put("feedback_sent_date", course_date);
                params.put("feedback_sent_time", course_time);
                params.put("feedback_submit_date", course_date);
                params.put("feedback_submit_time", course_time);
                params.put("student_id", student_id);
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

}