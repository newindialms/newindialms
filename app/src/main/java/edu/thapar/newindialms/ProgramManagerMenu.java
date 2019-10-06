package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ProgramManagerMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;

    private AlertDialog.Builder builder;

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

    public void homescreen_notification_layout(View view) {
        Intent intent = new Intent(getApplicationContext(), NotificationScreen.class);
        startActivity(intent);
    }

    public void homescreen_courselist_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_courselist);
    }

    public void homescreen_calendar_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_calendar);
    }

    public void homescreen_feedback_layout(View view) {
        Intent intent = new Intent(getApplicationContext(), FeedbackTab.class);
        startActivity(intent);
    }

    public void homescreen_logout_layout(View view) {
        builder = new AlertDialog.Builder(ProgramManagerMenu.this, R.style.MyAlertDialogStyle);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to Logout?");
        displayAlert();
    }


    //Calendar
   /* public void GoToCalendar(View v) {
        Intent intent = new Intent(getApplicationContext(), AcademicCalendar.class);
        startActivity(intent);
    }*/

    public void GoToFirstYearSchedule(View v) {
        Intent intent = new Intent(getApplicationContext(), ProgramManagerFirtYearSchedule.class);
        startActivity(intent);
    }

    public void GoToSecondYearSchedule(View v) {
        // fragment=new ProgramManagerSchedule();
        Intent intent = new Intent(getApplicationContext(), ProgramManagerSchedule.class);
        startActivity(intent);
    }
    public void GoToFirstYearCourses(View v) {
        Intent intent = new Intent(getApplicationContext(), ProgramManagerCourseList.class);
        intent.putExtra("year", "1");
        startActivity(intent);
    }

    public void GoToSecondYearCourses(View v) {
        // fragment=new ProgramManagerSchedule();
        Intent intent = new Intent(getApplicationContext(), ProgramManagerCourseList.class);
        intent.putExtra("year", "2");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
            drawer.openDrawer(GravityCompat.START);
        }
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_program_home:
                fragment = new ProgramManagerHome();
                break;
            case R.id.navigation_program_courselist:
                fragment = new ProgramManagerCourseSelector();
                break;
            case R.id.navigation_program_schedule:
                fragment = new ProgramManagerSelector();
                break;
            case R.id.navigation_program_calendar:
                fragment = new ProgramManagerCalendar();
                break;
            case R.id.navigation_program_picbook:
                fragment = new ProgramManagerStudentPic();
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

        if (fragment != null) {
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.courselist_fragment, fragment);
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

        if (id != R.id.navigation_program_logout) {
            displaySelectedScreen(id);
        } else {
            builder = new AlertDialog.Builder(ProgramManagerMenu.this, R.style.MyAlertDialogStyle);
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
                SharedPrefManager.getInstance(ProgramManagerMenu.this).logout();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginScreen.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
