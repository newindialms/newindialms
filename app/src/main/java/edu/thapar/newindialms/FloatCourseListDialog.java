package edu.thapar.newindialms;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamalshree on 9/28/2017.
 */

public class FloatCourseListDialog extends DialogFragment {

    LayoutInflater inflater;
    private Context context;
    View view;
    String courselist_url = "https://newindialms.000webhostapp.com/add_courselist.php";
    EditText floatcourse_title, floatcourse_faculty;
    String coursetitle, coursefaculty;
    AlertDialog.Builder builder;
    CourseListAdapter courseListAdapter;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.dialog_addcourselist,null);

        floatcourse_title=(EditText)view.findViewById(R.id.floatingdialog_course);
        floatcourse_faculty=(EditText)view.findViewById(R.id.floatingdialog_faculty);
        builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);


        builder.setView(view).setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            Activity activity = getActivity();
            @Override

            public void onClick(final DialogInterface dialogInterface, int i) {
              //submit data//
                coursetitle=floatcourse_title.getText().toString();
                coursefaculty=floatcourse_faculty.getText().toString();

                if (coursetitle.equals("") || coursefaculty.equals("")){
                    Toast.makeText(getActivity(),"Missing fields", Toast.LENGTH_LONG).show();
                }
                else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, courselist_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                if (code.equals("Success")) {

                                   Toast.makeText(activity,"Course List Inserted Successfully", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("coursetitle", coursetitle);
                            params.put("coursefaculty", coursefaculty);
                            return params;
                        }
                    };
                    MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
                    //End of submit data//
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

}
