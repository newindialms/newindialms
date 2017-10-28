package edu.thapar.newindialms;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamalshree on 10/8/2017.
 */

public class OpenCalendar extends AppCompatActivity {
    private static final String TAG = "OpenCalendar";
    String app_server_url= "https://newindialms.000webhostapp.com/fcm_insert.php";
    TextView displayDate;
    Button open_calendar_details,send_token_details;
    private CalendarView calendarView;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_layout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        displayDate = (TextView) findViewById(R.id.calendar_displaydate);
        open_calendar_details = (Button) findViewById(R.id.open_calendar_details);
        send_token_details = (Button) findViewById(R.id.send_token_details);

        send_token_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                final String token=sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        builder=new AlertDialog.Builder(OpenCalendar.this, R.style.MyAlertDialogStyle);
                        builder.setTitle("Token");
                        builder.setMessage("Token Generated Successfull");
                        displayAlert();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("fcm_token", token);
                        return params;
                    }
                };
                MySingleton.getInstance(OpenCalendar.this).addToRequestQueue(stringRequest);
            }
        });

        open_calendar_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarintent= new Intent(getApplicationContext(), AcademicCalendar.class);
                startActivity(calendarintent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int yearvalue, int monthvalue, int datevalue) {
                String date = datevalue + "/" + (monthvalue+1) + "/" + yearvalue;
                Log.i(TAG, "The Date is " + date);
                displayDate.setText(date);
            }
        });
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
