package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenCoreCourseStudentListItems {
    private String Name,Fname,Rollno;
    private int Rowcount;


    public ProgramScreenCoreCourseStudentListItems() {
    }

    public ProgramScreenCoreCourseStudentListItems(String fname,String name, String rollno) {
        Name = name;
        Fname=fname;
        Rollno = rollno;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public void setRowcount(int rowcount) {
        Rowcount = rowcount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRollno() {
        return Rollno;
    }

}
