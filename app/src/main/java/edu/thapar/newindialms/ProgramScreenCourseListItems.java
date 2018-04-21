package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/25/2017.
 */

public class ProgramScreenCourseListItems {
    private String coursename, coursecode;

    public ProgramScreenCourseListItems(String coursename, String coursecode) {
        this.coursename = coursename;
        this.coursecode = coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}
