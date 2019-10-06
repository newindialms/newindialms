package edu.thapar.newindialms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by kamalshree on 10/7/2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String studentid = "0";
        String course_date = "0";
        String feedback_course_details = "0";
        String course_time = "0";
        String faculty_employeeid = "0";

        if (remoteMessage.getData().size() > 0) {
            studentid = remoteMessage.getData().get("studentid");
            course_date = remoteMessage.getData().get("course_date");
            feedback_course_details = remoteMessage.getData().get("feedback_course_details");
            course_time = remoteMessage.getData().get("course_time");
            faculty_employeeid = remoteMessage.getData().get("faculty_employeeid");
            showNotification(remoteMessage.getNotification(),studentid,course_date,feedback_course_details,course_time,faculty_employeeid);
        }
        else{
            showNotificationwithFeedback(remoteMessage.getNotification());
        }


    }

    private void showNotification(RemoteMessage.Notification notification, String studentid, String course_date, String feedback_course_details, String course_time, String faculty_employeeid) {
        String click_action = notification.getClickAction();
        Intent intent = new Intent(click_action);
        intent.putExtra("studentid", studentid);
        intent.putExtra("course_date", course_date);
        intent.putExtra("feedback_course_details", feedback_course_details);
        intent.putExtra("course_time", course_time);
        intent.putExtra("faculty_employeeid", faculty_employeeid);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(notification.getTitle());
        notificationBuilder.setContentText(notification.getBody());
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }


    private void showNotificationwithFeedback(RemoteMessage.Notification notification) {
        Intent intent = new Intent(this, CalendarView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(notification.getTitle());
        notificationBuilder.setContentText(notification.getBody());
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}