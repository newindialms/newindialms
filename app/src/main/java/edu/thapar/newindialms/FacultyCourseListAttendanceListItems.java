package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListAttendanceListItems {

    private String coursename;
    private String takeattendance;
    private String seeattendance;

    public FacultyCourseListAttendanceListItems(String coursename) {
        this.coursename = coursename;
    }

    public FacultyCourseListAttendanceListItems(String takeattendance, String seeattendance, String coursename) {
        this.takeattendance = takeattendance;
        this.seeattendance=seeattendance;
        this.coursename=coursename;
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

    public void setTakeattendance(String takeattendance) {
        this.takeattendance = takeattendance;
    }

    public String getSeeattendance() {
        return seeattendance;
    }

    public void setSeeattendance(String seeattendance) {
        this.seeattendance = seeattendance;
    }
}
