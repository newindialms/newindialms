package edu.thapar.newindialms;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class FacultyListItemCourseList {

    private String CourseListTitle;
    private String Coursecode;
    private String faculty_employeeid;
    private String course_type;

    public FacultyListItemCourseList(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }

    public FacultyListItemCourseList(String courseListTitle, String coursecode, String course_type, String faculty_employeeid) {
        this.CourseListTitle = courseListTitle;
        this.Coursecode = coursecode;
        this.faculty_employeeid = faculty_employeeid;
        this.course_type = course_type;
    }

    public String getCourse_type() {
        return course_type;
    }

    public String getCoursecode() {
        return Coursecode;
    }

    public void setCoursecode(String coursecode) {
        Coursecode = coursecode;
    }

    public String getCourseListTitle() {
        return CourseListTitle;
    }

    public String getFaculty_employeeid() {
        return faculty_employeeid;
    }

    public void setFaculty_employeeid(String faculty_employeeid) {
        this.faculty_employeeid = faculty_employeeid;
    }
}
