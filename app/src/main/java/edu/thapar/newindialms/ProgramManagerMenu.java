package edu.thapar.newindialms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class ProgramManagerMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button ShowStudents;
    String app_server_url= "https://newindialms.000webhostapp.com/fcm_insert.php";
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_manager_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }
    //Alert Dialog for the Course list floating button//
    public void ShowCourselistDialog(View v){
        FloatCourseListDialog floatCourseListDialog=new FloatCourseListDialog();
        floatCourseListDialog.show(getFragmentManager(),"my_courselistdialog");
    }

    //notification student list
    public void ShowenrollstudentList(View v){
        Intent intent = new Intent(getApplicationContext(),ShowAllNotificationActivity.class);
        startActivity(intent);
    }

    public void homescreen_notification_layout(View view){
        Intent intent = new Intent(getApplicationContext(),ShowAllNotificationActivity.class);
        startActivity(intent);
    }
    public void homescreen_courselist_layout(View view){
        displaySelectedScreen(R.id.navigation_program_courselist);
    }
    public void homescreen_calendar_layout(View view){
        displaySelectedScreen(R.id.navigation_program_calendar);
    }
    public void homescreen_feedback_layout(View view){
        Intent intent = new Intent(getApplicationContext(),ShowFeedbackActivity.class);
        startActivity(intent);
    }
    public void homescreen_logout_layout(View view){
        Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
        SharedPrefManager.getInstance(ProgramManagerMenu.this).logout();
        this.finish();
        this.startActivity(new Intent(this,LoginScreen.class));
    }

    //Notification firebase


    public void ShowNotification(View v){
        String ff = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        final String token=sharedPreferences.getString(getString(R.string.FCM_TOKEN),ff);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fcm_token", token);
                return params;
            }
        };
        MySingleton.getInstance(ProgramManagerMenu.this).addToRequestQueue(stringRequest);
    }

//Calendar
    public void GoToCalendar(View v){
        Intent intent = new Intent(getApplicationContext(),OpenCalendar.class);
        startActivity(intent);
    }

    //Feedback

    public void AddFeedback(View v){
        Intent intent = new Intent(getApplicationContext(),AddFeedbackActivity.class);
        startActivity(intent);
    }
    public void ShowFeedback(View v){
        Intent intent = new Intent(getApplicationContext(),ShowFeedbackActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void displaySelectedScreen(int id){
        Fragment fragment=null;
        switch(id){
            case R.id.navigation_program_home:
                fragment=new ProgramManagerHome();
                break;
            case R.id.navigation_program_courselist:
                fragment=new ProgramManagerCourseList();
                break;
            case R.id.navigation_program_schedule:
                fragment=new ProgramManagerSchedule();
                break;
            case R.id.navigation_program_calendar:

                fragment=new ProgramManagerCalendar();
                break;
            case R.id.navigation_program_picbook:

                fragment=new ProgramManagerStudentPic();
                break;
            case R.id.navigation_program_addcourse:
                Intent addcourseintent = new Intent(getApplicationContext(), AddCourseTab.class);
                startActivity(addcourseintent);
                break;
            case R.id.navigation_program_removecourse:
                fragment=new ProgramManagerRemoveCourse();
                break;
            case R.id.navigation_program_enrollstudent:
                Intent intent = new Intent(getApplicationContext(), ViewStudentsTab.class);
                startActivity(intent);
                break;
            case R.id.navigation_program_enrollfaculty:
                fragment=new ProgramManagerEnrollFaculty();
                break;
            case R.id.navigation_program_feedback:
                fragment=new ProgramManagerFeedbackModule();
                break;
            case R.id.navigation_program_notification:
                fragment=new ProgramManagerNotification();
                break;
        }

        if(fragment!=null){
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.courselist_fragment,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id!= R.id.navigation_program_logout) {
            displaySelectedScreen(id);
        }
        else{
            Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
            SharedPrefManager.getInstance(ProgramManagerMenu.this).logout();
            this.finish();
            this.startActivity(new Intent(this,LoginScreen.class));
        }
        return true;
    }
}
