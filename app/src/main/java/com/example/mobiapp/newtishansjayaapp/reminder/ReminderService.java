package com.example.mobiapp.newtishansjayaapp.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.example.mobiapp.newtishansjayaapp.PredpisanieActivity;
import com.example.mobiapp.newtishansjayaapp.R;


/**
 * Created by mobi app on 17.07.2017.
 */

public class ReminderService extends WakeReminderIntentService {
    Notification myNotication;

    public ReminderService() {
        super("ReminderService");
    }

    @Override
    void doReminderWork(Intent intent) {
        Log.d("ReminderService", "Doing work.");
        Long rowId = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);
        String title = intent.getExtras().getString(RemindersDbAdapter.KEY_TITLE);
        String sound = intent.getExtras().getString(RemindersDbAdapter.KEY_SOUND);

        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, PredpisanieActivity.class);
        notificationIntent.putExtra(RemindersDbAdapter.KEY_ROWID, rowId);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification.Builder builder = new Notification.Builder(this);

        builder.setAutoCancel(true);
        builder.setTicker("Выполнение предписания");
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(title!=null?title:"Новое уведомление");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pi);
        builder.setOngoing(true);
        builder.setNumber(100);
        if(Integer.parseInt(sound) == 1) builder.setSound(defaultSoundUri);
        builder.build();

        myNotication = builder.getNotification();
        int id = (int)((long)rowId);
        mgr.notify(id, myNotication);




    }
}