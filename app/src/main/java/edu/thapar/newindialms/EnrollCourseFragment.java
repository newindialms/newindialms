package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

import static android.media.CamcorderProfile.get;
import static edu.thapar.newindialms.R.id.enrollcourses_ListView;
import static edu.thapar.newindialms.R.id.view;


/**
 * A simple {@link Fragment} subclass.
 */
public class EnrollCourseFragment extends Fragment{
    View rootview;
    String student_specialization;
    TextView enrolled_specialization;
    String enrollallcourselist_url = "https://newindialms.000webhostapp.com/AllCourseList.php";
    EnrollcourseListItems pglist;
    List<EnrollcourseListItems> heroList;
    EnrollcourseAdapter adapter;
    ListView listView;
    Button EnrollButton;
    SparseBooleanArray sparseBooleanArray ;

    String[] ListViewItems = new String[]{};
    public EnrollCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.activity_new_enrollcourse, container, false);

        student_specialization =  getActivity().getIntent().getExtras().getString("student_specialization");
        enrolled_specialization=(TextView)rootview.findViewById(R.id.Enrollspecialization_textview_value);
        enrolled_specialization.setText(student_specialization);
        heroList = new ArrayList<>();

        listView = (ListView) rootview.findViewById(R.id.enrollcourses_ListView);
        EnrollButton=(Button)rootview.findViewById(R.id.EnrollButton);
        loadRecyclerViewData();
        EnrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return rootview;

    }



    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, enrollallcourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
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

}
