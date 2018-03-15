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

    public String getFacultyid() {
        return facultyid;
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

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackQuestions() {
        return feedbackQuestions;
    }

    public void setFeedbackQuestions(String feedbackQuestions) {
        this.feedbackQuestions = feedbackQuestions;
    }
}
