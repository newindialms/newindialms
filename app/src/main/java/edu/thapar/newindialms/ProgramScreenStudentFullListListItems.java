package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/2/2017.
 */

public class ProgramScreenStudentFullListListItems {

    private String Name,Fname,Rollno;
    private int Rowcount;


    public ProgramScreenStudentFullListListItems() {
    }

    public ProgramScreenStudentFullListListItems(String fname,String name, String rollno) {
        Fname=fname;
        Name = name;
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

    public void setRollno(String rollno) {
        Rollno = rollno;
    }
}
