package edu.thapar.newindialms;

/**
 * Created by kamalshree on 2/21/2018.
 */

public class FacultySelectFeedbackScreenDetails {

    private String feedbackQuestions;
    private String feedbackDescription;
    private String feedbackType;

    public FacultySelectFeedbackScreenDetails(String feedbackQuestions, String feedbackDescription, String feedbackType) {
        this.feedbackQuestions = feedbackQuestions;
        this.feedbackDescription = feedbackDescription;
        this.feedbackType = feedbackType;
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
