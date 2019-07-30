package com.torqueblueblood.application.rkgitsac.Service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.torqueblueblood.application.rkgitsac.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData()!=null){
            sendNotification(remoteMessage);
            }
    }
    private  void  sendNotification(RemoteMessage remoteMessage){
        Map<String,String>data=remoteMessage.getData();
        String title=data.get("title");
        String content=data.get("content");

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="RKGIT SAc";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //only active for android O and higher as it needs Notification Channel

            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "RKGIT SAc Notification",
                    NotificationManager.IMPORTANCE_MAX);

            notificationChannel.setDescription("RKGIT SAc for your future");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(android.support.design.R.color.abc_background_cache_hint_selector_material_light);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.logo_round)
                .setTicker("ISHANK")
                .setContentTitle(title)
                .setContentText(content)
                .setContentInfo("Info");

        notificationManager.notify(1,notificationBuilder.build());
    }
}
