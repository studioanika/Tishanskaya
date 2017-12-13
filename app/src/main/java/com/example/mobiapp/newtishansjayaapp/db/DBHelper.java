package com.example.mobiapp.newtishansjayaapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mobi app on 09.09.2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app";
    public static final String TABLE_COMPANY = "list";

    public static final String TABLE_COMPANY_PA = "pa";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "_date";
    public static final String KEY_TIME = "_time";
    public static final String KEY_TEXT = "_text";
    public static final String KEY_PLACE = "_place";
    public static final String KEY_PEOPLE = "_people";
    public static final String KEY_SITUATION = "_situation";
    public static final String KEY_THOUGHTS = "_thoughts";
    public static final String KEY_SYMPTOMS = "_symptoms";
    public static final String KEY_SYMPTOMS1 = "_symptoms1";
    public static final String KEY_SYMPTOMS2 = "_symptoms2";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_COMPANY + "(" + KEY_ID
                + " integer primary key," + KEY_DATE + " text,"+
                KEY_TIME + " text,"
                +KEY_PLACE + " text,"
                +KEY_PEOPLE + " text,"
                +KEY_SITUATION + " text,"
                +KEY_THOUGHTS + " text,"
                +KEY_SYMPTOMS + " text,"
                +KEY_SYMPTOMS1 + " text,"
                + KEY_SYMPTOMS2 + " text"+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_COMPANY);
        onCreate(db);
    }




}
