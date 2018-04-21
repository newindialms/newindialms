package edu.thapar.newindialms;

/**
 * Created by kamalshree on 2/20/2018.
 */

public class NotificationScreenDetails {

    private String notificationtitle;
    private String notificationmsg;
    private String notificationtime;

    public NotificationScreenDetails(String notificationtitle, String notificationmsg, String notificationtime) {
        this.notificationtitle = notificationtitle;
        this.notificationmsg = notificationmsg;
        this.notificationtime = notificationtime;
    }

    public String getNotificationmsg() {
        return notificationmsg;
    }


    public String getNotificationtitle() {
        return notificationtitle;
    }


    public String getNotificationtime() {
        return notificationtime;
    }


}
