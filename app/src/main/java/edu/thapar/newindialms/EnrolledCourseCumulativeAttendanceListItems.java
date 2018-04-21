package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/21/2017.
 */

public class EnrolledCourseCumulativeAttendanceListItems {


    private String total_count;
    private String present_count;
    private String absent_count;
    private String percentage;

    public EnrolledCourseCumulativeAttendanceListItems(String total_count, String present_count, String absent_count, String percentage) {
        this.total_count = total_count;
        this.present_count = present_count;
        this.absent_count = absent_count;
        this.percentage = percentage;

    }


    public String getTotal_count() {
        return total_count;
    }


    public String getPresent_count() {
        return present_count;
    }


    public String getAbsent_count() {
        return absent_count;
    }


    public String getPercentage() {
        return percentage;
    }

}
