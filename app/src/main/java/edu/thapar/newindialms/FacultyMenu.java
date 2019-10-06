package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kamalshree on 11/13/2017.
 */

public class FacultyMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;
    private String CalendarVal;
    private String facultyname, facultyid;
    private TextView faculty_toolbar_name, faculty_toolbar_id;
    private AlertDialog.Builder builder;
    private String calendarValDetails_Url = "https://www.newindialms.com/get_calendarValdetails.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_menu);
        Toolbar toolbar = findViewById(R.id.faculty_toolbar);
        setSupportActionBar(toolbar);
        loadCalendarValDetails();

        facultyname = getIntent().getStringExtra("facultyname");
        facultyid = getIntent().getStringExtra("facultyid");

        DrawerLayout drawer = findViewById(R.id.faculty_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.faculty_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        faculty_toolbar_name = header.findViewById(R.id.faculty_toolbar_name);
        faculty_toolbar_id = header.findViewById(R.id.faculty_toolbar_id);
        faculty_toolbar_name.setText(facultyname);
        faculty_toolbar_id.setText(facultyid);


    }

    public void homescreen_calendar_layout(View view) {
        displaySelectedScreen(R.id.navigation_faculty_program_accademic_calendar);
    }

    public void homescreen_schedule_layout(View view) {
        displaySelectedScreen(R.id.navigation_faculty_program_schedule);
    }

    public String getEmployeeid() {
        return facultyid;
    }

    public void homescreen_courselist_layout(View view) {
        displaySelectedScreen(R.id.navigation_faculty_program_courselist);
    }

    public void homescreen_logout_layout(View view) {
        builder = new AlertDialog.Builder(FacultyMenu.this, R.style.MyFacultyAlertDialogStyle);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to Logout?");
        displayAlert();
    }

    public void homescreen_notification_layout(View view) {
        displaySelectedScreen(R.id.navigation_faculty_program_notification);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.faculty_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_faculty_program_home:
                fragment = new FacultyHome();
                break;
            case R.id.navigation_faculty_program_courselist:
                fragment = new FacultyCourseList();
                break;
            case R.id.navigation_faculty_program_schedule:
                fragment = new FacultySchedule();
                break;
            case R.id.navigation_faculty_program_accademic_calendar:
                Bundle bundle = new Bundle();
                bundle.putString("CalendarVal", CalendarVal);
                fragment = new FacultyAccademicCalendar();
                fragment.setArguments(bundle);
                break;
            case R.id.navigation_faculty_program_notification:
                Intent notificationitent = new Intent(getApplicationContext(), FacultyNotificationScreen.class);
                startActivity(notificationitent);
                break;
        }

        if (fragment != null) {
            RelativeLayout layout = findViewById(R.id.faculty_courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.faculty_courselist_fragment, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.faculty_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id != R.id.navigation_faculty_program_logout) {
            displaySelectedScreen(id);
        } else {
            builder = new AlertDialog.Builder(FacultyMenu.this, R.style.MyFacultyAlertDialogStyle);
            builder.setTitle("Logout");
            builder.setMessage("Do you want to Logout?");
            displayAlert();
        }
        return true;
    }

    public void displayAlert() {
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                dialoginterface.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_LONG).show();
                SharedPrefManager.getInstance(FacultyMenu.this).logout();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadCalendarValDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, calendarValDetails_Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonarray = new JSONArray(response);

                            JSONObject jsonobject = jsonarray.getJSONObject(0);

                            CalendarVal = jsonobject.getString("calendar_val");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {

                            Toast.makeText(FacultyMenu.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
