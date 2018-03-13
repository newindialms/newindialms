package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackDashboardListItems {

    private String feedbackType;

    public FacultyFeedbackDashboardListItems(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }
}
