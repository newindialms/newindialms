package edu.thapar.newindialms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by kamalshree on 10/8/2017.
 */

public class OpenCalendar extends AppCompatActivity {
    private static final String TAG = "OpenCalendar";
    TextView displayDate;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_calendar_layout);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        displayDate = (TextView) findViewById(R.id.calendar_displaydate);

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
