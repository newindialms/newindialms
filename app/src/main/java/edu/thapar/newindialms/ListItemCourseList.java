package edu.thapar.newindialms;

/**
 * Created by kamalshree on 9/27/2017.
 */

public class ListItemCourseList {

    private String CourseListTitle;
    private String CourseListFaculty;

    public ListItemCourseList(String courseListTitle, String courseListFaculty) {
        CourseListTitle = courseListTitle;
        CourseListFaculty = courseListFaculty;
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
