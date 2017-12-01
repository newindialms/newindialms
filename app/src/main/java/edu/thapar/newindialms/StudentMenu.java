package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static edu.thapar.newindialms.R.id.faculty_toolbar_id;

/**
 * Created by kamalshree on 11/13/2017.
 */

public class StudentMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment=null;
    String studentname,studentid,studentyear;
    TextView student_toolbar_name,student_toolbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.student_toolbar);
        setSupportActionBar(toolbar);

        studentname = getIntent().getStringExtra("studentname");
        studentid = getIntent().getStringExtra("studentid");
        studentyear = getIntent().getStringExtra("studentyear");

        if(studentyear.equals("2")) {
            NavigationView navigationView=(NavigationView)findViewById(R.id.student_nav_view);
            Menu menu =navigationView.getMenu();
            MenuItem target = menu.findItem(R.id.navigation_program_enrollcourse);
            target.setVisible(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.student_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.student_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        student_toolbar_name=(TextView)header.findViewById(R.id.student_toolbar_name);
        student_toolbar_id=(TextView)header.findViewById(R.id.student_toolbar_id);
        student_toolbar_name.setText(studentname);
        student_toolbar_id.setText(studentid);


    }
    public void student_myprofile_layout(View view){
        displaySelectedScreen(R.id.navigation_program_myprofile);
    }
    public void student_mycourse_layout(View view){
        displaySelectedScreen(R.id.navigation_program_mycourses);
    }
    public void student_calendar_layout(View view){
        displaySelectedScreen(R.id.navigation_program_academiccalendar);
    }
    public void student_schedule_layout(View view){
        displaySelectedScreen(R.id.navigation_program_myschedule);
    }

    public void homescreen_logout_layout(View view){
        Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
        SharedPrefManager.getInstance(StudentMenu.this).logout();
        this.finish();
        this.startActivity(new Intent(this,LoginScreen.class));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.faculty_drawer_layout);
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
                fragment=new StudentHome();
                break;
            case R.id.navigation_program_myprofile:
                fragment=new StudentMyProfile();
                break;
            case R.id.navigation_program_mycourses:
                fragment=new StudentMyCourse();
                break;
            case R.id.navigation_program_coursefeedback:
                fragment=new StudentCourseFeedback();
                break;
            case R.id.navigation_program_myschedule:
                fragment=new StudentSchedule();
                break;
            case R.id.navigation_program_academiccalendar:
                fragment=new StudentAcademicCalendar();
                break;
            case R.id.navigation_program_notification:
                fragment=new StudentNotification();
                break;
        }

        if(fragment!=null){
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.student_courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.student_courselist_fragment,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.student_drawer_layout);
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
            SharedPrefManager.getInstance(StudentMenu.this).logout();
            this.finish();
            this.startActivity(new Intent(this,LoginScreen.class));
        }
        return true;
    }
}
