package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import edu.thapar.newindialms.MySingleton;
import edu.thapar.newindialms.R;

import static android.provider.Telephony.Carriers.PASSWORD;

public class RegisterScreen extends AppCompatActivity {

    Toolbar toolbar_register_screen;
    Context context;
    EditText register_id, register_phone, register_email, register_password;
    CheckBox register_checkbox1, register_checkbox2;

    String studentid, emailaddress, phone, password, idtype;
    Button register_button;
    String reg_url = "https://newindialms.000webhostapp.com/register.php";
    String EMAIL,PASSWORD;
    String emailprofile_url = "https://newindialms.000webhostapp.com/email_profile.php";
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);


        register_id = (EditText) findViewById(R.id.registration_id);
        register_phone = (EditText) findViewById(R.id.registration_phone);
        register_email = (EditText) findViewById(R.id.registration_email);
        register_password = (EditText) findViewById(R.id.registration_password);
        register_checkbox1 = (CheckBox) findViewById(R.id.registration_checkbox1);
        register_checkbox2 = (CheckBox) findViewById(R.id.registration_checkbox2);
        register_button = (Button) findViewById(R.id.registration_button);
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(RegisterScreen.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("Registering your Details...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                studentid = register_id.getText().toString();
                phone = register_phone.getText().toString();
                emailaddress = register_email.getText().toString();
                password = register_password.getText().toString();

                if (register_checkbox1.isChecked()) {
                    idtype = register_checkbox1.getText().toString();
                } else if (register_checkbox2.isChecked()) {
                    idtype = register_checkbox2.getText().toString();
                }


                if (studentid.equals("") || phone.equals("") || emailaddress.equals("") || password.equals("") || (!register_checkbox1.isChecked() && !register_checkbox2.isChecked())) {
                    progressDialog.dismiss();
                    builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
                    builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
                    displayAlert("input_error");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                progressDialog.dismiss();
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                SendRegistrationEmail(emailaddress);
                                builder.setTitle(getResources().getString(R.string.registration_server_response));
                                builder.setMessage(message);
                                displayAlert(code);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("studentid", studentid);
                            params.put("phonenumber", phone);
                            params.put("emailaddress", emailaddress);
                            params.put("password", password);
                            params.put("idtype", idtype);
                            return params;
                        }
                    };
                    MySingleton.getInstance(RegisterScreen.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }

public void SendRegistrationEmail(final String EmailAddress){
    StringRequest stringRequest = new StringRequest(Request.Method.GET, emailprofile_url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            JSONArray jsonArray = null;
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray array = jsonObject.getJSONArray("email_profile");

                JSONObject jsonObject1 = array.getJSONObject(0);

                EMAIL= jsonObject1.getString("username");
                PASSWORD= jsonObject1.getString("password");
                //Creating SendMail object
                SendEmailRegistration sm = new SendEmailRegistration(RegisterScreen.this, EmailAddress,EMAIL,PASSWORD);

                //Executing sendmail to send email
                sm.execute();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(RegisterScreen.this,error.getMessage(),Toast.LENGTH_LONG).show();
        }
    });
    RequestQueue requestQueue = Volley.newRequestQueue(RegisterScreen.this);
    requestQueue.add(stringRequest);


}
    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("input_error")) {
                    register_password.setText("");
                } else if(code.equals("send otp")){
                    Intent otp_intent = new Intent(RegisterScreen.this, OtpScreen.class);
                    startActivity(otp_intent);
                } else if (code.equals("registration_failed")) {
                    register_id.setText("");
                    register_phone.setText("");
                    register_email.setText("");
                    register_password.setText("");
                    register_checkbox1.setChecked(false);
                    register_checkbox2.setChecked(false);
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


