package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * A simple {@link Fragment} subclass.
 */
public class AddFeedbackFragment extends Fragment {
    private View rootview;

    private Spinner feedbackspinner;
    private Button addfeedback;
    private EditText feedbacktitle, feedbackquestion;
    private String feedback_title, feedback_question;

    private String feedback_type;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder builder;
    private String feedback_url = "https://newindialms.000webhostapp.com/add_feedback.php";

    public AddFeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.add_feedback_layout, container, false);
        feedbackspinner = (Spinner) rootview.findViewById(R.id.addfeedbackspinner);
        feedbackspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                feedback_type = feedbackspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Nothing selected. Please select one", Toast.LENGTH_SHORT).show();
            }
        });

        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        feedbacktitle = (EditText) rootview.findViewById(R.id.addfeedbackmaintitle);
        feedbackquestion = (EditText) rootview.findViewById(R.id.addfeedbackdescription);
        addfeedback = (Button) rootview.findViewById(R.id.addfeedbacksubmit);
        addfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFeedBack();
            }
        });

        return rootview;
    }

    public void AddFeedBack() {
        progressDialog = ProgressDialog.show(getActivity(), "Sending feedback", "Please wait...", false, false);
        feedback_title = feedbacktitle.getText().toString();
        feedback_question = feedbackquestion.getText().toString();

        if (feedback_title.equals("") || feedback_question.equals("") || feedback_type.equals("")) {
            progressDialog.dismiss();
            builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
            builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
            displayAlert("missing_fields");
        } else {
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
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("missing_fields")) {
                    feedbacktitle.setText("");
                    feedbackquestion.setText("");
                }
                if (code.equals("Success")) {
                    getActivity().finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
