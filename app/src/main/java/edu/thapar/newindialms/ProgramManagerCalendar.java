package edu.thapar.newindialms;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.ghyeok.stickyswitch.widget.StickySwitch;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgramManagerCalendar extends Fragment {
    private String calendar_val,CalendarVal;
    private String calendarlist_url = "https://www.newindialms.com/save_calendar.php";
    private String calendarValDetails_Url = "https://www.newindialms.com/get_calendarValdetails.php";
    private  StickySwitch stickySwitch;
    public ProgramManagerCalendar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_program_manager_calendar, container, false);
        stickySwitch = rootview.findViewById(R.id.sticky_switch);
        Button calendar_button = rootview.findViewById(R.id.calendar_button);
        loadCalendarValDetails();


        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String SwitchText) {
                calendar_val=SwitchText;
                //Toast.makeText(getContext(), SwitchText, Toast.LENGTH_SHORT).show();
                saveCalendarValue();

            }
        });
        // Inflate the layout for this fragment

        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AcademicCalendar.class);
                intent.putExtra("CalendarVal", CalendarVal);
                startActivity(intent);
            }
        });
        return rootview;
    }



    private void saveCalendarValue() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, calendarlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadCalendarValDetails();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("calendar_val", calendar_val);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
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
                            if(CalendarVal.equals("Spring")){
                                stickySwitch.setDirection(StickySwitch.Direction.RIGHT);
                            }
                            else{
                                stickySwitch.setDirection(StickySwitch.Direction.LEFT);
                            }


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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.navigation_program_calendar));
    }

}
