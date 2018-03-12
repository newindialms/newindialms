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

    private String rateval;
    private String smileyval;
    private String textval;
    private String likeval;

    Boolean rateStatus,textStatus,likeStatus,smileyStatus;



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

    public String getRateval() {
        return rateval;
    }

    public void setRateval(String rateval) {
        this.rateval = rateval;
    }

    public String getSmileyval() {
        return smileyval;
    }

    public void setSmileyval(String smileyval) {
        this.smileyval = smileyval;
    }

    public String getTextval() {
        return textval;
    }

    public void setTextval(String textval) {
        this.textval = textval;
    }

    public String getLikeval() {
        return likeval;
    }

    public void setLikeval(String likeval) {
        this.likeval = likeval;
    }

    public Boolean getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(Boolean rateStatus) {
        this.rateStatus = rateStatus;
    }

    public Boolean getTextStatus() {
        return textStatus;
    }

    public void setTextStatus(Boolean textStatus) {
        this.textStatus = textStatus;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Boolean getSmileyStatus() {
        return smileyStatus;
    }

    public void setSmileyStatus(Boolean smileyStatus) {
        this.smileyStatus = smileyStatus;
    }
}
