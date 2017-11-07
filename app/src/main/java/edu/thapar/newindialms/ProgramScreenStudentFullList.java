package edu.thapar.newindialms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import static android.R.attr.id;
import static android.R.attr.value;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static edu.thapar.newindialms.R.id.view;

/**
 * Created by kamalshree on 11/2/2017.
 */

public class ProgramScreenStudentFullList extends AppCompatActivity {
    Toolbar studentpic_toolbar;
    String coursesname;
    TextView Studentpic_programspecialization_title;

    String studentfulllist_url = "https://newindialms.000webhostapp.com/get_student_fulllist.php";
    ProgramScreenStudentFullListAdapter adapter;
    List<ProgramScreenStudentFullListListItems> heroList;
    ListView listView;
    int Student_size;
    ProgramScreenStudentFullListListItems arraycount=new ProgramScreenStudentFullListListItems();
    TextView Studentpic_programstudentfulllist_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_studentfulllist);
        coursesname = getIntent().getStringExtra("coursesname");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title=(TextView)findViewById(R.id.studentpic_title);

        studentpic_title.setText(coursesname);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programspecialization_title = (TextView) findViewById(R.id.Studentpic_programstudentfulllist_title);
        Studentpic_programspecialization_title.setText(coursesname);
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreenstudentfulllist_ListView);

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, studentfulllist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("studentlist");

                   Student_size = j.getInt("rowcount");
                    arraycount.setRowcount(Student_size);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenStudentFullListListItems listItemProgramList = new ProgramScreenStudentFullListListItems(
                                jsonObject1.getString("student_name"),
                                jsonObject1.getString("student_rollnno")


                        );
                        myarraycount(Student_size);
                        heroList.add(listItemProgramList);
                    }
                    adapter = new ProgramScreenStudentFullListAdapter(getApplicationContext(),R.layout.activity_program_screen_studentfulllist_listitems,heroList,Student_size);
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
                params.put("student_coursename", coursesname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void myarraycount(int rowcount){
        Studentpic_programstudentfulllist_total = (TextView)findViewById(R.id.Studentpic_programstudentfulllist_total);
        Studentpic_programstudentfulllist_total.setText("Total Students : "+rowcount);
    }

}