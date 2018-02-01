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

import static edu.thapar.newindialms.R.id.removecourselist_name;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrolledCourseAdapter extends ArrayAdapter<EnrolledCourseListItems> {

    //the list values in the List of type hero
    List<EnrolledCourseListItems> enrolledCourseListItemses;
    AlertDialog.Builder builder;
    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;


    //constructor initializing the values
    public EnrolledCourseAdapter(Context context, int resource, List<EnrolledCourseListItems> enrolledCourseListItems) {
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


        //getting the hero of the specified position
        final EnrolledCourseListItems hero = enrolledCourseListItemses.get(position);

        //adding values to the list item
        enrolledcourselist_name.setText(hero.getEnrolledcoursename());


        //finally returning the view
        return view;
    }
}