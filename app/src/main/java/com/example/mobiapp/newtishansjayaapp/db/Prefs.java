package com.example.mobiapp.newtishansjayaapp.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobiapp.newtishansjayaapp.reminder.RemindersDbAdapter;

/**
 * Created by mobi app on 22.09.2017.
 */

public class Prefs {

    Context context;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_FIRST = "first";
    private static final String APP_PREFERENCES_REVIEW = "review";
    private static final String APP_PREFERENCES_VAHT = "vaht";
    private static final String APP_PREFERENCES_ROW = "row";
    private static final String APP_PREFERENCES_PRED = "pred";
    private static final String APP_PREFERENCES_PRED_FIRST = "pred_first";
    private static final String APP_PREFERENCES_PRED_CODE = "pred_code";
    private SharedPreferences mSettings;

    public Prefs(Context context) {
        this.context = context;
        mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    public int  getFirst(){
        return Integer.parseInt(mSettings.getString(APP_PREFERENCES_FIRST,"0"));
    }

    public void setFirst(){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_FIRST, "1");
        editor.apply();
    }

    public int getVaht(){
        return Integer.parseInt(mSettings.getString(APP_PREFERENCES_VAHT,"0"));
    }

    public void setVaht(int position){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_VAHT, String.valueOf(position));
        editor.apply();
    }

    public int getROW(){
        return Integer.parseInt(mSettings.getString(APP_PREFERENCES_ROW,"0"));
    }

    public void setROW(){
        int position = Integer.parseInt(mSettings.getString(APP_PREFERENCES_ROW,"0"));
        position+=1;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_ROW, String.valueOf(position));
        editor.apply();
    }
    public String getPred(){
        return mSettings.getString(APP_PREFERENCES_PRED,"0");
    }

    public void setPred(String pred){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_PRED, pred);
        editor.apply();
    }
    public int getPredFirst(){
        return Integer.parseInt(mSettings.getString(APP_PREFERENCES_PRED_FIRST,"0"));
    }

    public void setPredFirst(){
        int position = Integer.parseInt(mSettings.getString(APP_PREFERENCES_PRED_FIRST,"0"));
        position+=1;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_PRED_FIRST, String.valueOf(position));
        editor.apply();
    }

    public String getPredCode(){
        return mSettings.getString(APP_PREFERENCES_PRED_CODE,"");
    }

    public void setPredCode(){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_PRED_CODE, "Победить страх");
        editor.apply();
    }

    public void setReview(int pred){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_REVIEW, String.valueOf(pred));
        editor.apply();
    }
    public int getReview(){
        return Integer.parseInt(mSettings.getString(APP_PREFERENCES_REVIEW,"0"));
    }
}
