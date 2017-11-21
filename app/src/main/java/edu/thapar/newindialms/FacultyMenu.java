package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static edu.thapar.newindialms.R.id.faculty_toolbar_id;
import static edu.thapar.newindialms.R.id.faculty_toolbar_name;
import static edu.thapar.newindialms.R.layout.nav_header_faculty_menu;

/**
 * Created by kamalshree on 11/13/2017.
 */

public class FacultyMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment=null;
    String facultyname,facultyid;
    TextView faculty_toolbar_name,faculty_toolbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.faculty_toolbar);
        setSupportActionBar(toolbar);

        facultyname = getIntent().getStringExtra("facultyname");
        facultyid = getIntent().getStringExtra("facultyid");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.faculty_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.faculty_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        faculty_toolbar_name=(TextView)header.findViewById(R.id.faculty_toolbar_name);
        faculty_toolbar_id=(TextView)header.findViewById(R.id.faculty_toolbar_id);
        faculty_toolbar_name.setText(facultyname);
        faculty_toolbar_id.setText(facultyid);


    }
    public void homescreen_schedule_layout(View view){
        displaySelectedScreen(R.id.navigation_faculty_program_schedule);
    }
    public String getEmployeeid() {
        return facultyid;
    }
    public void homescreen_courselist_layout(View view){
        displaySelectedScreen(R.id.navigation_faculty_program_courselist);
    }

    public void homescreen_logout_layout(View view){
        Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
        SharedPrefManager.getInstance(FacultyMenu.this).logout();
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
            case R.id.navigation_faculty_program_home:
                fragment=new FacultyHome();
                break;
            case R.id.navigation_faculty_program_courselist:
                fragment=new FacultyCourseList();
                break;
            case R.id.navigation_faculty_program_schedule:
                fragment=new FacultySchedule();
                break;
            case R.id.navigation_program_calendar:
                fragment=new ProgramManagerCalendar();
                break;
            case R.id.navigation_program_notification:
                fragment=new ProgramManagerStudentPic();
                break;
        }

        if(fragment!=null){
            RelativeLayout layout = (RelativeLayout)findViewById(R.id.faculty_courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.faculty_courselist_fragment,fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.faculty_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id!= R.id.navigation_faculty_program_logout) {
            displaySelectedScreen(id);
        }
        else{
            Toast.makeText(getApplicationContext(),"Logged out successfully",Toast.LENGTH_LONG).show();
            SharedPrefManager.getInstance(FacultyMenu.this).logout();
            this.finish();
            this.startActivity(new Intent(this,LoginScreen.class));
        }
        return true;
    }
}
