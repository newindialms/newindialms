package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/5/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ShowFacultySingleRecordActivity extends AppCompatActivity {

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter Student Data from Id Sent from previous activity.
    private String HttpURL = "https://newindialms.000webhostapp.com/FilterFacultyData.php";

    // Http URL for delete Already Open Student Record.
    private String HttpUrlDeleteRecord = "https://newindialms.000webhostapp.com/DeleteFaculty.php";
    private Toolbar toolbar_delete_faculty;
    private String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    private String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    private String FinalJSonObject ;
    TextView FIRSTNAME,DESIGNATION,ROLLNO;
    private String FirstNameHolder, Designation, RollnoHolder;
    private Button DeleteButton;
    private String TempItem;
    private ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_faculty_record);

        toolbar_delete_faculty = (Toolbar) findViewById(R.id.toolbar_delete_faculty);
        toolbar_delete_faculty.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_delete_faculty);
        toolbar_delete_faculty.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });


        FIRSTNAME = (TextView)findViewById(R.id.textFirstName);
        DESIGNATION = (TextView)findViewById(R.id.textDesignation);
        ROLLNO = (TextView)findViewById(R.id.textRollno);

        DeleteButton = (Button)findViewById(R.id.buttonDeleteFaculty);

        //Receiving the ListView Clicked item value send by previous activity.
        TempItem = getIntent().getStringExtra("ListViewValue");

        //Calling method to filter Student Record and open selected record.
        HttpWebCall(TempItem);

        // Add Click listener on Delete button.
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling Student delete method to delete current record using Student ID.
                FacultyDelete(TempItem);

            }
        });

    }

    // Method to Delete Student Record
    public void FacultyDelete(final String FacultyID) {

        class StudentDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(ShowFacultySingleRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                Toast.makeText(ShowFacultySingleRecordActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

                finish();

            }

            @Override
            protected String doInBackground(String... params) {

                // Sending STUDENT id.
                hashMap.put("id", params[0]);

                finalResult = httpParse.postRequest(hashMap, HttpUrlDeleteRecord);

                return finalResult;
            }
        }

        StudentDeleteClass studentDeleteClass = new StudentDeleteClass();

        studentDeleteClass.execute(FacultyID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowFacultySingleRecordActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(ShowFacultySingleRecordActivity.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("id",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing Student Name, Phone Number, Class into Variables.
                            FirstNameHolder = jsonObject.getString("firstname").toString() ;
                            Designation = jsonObject.getString("designation").toString() ;
                            RollnoHolder = jsonObject.getString("rollno").toString() ;

                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            FIRSTNAME.setText(FirstNameHolder);
            DESIGNATION.setText(Designation);
            ROLLNO.setText(RollnoHolder);

        }
    }

}
