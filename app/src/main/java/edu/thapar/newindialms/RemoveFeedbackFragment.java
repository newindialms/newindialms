package edu.thapar.newindialms;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
public class RemoveFeedbackFragment extends Fragment {
    View rootview;
    private AlertDialog.Builder builder;

    private String getfeedback_url = "https://www.newindialms.com/get_feedback.php";
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
        rootview = inflater.inflate(R.layout.show_feedback_layout, container, false);

        swipeRefreshLayout = rootview.findViewById(R.id.showfeedback_swipe);

        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);

        recyclerView = rootview.findViewById(R.id.feedback_recyclerView);
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