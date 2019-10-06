package edu.thapar.newindialms;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamalshree on 9/26/2017.
 */

public class StudentHome extends Fragment {
    private String CalendarVal;
    private String calendarValDetails_Url = "https://www.newindialms.com/get_calendarValdetails.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loadCalendarValDetails();
        return inflater.inflate(R.layout.content_student_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.registration_radio2));

    }

    private void loadCalendarValDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarValDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            JSONObject jsonobject = jsonarray.getJSONObject(0);

                            CalendarVal = jsonobject.getString("calendar_val");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
