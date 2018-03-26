package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/22/2017.
 */

public class SubmitFeedbackScreenListItems {
    private String feedbackquestion;
    private String feedbackanswer;
    private String feedbacktype;

    private String rateval;
    private String smileyval;
    private String textval;
    private String likeval;

    Boolean rateStatus, textStatus, likeStatus, smileyStatus;


    public SubmitFeedbackScreenListItems(String feedbackquestion, String feedbackanswer, String feedbacktype) {
        this.feedbackquestion = feedbackquestion;
        this.feedbackanswer = feedbackanswer;
        this.feedbacktype = feedbacktype;
    }

    public String getFeedbackquestion() {
        return feedbackquestion;
    }


    public String getFeedbacktype() {
        return feedbacktype;
    }


    public void setRateval(String rateval) {
        this.rateval = rateval;
    }


    public void setSmileyval(String smileyval) {
        this.smileyval = smileyval;
    }


    public void setLikeval(String likeval) {
        this.likeval = likeval;
    }


    public void setRateStatus(Boolean rateStatus) {
        this.rateStatus = rateStatus;
    }


    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }


    public void setSmileyStatus(Boolean smileyStatus) {
        this.smileyStatus = smileyStatus;
    }
}
