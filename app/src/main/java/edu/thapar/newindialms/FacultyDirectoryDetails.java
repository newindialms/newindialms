package edu.thapar.newindialms;

/**
 * Created by kamalshree on 12/31/2018.
 */

public class FacultyDirectoryDetails {

    private String facultyfirstname;
    private String facultylastname;
    private String facultyemail;
    private String facultyphone;
    private String facultyspecialization;

    public FacultyDirectoryDetails(String facultyfirstname, String facultylastname, String facultyemail, String facultyphone, String facultyspecialization) {
        this.facultyfirstname = facultyfirstname;
        this.facultylastname = facultylastname;
        this.facultyemail = facultyemail;
        this.facultyphone = facultyphone;
        this.facultyspecialization = facultyspecialization;
    }

    public String getFacultyfirstname() {
        return facultyfirstname;
    }

    public void setFacultyfirstname(String facultyfirstname) {
        this.facultyfirstname = facultyfirstname;
    }

    public String getFacultylastname() {
        return facultylastname;
    }

    public void setFacultylastname(String facultylastname) {
        this.facultylastname = facultylastname;
    }

    public String getFacultyemail() {
        return facultyemail;
    }

    public void setFacultyemail(String facultyemail) {
        this.facultyemail = facultyemail;
    }

    public String getFacultyphone() {
        return facultyphone;
    }

    public void setFacultyphone(String facultyphone) {
        this.facultyphone = facultyphone;
    }

    public String getFacultyspecialization() {
        return facultyspecialization;
    }

    public void setFacultyspecialization(String facultyspecialization) {
        this.facultyspecialization = facultyspecialization;
    }
}
