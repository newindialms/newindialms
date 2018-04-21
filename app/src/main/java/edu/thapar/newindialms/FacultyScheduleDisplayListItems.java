package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/20/2017.
 */

public class FacultyScheduleDisplayListItems {

    private String program;
    private String starttime;
    private String endtime;
    private String course;


    public FacultyScheduleDisplayListItems(String program, String starttime, String endtime, String course) {
        this.program = program;
        this.starttime = starttime;
        this.endtime = endtime;
        this.course = course;
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

    public String getEndtime() {
        return endtime;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
