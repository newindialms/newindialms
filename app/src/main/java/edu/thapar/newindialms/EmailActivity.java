package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/4/2017.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EmailActivity extends AppCompatActivity {

    private Toolbar toolbar_all_notification;
    private String email_address;
    private EditText editTextEmail, editTextSubject, editTextMessage;
    private Button buttonSend;
    private String EMAIL, PASSWORD;
    private String emailprofile_url = "https://www.newindialms.com/email_profile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_activity);

        toolbar_all_notification = findViewById(R.id.toolbar_all_notiifcation);

        toolbar_all_notification.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_all_notification);
        toolbar_all_notification.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Receiving the ListView Clicked item value send by previous activity.
        email_address = getIntent().getStringExtra("email_address");


        editTextEmail = findViewById(R.id.emailid);
        editTextEmail.setText(email_address);
        editTextSubject = findViewById(R.id.subject);
        editTextMessage = findViewById(R.id.message);

        buttonSend = findViewById(R.id.notification_email_button);

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

                            sendEmail(EMAIL, PASSWORD);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(EmailActivity.this);
                requestQueue.add(stringRequest);


            }
        });
    }

    private void sendEmail(String email_address, String Password) {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendEmail sm = new SendEmail(this, email, subject, message, email_address, Password);

        //Executing sendmail to send email
        sm.execute();
    }

}