package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
 * A simple {@link Fragment} subclass.
 */
public class EnrolledCourseActivity extends AppCompatActivity {
    private String enrolled_courselist = "https://www.newindialms.com/listenrolledcourses.php";
    EnrolledCourseAdapter adapter;
    private String studentid, studentyear;
    List<EnrolledCourseListItems> heroList;
    ListView listView;
    private AlertDialog.Builder builder;
    private Toolbar studentprofile_toolbar;
    private TextView studentpic_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enrolled_course_activity);
       studentid = getIntent().getExtras().getString("studentid");
        studentyear = getIntent().getExtras().getString("studentyear");

        studentprofile_toolbar = (Toolbar) findViewById(R.id.studentprofile_toolbar);
        studentprofile_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(studentprofile_toolbar);
        studentpic_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        studentpic_title.setText("My Courses");
        studentprofile_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.enrolledcourselistView);
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(EnrolledCourseActivity.this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolled_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.equals("")){
                    builder = new AlertDialog.Builder(getApplicationContext(), R.style.MyStudentAlertDialogStyle);
                    builder.setTitle("Records");
                    builder.setMessage("No Courses available,Update your specialization and Courses or check with Program Manager for the enrollment Dates.");
                    displayAlert();

                }
                try {
                    JSONObject j = new JSONObject(response);

                    JSONArray array = j.getJSONArray("course_details");
                    for (int i = 0; i < array.length(); i++) {

                        EnrolledCourseListItems listItemProgramList = new EnrolledCourseListItems(
                                array.getString(i)
                        );
                        heroList.add(listItemProgramList);

                    }
                    adapter = new EnrolledCourseAdapter(getApplicationContext(), R.layout.fragment_enrolled_course_listitems, heroList, studentyear);
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
                params.put("student_rollno", studentid);
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
