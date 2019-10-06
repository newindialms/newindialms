package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import java.util.List;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class ProgramManagerCourseList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String menucourselist_url = "https://www.newindialms.com/menu_courselist.php";
    String yearVal;
    private Toolbar toolbar_all_notiifcation;
    private List<ListItemCourseList> listItemCourseLists;
    public SwipeRefreshLayout swipeRefreshLayout;
    private TextView toolbar_title,CourselistTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_program_manager_courselist);
        yearVal=getIntent().getStringExtra("year");
        CourselistTitle = findViewById(R.id.CourselistTitle);

        toolbar_all_notiifcation = findViewById(R.id.toolbar_all_notiifcation);
        toolbar_title = findViewById(R.id.itemsselected);
        toolbar_all_notiifcation.setNavigationIcon(R.drawable.ic_left);


        setSupportActionBar(toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.courselistRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.showfeedback_swipe);

        listItemCourseLists = new ArrayList<>();

        if(yearVal.equals("1")){
            toolbar_title.setText("First Year Courses");
            CourselistTitle.setText("First Year Course List");
            loadRecyclerViewDatafirstyear();
        }
        else{
            toolbar_title.setText("Second Year Courses");
            CourselistTitle.setText("Second Year Course List");
            loadRecyclerViewDatasecondyear();
        }



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                listItemCourseLists.clear();

                loadRecyclerViewDatafirstyear();
            }
        });
    }

    private void loadRecyclerViewDatasecondyear() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, menucourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Course_List");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ListItemCourseList listItemCourseList = new ListItemCourseList(
                                jsonObject1.getString("course_details_name"),
                                jsonObject1.getString("course_details_faculty"),
                                jsonObject1.getString("course_details_code")
                        );
                        listItemCourseLists.add(listItemCourseList);
                    }
                    adapter = new CourseListAdapter(listItemCourseLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ProgramManagerCourseList.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void loadRecyclerViewDatafirstyear() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, menucourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array1 = jsonObject.getJSONArray("Course_List_first");

                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject jsonObject1 = array1.getJSONObject(i);
                        ListItemCourseList listItemCourseList = new ListItemCourseList(
                                jsonObject1.getString("first_year_course_list_name"),
                                jsonObject1.getString("first_year_course_list_faculty"),
                                jsonObject1.getString("first_year_course_list_code")
                        );
                        listItemCourseLists.add(listItemCourseList);
                    }
                    adapter = new CourseListAdapter(listItemCourseLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(ProgramManagerCourseList.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
