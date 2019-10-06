package edu.thapar.newindialms;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
 * A simple {@link Fragment} subclass.
 */
public class SearchStudent extends Fragment {


    //Declaring an Spinner
    private Spinner joiningSpinner, programSpinner, specializationSpinner;

    //An ArrayList for Spinner Items
    private ArrayList<Integer> studentsyear;
    private ArrayList<String> studentsprogram;
    private ArrayList<String> studentsspecilization;

    Button spinner_show_button;

    //JSON Array
    private JSONArray resultyear, resultprogram, resultspecialization;

    public SearchStudent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_search_student, container, false);

        studentsyear = new ArrayList<Integer>();
        studentsprogram = new ArrayList<String>();
        studentsspecilization = new ArrayList<String>();

        //Initializing Spinner
        joiningSpinner = rootview.findViewById(R.id.joining_spinner);
        programSpinner = rootview.findViewById(R.id.program_spinner);
        specializationSpinner = rootview.findViewById(R.id.specialization_spinner);

        spinner_show_button = rootview.findViewById(R.id.spinner_show_button);

        //This method will fetch the data from the URL
        getYearData();
        spinner_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = Integer.parseInt(joiningSpinner.getSelectedItem().toString());
                String program = programSpinner.getSelectedItem().toString();
                String specialization = specializationSpinner.getSelectedItem().toString();
                Intent spinneritent = new Intent(getActivity(), SpinnerStudentList.class);

                spinneritent.putExtra("year", year);
                spinneritent.putExtra("program", program);
                spinneritent.putExtra("specialization", specialization);
                startActivity(spinneritent);

            }
        });

        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_enrollstudent));
    }


    private void getYearData() {
        //Creating a string request
        StringRequest stringRequest = new StringRequest(StudentConfig.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultyear = j.getJSONArray(StudentConfig.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getStudentsYear(resultyear);
                            getStudentsProgram(resultyear);
                            getStudentsSpecialization(resultyear);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getStudentsYear(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                studentsyear.add(Integer.parseInt(json.getString(StudentConfig.TAG_year)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        joiningSpinner.setAdapter(new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_dropdown_item, studentsyear));
    }

    private void getStudentsProgram(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                studentsprogram.add(json.getString(StudentConfig.TAG_program));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        programSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, studentsprogram));
    }

    private void getStudentsSpecialization(JSONArray j) {
        //Traversing through all the items in the json array
        for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                studentsspecilization.add(json.getString(StudentConfig.TAG_specialization));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        specializationSpinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, studentsspecilization));
    }

}
