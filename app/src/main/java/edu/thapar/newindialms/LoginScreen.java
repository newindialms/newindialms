package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import edu.thapar.newindialms.R;

/**
 * Created by kamalshree on 10/14/2017.
 */

public class LoginScreen extends AppCompatActivity {
    Toolbar toolbar_login_screen;
    EditText login_id, login_phone;
    TextView login_signup;
    Button login_button;
    AlertDialog.Builder builder;
    String studentid, phonenumber;
    String login_url = "https://newindialms.000webhostapp.com/login.php";
    Context context;
    ProgressDialog progressDialog;
    public View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        toolbar_login_screen = (Toolbar) findViewById(R.id.toolbar_login_screen);
        toolbar_login_screen.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_login_screen);
        toolbar_login_screen.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Get your custom_toast.xml ayout
        LayoutInflater inflater = getLayoutInflater();

       layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
        final TextView toast_text = (TextView) layout.findViewById(R.id.toast_text);

       /* if(SharedPrefManager.getInstance(this).isLoggedIn()){
            // logged in
            finish();
            startActivity(new Intent(this,Dashboard.class));
            return;
        }*/

        login_signup=(TextView)findViewById(R.id.login_signup);

        login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginScreen.this,RegisterScreen.class));
                return;
            }
        });

        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        login_id = (EditText) findViewById(R.id.login_id);
        login_phone = (EditText) findViewById(R.id.login_phone);
        login_button = (Button) findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(LoginScreen.this);
                progressDialog.setMessage("Loading...");
                progressDialog.setTitle("Login");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                studentid = login_id.getText().toString();
                phonenumber = login_phone.getText().toString();

                if (studentid.equals("") || phonenumber.equals("")) {
                    progressDialog.dismiss();
                    builder.setTitle("Error");
                    builder.setMessage("Fields are missing");
                    displayAlert("input_error");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                 progressDialog.dismiss();
                                    JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                if (code.equals("login_failed")) {
                                    progressDialog.dismiss();
                                    builder.setTitle("Login Failed");
                                    builder.setMessage("Check your Login details");
                                    displayAlert("input_error");

                                } else {

                                    if(jsonObject.getString("idtype").equals("student")) {
                                        //student dashboard
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), StudentDashboard.class));
                                        toast_text.setText("Student Dashboard");
                                        customToast();

                                    }
                                    else if(jsonObject.getString("idtype").equals("programmanager")){
                                        //program manager dashboard
                                        finish();
                                        startActivity(new Intent(getApplicationContext(), ProgramManagerMenu.class));
                                        toast_text.setText("ProgramManager Dashboard");
                                        customToast();

                                    }
                                    else{
                                        //faculty dashboard
                                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                                jsonObject.getString("faculty_employeeid")
                                        );

                                        String facultyname = jsonObject.getString("faculty_firstname");
                                        String facultyid = jsonObject.getString("faculty_employeeid");
                                        finish();
                                        Intent facultyintent=new Intent(getApplicationContext(), FacultyMenu.class);
                                        facultyintent.putExtra("facultyname",facultyname);
                                        facultyintent.putExtra("facultyid",facultyid);
                                        startActivity(facultyintent);
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            toast_text.setText("Unable to Connect Check your Network Connection");
                            customToast();
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("studentid", studentid);
                            params.put("phonenumber", phonenumber);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginScreen.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("input_error")) {
                    login_id.setText("");
                    login_phone.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void customToast(){
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
        toast.setView(layout); // set the inflated layout
        toast.show(); // display the custom Toast
    }
}
