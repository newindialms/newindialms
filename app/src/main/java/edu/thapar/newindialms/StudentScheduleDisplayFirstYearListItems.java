package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class StudentScheduleDisplayFirstYearListItems {

    private String course_code;
    private String course_name;
    private String faculty_code;
    private String course_schedule_time;
    private String course_classroom;


    public StudentScheduleDisplayFirstYearListItems(String course_code, String course_name, String faculty_code, String course_schedule_time, String course_classroom) {
       this.course_code=course_code;
        this.course_name=course_name;
        this.faculty_code=faculty_code;
        this.course_schedule_time=course_schedule_time;
        this.course_classroom=course_classroom;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getFaculty_code() {
        return faculty_code;
    }

    public void setFaculty_code(String faculty_code) {
        this.faculty_code = faculty_code;
    }

    public String getCourse_schedule_time() {
        return course_schedule_time;
    }

    public void setCourse_schedule_time(String course_schedule_time) {
        this.course_schedule_time = course_schedule_time;
    }

    public String getCourse_classroom() {
        return course_classroom;
    }

    public void setCourse_classroom(String course_classroom) {
        this.course_classroom = course_classroom;
    }
}
