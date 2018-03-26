package edu.thapar.newindialms;

/**
 * Created by kamalshree on 2/21/2018.
 */

public class FacultySelectFeedbackScreenDetails {
    private String feedbackId;
    private String feedbackQuestions;
    private String feedbackDescription;
    private String feedbackType;
    private boolean status;
    private String facultyid;

    public FacultySelectFeedbackScreenDetails(String facultyid) {
        this.facultyid = facultyid;
    }

    public FacultySelectFeedbackScreenDetails(String feedbackId, String feedbackQuestions, String feedbackDescription, String feedbackType) {
        this.feedbackId=feedbackId;
        this.feedbackQuestions = feedbackQuestions;
        this.feedbackDescription = feedbackDescription;
        this.feedbackType = feedbackType;
    }


    public void setFacultyid(String facultyid) {
        this.facultyid = facultyid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFeedbackId() {
        return feedbackId;
    }


    public String getFeedbackDescription() {
        return feedbackDescription;
    }


    public String getFeedbackType() {
        return feedbackType;
    }


    public String getFeedbackQuestions() {
        return feedbackQuestions;
    }

}
