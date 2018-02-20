package edu.thapar.newindialms;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
 * Created by kamalshree on 10/28/2017.
 */

public class NotificationScreen extends AppCompatActivity {
    Toolbar toolbar_all_notiifcation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_screen);

        toolbar_all_notiifcation = (Toolbar) findViewById(R.id.toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationIcon(R.drawable.ic_left);

        setSupportActionBar(toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void GoToAccademicCalendar(View v){
        Intent intent = new Intent(getApplicationContext(),OpenCalendar.class);
        startActivity(intent);
    }
    public void SendFacultyNotification(View v){
        Intent intent = new Intent(getApplicationContext(),SendFacultyNotificaton.class);
        startActivity(intent);
    }
    public void SendStudentNotification(View v){
        Intent intent = new Intent(getApplicationContext(),SendStudentNotificaton.class);
        startActivity(intent);
    }
}