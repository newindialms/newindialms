package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrolledCourseListItems {
    private String enrolledcoursename;
    private String studentid;

    public EnrolledCourseListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }
    public EnrolledCourseListItems(String enrolledcoursename,String studentid) {
        this.enrolledcoursename = enrolledcoursename;
        this.studentid = studentid;
    }

    public String getEnrolledcoursename() {
        return enrolledcoursename;
    }


    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

}
