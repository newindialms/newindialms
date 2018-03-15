package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class MyfeedbackEnrolledCourseListItems {
    private String enrolledcoursename;
    private String studentid;
    private String course_date;
    private String course_time;
    private String faculty_employeeid;

    public MyfeedbackEnrolledCourseListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }
    public MyfeedbackEnrolledCourseListItems(String enrolledcoursename, String studentid,String course_date,String course_time,String faculty_employeeid) {
        this.enrolledcoursename = enrolledcoursename;
        this.studentid = studentid;
        this.course_date = course_date;
        this.course_time = course_time;
        this.faculty_employeeid=faculty_employeeid;
    }

    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }

    public String getEnrolledcoursename() {
        return enrolledcoursename;
    }

    public void setEnrolledcoursename(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCourse_date() {
        return course_date;
    }

    public void setCourse_date(String course_date) {
        this.course_date = course_date;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }
}
