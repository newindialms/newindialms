package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class ProgramScreenCourseModule extends AppCompatActivity {
    private String specializationname;
    private TextView Studentpic_programycoursemodule_title;
    private Toolbar studentpic_toolbar;
    private String coursemodulelist_url = "https://www.newindialms.com/get_coursemodule.php";
    ProgramScreenCourseModuleAdapter adapter;
    ProgramScreenCourseModuleListItems pglist;
    List<ProgramScreenCourseModuleListItems> heroList;
    ListView listView;
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_coursemodule);
        specializationname = getIntent().getStringExtra("specializationname");

        studentpic_toolbar = findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title = findViewById(R.id.studentpic_title);
        studentpic_title.setText(specializationname);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programycoursemodule_title = findViewById(R.id.Studentpic_programycoursemodule_title);
        Studentpic_programycoursemodule_title.setText(specializationname + " Courses");
        heroList = new ArrayList<>();
        listView = findViewById(R.id.studentpic_programscreencoursemodulelist_ListView);
        pglist = new ProgramScreenCourseModuleListItems(specializationname);
        pglist.setSpecializationname(specializationname);
        loadRecyclerViewData();

        swipeRefreshLayout = findViewById(R.id.showfeedback_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                heroList.clear();

                loadRecyclerViewData();
            }
        });

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, coursemodulelist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("courses");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenCourseModuleListItems listItemProgramList = new ProgramScreenCourseModuleListItems(
                                jsonObject1.getString("addcourse_name"),
                                jsonObject1.getString("addcourse_code"),
                                pglist.getSpecializationname()
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new ProgramScreenCourseModuleAdapter(getApplicationContext(), R.layout.activity_program_screencoursemodulelistitems, heroList);
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
                params.put("addcourse_specialization", specializationname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(specializationname + " Courses");
    }

}