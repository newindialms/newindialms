package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by kamalshree on 2/21/2018.
 */

public class FacultySelectFeedbackScreen extends AppCompatActivity {

    Toolbar facultyToolbar;
    private static final String FEEDBACK_URL = "http://newindialms.000webhostapp.com/get_feedback.php";
    private RecyclerView recyclerView;
    TextView facultyToolbarTitle;
    List<FacultySelectFeedbackScreenDetails> facultySelectFeedbackScreenDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_select_feedbackscreen);

        facultyToolbar = (Toolbar) findViewById(R.id.facultycourselist_toolbar);
        facultyToolbar.setNavigationIcon(R.drawable.ic_left);
        facultyToolbarTitle = (TextView) findViewById(R.id.facultydashboard_toolbar_title);
        facultyToolbarTitle.setText("Select Feedback");

        setSupportActionBar(facultyToolbar);
        facultyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.faculty_select_feedbackscreen_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));

        facultySelectFeedbackScreenDetails = new ArrayList<>();
        LoadFeedbackQuestions();
    }

    private void LoadFeedbackQuestions() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, FEEDBACK_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                FacultySelectFeedbackScreenDetails listItemProgramList = new FacultySelectFeedbackScreenDetails(
                                        obj.getString("feedback_title"),
                                        obj.getString("feedback_question"),
                                        obj.getString("feedback_type")
                                );
                                facultySelectFeedbackScreenDetails.add(listItemProgramList);


                            }
                            FacultySelectFeedbackScreenAdapter adapter = new FacultySelectFeedbackScreenAdapter(FacultySelectFeedbackScreen.this, facultySelectFeedbackScreenDetails);
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
        Volley.newRequestQueue(FacultySelectFeedbackScreen.this).add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_feedback_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.feedbacksubmit) {
            sendFeedbackQuestions();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendFeedbackQuestions() {
        Toast.makeText(FacultySelectFeedbackScreen.this, "Submit Selected", Toast.LENGTH_LONG).show();

    }

}
