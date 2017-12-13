package com.example.mobiapp.newtishansjayaapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.adapter.TypePAAdapter;
import com.example.mobiapp.newtishansjayaapp.classes.GenerateTypePA;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.reminder.RemindersDbAdapter;

public class DescriptionPAActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    Prefs prefs;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_pa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_type_pa);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        GenerateTypePA generateTypePA = new GenerateTypePA(this);

        recyclerView.setAdapter(new TypePAAdapter(this, generateTypePA.getAllTypePA()));
        prefs = new Prefs(this);

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
    public void showSnack(){
        Snackbar snackbar = Snackbar.make(recyclerView,"Требуется пройти тест." , Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(DescriptionPAActivity.this, TestPAActivity.class));
                    }
                });
        snackbar.setDuration(3000);
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        // 8 секунд
        snackbar.show();
    }

    public void startTest(int position){
        Intent intent = new Intent(DescriptionPAActivity.this, PredpisanieActivity.class);
        intent.putExtra("id",position);
        startActivity(intent);
    }

    public void showSnackNewTask() {
        Snackbar snackbar = Snackbar.make(recyclerView,"Сбросить результаты диагностики?" , Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            prefs.setVaht(0);
                            new RemindersDbAdapter(recyclerView.getContext()).open().removeAllData();
                            startActivity(new Intent(DescriptionPAActivity.this, TestPAActivity.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        snackbar.setDuration(3000);
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        // 8 секунд
        snackbar.show();
    }

    public void showSnackPred(final int position) {

        final Dialog dialogEdit = new Dialog(this);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_pred_next);

        TextView tv_ok = (TextView) dialogEdit.findViewById(R.id.alert_pred_next_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest(position);
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogEdit.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogEdit.show();
        dialogEdit.getWindow().setAttributes(lp);

    }
}
