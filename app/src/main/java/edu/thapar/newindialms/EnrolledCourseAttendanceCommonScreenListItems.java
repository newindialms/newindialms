package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseAttendanceCommonScreenListItems {

    private String enrolledcoursename,cumulative,daywise;

    public EnrolledCourseAttendanceCommonScreenListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }

    public EnrolledCourseAttendanceCommonScreenListItems(String daywise,String cumulative,String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
        this.daywise=daywise;
        this.cumulative=cumulative;
    }

    public String getEnrolledcoursename() {
        return enrolledcoursename;
    }

    public void setEnrolledcoursename(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }

    public String getCumulative() {
        return cumulative;
    }

    public void setCumulative(String cumulative) {
        this.cumulative = cumulative;
    }

    public String getDaywise() {
        return daywise;
    }

    public void setDaywise(String daywise) {
        this.daywise = daywise;
    }
}
