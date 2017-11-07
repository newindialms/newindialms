package edu.thapar.newindialms;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import static edu.thapar.newindialms.R.array.feedbacktype;



/**
 * A simple {@link Fragment} subclass.
 */
public class RemoveFeedbackFragment extends Fragment {
    View rootview;
    AlertDialog.Builder builder;

    String getfeedback_url = "https://newindialms.000webhostapp.com/get_feedback.php";
    RecyclerView recyclerView;
    FeedbackAdapter adapter;
    List<Feedback> feedbackList;
    SwipeRefreshLayout swipeRefreshLayout;
    public RemoveFeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview=inflater.inflate(R.layout.show_feedback_layout, container, false);

        swipeRefreshLayout=(SwipeRefreshLayout)rootview.findViewById(R.id.showfeedback_swipe);

        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);

        recyclerView = (RecyclerView) rootview.findViewById(R.id.feedback_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        feedbackList = new ArrayList<>();
        loadFeedBack();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                feedbackList.clear();
                loadFeedBack();
            }
        });
        return rootview;
    }

    public void loadFeedBack() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getfeedback_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                Feedback feedback = new Feedback(
                                        obj.getString("id"),
                                        obj.getString("feedback_title"),
                                        obj.getString("feedback_question"),
                                        obj.getString("feedback_type")

                                );

                                feedbackList.add(feedback);
                            }

                            adapter = new FeedbackAdapter(feedbackList, getActivity());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            swipeRefreshLayout.setRefreshing(false);
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}