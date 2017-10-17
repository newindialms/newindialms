package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
 * Created by kamalshree on 10/9/2017.
 */

public class AddFeedbackActivity extends AppCompatActivity {

    Toolbar feedbacktoolbar;
    Spinner feedbackspinner;
    Button addfeedback;
    EditText feedbacktitle,feedbackquestion;
    String feedback_title,feedback_question;

    String feedback_type;
    ArrayAdapter<String> feedbacktype;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    String feedback_url = "https://newindialms.000webhostapp.com/add_feedback.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_feedback_layout);

        feedbacktoolbar = (Toolbar) findViewById(R.id.feedback_toolbar);
        feedbacktoolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(feedbacktoolbar);
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        feedbacktoolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        feedbackspinner=(Spinner)findViewById(R.id.addfeedbackspinner);

       feedbacktype = new ArrayAdapter<String>(
                AddFeedbackActivity.this,R.layout.feedbacklisttype, getResources().getStringArray(R.array.feedbacktype) );
        feedbacktype.setDropDownViewResource(R.layout.feedbacklisttype);
        feedbackspinner.setAdapter(feedbacktype);

        feedbackspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                feedback_type= feedbackspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddFeedbackActivity.this,"Select the Feedback Type",Toast.LENGTH_LONG).show();
            }
        });

        feedbacktitle=(EditText) findViewById(R.id.addfeedbackmaintitle);
        feedbackquestion=(EditText) findViewById(R.id.addfeedbackdescription);
        addfeedback=(Button)findViewById(R.id.addfeedbacksubmit);

        addfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(AddFeedbackActivity.this,"Sending feedback","Please wait...",false,false);
                feedback_title = feedbacktitle.getText().toString();
                feedback_question = feedbackquestion.getText().toString();

                if (feedback_title.equals("") || feedback_question.equals("") || feedback_type.equals("")){
                    progressDialog.dismiss();
                    builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
                    builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
                    displayAlert("missing_fields");
                }

                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, feedback_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                progressDialog.dismiss();
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");

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
                            params.put("feedback_title", feedback_title);
                            params.put("feedback_question", feedback_question);
                            params.put("feedback_type", feedback_type);
                            return params;
                        }
                    };
                    MySingleton.getInstance(AddFeedbackActivity.this).addToRequestQueue(stringRequest);
                }
            }
        });
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("missing_fields")) {
                    feedbacktitle.setText("");
                    feedbackquestion.setText("");
                }
                if (code.equals("Success")) {
                   finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}


