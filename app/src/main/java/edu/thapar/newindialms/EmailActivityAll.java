package edu.thapar.newindialms;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

/**
 * Created by kamalshree on 10/29/2017.
 */

public class EmailActivityAll extends AppCompatActivity {

    private Toolbar toolbar_all_notification;
    private EditText editTextSubject, editTextMessage;
    private Button buttonSend;
    private String EMAIL, PASSWORD;
    private String emailprofile_url = "https://www.newindialms.com/email_profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_activity_all);
        final ArrayList<String> EmailList = getIntent().getStringArrayListExtra("emaillist");
        toolbar_all_notification = (Toolbar) findViewById(R.id.toolbar_all_notiifcation);

        toolbar_all_notification.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_all_notification);
        toolbar_all_notification.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Receiving the ListView Clicked item value send by previous activity.


        editTextSubject = (EditText) findViewById(R.id.subject);
        editTextMessage = (EditText) findViewById(R.id.message);

        buttonSend = (Button) findViewById(R.id.notification_email_button);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, emailprofile_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("email_profile");

                            JSONObject jsonObject1 = array.getJSONObject(0);

                            EMAIL = jsonObject1.getString("username");
                            PASSWORD = jsonObject1.getString("password");

                            sendEmail(EMAIL, PASSWORD, EmailList);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(EmailActivityAll.this);
                requestQueue.add(stringRequest);


            }
        });
    }

    private void sendEmail(String email_address, String Password, ArrayList<String> EmailList) {
        //Getting content for email
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendEmailAll sm = new SendEmailAll(this, subject, message, email_address, Password, EmailList);

        //Executing sendmail to send email
        sm.execute();
    }

}