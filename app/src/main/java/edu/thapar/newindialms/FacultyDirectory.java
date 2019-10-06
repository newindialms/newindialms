package edu.thapar.newindialms;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 12/31/2018.
 */

public class FacultyDirectory extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<FacultyDirectoryDetails> facultyDirectoryDetails;
    private Toolbar student_toolbar;
    private static final String FACULTY_URL = "https://www.newindialms.com/get_directory.php";
    public SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faculty_directoryscreen);
        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);
        faculty_title.setText("Directory");
        setSupportActionBar(student_toolbar);

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.faculty_directoryscreen_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadDirectory();
        facultyDirectoryDetails = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.showdirectory_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                facultyDirectoryDetails.clear();

                LoadDirectory();
            }
        });

    }

    private void LoadDirectory() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FACULTY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject j = new JSONObject(response);
                            JSONArray array = j.getJSONArray("faculty_directory");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                FacultyDirectoryDetails listItemProgramList = new FacultyDirectoryDetails(
                                        jsonObject1.getString("faculty_firstname"),
                                        jsonObject1.getString("faculty_lastname"),
                                        jsonObject1.getString("faculty_phone"),
                                        jsonObject1.getString("faculty_email"),
                                        jsonObject1.getString("faculty_specialization")
                                );
                                facultyDirectoryDetails.add(listItemProgramList);


                            }
                            FacultyDirectoryAdapter adapter = new FacultyDirectoryAdapter(getApplicationContext(), facultyDirectoryDetails);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }
}
