package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/10/2017.
 */

public class ProgramScreenCourseScheduleListItems {
    private String timetable_day,timetable_date,timetable_startdate,timetable_enddate,timetable_course,timetable_faculty,timetable_program;

    public ProgramScreenCourseScheduleListItems(String timetable_day, String timetable_date, String timetable_startdate, String timetable_enddate, String timetable_course, String timetable_faculty, String timetable_program) {
        this.timetable_day = timetable_day;
        this.timetable_date = timetable_date;
        this.timetable_startdate = timetable_startdate;
        this.timetable_enddate = timetable_enddate;
        this.timetable_course = timetable_course;
        this.timetable_faculty = timetable_faculty;
        this.timetable_program = timetable_program;
    }

    public String getTimetable_day() {
        return timetable_day;
    }

    public void setTimetable_day(String timetable_day) {
        this.timetable_day = timetable_day;
    }

    public String getTimetable_date() {
        return timetable_date;
    }

    public void setTimetable_date(String timetable_date) {
        this.timetable_date = timetable_date;
    }

    public String getTimetable_startdate() {
        return timetable_startdate;
    }

    public void setTimetable_startdate(String timetable_startdate) {
        this.timetable_startdate = timetable_startdate;
    }

    public String getTimetable_enddate() {
        return timetable_enddate;
    }

    public void setTimetable_enddate(String timetable_enddate) {
        this.timetable_enddate = timetable_enddate;
    }

    public String getTimetable_course() {
        return timetable_course;
    }

    public void setTimetable_course(String timetable_course) {
        this.timetable_course = timetable_course;
    }

    public String getTimetable_faculty() {
        return timetable_faculty;
    }

    public void setTimetable_faculty(String timetable_faculty) {
        this.timetable_faculty = timetable_faculty;
    }

    public String getTimetable_program() {
        return timetable_program;
    }

    public void setTimetable_program(String timetable_program) {
        this.timetable_program = timetable_program;
    }
}