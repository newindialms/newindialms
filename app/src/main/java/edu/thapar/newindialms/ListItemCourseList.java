package edu.thapar.newindialms;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class ListItemCourseList {

    private String CourseListTitle;
    private String CourseListFaculty;
    private String CourseListCode;

    public ListItemCourseList(String courseListTitle, String courseListFaculty, String courseListCode) {
        CourseListTitle = courseListTitle;
        CourseListFaculty = courseListFaculty;
        CourseListCode = courseListCode;

    }

    public String getCourseListCode() {
        return CourseListCode;
    }


    public String getCourseListTitle() {
        return CourseListTitle;
    }


    public String getCourseListFaculty() {
        return CourseListFaculty;
    }


}
