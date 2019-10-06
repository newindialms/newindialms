package edu.thapar.newindialms;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class RemoveCourseAdapter extends ArrayAdapter<RemoveCourseListItems> {

    //the list values in the List of type hero
    List<RemoveCourseListItems> removeCourseListItems;
    private AlertDialog.Builder builder;
    //activity context
    private Context context;

    //the layout resource file for the list items
    private int resource;

    private String removecourselist_url = "https://www.newindialms.com/get_removecourselist.php";
    RemoveCourseAdapter adapter;


    //constructor initializing the values
    public RemoveCourseAdapter(Context context, int resource, List<RemoveCourseListItems> removeCourseListItems) {
        super(context, resource, removeCourseListItems);
        this.context = context;
        this.resource = resource;
        this.removeCourseListItems = removeCourseListItems;
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
        TextView removecourselist_name = (TextView) view.findViewById(R.id.removecourselist_name);
        TextView removecourselist_code = (TextView) view.findViewById(R.id.removecourselist_code);
        ImageView studentdeletearrow = (ImageView) view.findViewById(R.id.studentdeletearrow);


        //getting the hero of the specified position
        final RemoveCourseListItems hero = removeCourseListItems.get(position);

        //adding values to the list item
        removecourselist_name.setText(hero.getRemovecoursename());
        removecourselist_code.setText(hero.getRemovecoursecode());
        studentdeletearrow.setImageResource(R.drawable.ic_delete);

        studentdeletearrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                String coursename = hero.getRemovecoursename();
                removeCourse(position, coursename);
            }
        });

        //finally returning the view
        return view;
    }

    //this method will remove the item from the list
    private void removeCourse(final int position, final String removecoursename) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                removeCourseListItems.remove(position);
                removeCoursefromDB(removecoursename);
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


    private void removeCoursefromDB(final String removecoursename) {
        builder = new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, removecourselist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
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
                params.put("course_details_name", removecoursename);
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