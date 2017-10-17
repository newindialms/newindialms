package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/5/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShowAllFacultyActivity extends AppCompatActivity {
    Toolbar toolbar_all_faculty;
    ListView FacultyListView;
    ProgressBar progressBar;
    String HttpUrl = "https://newindialms.000webhostapp.com/AllFacultyData.php";
    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all_faculty);
        toolbar_all_faculty = (Toolbar) findViewById(R.id.toolbar_all_faculty);

        toolbar_all_faculty.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_all_faculty);
        toolbar_all_faculty.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });

        FacultyListView = (ListView) findViewById(R.id.all_faculty_list);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new GetHttpResponse(ShowAllFacultyActivity.this).execute();

        //Adding ListView Item click Listener.
        FacultyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(ShowAllFacultyActivity.this, ShowFacultySingleRecordActivity.class);

                // Sending ListView clicked value using intent.
                intent.putExtra("ListViewValue", IdList.get(position).toString());

                startActivity(intent);

                //Finishing current activity after open next activity.
                finish();

            }
        });
    }

    // JSON parse class started from here.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        List<Faculty> facultyList;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Passing HTTP URL to HttpServicesClass Class.
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try {
                httpServicesClass.ExecutePostRequest();

                if (httpServicesClass.getResponseCode() == 200) {
                    JSonResult = httpServicesClass.getResponse();

                    if (JSonResult != null) {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            Faculty faculty;

                            facultyList = new ArrayList<Faculty>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                faculty = new Faculty();

                                jsonObject = jsonArray.getJSONObject(i);

                                // Adding Faculty Id TO IdList Array.
                                IdList.add(jsonObject.getString("id").toString());

                                //Adding Faculty Name.
                                faculty.FacultyName = jsonObject.getString("firstname").toString();

                                facultyList.add(faculty);

                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.GONE);

            FacultyListView.setVisibility(View.VISIBLE);

            ListFacultyAdapterClass adapter = new ListFacultyAdapterClass(facultyList, context);

            FacultyListView.setAdapter(adapter);

        }
    }
}