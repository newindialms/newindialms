package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    private String program, specialization;
    private String year;
    private Toolbar studentlist_toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AlertDialog.Builder builder;

    private List<SpinnerListItem> listItems;
    private List<String> EmaillistItems;
    private static final String spinnerlisturl = "https://www.newindialms.com/spinner_studentlist.php";

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


        String yearsstring = Integer.toString(getIntent().getIntExtra("year", 1));
        year = yearsstring;
        program = getIntent().getStringExtra("program");
        specialization = getIntent().getStringExtra("specialization");

        recyclerView = (RecyclerView) findViewById(R.id.spinnerstudentlist_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems = new ArrayList<>();
        EmaillistItems = new ArrayList<>();
        loadSpinnerData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sendall_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.feedbacksendall) {
            Intent intent = new Intent(getApplicationContext(), EmailActivityAll.class);
            intent.putStringArrayListExtra("emaillist", (ArrayList<String>) EmaillistItems);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadSpinnerData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                spinnerlisturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    if (jsonArray != null && jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            EmaillistItems.add(jsonObject1.getString("student_email"));
                            SpinnerListItem spinnerListItem = new SpinnerListItem(
                                    jsonObject1.getString("student_lastname"),
                                    jsonObject1.getString("student_firstname"),
                                    jsonObject1.getString("student_rollnno"),
                                    jsonObject1.getString("student_email")
                            );
                            listItems.add(spinnerListItem);

                        }
                        adapter = new SpinnerAdapter(listItems, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    } else {
                        //Toast.makeText(FacultyScheduleDisplay.this,"inside else",Toast.LENGTH_LONG).show();
                        builder = new AlertDialog.Builder(SpinnerStudentList.this, R.style.MyAlertDialogStyle);
                        builder.setTitle("Result");
                        builder.setMessage("No Records available for the selected search.");
                        displayAlert();
                    }
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
                params.put("student_joining", year);
                params.put("student_program", program);
                params.put("student_specialization", specialization);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
