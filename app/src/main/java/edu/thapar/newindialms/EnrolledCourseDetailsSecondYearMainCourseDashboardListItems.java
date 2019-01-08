package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseDetailsSecondYearMainCourseDashboardListItems {

    private String courseElective;
    private String courseCore;
    private String studentid;
    private String studentyear;


    public EnrolledCourseDetailsSecondYearMainCourseDashboardListItems(String courseElective, String courseCore,String studentid,String studentyear) {
        this.courseElective = courseElective;
        this.courseCore = courseCore;
        this.studentid = studentid;
        this.studentyear = studentyear;
    }

    public String getCourseElective() {
        return courseElective;
    }

    public void setCourseElective(String courseElective) {
        this.courseElective = courseElective;
    }

    public String getCourseCore() {
        return courseCore;
    }

    public void setCourseCore(String courseCore) {
        this.courseCore = courseCore;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentyear() {
        return studentyear;
    }

    public void setStudentyear(String studentyear) {
        this.studentyear = studentyear;
    }
}
