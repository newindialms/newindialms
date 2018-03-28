package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
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

public class StudentNotification extends Fragment{

    private View rootView;
    private RecyclerView recyclerView;
    List<NotificationScreenDetails> notificationScreenDetails;

    private static final String NOTIFICATION_URL = "http://newindialms.000webhostapp.com/get_student_notification.php";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student_noticationscreen, null);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.student_notification_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        LoadNotifications();
        notificationScreenDetails = new ArrayList<>();
        return rootView;
    }

    private void LoadNotifications() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, NOTIFICATION_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject j = new JSONObject(response);
                            JSONArray array = j.getJSONArray("student_notification_list");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                NotificationScreenDetails listItemProgramList = new NotificationScreenDetails(
                                        jsonObject1.getString("notification_title"),
                                        jsonObject1.getString("notification_message"),
                                        jsonObject1.getString("notification_date")
                                );
                                notificationScreenDetails.add(listItemProgramList);


                            }
                            StudentNotificationScreenAdapter adapter = new StudentNotificationScreenAdapter(getActivity(), notificationScreenDetails);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_notification));
    }
}