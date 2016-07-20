package com.jeffreyleeon.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNManager;
    private static final int NOTIDY_ID = 1100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ns = Context.NOTIFICATION_SERVICE;
        mNManager = (NotificationManager) getSystemService(ns);
        Context context = getApplicationContext();
        CharSequence contentTitle = "ShowNotification Example";
        CharSequence contentText = "Browse Android Cookbook Site";
        Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pearson.com"));
        PendingIntent intent = PendingIntent.getActivity(MainActivity.this, 0, msgIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
        final Notification msg = new Notification.Builder(context)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.drawable.icon)
                .setContentIntent(intent)
                .build();

        Button start = (Button) findViewById(R.id.start);
        Button cancel = (Button) findViewById(R.id.cancel);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                msg.defaults |= Notification.DEFAULT_SOUND;
                msg.flags |= Notification.FLAG_AUTO_CANCEL;
                mNManager.notify(NOTIDY_ID, msg);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mNManager.cancel(NOTIDY_ID);
            }
        });
    }
}
