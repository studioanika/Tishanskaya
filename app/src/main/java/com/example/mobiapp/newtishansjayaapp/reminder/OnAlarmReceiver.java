package com.example.mobiapp.newtishansjayaapp.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.util.Log;

/**
 * Created by mobi app on 17.07.2017.
 */

public class OnAlarmReceiver extends BroadcastReceiver {

    private static final String TAG = ComponentInfo.class.getCanonicalName();


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received wake up from alarm manager.");

        long rowid = intent.getExtras().getLong(RemindersDbAdapter.KEY_ROWID);
        String title = intent.getExtras().getString(RemindersDbAdapter.KEY_TITLE);
        String sound = intent.getExtras().getString(RemindersDbAdapter.KEY_SOUND);

        WakeReminderIntentService.acquireStaticLock(context);

        Intent i = new Intent(context, ReminderService.class);
        i.putExtra(RemindersDbAdapter.KEY_ROWID, rowid);
        i.putExtra(RemindersDbAdapter.KEY_TITLE, title);
        i.putExtra(RemindersDbAdapter.KEY_SOUND, sound);
        context.startService(i);

    }
}