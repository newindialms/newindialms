package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
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
 * Created by kamalshree on 9/26/2017.
 */

public class StudentMyCourse extends Fragment{

    private String facultycourselist_url = "http://newindialms.000webhostapp.com/faculty_courselist.php";
    private FacultyCourseListAdapter adapter;
    private String faculty_employeeid;
    List<FacultyListItemCourseList> heroList;
    FacultyListItemCourseList pglist;
    ListView listView;
    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_faculty_courselist, null);
        FacultyMenu activity = (FacultyMenu) getActivity();
        faculty_employeeid=activity.getEmployeeid();
        TextView studentpic_title=(TextView)rootView.findViewById(R.id.faculty_courselist_title);
        studentpic_title.setText("CourseList");

        pglist= new FacultyListItemCourseList(faculty_employeeid);
        pglist.setFaculty_employeeid(faculty_employeeid);

        heroList = new ArrayList<>();
        listView = (ListView) rootView.findViewById(R.id.faculty_courselist_ListView);
        loadRecyclerViewData();

        return rootView;
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, facultycourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("course_details");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        FacultyListItemCourseList listItemProgramList = new FacultyListItemCourseList(
                                jsonObject1.getString("course_details_name"),pglist.getFaculty_employeeid()
                        );
                        heroList.add(listItemProgramList);


                    }
                    adapter = new FacultyCourseListAdapter(getContext(),R.layout.cardview_faculty_courselist,heroList);
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
                params.put("faculty_employeeid", faculty_employeeid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}