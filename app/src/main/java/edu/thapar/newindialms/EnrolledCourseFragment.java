package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class EnrolledCourseFragment extends Fragment {
    View rootview;
    private String enrolled_courselist = "https://newindialms.000webhostapp.com/listenrolledcourses.php";
    EnrolledCourseAdapter adapter;
    private String studentid;
    List<EnrolledCourseListItems> heroList;
    ListView listView;
    private TextView enrolledcourses_title;

    public EnrolledCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.fragment_enrolled_course, container, false);
        studentid =  getActivity().getIntent().getExtras().getString("studentid");
        heroList = new ArrayList<>();
        enrolledcourses_title=(TextView) rootview.findViewById(R.id.enrolledcourses_title);
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
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        EnrolledCourseListItems listItemProgramList = new EnrolledCourseListItems(
                               array.getString(i)
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new EnrolledCourseAdapter(getActivity(),R.layout.fragment_enrolled_course_listitems,heroList);
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
                params.put("student_rollno", studentid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_picbook));
    }

}
