package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainScreen extends AppCompatActivity implements View.OnClickListener {
    private Button register_button;
    private Button login_button;
    private String deviceid, token;
    public static final String token_url = "http://newindialms.000webhostapp.com/device_details.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        token = FirebaseInstanceId.getInstance().getToken();
        deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        AddTokenFunction();
//        Toast.makeText(MainScreen.this,FirebaseInstanceId.getInstance().getToken(),Toast.LENGTH_LONG).show();
//        Toast.makeText(MainScreen.this,deviceid,Toast.LENGTH_LONG).show();

        /*if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            // logged in
            finish();
            startActivity(new Intent(this, Dashboard.class));
            return;
        }*/

        login_button = (Button) findViewById(R.id.mainscreen_login);
        register_button = (Button) findViewById(R.id.mainscreen_register);

        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mainscreen_login:
                Intent intent_login = new Intent(MainScreen.this, LoginScreen.class);
                startActivity(intent_login);
                break;

            case R.id.mainscreen_register:
                Intent intent_register = new Intent(MainScreen.this, RegisterScreen.class);
                startActivity(intent_register);
                break;
        }
    }

    public void AddTokenFunction() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, token_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Congratulations you have successfully installed NewIndiaLMS App.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);
                params.put("deviceid", deviceid);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
