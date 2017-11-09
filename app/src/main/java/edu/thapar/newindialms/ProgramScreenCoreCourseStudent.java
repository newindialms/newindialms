package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
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

import static edu.thapar.newindialms.R.id.Studentpic_programstudentfulllist_total;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenCoreCourseStudent extends AppCompatActivity {
    Toolbar studentpic_toolbar;
    TextView Studentpic_programcorecourselist_title;
    String corecoursename;
    String corecoursestudent_url = "https://newindialms.000webhostapp.com//get_corecourse_student.php";
    ProgramScreenCoreCourseStudentAdapter adapter;
    List<ProgramScreenCoreCourseStudentListItems> heroList;
    ListView listView;
    int Student_size;
    ProgramScreenCoreCourseStudentListItems arraycount=new ProgramScreenCoreCourseStudentListItems();
    TextView Studentpic_programstudentcorecourselist_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_screen_corecoursestudent);
        corecoursename = getIntent().getStringExtra("corecoursename");

        studentpic_toolbar = (Toolbar) findViewById(R.id.studentpic_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView studentpic_title=(TextView)findViewById(R.id.studentpic_title);

        studentpic_title.setText(corecoursename);
        setSupportActionBar(studentpic_toolbar);
        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Studentpic_programcorecourselist_title = (TextView) findViewById(R.id.Studentpic_programcorecourselist_title);
        Studentpic_programcorecourselist_title.setText(corecoursename);
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.studentpic_programscreenstudentcorecourseList_ListView);

        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, corecoursestudent_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("corecoursestudentlist");

                    Student_size = j.getInt("rowcount");
                    arraycount.setRowcount(Student_size);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ProgramScreenCoreCourseStudentListItems listItemProgramList = new ProgramScreenCoreCourseStudentListItems(
                                jsonObject1.getString("student_firstname"),
                                jsonObject1.getString("student_rollnno")


                        );
                        myarraycount(Student_size);
                        heroList.add(listItemProgramList);
                    }
                    adapter = new ProgramScreenCoreCourseStudentAdapter(getApplicationContext(),R.layout.activity_program_screen_corecoursefulllist_listitems,heroList,Student_size);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void myarraycount(int rowcount){
        Studentpic_programstudentcorecourselist_total = (TextView)findViewById(R.id.Studentpic_programstudentcorecourselist_total);
        Studentpic_programstudentcorecourselist_total.setText("Total Students : "+rowcount);
    }

}