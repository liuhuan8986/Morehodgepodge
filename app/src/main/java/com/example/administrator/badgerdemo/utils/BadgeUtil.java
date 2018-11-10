package com.example.administrator.badgerdemo.utils;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.badgerdemo.R;

import me.leolin.shortcutbadger.ShortcutBadger;

public class BadgeUtil {

    public static final String XIAOMI_BADGE_CHANNELID="xiaomiBadge";
    public static final int NOTIFY_ID = 147852963;
    public static  void setBadgeCount(Context context,int count,NotificationManager manager){
        manager.cancel(NOTIFY_ID);
        if(Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")){
            Notification notification = createNotifycation(context,manager);
            ShortcutBadger.applyNotification(context,notification,count);
            manager.notify(NOTIFY_ID,notification);
        }else{
            ShortcutBadger.applyCount(context,count);
        }
    }

    public static void createChannel(Context context){
        String channelId = XIAOMI_BADGE_CHANNELID;
        String channelName = "订阅消息";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        createNotificationChannel(context,channelId, channelName, importance);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private static  void createNotificationChannel(Context context,String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        channel.enableVibration(false);
        channel.setVibrationPattern(new long[]{0});
        notificationManager.createNotificationChannel(channel);
    }

    private static Notification createNotifycation(Context context,NotificationManager manager){
        PendingIntent pendingIntent=PendingIntent.getActivities(context,-1,new Intent[]{new Intent()},PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context, XIAOMI_BADGE_CHANNELID)
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{0l})
                .build();
        return notification;
    }
}
