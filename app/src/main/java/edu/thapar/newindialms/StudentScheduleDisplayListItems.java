package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class StudentScheduleDisplayListItems {

    private String program;
    private String starttime;
    private String endtime;
    private String course;
    private String faculty;


    public StudentScheduleDisplayListItems(String program, String starttime, String endtime, String course, String faculty) {
       this.program=program;
        this.starttime=starttime;
        this.endtime=endtime;
        this.course=course;
        this.faculty=faculty;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
