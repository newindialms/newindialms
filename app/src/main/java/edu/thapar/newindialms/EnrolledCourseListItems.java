package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrolledCourseListItems {
    private String enrolledcoursename;
    private String studentid;
    private String studentyear;

    public EnrolledCourseListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }

    public EnrolledCourseListItems(String enrolledcoursename, String studentid,String studentyear) {
        this.enrolledcoursename = enrolledcoursename;
        this.studentid = studentid;
        this.studentyear = studentyear;
    }

    public String getEnrolledcoursename() {
        return enrolledcoursename;
    }

    public String getStudentyear() {
        return studentyear;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

}
