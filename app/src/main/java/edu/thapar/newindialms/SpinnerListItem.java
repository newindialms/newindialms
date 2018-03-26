package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/18/2017.
 */

public class SpinnerListItem {

    private String firstname;
    private String rollno;
    private String email;


    public SpinnerListItem(String firstname, String rollno, String email) {
        this.firstname = firstname;
        this.rollno = rollno;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }


    public String getRollno() {
        return rollno;
    }


    public String getEmail() {
        return email;
    }

}
