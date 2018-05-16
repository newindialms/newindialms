package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SendStudentNotificaton extends AppCompatActivity {
    private Button SendNotification;
    private String notification_url = "https://www.newindialms.com/send_student_notification.php";
    private EditText message_notification, title_notification;
    private String title, message;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentnotification_layout);

        Toolbar calendar_toolbar = (Toolbar) findViewById(R.id.toolbar_all_notiifcation);
        calendar_toolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(calendar_toolbar);
        calendar_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SendNotification = (Button) findViewById(R.id.sendnotification_button);
        title_notification = (EditText) findViewById(R.id.send_student_notification_subtitle_hint);
        message_notification = (EditText) findViewById(R.id.send_student_notification_subtitle_message_hint);


        SendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = title_notification.getText().toString();
                message = message_notification.getText().toString();
                if (title.equals("") || message.equals("")) {
                    builder = new AlertDialog.Builder(SendStudentNotificaton.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("Missing");
                    builder.setMessage("Please enter the Title and Message ");
                    displayAlert();
                } else {
                    SendStudentNotificationFunction();
                    builder = new AlertDialog.Builder(SendStudentNotificaton.this, R.style.MyStudentAlertDialogStyle);
                    builder.setTitle("Success");
                    builder.setMessage("Notification was sent successfully");
                    displayAlert();
                }
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

    public void SendStudentNotificationFunction() {

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

}

