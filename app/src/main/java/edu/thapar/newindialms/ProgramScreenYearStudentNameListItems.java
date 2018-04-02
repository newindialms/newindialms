package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearStudentNameListItems {
    private String studentname;
    private String studentrollno;
    private String studentspecialization;

    public ProgramScreenYearStudentNameListItems(String studentname,String rollno,String specialization) {
        this.studentname=studentname;
        this.studentrollno=rollno;
        this.studentspecialization=specialization;

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
