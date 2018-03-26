package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrollCourseFragment extends Fragment{
    View rootview;
    String student_specialization,studentid;
    TextView enrolled_specialization;
    String enrollallcourselist_url = "https://newindialms.000webhostapp.com/AllCourseList.php";
    String insertenroll_url = "https://newindialms.000webhostapp.com/insert_enrollcourse.php";
    List<EnrollcourseListItems> heroList;
    EnrollcourseAdapter adapter;
    ListView listView;
    Button EnrollButton;
    AlertDialog.Builder builder;
    String student_year="2";
    String enrollist="";
    public EnrollCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.activity_new_enrollcourse, container, false);

        student_specialization =  getActivity().getIntent().getExtras().getString("student_specialization");
        studentid =  getActivity().getIntent().getExtras().getString("studentid");
        enrolled_specialization=(TextView)rootview.findViewById(R.id.Enrollspecialization_textview_value);
        enrolled_specialization.setText(student_specialization);
        heroList = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.enrollcourses_ListView);
        EnrollButton=(Button)rootview.findViewById(R.id.EnrollButton);
        loadRecyclerViewData();

        EnrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickData(view);
            }
        });

        return rootview;


    }

    public void onClickData ( View view ) {
        List<EnrollcourseListItems> empData = adapter.enrollcourseListItemses;
        enrollist="";
        int counter=0;
        for (EnrollcourseListItems employeeModel : empData) {
            if ( employeeModel.isSelected() ) {
                counter++;
                if(counter==1) {
                    enrollist = employeeModel.getCoursedetails_name();
                }
                else{
                    enrollist +=","+employeeModel.getCoursedetails_name();
                }
            }

        }

        enrolRecyclerViewData();
    }

    private void enrolRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertenroll_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                builder=new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
                builder.setTitle("Enroll");
                builder.setMessage("Courses Enrolled Succcessfully");
                displayAlert();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_rollno", studentid);
                params.put("student_year", student_year);
                params.put("courses_enrolled", enrollist);
                params.put("student_specialization", student_specialization);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrollallcourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        EnrollcourseListItems listItemProgramList = new EnrollcourseListItems(
                                jsonObject1.getString("course_details_name"),
                                jsonObject1.getString("course_details_code"),
                                jsonObject1.getString("course_details_credits"),
                                jsonObject1.getString("course_details_faculty")
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrollcourseAdapter(getContext(),R.layout.activity_student_enrollcourselistitems,heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_specialization", student_specialization);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                getActivity().finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
