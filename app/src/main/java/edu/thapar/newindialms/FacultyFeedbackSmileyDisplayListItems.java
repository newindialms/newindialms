package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackSmileyDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private String smiley;
    private String question;

    public FacultyFeedbackSmileyDisplayListItems(String smiley, String question, String daywise_date, String daywise_time) {
        this.smiley = smiley;
        this.question = question;
        this.daywise_date = daywise_date;
        this.daywise_time = daywise_time;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDaywise_date() {
        return daywise_date;
    }


    public String getDaywise_time() {
        return daywise_time;
    }


    public String getSmiley() {
        return smiley;
    }

}
