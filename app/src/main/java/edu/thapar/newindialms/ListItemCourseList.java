package edu.thapar.newindialms;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class ListItemCourseList {

    private String CourseListTitle;
    private String CourseListFaculty;
    private String CourseListCode;

    public ListItemCourseList(String courseListTitle, String courseListFaculty,String courseListCode) {
        CourseListTitle = courseListTitle;
        CourseListFaculty = courseListFaculty;
        CourseListCode = courseListCode;

    }

    public String getCourseListCode() {
        return CourseListCode;
    }

    public void setCourseListCode(String courseListCode) {
        CourseListCode = courseListCode;
    }

    public String getCourseListTitle() {
        return CourseListTitle;
    }

    public void setCourseListTitle(String courseListTitle) {
        CourseListTitle = courseListTitle;
    }


    public String getCourseListFaculty() {
        return CourseListFaculty;
    }

    public void setCourseListFaculty(String courseListFaculty) {
        CourseListFaculty = courseListFaculty;
    }

}
