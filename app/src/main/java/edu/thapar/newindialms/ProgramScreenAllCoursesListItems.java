package edu.thapar.newindialms;

/**
 * Created by kamalshree on 11/8/2017.
 */

public class ProgramScreenAllCoursesListItems {

    private String Name,Rollno;
    private int Rowcount;


    public ProgramScreenAllCoursesListItems() {
    }

    public ProgramScreenAllCoursesListItems(String name, String rollno) {
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

}
