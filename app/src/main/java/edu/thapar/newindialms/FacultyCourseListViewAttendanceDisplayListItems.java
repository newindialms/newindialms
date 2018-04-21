package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyCourseListViewAttendanceDisplayListItems {

    private String student_details;
    private String attendance_status;
    private String student_name;

    public FacultyCourseListViewAttendanceDisplayListItems(String student_details, String attendance_status, String student_name) {
        this.attendance_status = attendance_status;
        this.student_details = student_details;
        this.student_name = student_name;

    }

    public String getStudent_details() {
        return student_details;
    }


    public String getAttendance_status() {
        return attendance_status;
    }


    public String getStudent_name() {
        return student_name;
    }

}
