package com.example.mobiapp.newtishansjayaapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentMan;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentOtvlechenie;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentPaket;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentScratchView;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentSvechi;
import com.example.mobiapp.newtishansjayaapp.fragments.FragmentZhivot;

public class StopActivity extends AppCompatActivity {

    private static Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    FloatingActionButton fab;
    float dX, dY;
    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        prefs = new Prefs(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String s = "";
        switch (id){
            case 0:
                fragment = new FragmentMan();
                break;
            case 1:
                fragment = new FragmentOtvlechenie();
                break;
            case 2:
                fragment = new FragmentSvechi();
                break;
            case 3:
                fragment = new FragmentZhivot();
                break;
            case 4:
                fragment = new FragmentScratchView();
                break;
            case 5:
                fragment = new FragmentPaket();
                break;
        }

        //fragment = new FragmentScratchView();
        //fragment = new FragmentSvechi();
        //fragment = new FragmentZhivot();
        //fragment = new FragmentOtvlechenie();
        transactionFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void transactionFragment(){
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void showAlert(){

        final Dialog dialogEdit = new Dialog(this);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_question_da_net);

        TextView tv_yes = (TextView) dialogEdit.findViewById(R.id.alert_question_yes);
        TextView tv_no = (TextView) dialogEdit.findViewById(R.id.alert_question_no);

        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEdit.dismiss();
            }
        });


        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StopActivity.this, TestPAActivity.class));

            }
        });

        dialogEdit.show();

    }

}
