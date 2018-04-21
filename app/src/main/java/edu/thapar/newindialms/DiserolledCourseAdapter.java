package edu.thapar.newindialms;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class DiserolledCourseAdapter extends ArrayAdapter<EnrolledCourseListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseListItems> enrolledCourseListItemses;
    //activity context
    private Context context;
    private AlertDialog.Builder builder;
    //the layout resource file for the list items
    private String removecourselist_url = "https://newindialms.000webhostapp.com/disenrolledcourses.php";
    int resource;


    //constructor initializing the values
    public DiserolledCourseAdapter(Context context, int resource, List<EnrolledCourseListItems> enrolledCourseListItems) {
        super(context, resource, enrolledCourseListItems);
        this.context = context;
        this.resource = resource;
        this.enrolledCourseListItemses = enrolledCourseListItems;
    }

    //this will return the ListView Item as a View
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView enrolledcourselist_name = (TextView) view.findViewById(R.id.enrolledcourselist_name);
        RelativeLayout relative1 = (RelativeLayout) view.findViewById(R.id.relative1);
        ImageView cancelcourselist_name = (ImageView) view.findViewById(R.id.studentcancel);


        //getting the hero of the specified position
        final EnrolledCourseListItems hero = enrolledCourseListItemses.get(position);

        //adding values to the list item
        enrolledcourselist_name.setText(hero.getEnrolledcoursename());
        cancelcourselist_name.setImageResource(R.drawable.cancel);

        relative1.setOnClickListener(new View.OnClickListener() {
            String coursename = hero.getEnrolledcoursename();
            String student_rollno = hero.getStudentid();

            @Override
            public void onClick(View view) {
                removeCourse(position, coursename, student_rollno);

            }
        });
        //finally returning the view
        return view;
    }

    private void removeCourse(final int position, final String removecoursename, final String student_rollno) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                enrolledCourseListItemses.remove(position);
                removeCoursefromDB(removecoursename, student_rollno);
                //remove from database

                //reloading the list
                notifyDataSetChanged();
            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void removeCoursefromDB(final String removecoursename, final String student_rollno) {
        builder = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, removecourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    //JSONObject j = new JSONObject(response);
                    JSONObject array = jsonArray.getJSONObject(0);
                    String code = array.getString("code");
                    if (code.equals("Deleted")) {
                        builder.setTitle("Deleted");
                        builder.setMessage("Course Deleted successfully");
                        displayAlert("Deleted");
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("courses_disenrolled", removecoursename);
                params.put("student_rollno", student_rollno);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("Deleted")) {

                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}