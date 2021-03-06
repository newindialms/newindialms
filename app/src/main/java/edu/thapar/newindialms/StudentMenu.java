package edu.thapar.newindialms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kamalshree on 11/13/2017.
 */

public class StudentMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;
    private String CalendarVal;
    private String studentname, studentlastname, studentid, studentyear, student_specialization;
    private TextView student_toolbar_name, student_toolbar_id;
    private AlertDialog.Builder builder;
    private String enroll_dates = "https://www.newindialms.com/get_enrolldate.php";
    private String calendarValDetails_Url = "https://www.newindialms.com/get_calendarValdetails.php";
    String from_date;// mm/dd/yyyy
    String to_date;

    Boolean your_date_is_outdated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        Toolbar toolbar = findViewById(R.id.student_toolbar);
        setSupportActionBar(toolbar);
        loadCalendarValDetails();
        studentname = getIntent().getStringExtra("studentname");
        studentlastname = getIntent().getStringExtra("studentlastname");
        studentid = getIntent().getStringExtra("studentid");
        studentyear = getIntent().getStringExtra("studentyear");
        student_specialization = getIntent().getStringExtra("student_specialization");
        from_date = getIntent().getStringExtra("from_date").replaceAll("\\\\/", "/");
        to_date = getIntent().getStringExtra("to_date").replaceAll("\\\\/", "/");
        //Toast.makeText(StudentMenu.this,"Specialization"+student_specialization,Toast.LENGTH_LONG).show();

        if (studentyear.equals("2")) {
            NavigationView navigationView = findViewById(R.id.student_nav_view);
            Menu menu = navigationView.getMenu();
            MenuItem target = menu.findItem(R.id.navigation_program_enrollcourse);
            MenuItem targetspecialization = menu.findItem(R.id.navigation_program_specialization);
            MenuItem mspecialization = menu.findItem(R.id.navigation_program_myspecialization);
            //target.setVisible(true);
            //targetspecialization.setVisible(true);

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
           try {
               //Toast.makeText(StudentMenu.this,"from_date"+from_date,Toast.LENGTH_LONG).show();
               //Toast.makeText(StudentMenu.this,"to_date"+to_date,Toast.LENGTH_LONG).show();
               Date fromDate = sdf.parse(from_date);
               Date toDate = sdf.parse(to_date);

               if (System.currentTimeMillis()>=fromDate.getTime() && System.currentTimeMillis()<=toDate.getTime()) {
                   your_date_is_outdated = true;
                   target.setVisible(true);
                   targetspecialization.setVisible(true);
               } else {
                   your_date_is_outdated = false;
                   mspecialization.setVisible(true);
               }
           }catch (ParseException e){
               e.printStackTrace();
           }
        }

        DrawerLayout drawer = findViewById(R.id.student_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.student_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        student_toolbar_name = header.findViewById(R.id.student_toolbar_name);
        student_toolbar_id = header.findViewById(R.id.student_toolbar_id);
        student_toolbar_name.setText(studentlastname + " " + studentname);
        student_toolbar_id.setText(studentid);


    }

    public void student_myprofile_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_myprofile);
    }

    public void student_mycourse_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_mycourses);
    }

    public void student_calendar_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_academiccalendar);
    }

    public void student_schedule_layout(View view) {
        displaySelectedScreen(R.id.navigation_program_myschedule);
    }

    public void homescreen_logout_layout(View view) {
        builder = new AlertDialog.Builder(StudentMenu.this, R.style.MyStudentAlertDialogStyle);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to Logout?");
        displayAlert();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.student_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.navigation_program_home:
                fragment = new StudentHome();
                break;
            case R.id.navigation_program_myprofile:
                Intent studentmyprofileintent = new Intent(getApplicationContext(), StudentMyProfile.class);
                studentmyprofileintent.putExtra("studentid", studentid);
                studentmyprofileintent.putExtra("studentyear", studentyear);
                startActivity(studentmyprofileintent);
                break;
            case R.id.navigation_program_myspecialization:
                Intent intent = new Intent(getApplicationContext(), SpecializationScreen.class);
                intent.putExtra("studentid", studentid);
                startActivity(intent);
                break;
            case R.id.navigation_program_mycourses:
                Bundle bundlecourse = new Bundle();
                if (studentyear.equals("1")) {
                    bundlecourse.putString("studentyear", studentyear);
                    fragment = new EnrolledCourseFragment();
                    fragment.setArguments(bundlecourse);
                    break;
                }else{
                    Intent secondyeardashboard = new Intent(getApplicationContext(), EnrolledCourseDetailsSecondYearMainCourseDashboard.class);
                    secondyeardashboard.putExtra("studentyear", studentyear);
                    secondyeardashboard.putExtra("studentid", studentid);
                    startActivity(secondyeardashboard);
                    break;
                }

           /* case R.id.navigation_program_coursefeedback:
                Intent myfeedback=new Intent(getApplicationContext(),MyFeedbackEnrolledCourse.class);
                myfeedback.putExtra("studentid", studentid);
                myfeedback.putExtra("studentyear", studentyear);
                startActivity(myfeedback);
                break;*/
            case R.id.navigation_program_Attendance:
                Intent attendanceactivity = new Intent(getApplicationContext(), EnrolledCourseAttendanceActivity.class);
                attendanceactivity.putExtra("studentid", studentid);
                attendanceactivity.putExtra("studentyear", studentyear);
                startActivity(attendanceactivity);
                break;
            case R.id.navigation_program_myschedule:
                Bundle bundle = new Bundle();
                bundle.putString("studentyear", studentyear);
                bundle.putString("studentid", studentid);
                bundle.putString("student_specialization", student_specialization);
                fragment = new StudentSchedule();
                fragment.setArguments(bundle);
                break;
            case R.id.navigation_program_academiccalendar:
                Bundle bundlecal = new Bundle();
                bundlecal.putString("CalendarVal", CalendarVal);
                fragment = new StudentAcademicCalendar();
                fragment.setArguments(bundlecal);
                break;
            case R.id.navigation_program_enrollcourse:
                Intent enrollcourseintent = new Intent(getApplicationContext(), StudentEnrollCourseTab.class);
                enrollcourseintent.putExtra("openfragment", "0");
                enrollcourseintent.putExtra("student_specialization", student_specialization);
                enrollcourseintent.putExtra("studentid", studentid);
                enrollcourseintent.putExtra("studentyear", studentyear);
                startActivity(enrollcourseintent);
                break;
            case R.id.navigation_program_specialization:
                Intent specializationintent = new Intent(getApplicationContext(), StudentEnrollSpecializationTab.class);
                specializationintent.putExtra("openfragment", "0");
                specializationintent.putExtra("studentid", studentid);
                specializationintent.putExtra("studentyear", studentyear);
                startActivity(specializationintent);
                break;
            case R.id.navigation_program_notification:
                Intent notificationintent = new Intent(getApplicationContext(), StudentNotification.class);
                startActivity(notificationintent);
                break;

            case R.id.navigation_program_directory:
                Intent directoryintent = new Intent(getApplicationContext(), FacultyDirectory.class);
                startActivity(directoryintent);
                break;
        }

        if (fragment != null) {
            RelativeLayout layout = findViewById(R.id.student_courselist_fragment);
            layout.removeAllViewsInLayout();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.student_courselist_fragment, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.student_drawer_layout);
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
            builder = new AlertDialog.Builder(StudentMenu.this, R.style.MyStudentAlertDialogStyle);
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
                SharedPrefManager.getInstance(StudentMenu.this).logout();
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

                            Toast.makeText(StudentMenu.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
