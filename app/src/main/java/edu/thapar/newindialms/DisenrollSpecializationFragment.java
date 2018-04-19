package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
public class DisenrollSpecializationFragment extends Fragment {
    View rootview;
    private String enrolled_courselist = "https://newindialms.000webhostapp.com/listenrolledspecilization.php";
    DisenrolledSpecializationAdapter adapter;
    private String studentid;
    List<EnrollSpecializationListItems> heroList;
    ListView listView;
    private AlertDialog.Builder builder;
    public DisenrollSpecializationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_enrolled_specialization, container, false);
        studentid =  getActivity().getIntent().getExtras().getString("studentid");
        heroList = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.enrolledcourselistView);
        loadRecyclerViewData();
        return rootview;
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrolled_courselist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("specialization_details");

                    for (int i = 0; i < array.length(); i++) {
                        EnrollSpecializationListItems listItemProgramList = new EnrollSpecializationListItems(
                                    array.getString(i), studentid
                            );
                            heroList.add(listItemProgramList);

                    }
                    adapter = new DisenrolledSpecializationAdapter(getActivity(),R.layout.fragment_disenroll_course_listitems,heroList);
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.enrolledcourses_title));
    }

    public void displayAlert() {
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
                Intent specializationintent = new Intent(getContext(), StudentEnrollSpecializationTab.class);
                specializationintent.putExtra("openfragment", "0");
                specializationintent.putExtra("studentid", studentid);
                startActivity(specializationintent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
