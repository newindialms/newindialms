package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDetailsSecondYearDashboardListItems {

    private String courseDetailsSchedule;
    private String courseDetailsDetails;
    private String courseDetailsNotification;
    private String courseDetailsMarks;

    private String programname;

    public EnrolledCourseDetailsSecondYearDashboardListItems(String programname) {
        this.programname = programname;
    }

    public EnrolledCourseDetailsSecondYearDashboardListItems(String courseDetailsSchedule, String courseDetailsDetails, String courseDetailsNotification, String courseDetailsMarks, String programname) {
        this.courseDetailsSchedule = courseDetailsSchedule;
        this.courseDetailsDetails = courseDetailsDetails;
        this.courseDetailsNotification = courseDetailsNotification;
        this.courseDetailsMarks = courseDetailsMarks;
        this.programname = programname;
    }

    public String getCourseDetailsSchedule() {
        return courseDetailsSchedule;
    }

    public void setCourseDetailsSchedule(String courseDetailsSchedule) {
        this.courseDetailsSchedule = courseDetailsSchedule;
    }

    public String getCourseDetailsDetails() {
        return courseDetailsDetails;
    }

    public void setCourseDetailsDetails(String courseDetailsDetails) {
        this.courseDetailsDetails = courseDetailsDetails;
    }

    public String getCourseDetailsNotification() {
        return courseDetailsNotification;
    }

    public void setCourseDetailsNotification(String courseDetailsNotification) {
        this.courseDetailsNotification = courseDetailsNotification;
    }

    public String getCourseDetailsMarks() {
        return courseDetailsMarks;
    }

    public void setCourseDetailsMarks(String courseDetailsMarks) {
        this.courseDetailsMarks = courseDetailsMarks;
    }

    public String getProgramname() {
        return programname;
    }

    public void setProgramname(String programname) {
        this.programname = programname;
    }
}
