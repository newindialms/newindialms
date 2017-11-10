package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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

import static edu.thapar.newindialms.AddCourseFragment.facultyspinner_URL;
import static edu.thapar.newindialms.R.id.facultyspinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramManagerSchedule extends Fragment {
    String issue_url = "https://newindialms.000webhostapp.com/get_issue.php";
    String day_url = "https://newindialms.000webhostapp.com/get_days.php";
    private Spinner issuespinner,dayspinner;
    private ArrayList<String> issuelist,daylist;
    private JSONArray resultissue,resultday;
    Button gobutton;
    String issue_details,day_details;
    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview =inflater.inflate(R.layout.fragment_program_manager_schedule, container, false);

        addListenerOnIssueSpinnerItemSelection();
        addListenerOnDaysSpinnerItemSelection();




        gobutton = (Button) rootview.findViewById(R.id.course_schedule_go);
        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scheduleintent = new Intent(getActivity(), ProgramManagerCourseSchedule.class);
                scheduleintent.putExtra("issue_details",issue_details);
                scheduleintent.putExtra("day_details",day_details);
                scheduleintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(scheduleintent);
            }
        });
        return rootview;
    }

    //Spinner for Issue
    public void addListenerOnIssueSpinnerItemSelection() {
        issuelist = new ArrayList<String>();
        issuespinner = (Spinner) rootview.findViewById(R.id.course_schedule_issuespinner);
        issue_details=issuespinner.getSelectedItem().toString();
        getIssueData();

    }
    private void getIssueData(){

        issuespinner = (Spinner) rootview.findViewById(R.id.course_schedule_issuespinner);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(issue_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultissue = j.getJSONArray("issues");

                            //Calling method getStudents to get the students from the JSON Array
                            getIssueName(resultissue);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getIssueName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                issuelist.add(json.getString("course_schedule_issue"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        issuespinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, issuelist));
    }

    //Spinner for Issue
    public void addListenerOnDaysSpinnerItemSelection() {
        daylist = new ArrayList<String>();
        dayspinner = (Spinner) rootview.findViewById(R.id.course_schedule_dayspinner);
        day_details=dayspinner.getSelectedItem().toString();
        getDaysData();

    }
    private void getDaysData(){

        dayspinner = (Spinner) rootview.findViewById(R.id.course_schedule_dayspinner);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(day_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultday = j.getJSONArray("day");

                            //Calling method getStudents to get the students from the JSON Array
                            getDaysName(resultday);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getDaysName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                daylist.add(json.getString("course_schedule_day"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        dayspinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, daylist));
    }


}
