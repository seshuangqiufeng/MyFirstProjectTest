package com.guoqiang.broadcasttest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wangguoqiang on 2016/8/23.
 */
public class MyReceiver extends BroadcastReceiver {

    private NotificationManager mNotification;
    private Notification.Builder builder;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotification = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context).setContentTitle("来短信了！！")
                .setContentText("今天晚上去哪里？？")
                .setSmallIcon(R.drawable.icon_weituo);
        mNotification.notify(1001,builder.build());

    }
}
