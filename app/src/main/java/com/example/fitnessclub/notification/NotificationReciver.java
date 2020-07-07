package com.example.fitnessclub.notification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fitnessclub.CreateChannel.Channel;
import com.example.fitnessclub.R;

public class NotificationReciver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    Context context;

    public NotificationReciver(Context context) {
        this.context = context;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        boolean noConnectivity;
        notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,
                    false);

            if (noConnectivity) {
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                DisplayNotification1();
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                DisplayNotification2();
            }
        }
    }


    private void DisplayNotification1() {
        Notification notification=new NotificationCompat.Builder(context, Channel.CHANNEL_1)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Fitness Club")
                .setContentText("Internet is not connected! please connect the network")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(6,notification);
    }

    private void DisplayNotification2() {
        Notification notification=new NotificationCompat.Builder(context, Channel.CHANNEL_1)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal)
                .setContentTitle("Fitness Club")
                .setContentText("Network connected successfull")
                .setCategory(NotificationCompat.CATEGORY_SYSTEM)
                .build();

        notificationManagerCompat.notify(7,notification);
    }
}
