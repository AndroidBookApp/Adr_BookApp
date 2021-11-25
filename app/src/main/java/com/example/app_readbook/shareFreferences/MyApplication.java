package com.example.app_readbook.shareFreferences;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID = "channel_id";
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        CreateNotificationChannel();
        DataManager.init(getApplicationContext());

    }

    private void CreateNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID , "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);;
        }
    }
}
