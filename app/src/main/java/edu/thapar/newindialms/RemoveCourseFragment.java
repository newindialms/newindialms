package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
 * A simple {@link Fragment} subclass.
 */
public class RemoveCourseFragment extends Fragment {
    View rootview;
    private String removelist_url = "https://www.newindialms.com/get_courselist.php";
    RemoveCourseAdapter adapter;
    public SwipeRefreshLayout swipeRefreshLayout;

    List<RemoveCourseListItems> heroList;
    ListView listView;

    public RemoveCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_remove_course, container, false);

        heroList = new ArrayList<>();
        listView = (ListView) rootview.findViewById(R.id.removecourselistView);
        loadRecyclerViewData();

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

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, removelist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("Course_List");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        RemoveCourseListItems listItemProgramList = new RemoveCourseListItems(
                                jsonObject1.getString("course_details_name"),
                                jsonObject1.getString("course_details_code")
                        );
                        heroList.add(listItemProgramList);
                    }
                    adapter = new RemoveCourseAdapter(getActivity(), R.layout.fragmet_remove_course_listitems, heroList);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_picbook));
    }

}
