package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/10/2017.
 */

public class ProgramScreenCourseScheduleListItems {
    private String timetable_day,timetable_date,timetable_startdate,timetable_enddate,timetable_course,timetable_faculty,timetable_program;
    private String course_code,course_name,faculty_code,course_date,course_schedule_day,course_schedule_time,student_group,student_batch,course_classroom;



    public ProgramScreenCourseScheduleListItems(String timetable_day, String timetable_date, String timetable_startdate, String timetable_enddate, String timetable_course, String timetable_faculty, String timetable_program) {
        this.timetable_day = timetable_day;
        this.timetable_date = timetable_date;
        this.timetable_startdate = timetable_startdate;
        this.timetable_enddate = timetable_enddate;
        this.timetable_course = timetable_course;
        this.timetable_faculty = timetable_faculty;
        this.timetable_program = timetable_program;
    }
    public ProgramScreenCourseScheduleListItems(String course_code, String course_name, String faculty_code, String course_date, String course_schedule_day, String course_schedule_time, String student_group, String student_batch, String course_classroom) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.faculty_code = faculty_code;
        this.course_date = course_date;
        this.course_schedule_day = course_schedule_day;
        this.course_schedule_time = course_schedule_time;
        this.student_group = student_group;
        this.student_batch = student_batch;
        this.course_classroom = course_classroom;
    }

    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getFaculty_code() {
        return faculty_code;
    }

    public String getCourse_date() {
        return course_date;
    }

    public String getCourse_schedule_day() {
        return course_schedule_day;
    }

    public String getCourse_schedule_time() {
        return course_schedule_time;
    }

    public String getStudent_group() {
        return student_group;
    }

    public String getStudent_batch() {
        return student_batch;
    }

    public String getCourse_classroom() {
        return course_classroom;
    }

    public String getTimetable_day() {
        return timetable_day;
    }


    public String getTimetable_date() {
        return timetable_date;
    }


    public String getTimetable_startdate() {
        return timetable_startdate;
    }


    public String getTimetable_enddate() {
        return timetable_enddate;
    }



    public String getTimetable_course() {
        return timetable_course;
    }



    public String getTimetable_faculty() {
        return timetable_faculty;
    }



    public String getTimetable_program() {
        return timetable_program;
    }


}