package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCourseFragment extends Fragment implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {
    private View rootview;
    private edu.thapar.newindialms.MultiSelectionSpinner facultyspinner;
    private Spinner coursetypespinner, specializationtypespinner, yeartypespinner;
    private Button btnSubmit;
    private ArrayList<String> facultylist;
    private JSONArray resultfaculty;
    public static final String facultyspinner_URL = "https://www.newindialms.com/get_facultyname.php";
    public static final String newcourse_url = "https://www.newindialms.com/new_course.php";
    public static final String firstnewcourse_url = "https://www.newindialms.com/add_first_course.php";
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;

    private EditText coursename, coursecode, coursecredits, courseabbr;
    private String course_details_name, course_details_code, course_details_credits, course_details_abbr, course_details_category, course_details_faculty, course_details_specialization, course_details_year;

    public AddCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.activity_new_course, container, false);
        addListenerOnYearSpinnerItemSelection();
        addListenerOnCourseSpinnerItemSelection();
        addListenerOnFacultySpinnerItemSelection();
        addListenerOnSpecializationSpinnerItemSelection();

        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        btnSubmit = rootview.findViewById(R.id.addcourse_addbutton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (course_details_year.equals("1")) {
                    AddFirstCourseFunction();
                } else {
                    AddCourseFunction();
                }
            }
        });
        return rootview;
    }

    //Spinner for coursetype
    public void addListenerOnCourseSpinnerItemSelection() {
        coursetypespinner = rootview.findViewById(R.id.coursetypespinner);
        coursetypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course_details_category = coursetypespinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Nothing selected, Please choose one item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Spinner for Year
    public void addListenerOnYearSpinnerItemSelection() {
        yeartypespinner = rootview.findViewById(R.id.specialization_spinner_year);
        yeartypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                specializationtypespinner = rootview.findViewById(R.id.specialization_spinner);
                coursetypespinner = rootview.findViewById(R.id.coursetypespinner);

                course_details_year = yeartypespinner.getSelectedItem().toString();
                if (course_details_year.equals("1")) {
                    specializationtypespinner.setVisibility(View.INVISIBLE);
                    coursetypespinner.setVisibility(View.INVISIBLE);
                } else {
                    specializationtypespinner.setVisibility(View.VISIBLE);
                    coursetypespinner.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Nothing selected, Please choose one item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Spinner for Specialization
    public void addListenerOnSpecializationSpinnerItemSelection() {
        specializationtypespinner = rootview.findViewById(R.id.specialization_spinner);
        specializationtypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course_details_specialization = specializationtypespinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(), "Nothing selected, Please choose one item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Spinner for faculty
    public void addListenerOnFacultySpinnerItemSelection() {
        facultylist = new ArrayList<String>();
        facultyspinner = rootview.findViewById(R.id.facultyspinner);
        getFacultyData();

    }

    private void getFacultyData() {

        facultyspinner = rootview.findViewById(R.id.facultyspinner);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(facultyspinner_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultfaculty = j.getJSONArray("faculty");

                            //Calling method getStudents to get the students from the JSON Array
                            getFacultyName(resultfaculty);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getFacultyName(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                facultylist.add(json.getString("faculty_code"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        facultyspinner.setItems(facultylist);
        facultyspinner.setListener(this);
    }

    public void AddCourseFunction() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data");
        progressDialog.show();

        coursename = rootview.findViewById(R.id.addcourse_name);
        coursecode = rootview.findViewById(R.id.addcourse_code);
        coursecredits = rootview.findViewById(R.id.addcourse_credits);
        courseabbr = rootview.findViewById(R.id.addcourse_abbr);


        course_details_name = coursename.getText().toString();
        course_details_code = coursecode.getText().toString();
        course_details_credits = coursecredits.getText().toString();
        course_details_abbr = courseabbr.getText().toString();
        course_details_category = coursetypespinner.getSelectedItem().toString();
        course_details_faculty = facultyspinner.getSelectedItem().toString();

        if (course_details_name.equals("") || course_details_code.equals("") || course_details_credits.equals("") || course_details_abbr.equals("") ||
                course_details_category.equals("") || course_details_faculty.equals("") || course_details_specialization.equals("")) {
            progressDialog.dismiss();
            builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
            builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
            displayAlert("input_error");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, newcourse_url, new Response.Listener<String>() {
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
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("course_details_code", course_details_code);
                    params.put("course_details_name", course_details_name);
                    params.put("course_details_specialization", course_details_specialization);
                    params.put("course_details_credits", course_details_credits);
                    params.put("course_details_category", course_details_category);
                    params.put("course_details_faculty", course_details_faculty);
                    params.put("course_details_abbr", course_details_abbr);
                    return params;
                }
            };
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    public void AddFirstCourseFunction() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data");
        progressDialog.show();

        coursename = rootview.findViewById(R.id.addcourse_name);
        coursecode = rootview.findViewById(R.id.addcourse_code);
        coursecredits = rootview.findViewById(R.id.addcourse_credits);
        courseabbr = rootview.findViewById(R.id.addcourse_abbr);


        course_details_name = coursename.getText().toString();
        course_details_code = coursecode.getText().toString();
        course_details_credits = coursecredits.getText().toString();
        course_details_abbr = courseabbr.getText().toString();
        course_details_category = coursetypespinner.getSelectedItem().toString();
        course_details_faculty = facultyspinner.getSelectedItem().toString();

        if (course_details_name.equals("") || course_details_code.equals("") || course_details_credits.equals("") || course_details_abbr.equals("") ||
                course_details_faculty.equals("")) {
            progressDialog.dismiss();
            builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
            builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
            displayAlert("input_error");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, firstnewcourse_url, new Response.Listener<String>() {
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
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("course_details_code", course_details_code);
                    params.put("course_details_name", course_details_name);
                    params.put("course_details_credits", course_details_credits);
                    params.put("course_details_faculty", course_details_faculty);
                    params.put("course_details_abbr", course_details_abbr);
                    return params;
                }
            };
            MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        }
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("input_error")) {
                    //missing fields
                } else if (code.equals("Success")) {
                    Intent addcourseintent = new Intent(getContext(), AddCourseTab.class);
                    addcourseintent.putExtra("openfragment", "0");
                    startActivity(addcourseintent);
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        // Toast.makeText(getContext(), strings.toString(), Toast.LENGTH_LONG).show();
    }
}
