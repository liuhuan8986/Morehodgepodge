package com.example.administrator.badgerdemo;

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
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.badgerdemo.utils.BadgeUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private Button remove;
    private Button show;
    private TextView textView;
    NotificationManager manager ;
    int notifyId = 123456;
    int count = 55;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        show = findViewById(R.id.show);
        textView = findViewById(R.id.text);
        manager.cancel(BadgeUtil.NOTIFY_ID);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            BadgeUtil.createChannel(this);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "add", Toast.LENGTH_SHORT).show();
                BadgeUtil.setBadgeCount(MainActivity.this,count++,manager);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "remove", Toast.LENGTH_SHORT).show();
                ShortcutBadger.removeCount(MainActivity.this);
                manager.cancel(BadgeUtil.NOTIFY_ID);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(getText());
            }
        });
    }


    private  void test3(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
/*        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);*/
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        channel.enableVibration(false);
        channel.setVibrationPattern(new long[]{0});
        manager.createNotificationChannel(channel);
    }

    private void test4(){
        PendingIntent pendingIntent=PendingIntent.getActivities(this,-1,new Intent[]{new Intent()},PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        ShortcutBadger.applyNotification(this,notification,66);
        manager.notify(notifyId, notification);
    }

    private String getText(){
        return "这里是显示的是全量包 Version"+BuildConfig.VERSION_NAME;
        //return "这里是显示的是打补丁之前的文本";
    }

}
