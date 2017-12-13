package com.example.mobiapp.newtishansjayaapp.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by mobi app on 17.07.2017.
 */

public class ReminderManager {

    private Context mContext;
    private AlarmManager mAlarmManager;

    public ReminderManager(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setReminder(Long taskId, Calendar calendar, String title, String sound) {


        Intent i = new Intent(mContext, OnAlarmReceiver.class);
        i.putExtra(RemindersDbAdapter.KEY_ROWID, (long)taskId);
        i.putExtra(RemindersDbAdapter.KEY_TITLE, title);
        i.putExtra(RemindersDbAdapter.KEY_SOUND, sound);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, i, PendingIntent.FLAG_ONE_SHOT);
        //calendar.setTimeInMillis(1508605070*1000);

        mAlarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pi);
    }
}