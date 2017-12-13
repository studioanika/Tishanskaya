package com.example.mobiapp.newtishansjayaapp.classes;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateFormat;
import android.widget.Toast;


import com.example.mobiapp.newtishansjayaapp.reminder.ReminderManager;
import com.example.mobiapp.newtishansjayaapp.reminder.RemindersDbAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mobi app on 19.10.2017.
 */

public class GeneratePredpisanie {

    Context context;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "kk:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm:ss";
    private RemindersDbAdapter mDbHelper;
    private Long mRowId;
    Calendar cal;
    SimpleDateFormat dateTimeFormat;


    public GeneratePredpisanie(Context context) {

        this.context = context;
        mDbHelper = new RemindersDbAdapter(this.context);
        mDbHelper.open();
        cal = Calendar.getInstance();
        dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

    }

    public void generatePA(){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date date = new Date();
        String reminderDateTime = dateTimeFormat.format(date);
        saveState(reminderDateTime,53);
        //saveState(reminderDateTime,50);
    }

    public void saveState(String reminderDateTime, int time) {
        String dateString = "";

        try {

            String s = reminderDateTime;
            String[] date_arr = s.split(" ");
            String new_date = "";
            new_date = date_arr[0] + " 18:"+String.valueOf(time)+":00";
            Date date_new = dateTimeFormat.parse(new_date);
            long it = (long) (date_new.getTime());
            long long_new_day_date = 1508489416;
            long new_day_date = 0;

            new_day_date = it + 86400;
            dateString = DateFormat.format(DATE_TIME_FORMAT, new Date(new_day_date)).toString();
            long id = mDbHelper.createReminder("1", String.valueOf(45), "2017-10-21 17:01:00", "0");
            mRowId = id;
            Calendar cal = Calendar.getInstance();

            cal.setTime(dateTimeFormat.parse(dateString));
            new ReminderManager(this.context).setReminder(mRowId, cal,"hi 16.1700","0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this.context, "TRUE", Toast.LENGTH_SHORT).show();

    }


}
