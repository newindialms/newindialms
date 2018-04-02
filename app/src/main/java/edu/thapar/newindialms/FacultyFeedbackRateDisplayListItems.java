package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackRateDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private double stars;
    private String question;

   public FacultyFeedbackRateDisplayListItems(double stars,String question,String daywise_date, String daywise_time) {
       this.stars=stars;
       this.question=question;
        this.daywise_date = daywise_date;
        this.daywise_time=daywise_time;



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


    public double getStars() {
        return stars;
    }

}
