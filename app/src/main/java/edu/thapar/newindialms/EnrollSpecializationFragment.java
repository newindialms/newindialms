package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
public class EnrollSpecializationFragment extends Fragment {
    View rootview;
    String studentid, student_rollnno;
    String getspecializationlist_url = "https://www.newindialms.com/get_specializationname.php";
    String insertenroll_url = "https://www.newindialms.com/insert_enrollspecialization.php";
    List<EnrollSpecializationListItems> heroList;
    EnrollSpecializationAdapter adapter;
    ListView listView;
    Button EnrollButton;
    AlertDialog.Builder builder;
    String enrollist = "";
    public SwipeRefreshLayout swipeRefreshLayout;

    public EnrollSpecializationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.activity_new_enrollspecialization, container, false);

        studentid = getActivity().getIntent().getExtras().getString("studentid");
        student_rollnno = getActivity().getIntent().getExtras().getString("studentid");
        heroList = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.enrollcourses_ListView);
        EnrollButton = (Button) rootview.findViewById(R.id.EnrollButton);
        loadRecyclerViewData();

        EnrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickData(view);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.showfeedback_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                heroList.clear();
                loadRecyclerViewData();
            }
        });

        return rootview;


    }

    public void onClickData(View view) {
        List<EnrollSpecializationListItems> empData = adapter.enrollcourseListItemses;
        enrollist = "";
        int counter = 0;
        for (EnrollSpecializationListItems employeeModel : empData) {
            if (employeeModel.isSelected()) {
                counter++;
                if (counter == 1) {
                    enrollist = employeeModel.getCoursedetails_name();
                } else {
                    enrollist += "," + employeeModel.getCoursedetails_name();
                }
            }

        }
        enrolRecyclerViewData();
    }

    private void enrolRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertenroll_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                builder = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
                builder.setTitle("Success");
                builder.setMessage("Specialization updated Succcessfully");
                displayAlert();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("student_rollnno", studentid);
                params.put("student_specialization", enrollist);
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getspecializationlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("specialization");

                    for (int i = 0; i < array.length(); i++) {
                        //JSONObject jsonObject1 = array.getJSONObject(i);
                        EnrollSpecializationListItems listItemProgramList = new EnrollSpecializationListItems(
                                array.getString(i)
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrollSpecializationAdapter(getContext(), R.layout.activity_student_enrollspecializationlistitems, heroList);
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
                params.put("student_rollnno", studentid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
               // getActivity().finish();
                heroList.clear();
                loadRecyclerViewData();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
