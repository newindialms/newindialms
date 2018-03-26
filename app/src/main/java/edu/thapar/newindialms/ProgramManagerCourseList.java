package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class ProgramManagerCourseList extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String menucourselist_url = "https://newindialms.000webhostapp.com/menu_courselist.php";

    private List<ListItemCourseList> listItemCourseLists;
    public SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program_manager_courselist, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.courselistRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipeRefreshLayout=(SwipeRefreshLayout)rootView.findViewById(R.id.showfeedback_swipe);

        listItemCourseLists = new ArrayList<>();

        loadRecyclerViewDatafirstyear();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                listItemCourseLists.clear();

                loadRecyclerViewDatafirstyear();
            }
        });
        return rootView;
    }

    private void loadRecyclerViewDatafirstyear() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, menucourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Course_List");
                    JSONArray array1 = jsonObject.getJSONArray("Course_List_first");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        ListItemCourseList listItemCourseList = new ListItemCourseList(
                                jsonObject1.getString("course_details_name"),
                                jsonObject1.getString("course_details_faculty")
                        );
                        listItemCourseLists.add(listItemCourseList);
                    }
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject jsonObject1 = array1.getJSONObject(i);
                        ListItemCourseList listItemCourseList = new ListItemCourseList(
                                jsonObject1.getString("first_year_course_list_name"),
                                jsonObject1.getString("first_year_course_list_faculty")
                        );
                        listItemCourseLists.add(listItemCourseList);
                    }
                    adapter = new CourseListAdapter(listItemCourseLists, getContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
