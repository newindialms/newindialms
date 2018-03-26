package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListAttendanceListItems {

    private String coursename;
    private String takeattendance;
    private String seeattendance;
    private String faculty_employeeid;

    public FacultyCourseListAttendanceListItems(String coursename) {
        this.coursename = coursename;
    }

    public FacultyCourseListAttendanceListItems(String takeattendance, String seeattendance, String coursename,String faculty_employeeid) {
        this.takeattendance = takeattendance;
        this.seeattendance=seeattendance;
        this.coursename=coursename;
        this.faculty_employeeid=faculty_employeeid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getTakeattendance() {
        return takeattendance;
    }


    public String getSeeattendance() {
        return seeattendance;
    }


    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }
}
