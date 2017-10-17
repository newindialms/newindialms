package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static edu.thapar.newindialms.R.array.feedbacktype;

public class UpdateFeedback extends AppCompatActivity {

    EditText feedback_udpate_title,feedback_udpate_question;
    TextView showfeedback_id;
    Button udpateButton,deleteButton;
    Spinner feedbackspinner;
    ArrayAdapter<String> feedbacktype;
    FeedbackAdapter feedbackAdapter;
    String feedback_title,feedback_question,feedback_type,id;
    String updatefeedback_url = "https://newindialms.000webhostapp.com/update_feedback.php";
    String deletefeedback_url = "https://newindialms.000webhostapp.com/delete_feedback.php";

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);
        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        showfeedback_id= (TextView) findViewById(R.id.showfeedback_id);
        feedback_udpate_title= (EditText)findViewById(R.id.feedback_udpate_title);
        feedback_udpate_question= (EditText)findViewById(R.id.feedback_udpate_question);

        udpateButton = (Button)findViewById(R.id.feedback_update);
        deleteButton = (Button)findViewById(R.id.feedback_delete);

          id = getIntent().getStringExtra("id");
         feedback_title = getIntent().getStringExtra("feedback_title");
         feedback_question = getIntent().getStringExtra("feedback_question");


        feedbackspinner=(Spinner)findViewById(R.id.addfeedbackspinner);

        feedbacktype = new ArrayAdapter<String>(
                UpdateFeedback.this,R.layout.feedbacklisttype, getResources().getStringArray(R.array.feedbacktype) );
        feedbacktype.setDropDownViewResource(R.layout.feedbacklisttype);
        feedbackspinner.setAdapter(feedbacktype);

        feedbackspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                feedback_type= feedbackspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(UpdateFeedback.this,"Select the Feedback Type",Toast.LENGTH_LONG).show();
            }
        });

        showfeedback_id.setText(id);
        feedback_udpate_title.setText(feedback_title);
        feedback_udpate_question.setText(feedback_question);

        udpateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateFeedbackFunction();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFeedbackFunction();
            }
        });
    }


    public void updateFeedbackFunction(){
        id=showfeedback_id.getText().toString();
        feedback_title=feedback_udpate_title.getText().toString();
        feedback_question=feedback_udpate_question.getText().toString();
        feedback_type= feedbackspinner.getSelectedItem().toString();

        if (feedback_title.equals("") || feedback_question.equals("") || feedback_type.equals("")){
            builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
            builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
            displayAlert("missing_fields");
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, updatefeedback_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              try {
                  JSONArray jsonArray = new JSONArray(response);
                  JSONObject jsonObject = jsonArray.getJSONObject(0);
                  String code = jsonObject.getString("code");
                  String message = jsonObject.getString("message");
                  builder.setTitle(code);
                  builder.setMessage(message);
                  displayAlert(message);
                  UpdateFeedback.this.finish();
              }
              catch (JSONException e){

              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                builder.setTitle("Error");
                builder.setMessage("fail");
                displayAlert("Fail");
                finish();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                params.put("feedback_title", feedback_title);
                params.put("feedback_question", feedback_question);
                params.put("feedback_type", feedback_type);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void deleteFeedbackFunction(){
        id=showfeedback_id.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, deletefeedback_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    builder.setTitle(code);
                    builder.setMessage(message);
                    displayAlert(message);
                    UpdateFeedback.this.finish();
                }
                catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                builder.setTitle("Error");
                builder.setMessage("fail");
                displayAlert("Fail");
                finish();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
            };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("missing_fields")) {
                    dialoginterface.dismiss();
                    //
                }
                if (code.equals("Success")) {
                    dialoginterface.dismiss();

                }
                if (code.equals("Fail")) {
                    dialoginterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
