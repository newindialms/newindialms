package edu.thapar.newindialms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by kamalshree on 10/8/2017.
 */

public class OpenCalendar extends AppCompatActivity {
    private static final String TAG = "OpenCalendar";
    TextView displayDate;
    Button open_calendar_details;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_layout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        displayDate = (TextView) findViewById(R.id.calendar_displaydate);
        open_calendar_details = (Button) findViewById(R.id.open_calendar_details);

        open_calendar_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendarintent= new Intent(getApplicationContext(), AcademicCalendar.class);
                startActivity(calendarintent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int yearvalue, int monthvalue, int datevalue) {
                String date = datevalue + "/" + (monthvalue+1) + "/" + yearvalue;
                Log.i(TAG, "The Date is " + date);
                displayDate.setText(date);
            }
        });
    }
}
