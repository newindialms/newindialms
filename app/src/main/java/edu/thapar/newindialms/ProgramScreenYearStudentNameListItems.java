package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class ProgramScreenYearStudentNameListItems {
    private String Yeardetails,Programname,studentname;

    public ProgramScreenYearStudentNameListItems(String studentname) {
        this.studentname=studentname;

    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
