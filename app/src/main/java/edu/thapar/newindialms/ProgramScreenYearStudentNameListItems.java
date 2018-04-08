package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearStudentNameListItems {
    private String studentname;
    private String studnetfirstname;
    private String studentrollno;
    private String studentspecialization;

    public ProgramScreenYearStudentNameListItems(String studnetfirstname,String studentname,String rollno,String specialization) {
        this.studentname=studentname;
        this.studnetfirstname=studnetfirstname;
        this.studentrollno=rollno;
        this.studentspecialization=specialization;

    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudnetfirstname() {
        return studnetfirstname;
    }

    public void setStudnetfirstname(String studnetfirstname) {
        this.studnetfirstname = studnetfirstname;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getStudentrollno() {
        return studentrollno;
    }

    public void setStudentrollno(String studentrollno) {
        this.studentrollno = studentrollno;
    }

    public String getStudentspecialization() {
        return studentspecialization;
    }

    public void setStudentspecialization(String studentspecialization) {
        this.studentspecialization = studentspecialization;
    }
}
