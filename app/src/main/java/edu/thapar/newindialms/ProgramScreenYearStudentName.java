package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class ProgramScreenYearStudentName extends AppCompatActivity {
    private String ProgramName,YearList;
    private Toolbar studentpic_toolbar;
    private TextView Studentpic_programstudentname_title;
    private String yearlist_url = "https://newindialms.000webhostapp.com/get_student_name.php";
    ProgramScreenYearAdapterStudentName adapter;
    public SwipeRefreshLayout swipeRefreshLayout;

    List<ProgramScreenYearStudentNameListItems> heroList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_year_studentname);
        ProgramName = getIntent().getStringExtra("programname");
        YearList = getIntent().getStringExtra("yearlist");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);

        TextView studentpic_title=(TextView)findViewById(R.id.studentpic_title);
        studentpic_title.setText(YearList);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programstudentname_title = (TextView) findViewById(R.id.Studentpic_programstudentname_title);
        Studentpic_programstudentname_title.setText(YearList);
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreenyearstudentnamelist_ListView);

        loadRecyclerViewData();

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.showfeedback_swipe);
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, yearlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("name");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenYearStudentNameListItems listItemProgramList = new ProgramScreenYearStudentNameListItems(
                                jsonObject1.getString("student_firstname"),
                                jsonObject1.getString("student_rollnno"),
                                jsonObject1.getString("student_specialization")
                        );
                        heroList.add(listItemProgramList);


                    }
                    adapter = new ProgramScreenYearAdapterStudentName(getApplicationContext(),R.layout.activity_program_screenyearstudentamelistitems,heroList);
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
                params.put("student_joining", YearList);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.setTitle(YearList);
    }

}