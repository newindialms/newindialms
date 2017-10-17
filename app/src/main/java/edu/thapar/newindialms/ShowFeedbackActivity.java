package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
 * Created by kamalshree on 10/9/2017.
 */

public class ShowFeedbackActivity extends AppCompatActivity {

    Toolbar showfeedbacktoolbar;
    AlertDialog.Builder builder;

    String getfeedback_url = "https://newindialms.000webhostapp.com/get_feedback.php";
    RecyclerView recyclerView;
    FeedbackAdapter adapter;
    List<Feedback> feedbackList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_feedback_layout);

       showfeedbacktoolbar = (Toolbar) findViewById(R.id.showfeedback_toolbar);
        showfeedbacktoolbar.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(showfeedbacktoolbar);

        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.showfeedback_swipe);


        builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);

        showfeedbacktoolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.feedback_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

                            adapter = new FeedbackAdapter(feedbackList, getApplicationContext());
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
