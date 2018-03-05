package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by kamalshree on 3/3/2018.
 */

public class SubmitFeedBackScreen extends AppCompatActivity {

    String feedbacklist = "https://newindialms.000webhostapp.com/getFeedbacklist.php";
    SubmitFeedBackScreenAdapter adapter;
    List<SubmitFeedbackScreenListItems> heroList;
    ListView listView;
    TextView myfeedbackenrolledcourses_title;
    private Toolbar studentpic_toolbar;
    String course_name,student_id,course_date,course_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_feedback_screen);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title=(TextView)findViewById(R.id.student_enroll_toolbar_title);


        course_name=getIntent().getStringExtra("corecoursename");
        student_id=getIntent().getStringExtra("studentid");
        course_date=getIntent().getStringExtra("course_date");
        course_time=getIntent().getStringExtra("course_time");

        Toast.makeText(SubmitFeedBackScreen.this,"Submitfeedback"+course_name+course_date+course_time,Toast.LENGTH_LONG).show();

        myfeedbackenrolledcourses_title.setText("My Feedback");
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.submitfeedbackscreenlistView);

        loadRecyclerViewData();

    }
    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, feedbacklist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
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
                    adapter = new SubmitFeedBackScreenAdapter(getApplicationContext(),R.layout.submit_feedback_screen_listitems,heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}