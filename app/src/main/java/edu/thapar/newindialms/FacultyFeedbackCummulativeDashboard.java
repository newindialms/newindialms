package edu.thapar.newindialms;

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
 * Created by kamalshree on 4/13/2018.
 */

public class FacultyFeedbackCummulativeDashboard extends AppCompatActivity {

    public static final String rate_url = "https://www.newindialms.com/get_faculty_cumulativedashboard.php";
    private String faculty_id, coursename, feedback_sent_date, type, question;
    private Toolbar rate_toolbar;
    FacultyFeedackCumulativeDasboardAdapter adapter;

    List<FacultyFeedbackCumulativeDashboardListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_cumulative_dashboard);

        faculty_id = getIntent().getStringExtra("faculty_employeeid");
        coursename = getIntent().getStringExtra("coursename");
        feedback_sent_date = getIntent().getStringExtra("feedback_sent_date");
        type = getIntent().getStringExtra("type");
        question = getIntent().getStringExtra("question");

        // Toast.makeText(getApplicationContext(),coursename+question+type+faculty_id+feedback_sent_date,Toast.LENGTH_LONG).show();

        rate_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        rate_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView daywise_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        daywise_title.setText("Average and Median Score");
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
                    JSONArray array = j.getJSONArray("response");

                    JSONObject jsonObject1 = array.getJSONObject(0);
                    FacultyFeedbackCumulativeDashboardListItems listItemProgramList = new FacultyFeedbackCumulativeDashboardListItems(
                            jsonObject1.getString("feedback_average"), jsonObject1.getString("feedback_median"), question, type
                    );
                    heroList.add(listItemProgramList);

                    adapter = new FacultyFeedackCumulativeDasboardAdapter(getApplicationContext(), R.layout.activity_faculty_cumulative_dashboard_listitems, heroList);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("faculty_id", faculty_id);
                params.put("coursename", coursename);
                params.put("type", type);
                params.put("question", question);
                params.put("feedback_sent_date", feedback_sent_date);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}