package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamalshree on 10/8/2017.
 */

public class OpenCalendar extends AppCompatActivity {
    private static final String TAG = "OpenCalendar";
    private Button open_calendar_details, SendNotification;
    private String CalendarVal;

    private String notification_url = "https://www.newindialms.com/send_notification.php";
    private String calendarValDetails_Url = "https://www.newindialms.com/get_calendarValdetails.php";

    private EditText message_notification, title_notification;
    private String title, message;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_layout);

        loadCalendarValDetails();

        Toolbar calendar_toolbar = findViewById(R.id.calendar_toolbar);
        calendar_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(calendar_toolbar);
        calendar_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        open_calendar_details = findViewById(R.id.open_calendar_details);
        SendNotification = findViewById(R.id.sendnotification_button);
        title_notification = findViewById(R.id.title_notification);
        message_notification = findViewById(R.id.message_notification);


        SendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = title_notification.getText().toString();
                message = message_notification.getText().toString();
                if (title.equals("") || message.equals("")) {
                    builder = new AlertDialog.Builder(OpenCalendar.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("Missing");
                    builder.setMessage("Please enter the Title and Message ");
                    displayAlert();
                } else {
                    SendAccademicCalendarNotificationFunction();
                    builder = new AlertDialog.Builder(OpenCalendar.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("Success");
                    builder.setMessage("Notification sent successfully ");
                    displayAlert();
                }
            }
        });

        open_calendar_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarintent = new Intent(getApplicationContext(), AcademicCalendar.class);
                calendarintent.putExtra("CalendarVal", CalendarVal);
                startActivity(calendarintent);
            }
        });

    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void SendAccademicCalendarNotificationFunction() {

        title = title_notification.getText().toString();
        message = message_notification.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, notification_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", title);
                params.put("message", message);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }



    private void loadCalendarValDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarValDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            JSONObject jsonobject = jsonarray.getJSONObject(0);

                            CalendarVal = jsonobject.getString("calendar_val");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(OpenCalendar.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}

