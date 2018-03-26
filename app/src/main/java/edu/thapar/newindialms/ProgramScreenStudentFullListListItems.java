package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/2/2017.
 */

public class ProgramScreenStudentFullListListItems {

    private String Name,Rollno;
    private int Rowcount;


    public ProgramScreenStudentFullListListItems() {
    }

    public ProgramScreenStudentFullListListItems(String name, String rollno) {
        Name = name;
        Rollno = rollno;
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
