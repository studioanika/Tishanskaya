package com.example.mobiapp.newtishansjayaapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.classes.ItemPredpisanie;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.fragments.predpisaniya.FragmentA;
import com.example.mobiapp.newtishansjayaapp.fragments.predpisaniya.FragmentM;
import com.example.mobiapp.newtishansjayaapp.fragments.predpisaniya.FragmentPA;
import com.example.mobiapp.newtishansjayaapp.fragments.predpisaniya.FragmentPAA;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen5;
import com.example.mobiapp.newtishansjayaapp.reminder.ReminderManager;
import com.example.mobiapp.newtishansjayaapp.reminder.RemindersDbAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PredpisanieActivity extends AppCompatActivity {

    private static Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            setContentView(R.layout.activity_predpisanie);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);



            init();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    private void init(){
        prefs = new Prefs(this);
        Log.e("prefs_type&", String.valueOf(prefs.getVaht()));
        switch (prefs.getVaht()){
            case 1:
                fragment = new FragmentPA();
                break;
            case 2:
                fragment = new FragmentPAA();
                break;
            case 3:
                fragment = new FragmentA();
                break;
            case 4:
                fragment = new FragmentM();
                break;
        }
        //fragment = new FragmentPAA();
        transactionFragment();


    }

    private void transactionFragment(){
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        // Stop listening to voice

        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(PredpisanieActivity.this, MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
