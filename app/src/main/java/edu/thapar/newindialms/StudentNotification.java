package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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
 * Created by kamalshree on 9/26/2017.
 */

public class StudentNotification extends AppCompatActivity {
    private RecyclerView recyclerView;
    List<NotificationScreenDetails> notificationScreenDetails;
    private Toolbar student_toolbar;
    private static final String NOTIFICATION_URL = "http://newindialms.000webhostapp.com/get_student_notification.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_student_noticationscreen);
        student_toolbar = (Toolbar) findViewById(R.id.toolbar_student_attendance);
        student_toolbar.setNavigationIcon(R.drawable.ic_left);
        TextView faculty_title=(TextView)findViewById(R.id.student_enroll_toolbar_title);
        faculty_title.setText(" Notification");
        setSupportActionBar(student_toolbar);

        student_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.student_notification_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadNotifications();
        notificationScreenDetails = new ArrayList<>();

    }

    private void LoadNotifications() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NOTIFICATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject j = new JSONObject(response);
                            JSONArray array = j.getJSONArray("student_notification_list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                NotificationScreenDetails listItemProgramList = new NotificationScreenDetails(
                                        jsonObject1.getString("notification_title"),
                                        jsonObject1.getString("notification_message"),
                                        jsonObject1.getString("notification_date")
                                );
                                notificationScreenDetails.add(listItemProgramList);


                            }
                            StudentNotificationScreenAdapter adapter = new StudentNotificationScreenAdapter(getApplicationContext(), notificationScreenDetails);
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
