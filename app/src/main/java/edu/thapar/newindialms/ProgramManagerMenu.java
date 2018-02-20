package edu.thapar.newindialms;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
    Fragment fragment=null;

    AlertDialog.Builder builder;

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

    public void homescreen_notification_layout(View view){
        Intent intent = new Intent(getApplicationContext(),NotificationScreen.class);
        startActivity(intent);
    }
    public void homescreen_courselist_layout(View view){
        displaySelectedScreen(R.id.navigation_program_courselist);
    }
    public void homescreen_calendar_layout(View view){
        displaySelectedScreen(R.id.navigation_program_calendar);
    }
    public void homescreen_feedback_layout(View view){
        Intent intent = new Intent(getApplicationContext(),FeedbackTab.class);
        startActivity(intent);
    }
    public void homescreen_logout_layout(View view){
        builder=new AlertDialog.Builder(ProgramManagerMenu.this, R.style.MyAlertDialogStyle);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to Logout?");
        displayAlert();
    }


//Calendar
    public void GoToCalendar(View v){
        Intent intent = new Intent(getApplicationContext(),OpenCalendar.class);
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
                addcourseintent.putExtra("openfragment", "0");
                startActivity(addcourseintent);
                break;
            case R.id.navigation_program_removecourse:
                Intent removecourseintent = new Intent(getApplicationContext(), AddCourseTab.class);
                removecourseintent.putExtra("openfragment", "1");
                startActivity(removecourseintent);
                break;
            case R.id.navigation_program_enrollstudent:
                Intent intent = new Intent(getApplicationContext(), ViewStudentsTab.class);
                startActivity(intent);
                break;
            case R.id.navigation_program_enrollfaculty:
                Intent facultyintent = new Intent(getApplicationContext(), FacultyTab.class);
                startActivity(facultyintent);
                break;
            case R.id.navigation_program_feedback:
                Intent feedbackintent = new Intent(getApplicationContext(), FeedbackTab.class);
                startActivity(feedbackintent);
                break;
            case R.id.navigation_program_notification:
                Intent notificationintent = new Intent(getApplicationContext(), NotificationScreen.class);
                startActivity(notificationintent);
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
            builder=new AlertDialog.Builder(ProgramManagerMenu.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Logout");
            builder.setMessage("Do you want to Logout?");
            displayAlert();
        }
        return true;
    }

    public void displayAlert() {
        builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
               dialoginterface.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.about_us_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialoginterface, int i) {
                Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
                SharedPrefManager.getInstance(ProgramManagerMenu.this).logout();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
