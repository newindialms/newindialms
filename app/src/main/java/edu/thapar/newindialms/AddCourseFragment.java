package edu.thapar.newindialms;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCourseFragment extends Fragment {
    View rootview;
    private Spinner  semesterspinner,coursetypespinner,facultyspinner,feedbackspinner;
    private Button btnSubmit;
    private ArrayList<String> facultylist,feedbacklist;
    private JSONArray resultfaculty,resultfeedback;
    public static final String facultyspinner_URL = "https://newindialms.000webhostapp.com/get_facultyname.php";
    public static final String feedbackspinner_URL = "https://newindialms.000webhostapp.com/get_feedbacktitle.php";
    public static final String  addcourse_url = "https://newindialms.000webhostapp.com/add_course.php";
    private EditText startDateEtxt,endDateEtxt,scheduleDate;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;

    private int  mHour, mMinute;

    EditText coursename,coursetime,descriptiondetails,outcomedetails;
    String addcourse_name,addcourse_startdate,addcourse_enddate,addcourse_semester,
            addcourse_coursetype,addcourse_scheduledate,addcourse_time,addcourse_faculty,addcourse_feedback,
            addcourse_description,addcourse_outcomes;

    public AddCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootview=inflater.inflate(R.layout.fragment_add_course, container, false);
        addListenerOnSemesterSpinnerItemSelection();
        addListenerOnCourseSpinnerItemSelection();
        addListenerOnFacultySpinnerItemSelection();
        addListenerOnFeedbackSpinnerItemSelection();

        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);

        startDateEtxt = (EditText)rootview.findViewById(R.id.addcourse_startDate);
        startDateEtxt.setInputType(InputType.TYPE_NULL);

        //start date Calendar click
        startDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startDatePicker();
            }
        });
        //End date Calendar click
        endDateEtxt = (EditText)rootview.findViewById(R.id.addcourse_endDate);
        endDateEtxt.setInputType(InputType.TYPE_NULL);
        endDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                endDatePicker();
            }
        });

        //Schedule date Calendar click
        scheduleDate = (EditText)rootview.findViewById(R.id.addcourse_scheduleDate);
        scheduleDate.setInputType(InputType.TYPE_NULL);
        scheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                scheduleDatePicker();
            }
        });

        //Time Dialog
        coursetime=(EditText) rootview.findViewById(R.id.addcourse_schedulingTime);
        coursetime.setInputType(InputType.TYPE_NULL);
        coursetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimeDialog();
            }
        });

        btnSubmit=(Button)rootview.findViewById(R.id.addcourse_addbutton);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCourseFunction();
            }
        });
        return rootview;
    }

//Spinner for semester
    public void addListenerOnSemesterSpinnerItemSelection() {
        semesterspinner = (Spinner) rootview.findViewById(R.id.semesterspinner);
        semesterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addcourse_semester=semesterspinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(getActivity(),"nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Spinner for coursetype
    public void addListenerOnCourseSpinnerItemSelection() {
        coursetypespinner = (Spinner) rootview.findViewById(R.id.coursetypespinner);
        coursetypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addcourse_coursetype=coursetypespinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getActivity(),"nothing selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Spinner for faculty
    public void addListenerOnFacultySpinnerItemSelection() {
        facultylist = new ArrayList<String>();
        facultyspinner = (Spinner) rootview.findViewById(R.id.facultyspinner);
        getFacultyData();

    }

    private void getFacultyData(){

        facultyspinner = (Spinner) rootview.findViewById(R.id.facultyspinner);
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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getFacultyName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                facultylist.add(json.getString("faculty_firstname"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        facultyspinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, facultylist));
    }

    //Spinner for feedback
    public void addListenerOnFeedbackSpinnerItemSelection() {
        feedbacklist = new ArrayList<String>();
        feedbackspinner = (Spinner) rootview.findViewById(R.id.feedbackspinner);
        getFeedbackData();

    }

    private void getFeedbackData(){

        //Creating a string request
        StringRequest stringRequest = new StringRequest(feedbackspinner_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            resultfeedback = j.getJSONArray("feedback");

                            //Calling method getStudents to get the students from the JSON Array
                            getFeedbackName(resultfeedback);
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

    private void getFeedbackName(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                feedbacklist.add(json.getString("feedback_title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        feedbackspinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, feedbacklist));
    }

    private void startDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    private void endDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(offdate);
        date.show(getFragmentManager(), "Date Picker");
    }

    private void scheduleDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(scheduledate);
        date.show(getFragmentManager(), "Date Picker");
    }

    OnDateSetListener ondate = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            startDateEtxt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };

    OnDateSetListener offdate = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            endDateEtxt.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };
    OnDateSetListener scheduledate = new OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            scheduleDate.setText(String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear+1)
                    + "-" + String.valueOf(year));
        }
    };


    public void AddCourseFunction(){
       progressDialog= new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data");
        progressDialog.show();

                     coursename=(EditText)rootview.findViewById(R.id.addcourse_name);
                    coursetime=(EditText)rootview.findViewById(R.id.addcourse_schedulingTime);

                    descriptiondetails=(EditText)rootview.findViewById(R.id.addcourse_description);
                    outcomedetails=(EditText)rootview.findViewById(R.id.addcourse_learningoutcomes);

                    addcourse_name=coursename.getText().toString();
                    addcourse_startdate=startDateEtxt.getText().toString();
                    addcourse_enddate=endDateEtxt.getText().toString();
                    //addcourse_semester,addcourse_coursetype
                    addcourse_scheduledate=scheduleDate.getText().toString();
                    addcourse_time=coursetime.getText().toString();
                    addcourse_faculty=facultyspinner.getSelectedItem().toString();
                    addcourse_feedback=feedbackspinner.getSelectedItem().toString();
                    addcourse_description=descriptiondetails.getText().toString();
                    addcourse_outcomes=outcomedetails.getText().toString();

            if(addcourse_name.equals("") ||
                    addcourse_startdate.equals("") ||
                    addcourse_enddate.equals("") ||
                    addcourse_semester.equals("") ||
                    addcourse_coursetype.equals("") ||
                    addcourse_scheduledate.equals("") ||
                    addcourse_time.equals("") ||
                    addcourse_faculty.equals("") ||
                    addcourse_feedback.equals("") ||
                    addcourse_description.equals("") ||addcourse_outcomes.equals("")){
                progressDialog.dismiss();
                builder.setTitle(getResources().getString(R.string.registration_error_missingfields_title));
                builder.setMessage(getResources().getString(R.string.registration_error_missingfields_text));
                displayAlert("input_error");

            }
            else{
                StringRequest stringRequest = new StringRequest(Request.Method.POST, addcourse_url, new Response.Listener<String>() {
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
                        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("addcourse_name", addcourse_name);
                        params.put("addcourse_startdate", addcourse_startdate);
                        params.put("addcourse_enddate", addcourse_enddate);
                        params.put("addcourse_semester", addcourse_semester);
                        params.put("addcourse_coursetype", addcourse_coursetype);
                        params.put("addcourse_scheduledate", addcourse_scheduledate);
                        params.put("addcourse_time", addcourse_time);
                        params.put("addcourse_faculty", addcourse_faculty);
                        params.put("addcourse_feedback", addcourse_feedback);
                        params.put("addcourse_description", addcourse_description);
                        params.put("addcourse_outcomes", addcourse_outcomes);
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
                }else if(code.equals("Success")) {
                    Intent home_intent = new Intent(getActivity(),ProgramManagerHome.class);
                    startActivity(home_intent);
                }

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void openTimeDialog(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        coursetime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }
}
