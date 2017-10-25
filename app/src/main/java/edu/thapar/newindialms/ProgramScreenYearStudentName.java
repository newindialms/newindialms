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
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearStudentName extends AppCompatActivity {
    String ProgramName,YearList;
    Toolbar studentpic_toolbar;
    TextView Studentpic_programstudentname_title;
    String yearlist_url = "https://newindialms.000webhostapp.com/get_student_name.php";
    ProgramScreenYearAdapterStudentName adapter;

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


    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, yearlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("name");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenYearStudentNameListItems listItemProgramList = new ProgramScreenYearStudentNameListItems(
                                jsonObject1.getString("student_firstname")
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
        this.setTitle(getResources().getString(R.string.navigation_program_picbook));
    }

}