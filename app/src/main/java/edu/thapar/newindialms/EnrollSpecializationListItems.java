package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class EnrollSpecializationListItems {

    private String coursedetails_name,studentid;
    private boolean selected;

    public EnrollSpecializationListItems(String coursedetails_name) {
        this.coursedetails_name=coursedetails_name;

    }
    public EnrollSpecializationListItems(String coursedetails_name,String studentid) {
        this.coursedetails_name=coursedetails_name;
        this.studentid=studentid;

    }

    public String getCoursedetails_name() {
        return coursedetails_name;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
