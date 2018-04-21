package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/18/2017.
 */

public class SpinnerListItem {

    private String firstname;
    private String lastname;
    private String rollno;
    private String email;


    public SpinnerListItem(String lastname, String firstname, String rollno, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rollno = rollno;
        this.email = email;
    }

    public String getLastname() {
        return lastname;
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
