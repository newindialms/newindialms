package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private EditText register_id, register_phone, register_email, register_password,editTextConfirmOtp;
    private CheckBox register_checkbox1, register_checkbox2;
    private String EMAIL,PASSWORD;
    private String studentid, emailaddress, phonenumber, password, idtype;
    private Button register_button, otp_button;
    private RequestQueue requestQueue;
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;

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
        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Calling register method on register button click
        //Getting user data
        studentid = register_id.getText().toString();
        phonenumber = register_phone.getText().toString();
        emailaddress = register_email.getText().toString();
        password = register_password.getText().toString();

        if (register_checkbox1.isChecked()) {
            idtype = register_checkbox1.getText().toString();
        } else if (register_checkbox2.isChecked()) {
            idtype = register_checkbox2.getText().toString();
        }

        if (studentid.equals("") || phonenumber.equals("") || emailaddress.equals("") || password.equals("") || (!register_checkbox1.isChecked() && !register_checkbox2.isChecked())) {
            //Toast.makeText(RegisterScreen.this,"Missing field",Toast.LENGTH_LONG).show();

            builder = new AlertDialog.Builder(RegisterScreen.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Error");
            builder.setMessage("Please fill in all fields");
            displayAlert("missingfields");
        }
        else{
           // Toast.makeText(RegisterScreen.this,studentid+phonenumber+emailaddress+password+idtype,Toast.LENGTH_LONG).show();
            register();
        }
    }

    private void register() {

        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Registering", "Please wait...", false, false);



        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegistrationConfig.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            if (studentid.equals("") || phonenumber.equals("") || emailaddress.equals("") || password.equals("") || (!register_checkbox1.isChecked() && !register_checkbox2.isChecked())) {
                                progressDialog.dismiss();
                            } else {
                                confirmOtp(emailaddress);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(RegisterScreen.this, error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put(RegistrationConfig.KEY_STUDENTID, studentid);
                params.put(RegistrationConfig.KEY_PHONENUMBER, phonenumber);
                params.put(RegistrationConfig.KEY_EMAILADDRESS, emailaddress);
                params.put(RegistrationConfig.KEY_PASSWORD, password);
                params.put(RegistrationConfig.KEY_IDTYPE, idtype);
                return params;

            }
        };

        //Adding request the the queue
        requestQueue.add(stringRequest);
    }

    //This method would confirm the otp
    private void confirmOtp(final String emailaddress) throws JSONException {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box

        View confirmDialog = li.inflate(R.layout.otp_screen, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        otp_button = (Button) confirmDialog.findViewById(R.id.otpsubmit);
        editTextConfirmOtp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);

        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        otp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(RegisterScreen.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String otp = editTextConfirmOtp.getText().toString().trim();

                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST, RegistrationConfig.CONFIRM_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //if the server response is success
                                if(response.equalsIgnoreCase("success")){
                                    //dismissing the progressbar
                                    loading.dismiss();
                                    SendRegistrationEmail(emailaddress);
                                    //Starting a new activity
                                    startActivity(new Intent(RegisterScreen.this, Success.class));
                                }else{
                                    //Displaying a toast if the otp entered is wrong
                                        Toast.makeText(RegisterScreen.this,"Wrong OTP Please Try Again after sometime.",Toast.LENGTH_LONG).show();
                                        loading.dismiss();
                                        alertDialog.dismiss();
                                        finish();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(RegisterScreen.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put(RegistrationConfig.KEY_OTP, otp);
                        params.put(RegistrationConfig.KEY_PHONENUMBER, phonenumber);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });
    }
    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("input_error")) {
                    register_password.setText("");
                } else if (code.equals("registration_failed")) {
                    register_id.setText("");
                    register_phone.setText("");
                    register_email.setText("");
                    register_password.setText("");
                    register_checkbox1.setChecked(false);
                    register_checkbox2.setChecked(false);
                }else if (code.equals("missingfields")){
                    dialoginterface.dismiss();
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void SendRegistrationEmail(final String EmailAddress){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, RegistrationConfig.EMAILPROFILE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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

}

/*
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
    } */







