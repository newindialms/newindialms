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
 * Created by kamalshree on 11/19/2017.
 */

public class EnrolledCourseCumulativeAttendanceActivity extends AppCompatActivity {
    public static final String cumulative_attendancedetails_url = "https://newindialms.000webhostapp.com/get_cumulative_attendance.php";
    private String student_rollnno, course_details_name;
    private TextView daywise_toolbar_title;
    private Toolbar daywise_toolbar;
    EnrolledCourseCumulativeAttendanceAdapter adapter;

    List<EnrolledCourseCumulativeAttendanceListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_cumulative);
        course_details_name = getIntent().getStringExtra("course_details_name");
        student_rollnno = getIntent().getStringExtra("student_rollnno");

        daywise_toolbar = (Toolbar) findViewById(R.id.enrollcoursetoolbar);
        daywise_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView daywise_title = (TextView) findViewById(R.id.enrolled_cumulative_title);
        daywise_title.setText(course_details_name+ " Cumulative");
        setSupportActionBar(daywise_toolbar);

        daywise_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        daywise_toolbar_title = (TextView) findViewById(R.id.itemsselected);
        daywise_toolbar_title.setText("Cumulative");
        listView = (ListView) findViewById(R.id.enrolledcourse_cumulative_list_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();

    }


    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, cumulative_attendancedetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("cumulative");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        EnrolledCourseCumulativeAttendanceListItems listItemProgramList = new EnrolledCourseCumulativeAttendanceListItems(
                                jsonObject1.getString("Total_count"),
                                jsonObject1.getString("Present_count"),
                                jsonObject1.getString("Absent_count"),
                                jsonObject1.getString("Percentage_count")

                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrolledCourseCumulativeAttendanceAdapter(getApplicationContext(),R.layout.activity_enrolled_course_cumulative_listitems,heroList);
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
                params.put("course_details_name", course_details_name);
                params.put("student_rollnno", student_rollnno);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}