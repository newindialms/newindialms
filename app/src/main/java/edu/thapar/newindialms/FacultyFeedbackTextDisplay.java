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
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackTextDisplay extends AppCompatActivity {

    public static final String rate_url = "https://newindialms.000webhostapp.com/get_all_text_feedback.php";
    String faculty_id,coursename,feedback_sent_date,feedback_type;
    Toolbar rate_toolbar;
    FacultyFeedackTextDisplayAdapter adapter;

    List<FacultyFeedbackTextDisplayListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_textfeedback_dashboard);

        faculty_id  = getIntent().getStringExtra("faculty_employeeid");
        coursename = getIntent().getStringExtra("coursename");
        feedback_sent_date = getIntent().getStringExtra("datevalue");
        feedback_type = getIntent().getStringExtra("feedback_type");



        rate_toolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        rate_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView daywise_title = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        daywise_title.setText("Text");
        setSupportActionBar(rate_toolbar);

        rate_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.text_list_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();

    }


    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, rate_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("Textfeedback");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        FacultyFeedbackTextDisplayListItems listItemProgramList = new FacultyFeedbackTextDisplayListItems(
                                jsonObject1.getString("feedback_response"),
                                jsonObject1.getString("feedback_sent_date"),
                                jsonObject1.getString("feedback_sent_time")

                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new FacultyFeedackTextDisplayAdapter(getApplicationContext(),R.layout.activity_faculty_textfeedback_dashboard_listitems,heroList);
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

}