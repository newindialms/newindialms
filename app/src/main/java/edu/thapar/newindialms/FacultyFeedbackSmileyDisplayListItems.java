package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class FacultyFeedbackSmileyDisplayListItems {


    private String daywise_date;
    private String daywise_time;
    private String smiley;

   public FacultyFeedbackSmileyDisplayListItems(String smiley, String daywise_date, String daywise_time) {
       this.smiley=smiley;
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

    public String getSmiley() {
        return smiley;
    }

    public void setSmiley(String smiley) {
        this.smiley = smiley;
    }
}
