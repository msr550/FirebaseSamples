package com.msr.firebasesamples.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.msr.firebasesamples.R;
import com.msr.firebasesamples.activities.ResetPasswordActivity;
import com.msr.firebasesamples.activities.SignInActivity;
import com.msr.firebasesamples.utils.NotificationID;

import java.util.Random;

/**
 * Created by Sandeep on 2/16/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "==MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "==From: " + remoteMessage.getFrom());
        // Log.d(TAG, "==Notification Message Body: " + remoteMessage.getNotification().getBody());
        if (remoteMessage.getData() != null && remoteMessage.getData().size() > 0) {
            Log.d(TAG, "==Message data payload: " + remoteMessage.getData());
            sendNotification(remoteMessage.getData().get("click_action"), remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
        } else {
            Log.e(TAG, "==Message data payload null: ");
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "==Notification Message Body: " + remoteMessage.getNotification().getBody());

            Log.d(TAG, "Notification Click Action: " + remoteMessage.getNotification().getClickAction());

            sendNotification(remoteMessage.getNotification().getClickAction(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        } else {
            Log.e(TAG, "==notification data null: ");

        }
        //Calling method to generate notification

    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String module, String title, String messageBody) {
        Intent intent = new Intent(this, SignInActivity.class);
        switch (module) {
            case "signin":
                intent = new Intent(this, SignInActivity.class);
                break;
            case "rt":
                intent = new Intent(this, ResetPasswordActivity.class);
                break;
            default:
        }
        Random random = new Random();
        int requestCode = random.nextInt(999999);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NotificationID.getID(), notificationBuilder.build());
    }
}
