package edu.thapar.newindialms;

/**
 * Created by kamalshree on 10/28/2017.
 */

public class NotificationTabListItems {

    private String notificationTitle,notificationMessage,notificationDate;

    public NotificationTabListItems(String notificationTitle, String notificationMessage, String notificationDate) {
        this.notificationTitle = notificationTitle;
        this.notificationMessage = notificationMessage;
        this.notificationDate = notificationDate;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
}
