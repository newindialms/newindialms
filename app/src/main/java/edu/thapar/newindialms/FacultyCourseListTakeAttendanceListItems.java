package edu.thapar.newindialms;

import java.io.Serializable;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListTakeAttendanceListItems implements Serializable {

    private String coursename;
    private String studentname;
    private String studentrollno;
    private boolean status;


    public FacultyCourseListTakeAttendanceListItems(String studentname, String studentrollno) {
        this.studentname = studentname;
        this.studentrollno = studentrollno;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(String studentrollno) {
        this.studentrollno = studentrollno;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
