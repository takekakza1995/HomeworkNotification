package com.example.most.homeworknotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Most on 30/10/2560.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Wake Up!", Toast.LENGTH_LONG).show();
        Intent intent1 = new Intent(context, AlarmActivity.class);
        //context.startActivity(intent1);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, intent1 , PendingIntent.FLAG_CANCEL_CURRENT);



                NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My Notification")
                        .setContentText("มีงานการบ้านต้องส่งนะ!!")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());


    }


}
