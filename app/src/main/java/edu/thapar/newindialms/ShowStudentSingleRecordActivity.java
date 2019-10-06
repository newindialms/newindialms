package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/4/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ShowStudentSingleRecordActivity extends AppCompatActivity {

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter Student Data from Id Sent from previous activity.
    private String HttpURL = "https://www.newindialms.com/FilterStudentData.php";

    // Http URL for delete Already Open Student Record.
    private String HttpUrlDeleteRecord = "https://www.newindialms.com/DeleteStudent.php";
    private Toolbar toolbar_delete_students;
    private String finalResult;
    HashMap<String, String> hashMap = new HashMap<>();
    private String ParseResult;
    HashMap<String, String> ResultHash = new HashMap<>();
    private String FinalJSonObject;
    private TextView FIRSTNAME, PHONE_NUMBER, ROLLNO;
    private String FirstNameHolder, lastNameHolder, PhoneHolder, RollnoHolder;
    private Button DeleteButton;
    private String TempItem;
    private ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        toolbar_delete_students = (Toolbar) findViewById(R.id.toolbar_delete_students);
        toolbar_delete_students.setNavigationIcon(R.drawable.ic_left);
        setSupportActionBar(toolbar_delete_students);
        toolbar_delete_students.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        FIRSTNAME = (TextView) findViewById(R.id.textFirstName);
        PHONE_NUMBER = (TextView) findViewById(R.id.textPhone);
        ROLLNO = (TextView) findViewById(R.id.textRollno);

        DeleteButton = (Button) findViewById(R.id.buttonDelete);

        //Receiving the ListView Clicked item value send by previous activity.
        TempItem = getIntent().getStringExtra("ListViewValue");

        //Calling method to filter Student Record and open selected record.
        HttpWebCall(TempItem);

        // Add Click listener on Delete button.
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling Student delete method to delete current record using Student ID.
                StudentDelete(TempItem);

            }
        });

    }

    // Method to Delete Student Record
    public void StudentDelete(final String StudentID) {

        class StudentDeleteClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog2 = ProgressDialog.show(ShowStudentSingleRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog2.dismiss();

                //Toast.makeText(ShowStudentSingleRecordActivity.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

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

        studentDeleteClass.execute(StudentID);
    }


    //Method to show current record Current Selected Record
    public void HttpWebCall(final String PreviousListViewClickedItem) {

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(ShowStudentSingleRecordActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(ShowStudentSingleRecordActivity.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("id", params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        public GetHttpResponse(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                if (FinalJSonObject != null) {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing Student Name, Phone Number, Class into Variables.
                            FirstNameHolder = jsonObject.getString("student_firstname").toString();
                            lastNameHolder = jsonObject.getString("student_lastname").toString();
                            PhoneHolder = jsonObject.getString("student_phone").toString();
                            RollnoHolder = jsonObject.getString("student_rollnno").toString();

                        }
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            FIRSTNAME.setText(lastNameHolder + " " + FirstNameHolder);
            PHONE_NUMBER.setText(PhoneHolder);
            ROLLNO.setText(RollnoHolder);

        }
    }

}