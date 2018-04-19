package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackCumulativeDashboardListItems {
    private String average;
    private String question;
    private String median;
    private String type;


   public FacultyFeedbackCumulativeDashboardListItems(String average, String median, String question,String type) {
       this.question=question;
        this.average = average;
        this.median=median;
        this.type=type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(String median) {
        this.median = median;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
