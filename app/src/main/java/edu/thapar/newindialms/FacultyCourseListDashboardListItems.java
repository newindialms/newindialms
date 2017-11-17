package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListDashboardListItems {

    private String coursename;
    private String attendance;
    private String feedback;
    private String faculty_employeeid;

    public FacultyCourseListDashboardListItems(String coursename) {
        this.coursename = coursename;
    }

    public FacultyCourseListDashboardListItems(String attendance, String feedback,String coursename,String faculty_employeeid) {
        this.attendance = attendance;
        this.feedback=feedback;
        this.coursename=coursename;
        this.faculty_employeeid=faculty_employeeid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }
}
