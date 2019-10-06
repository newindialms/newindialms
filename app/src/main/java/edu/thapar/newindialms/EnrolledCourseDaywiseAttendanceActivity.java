package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Map;

/**
 * Created by kamalshree on 11/19/2017.
 */

public class EnrolledCourseDaywiseAttendanceActivity extends AppCompatActivity {
    public static final String daywise_attendancedetails_url = "https://www.newindialms.com/get_daywise_attendance.php";
    private String student_rollnno, course_details_name,studentyear;
    private TextView daywise_toolbar_title;
    private Toolbar daywise_toolbar;
    EnrolledCourseDaywiseAttendanceAdapter adapter;
    private AlertDialog.Builder builder;

    List<EnrolledCourseDaywiseAttendanceListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course_daywise);
        course_details_name = getIntent().getStringExtra("course_details_name");
        student_rollnno = getIntent().getStringExtra("student_rollnno");
        studentyear = getIntent().getStringExtra("studentyear");

        daywise_toolbar = findViewById(R.id.enrollcoursetoolbar);
        daywise_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView daywise_title = findViewById(R.id.enrolled_daywise_title);
        daywise_title.setText(course_details_name + " Daywise");
        setSupportActionBar(daywise_toolbar);

        daywise_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        daywise_toolbar_title = findViewById(R.id.itemsselected);
        daywise_toolbar_title.setText("Daywise");
        listView = findViewById(R.id.enrolledcourse_daywise_list_ListView);
        heroList = new ArrayList<>();
        loadRecyclerViewData();

    }


    private void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, daywise_attendancedetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("DayWiseattendancelist");
                    if (array != null && array.length() > 0) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        EnrolledCourseDaywiseAttendanceListItems listItemProgramList = new EnrolledCourseDaywiseAttendanceListItems(
                                jsonObject1.getString("attendance_date"),
                                jsonObject1.getString("attendance_time"),
                                jsonObject1.getString("attendance_day"),
                                jsonObject1.getString("attendance_status")

                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrolledCourseDaywiseAttendanceAdapter(getApplicationContext(), R.layout.activity_enrolled_course_daywise_listitems, heroList);
                    listView.setAdapter(adapter);
                    } else {
                        //Toast.makeText(FacultyScheduleDisplay.this,"inside else",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(EnrolledCourseDaywiseAttendanceActivity.this, R.style.MyStudentAlertDialogStyle);
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
                params.put("course_details_name", course_details_name);
                params.put("student_rollnno", student_rollnno);
                params.put("studentyear", studentyear);
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