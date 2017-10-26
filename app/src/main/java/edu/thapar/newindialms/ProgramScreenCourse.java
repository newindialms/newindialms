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

import static edu.thapar.newindialms.R.id.Studentpic_program_title;

/**
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenCourse  extends AppCompatActivity {
    String ProgramName;
    TextView Studentpic_programCourse_title;
    Toolbar studentpic_toolbar;
    String coursespecificlist_url = "https://newindialms.000webhostapp.com/get_course_specific.php";
    ProgramScreenCourseAdapter adapter;

    List<ProgramScreenCourseListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_course);
        ProgramName = getIntent().getStringExtra("programname");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programCourse_title = (TextView) findViewById(R.id.Studentpic_programcourse_title);
        Studentpic_programCourse_title.setText("course");
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreencourselist_ListView);

        loadRecyclerViewData();


    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, coursespecificlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("courses");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenCourseListItems listItemProgramList = new ProgramScreenCourseListItems(
                                jsonObject1.getString("addcourse_name")
                        );
                        heroList.add(listItemProgramList);


                    }
                    adapter = new ProgramScreenCourseAdapter(getApplicationContext(),R.layout.activity_program_screencourselistitems,heroList);
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_program", ProgramName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(getResources().getString(R.string.navigation_program_picbook));
    }

}