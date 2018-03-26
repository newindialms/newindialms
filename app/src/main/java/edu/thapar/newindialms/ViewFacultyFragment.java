package edu.thapar.newindialms;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/30/2017.
 */

public class ViewFacultyFragment extends Fragment {
    View rootview;
    ListView FacultyListView;
    private String HttpUrl = "https://newindialms.000webhostapp.com/AllFacultyData.php";
    List<String> IdList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    List<Faculty> facultyList;

    public ViewFacultyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.activity_show_all_faculty, container, false);

        swipeRefreshLayout=(SwipeRefreshLayout)rootview.findViewById(R.id.showfaculty_swipe);
        FacultyListView = (ListView) rootview.findViewById(R.id.all_faculty_list);


        new GetHttpResponse(getActivity()).execute();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                facultyList.clear();
                new GetHttpResponse(getActivity()).execute();
            }
        });

        return rootview;
    }


    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;



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
                                IdList.add(jsonObject.getString("facultydetails_ID").toString());

                                //Adding Faculty Name.
                                faculty.FacultyName = jsonObject.getString("faculty_firstname").toString();
                                faculty.FacultyProgram = jsonObject.getString("faculty_program").toString();
                                faculty.FacultyID = jsonObject.getString("faculty_employeeid").toString();
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