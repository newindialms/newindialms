package edu.thapar.newindialms;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
 * Created by kamalshree on 9/26/2017.
 */

public class FacultyNotificationScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<NotificationScreenDetails> notificationScreenDetails;
    private Toolbar faculty_toolbar;
    private static final String NOTIFICATION_URL = "https://www.newindialms.com/get_faculty_notification.php";
    public SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faculty_noticationscreen);
        faculty_toolbar = findViewById(R.id.facultycourselist_toolbar);
        faculty_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title = findViewById(R.id.facultydashboard_toolbar_title);
        faculty_title.setText(" Notification");
        setSupportActionBar(faculty_toolbar);

        faculty_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.faculty_notification_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadNotifications();
        notificationScreenDetails = new ArrayList<>();

        swipeRefreshLayout = findViewById(R.id.showfeedback_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                notificationScreenDetails.clear();

                LoadNotifications();
            }
        });

    }

    private void LoadNotifications() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NOTIFICATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject j = new JSONObject(response);
                            JSONArray array = j.getJSONArray("faculty_notification_list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                NotificationScreenDetails listItemProgramList = new NotificationScreenDetails(
                                        jsonObject1.getString("notification_title"),
                                        jsonObject1.getString("notification_message"),
                                        jsonObject1.getString("notification_date")
                                );
                                notificationScreenDetails.add(listItemProgramList);


                            }
                            NotificationScreenAdapter adapter = new NotificationScreenAdapter(getApplicationContext(), notificationScreenDetails);
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
        this.setTitle(getResources().getString(R.string.navigation_program_notification));

    }
}
