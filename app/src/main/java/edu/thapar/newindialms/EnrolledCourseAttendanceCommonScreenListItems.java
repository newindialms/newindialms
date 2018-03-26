package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseAttendanceCommonScreenListItems {

    private String enrolledcoursename;
    private String cumulative;
    private String daywise;
    private String studentrollno;

    public EnrolledCourseAttendanceCommonScreenListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }

    public EnrolledCourseAttendanceCommonScreenListItems(String daywise, String cumulative, String enrolledcoursename, String studentrollno) {
        this.enrolledcoursename = enrolledcoursename;
        this.daywise = daywise;
        this.cumulative = cumulative;
        this.studentrollno = studentrollno;
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


    public String getDaywise() {
        return daywise;
    }


    public String getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(String studentrollno) {
        this.studentrollno = studentrollno;
    }

}
