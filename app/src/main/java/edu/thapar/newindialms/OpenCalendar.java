package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.Retrofit;

import static edu.thapar.newindialms.R.id.coursetypespinner;

/**
 * Created by kamalshree on 10/8/2017.
 */

public class OpenCalendar extends AppCompatActivity {
    private static final String TAG = "OpenCalendar";
    String app_server_url = "https://newindialms.000webhostapp.com/fcm_insert.php";
    Button open_calendar_details, SendNotification;
    String notification_url = "https://newindialms.000webhostapp.com/send_notification.php";
    EditText message_notification, title_notification;
    String title, message;
    AlertDialog.Builder builder;
    APIService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_layout);

        Toolbar calendar_toolbar = (Toolbar) findViewById(R.id.calendar_toolbar);
        calendar_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(calendar_toolbar);
        calendar_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        open_calendar_details = (Button) findViewById(R.id.open_calendar_details);
        SendNotification = (Button) findViewById(R.id.sendnotification_button);
        title_notification = (EditText) findViewById(R.id.title_notification);
        message_notification = (EditText) findViewById(R.id.message_notification);

        /*
            Push Notification using FCM
         */
        Common.currentToken = FirebaseInstanceId.getInstance().getToken();

        FirebaseMessaging.getInstance().subscribeToTopic("Calendar");
        Log.d("My Token", Common.currentToken);
        myService = Common.getFCMClient();


        SendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification notification = new Notification(message_notification.getText().toString(), title_notification.getText().toString());
                Sender sender = new Sender("/topics/Calendar", notification);
                Intent intent=new Intent(getApplicationContext(),MainScreen.class);
                startActivity(intent);
                myService.sendNotification(sender)
                        .enqueue(new Callback<MyResponse>() {
                            @Override
                            public void onResponse(retrofit.Response<MyResponse> response, Retrofit retrofit) {
                              /*  if (response.body().success == 1) {
                                    Toast.makeText(OpenCalendar.this, "success", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(OpenCalendar.this, "Failure", Toast.LENGTH_LONG).show();
                                }*/
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                Log.e("error", t.getMessage());
                            }
                        });
            }
        });

        open_calendar_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarintent= new Intent(getApplicationContext(), AcademicCalendar.class);
                startActivity(calendarintent);
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}

