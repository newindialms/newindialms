package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/5/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShowAllFacultyActivity extends AppCompatActivity {
    ListView FacultyListView;
    private ProgressBar progressBar;
    private String HttpUrl = "https://www.newindialms.com/AllFacultyData.php";
    List<String> IdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_all_faculty);

        FacultyListView = findViewById(R.id.all_faculty_list);


        new GetHttpResponse(ShowAllFacultyActivity.this).execute();

        //Adding ListView Item click Listener.
        FacultyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(ShowAllFacultyActivity.this, ShowFacultySingleRecordActivity.class);

                // Sending ListView clicked value using intent.
                intent.putExtra("ListViewValue", IdList.get(position));

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
                                IdList.add(jsonObject.getString("facultydetails_ID"));

                                //Adding Faculty Name.
                                faculty.FacultyName = jsonObject.getString("faculty_firstname");
                                faculty.FacultyProgram = jsonObject.getString("faculty_program");
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

            FacultyListView.setVisibility(View.VISIBLE);

            ListFacultyAdapterClass adapter = new ListFacultyAdapterClass(facultyList, context);

            FacultyListView.setAdapter(adapter);

        }
    }
}