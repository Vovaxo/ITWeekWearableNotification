package com.epam.itweekwearable;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;


public class MainActivity extends Activity {
    public static final int NOTIFICATION_PAGES_ID = 1;
    public static final int NOTIFICATION_SIMPLE_ID = 2;
    private static final int NOTIFICATION_PAGES_STACK = 3;

    private final static String GROUP_KEY_EMAILS = "group_key_emails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void showPagesNotification(View view) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(0xFFffeb3b);

        Notification notification1 = new Notification.Builder(this).setContentText("first page").setContentTitle("Pages")
                .setSmallIcon(R.drawable.ic_notification)
                .extend(new Notification.WearableExtender().setBackground(bitmap))
                .build();

        Bitmap bitmap2 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap2.eraseColor(0xFFff5722);

        Notification notification2 = new Notification.Builder(this).setContentText("second page").setContentTitle("Pages")
                .setSmallIcon(R.drawable.ic_notification)
                .extend(new Notification.WearableExtender().setBackground(bitmap2))
                .build();

        Bitmap bitmap3 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap3.eraseColor(0xFF795548);
        Notification notification3 = new Notification.Builder(this).setContentText("third page").setContentTitle("Pages")
                .setSmallIcon(R.drawable.ic_notification)
                .extend(new Notification.WearableExtender().setBackground(bitmap3))
                .build();

        Bitmap bitmap4 = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        bitmap4.eraseColor(0xFF607d8b);
        Notification.Action action = new Notification.Action(R.drawable.ic_action_av_pause,
                getString(R.string.action_b), pendingIntent);
        Notification notification = new Notification.Builder(this).setContentText("Page notifications").setContentTitle("Pages")
                .setSmallIcon(R.drawable.ic_notification)
                .extend(new Notification.WearableExtender().setBackground(bitmap4)
                                .addPage(notification1)
                                .addPage(notification2)
                                .addPage(notification3)
                                .addAction(action)
                                .addAction(new Notification.Action(R.drawable.ic_action_av_play,
                                        getString(R.string.action_a), pendingIntent))
                )

                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIFICATION_PAGES_ID, notification);
    }

    public void showGroupNotifications(View view) {

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        // Build the notification, setting the group appropriately
        String sender1 = "EPAM IT Week";
        String subject1 = "Android Wear";

        String sender2 = "HR Department";
        String subject2 = "IT Week";

        Notification notif = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + sender1)
                .setContentText(subject1)
                .setSmallIcon(R.drawable.ic_notification)
                .addAction(R.drawable.ic_action_av_play, "OK EPAM", pendingIntent)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        // Issue the notification
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_PAGES_STACK, notif);

        Notification notif2 = new NotificationCompat.Builder(this)
                .setContentTitle("New mail from " + sender2)
                .setContentText(subject2)
                .setSmallIcon(R.drawable.ic_notification)
                .addAction(R.drawable.ic_action_av_play, "OK HR", pendingIntent)
                .setGroup(GROUP_KEY_EMAILS)
                .build();

        notificationManager.notify(NOTIFICATION_PAGES_STACK + 1, notif2);

        Bitmap background = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        background.eraseColor(0xFF259b24);

        // Create an InboxStyle notification
        Notification summaryNotification = new NotificationCompat.Builder(this)
                .setContentTitle("2 new messages")
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(background)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("EPAM IT Week    Android Wear")
                        .addLine("HR Department   IT Week")
                        .setBigContentTitle("2 new messages")
                        .setSummaryText("igor_korobka@epam.com"))
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .build();

        notificationManager.notify(NOTIFICATION_PAGES_STACK + 2, summaryNotification);
    }

    public void showSimpleNotification(View view) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("Event");
        builder.setContentText("EPAM IT week");
        builder.addAction(R.drawable.ic_action_av_play, "Play", pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_SIMPLE_ID, builder.build());
    }

}
