package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackDashboardListItems {

    private String feedbackType;
    private String coursename;
    private String faculty_employeeid;
    private String datevalue;

    public FacultyFeedbackDashboardListItems(String feedbackType, String coursename, String faculty_employeeid, String datevalue) {
        this.feedbackType = feedbackType;
        this.coursename = coursename;
        this.faculty_employeeid = faculty_employeeid;
        this.datevalue = datevalue;
    }

    public String getDatevalue() {
        return datevalue;
    }

    public void setDatevalue(String datevalue) {
        this.datevalue = datevalue;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }
}
