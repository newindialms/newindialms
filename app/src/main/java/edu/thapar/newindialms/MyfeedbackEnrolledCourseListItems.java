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
    private String coursename;

    public MyfeedbackEnrolledCourseListItems(String enrolledcoursename, String studentid, String course_date, String course_time, String faculty_employeeid, String coursename) {
        this.enrolledcoursename = enrolledcoursename;
        this.studentid = studentid;
        this.course_date = course_date;
        this.course_time = course_time;
        this.faculty_employeeid = faculty_employeeid;
        this.coursename = coursename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
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

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCourse_date() {
        return course_date;
    }


    public String getCourse_time() {
        return course_time;
    }

}
