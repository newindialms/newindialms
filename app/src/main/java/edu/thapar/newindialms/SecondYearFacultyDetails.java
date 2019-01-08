package edu.thapar.newindialms;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class SecondYearFacultyDetails {

    private String facultyfirstname;
    private String facultylastname;
    private String facultyfirstphone;
    private String facultyfirstemail;
    private String facultyfirstcredits;

    public SecondYearFacultyDetails(String facultyfirstname, String facultylastname, String facultyfirstphone, String facultyfirstemail, String facultyfirstcredits) {
        this.facultyfirstname = facultyfirstname;
        this.facultylastname = facultylastname;
        this.facultyfirstphone = facultyfirstphone;
        this.facultyfirstemail = facultyfirstemail;
        this.facultyfirstcredits = facultyfirstcredits;
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

    public String getFacultyfirstphone() {
        return facultyfirstphone;
    }

    public void setFacultyfirstphone(String facultyfirstphone) {
        this.facultyfirstphone = facultyfirstphone;
    }

    public String getFacultyfirstemail() {
        return facultyfirstemail;
    }

    public void setFacultyfirstemail(String facultyfirstemail) {
        this.facultyfirstemail = facultyfirstemail;
    }

    public String getFacultyfirstcredits() {
        return facultyfirstcredits;
    }

    public void setFacultyfirstcredits(String facultyfirstcredits) {
        this.facultyfirstcredits = facultyfirstcredits;
    }
}
