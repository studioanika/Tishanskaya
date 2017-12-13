package com.example.mobiapp.newtishansjayaapp.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mobi app on 17.07.2017.
 */

public class OnBootReceiver extends BroadcastReceiver {

    private static final String TAG = ComponentInfo.class.getCanonicalName();
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";

    @Override
    public void onReceive(Context context, Intent intent) {

        ReminderManager reminderMgr = new ReminderManager(context);

        RemindersDbAdapter dbHelper = new RemindersDbAdapter(context);
        dbHelper.open();

        Cursor cursor = dbHelper.fetchAllReminders();

        if(cursor != null) {
            cursor.moveToFirst();

            int rowIdColumnIndex = cursor.getColumnIndex(RemindersDbAdapter.KEY_ROWID);
            int dateTimeColumnIndex = cursor.getColumnIndex(RemindersDbAdapter.KEY_DATE_TIME);
            int titleColumn = cursor.getColumnIndex(RemindersDbAdapter.KEY_TITLE);
            int soundColumn = cursor.getColumnIndex(RemindersDbAdapter.KEY_SOUND);

            while(cursor.isAfterLast() == false) {

                Log.d(TAG, "Adding alarm from boot.");
                Log.d(TAG, "Row Id Column Index - " + rowIdColumnIndex);
                Log.d(TAG, "Date Time Column Index - " + dateTimeColumnIndex);

                Long rowId = cursor.getLong(rowIdColumnIndex);
                String dateTime = cursor.getString(dateTimeColumnIndex);
                String titleString = cursor.getString(titleColumn);
                String soundString = cursor.getString(titleColumn);


                Calendar cal = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);

                try {
                    java.util.Date date = format.parse(dateTime);
                    cal.setTime(date);

                    reminderMgr.setReminder(rowId, cal,titleString, soundString);
                } catch (java.text.ParseException e) {
                    Log.e("OnBootReceiver", e.getMessage(), e);
                }

                cursor.moveToNext();
            }
            cursor.close() ;
        }

        dbHelper.close();
    }
}

