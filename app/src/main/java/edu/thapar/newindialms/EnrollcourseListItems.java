package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrollcourseListItems {

    private String coursedetails_name,coursedetails_code,coursedetails_credits,coursedetails_faculty;
    private boolean selected;

    public EnrollcourseListItems(String coursedetails_name,String coursedetails_code,String coursedetails_credits,String coursedetails_faculty) {
        this.coursedetails_name=coursedetails_name;
        this.coursedetails_code=coursedetails_code;
        this.coursedetails_credits=coursedetails_credits;
        this.coursedetails_faculty=coursedetails_faculty;
    }

    public String getCoursedetails_name() {
        return coursedetails_name;
    }


    public String getCoursedetails_code() {
        return coursedetails_code;
    }



    public String getCoursedetails_credits() {
        return coursedetails_credits;
    }


    public String getCoursedetails_faculty() {
        return coursedetails_faculty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
