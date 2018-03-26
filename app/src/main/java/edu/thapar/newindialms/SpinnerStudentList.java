package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

public class SpinnerStudentList extends AppCompatActivity {
    private String program,specialization;
    private String year;
    private Toolbar studentlist_toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<SpinnerListItem> listItems;
    private List<String> EmaillistItems;
    private Button Send_Email_All;
    private static final String spinnerlisturl="https://newindialms.000webhostapp.com/spinner_studentlist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_student_list);

        studentlist_toolbar = (Toolbar) findViewById(R.id.studentlist_toolbar);
        studentlist_toolbar.setNavigationIcon(R.drawable.ic_left);

        setSupportActionBar(studentlist_toolbar);
        studentlist_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        String yearsstring=Integer.toString(getIntent().getIntExtra("year",1));
        year = yearsstring;
        program = getIntent().getStringExtra("program");
        specialization = getIntent().getStringExtra("specialization");

        recyclerView=(RecyclerView)findViewById(R.id.spinnerstudentlist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems=new ArrayList<>();
        EmaillistItems=new ArrayList<>();
        loadSpinnerData();
        Send_Email_All= (Button)findViewById(R.id.Send_Email_All);
        Send_Email_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EmailActivityAll.class);
                intent.putStringArrayListExtra("emaillist", (ArrayList<String>) EmaillistItems);
                startActivity(intent);
            }
        });
    }

    private void loadSpinnerData(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                spinnerlisturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        EmaillistItems.add(jsonObject1.getString("student_email"));
                        SpinnerListItem spinnerListItem = new SpinnerListItem(
                                jsonObject1.getString("student_firstname"),
                                jsonObject1.getString("student_rollnno"),
                                jsonObject1.getString("student_email")
                        );
                        listItems.add(spinnerListItem);

                    }
                    adapter = new SpinnerAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_joining", year);
                params.put("student_program", program);
                params.put("student_specialization", specialization);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
