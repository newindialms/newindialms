package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDaywiseAttendanceListItems {


    private String daywise_date;
    private String daywise_time;
    private String daywise_status;

   public EnrolledCourseDaywiseAttendanceListItems(String daywise_date, String daywise_time, String daywise_status) {
        this.daywise_date = daywise_date;
        this.daywise_time=daywise_time;
       this.daywise_status=daywise_status;

    }

    public String getDaywise_date() {
        return daywise_date;
    }

    public void setDaywise_date(String daywise_date) {
        this.daywise_date = daywise_date;
    }

    public String getDaywise_time() {
        return daywise_time;
    }

    public void setDaywise_time(String daywise_time) {
        this.daywise_time = daywise_time;
    }

    public String getDaywise_status() {
        return daywise_status;
    }

    public void setDaywise_status(String daywise_status) {
        this.daywise_status = daywise_status;
    }


}
