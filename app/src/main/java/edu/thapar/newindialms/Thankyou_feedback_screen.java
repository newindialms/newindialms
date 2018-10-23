package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by kamalshree on 10/27/2017.
 */

public class Thankyou_feedback_screen extends AppCompatActivity {

    private Button close_button;
    private Toolbar studentpic_toolbar;
    private TextView myfeedbackenrolledcourses_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanksfeedback_screen);
        close_button = (Button) findViewById(R.id.thankyoubutton);

        studentpic_toolbar = (Toolbar) findViewById(R.id.student_enroll_toolbar);
        studentpic_toolbar.setNavigationIcon(R.drawable.ic_left);
        myfeedbackenrolledcourses_title = (TextView) findViewById(R.id.student_enroll_toolbar_title);

        myfeedbackenrolledcourses_title.setText("Thank you");
        setSupportActionBar(studentpic_toolbar);

        studentpic_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAffinity(Thankyou_feedback_screen.this);
            }
        });
    }
}