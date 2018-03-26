package edu.thapar.newindialms;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamalshree on 10/28/2017.
 */

public class NotificationTab extends AppCompatActivity {
    private Toolbar toolbar_all_notiifcation;
    private String notification_url = "https://newindialms.000webhostapp.com/get_notification.php";
    NotificationTabAdapter adapter;

    List<NotificationTabListItems> heroList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notification);

        toolbar_all_notiifcation = (Toolbar) findViewById(R.id.toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationIcon(R.drawable.ic_left);

        setSupportActionBar(toolbar_all_notiifcation);
        toolbar_all_notiifcation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.notification_listview);
        loadRecyclerViewData();


    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Refreshing Data");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, notification_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                try {
                    JSONObject j = new JSONObject(response);
                    JSONArray array = j.getJSONArray("notification");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject1 = array.getJSONObject(i);
                        NotificationTabListItems listItemProgramList = new NotificationTabListItems(
                                jsonObject1.getString("notification_title"),
                                jsonObject1.getString("notification_message"),
                                jsonObject1.getString("notification_date")
                        );
                        heroList.add(listItemProgramList);


                    }
                    adapter = new NotificationTabAdapter(getApplicationContext(),R.layout.listview_item_notificiation,heroList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}