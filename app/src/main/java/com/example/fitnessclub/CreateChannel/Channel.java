package com.example.fitnessclub.CreateChannel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class Channel {
    Context context;
    public final static String CHANNEL_1 = "channel1";

    public Channel(Context context) {
        this.context = context;
    }

    public void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("This is channel 1");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
