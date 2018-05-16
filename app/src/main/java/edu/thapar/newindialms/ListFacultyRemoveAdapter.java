package edu.thapar.newindialms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
 * Created by kamalshree on 10/30/2017.
 */

public class ListFacultyRemoveAdapter extends BaseAdapter {
    Context context;
    List<Faculty> valueList;
    String removefacultylist_url = "https://www.newindialms.com/DeleteFaculty.php";
    AlertDialog.Builder builder;
    public ListFacultyRemoveAdapter(List<Faculty> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewItemRemoveFaculty viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItemRemoveFaculty();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_removefaculty_item, null);

            viewItem.TextViewFacultyName = (TextView)convertView.findViewById(R.id.faculty_text_view);
            viewItem.TextViewFacultyRollno = (TextView)convertView.findViewById(R.id.facultyrollno_text_view);
            viewItem.TextViewFacultyCode= (TextView)convertView.findViewById(R.id.facultyCode_text_view);
            viewItem.TextViewFacultyspecialization = (TextView)convertView.findViewById(R.id.facultyspecialization_text_view);
            viewItem.FacultyBin = (ImageView) convertView.findViewById(R.id.facultydeletebin);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItemRemoveFaculty) convertView.getTag();
        }

        viewItem.TextViewFacultyName.setText(valueList.get(position).FacultyName);
        viewItem.TextViewFacultyRollno.setText(valueList.get(position).FacultyRollno);
        viewItem.TextViewFacultyCode.setText(valueList.get(position).FacultyCode);
        viewItem.TextViewFacultyspecialization.setText(valueList.get(position).FacultySpecialization);
        viewItem.FacultyBin.setImageResource(R.drawable.ic_delete);

        viewItem.FacultyBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String facultyID=valueList.get(position).FacultyID;
                removeFaculty(position,facultyID);
            }
        });
        return convertView;
    }

    private void removeFaculty(final int position,final String facultyID) {
        //Creating an alert dialog to confirm the deletion
       builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //removing the item
                valueList.remove(position);
                removeFacultyfromDB(facultyID);
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


    private void removeFacultyfromDB(final String removefacultyid) {
        builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, removefacultylist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONObject array = jsonArray.getJSONObject(0);
                    String code = array.getString("code");
                    if(code.equals("Deleted")){
                        builder.setTitle("Deleted");
                        builder.setMessage("Faculty Deleted successfully");
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("facultydetails_ID", removefacultyid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void displayAlert(final String code) {
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {

                if (code.equals("Deleted")) {
                    dialoginterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

class ViewItemRemoveFaculty
{
    TextView TextViewFacultyName,TextViewFacultyProgram,TextViewFacultyRollno,TextViewFacultyCode,TextViewFacultyspecialization;
    ImageView FacultyBin;

}