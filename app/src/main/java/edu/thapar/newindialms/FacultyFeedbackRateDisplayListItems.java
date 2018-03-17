package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackRateDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private double stars;

   public FacultyFeedbackRateDisplayListItems(double stars,String daywise_date, String daywise_time) {
       this.stars=stars;
        this.daywise_date = daywise_date;
        this.daywise_time=daywise_time;



    }

    public String getDaywise_date() {
        return daywise_date;
    }

    public void setDaywise_date(String daywise_date) {
        this.daywise_date = daywise_date;
    }

    public String getDaywise_time() {
        return daywise_time;
    }

    public void setDaywise_time(String daywise_time) {
        this.daywise_time = daywise_time;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }
}
