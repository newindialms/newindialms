package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenCoreCourseListItems {

    private String coursedetails,core_code;

    public ProgramScreenCoreCourseListItems(String coursedetails,String core_code) {
        this.coursedetails = coursedetails;
        this.core_code = core_code;
    }

    public String getCoursedetails() {
        return coursedetails;
    }

    public String getCore_code() {
        return core_code;
    }

    public void setCore_code(String core_code) {
        this.core_code = core_code;
    }
}
