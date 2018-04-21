package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackCumulativeDisplayListItems {


    private String facultyid;
    private String coursename;
    private String dateval;
    private String question;
    private String type;

    public FacultyFeedbackCumulativeDisplayListItems(String question, String type, String facultyid, String coursename, String dateval) {
        this.facultyid = facultyid;
        this.question = question;
        this.coursename = coursename;
        this.dateval = dateval;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFacultyid() {
        return facultyid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDateval() {
        return dateval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
