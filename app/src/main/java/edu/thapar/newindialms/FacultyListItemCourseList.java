package edu.thapar.newindialms;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class FacultyListItemCourseList {

    private String CourseListTitle;
    private String faculty_employeeid;

    public FacultyListItemCourseList(String faculty_employeeid) {
        this.faculty_employeeid=faculty_employeeid;
    }

    public FacultyListItemCourseList(String courseListTitle,String faculty_employeeid) {
        CourseListTitle = courseListTitle;
        this.faculty_employeeid=faculty_employeeid;
    }

    public String getCourseListTitle() {
        return CourseListTitle;
    }

    public void setCourseListTitle(String courseListTitle) {
        CourseListTitle = courseListTitle;
    }

    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }
}
