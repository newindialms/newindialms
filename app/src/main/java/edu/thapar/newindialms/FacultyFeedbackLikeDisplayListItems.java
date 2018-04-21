package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackLikeDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private String like;
    private String question;

    public FacultyFeedbackLikeDisplayListItems(String like, String question, String daywise_date, String daywise_time) {
        this.like = like;
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


    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
