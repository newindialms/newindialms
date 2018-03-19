package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackTextDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private String text;

   public FacultyFeedbackTextDisplayListItems(String text, String daywise_date, String daywise_time) {
       this.text=text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
