package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SubmitFeedbackScreenListItems {
    private String enrolledcoursename;
    private String studentid;
    private String feedbackquestion;
    private String feedbackanswer;
    private String feedbacktype;

    public SubmitFeedbackScreenListItems(String enrolledcoursename) {
        this.enrolledcoursename = enrolledcoursename;
    }
    public SubmitFeedbackScreenListItems(String feedbackquestion, String feedbackanswer, String feedbacktype) {
        this.feedbackquestion = feedbackquestion;
        this.feedbackanswer = feedbackanswer;
        this.feedbacktype = feedbacktype;
    }

    public String getFeedbackquestion() {
        return feedbackquestion;
    }

    public void setFeedbackquestion(String feedbackquestion) {
        this.feedbackquestion = feedbackquestion;
    }

    public String getFeedbackanswer() {
        return feedbackanswer;
    }

    public void setFeedbackanswer(String feedbackanswer) {
        this.feedbackanswer = feedbackanswer;
    }

    public String getFeedbacktype() {
        return feedbacktype;
    }

    public void setFeedbacktype(String feedbacktype) {
        this.feedbacktype = feedbacktype;
    }
}
