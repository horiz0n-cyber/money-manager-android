package com.example.project07.reminder;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.project07.LoginActivity;
import com.example.project07.MainActivity;
import com.example.project07.R;

import java.util.Date;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // gọi acitvity khi bam vao noti
        Intent intent1 = new Intent(context, LoginActivity.class);
        PendingIntent resultIntent = PendingIntent.getActivity(
                context,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(context,MyAplication.CHANNEL_ID)
                .setContentTitle("Bạn có khoản chi cần thanh toán hôm nay")
                .setContentText("Ấn để xem chi tiết")
                .setSmallIcon(R.drawable.ic_pay)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(resultIntent)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(getNotificationId(), notification);
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}
