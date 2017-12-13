package com.example.mobiapp.newtishansjayaapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.view.DiseaseView;

public class SelectStopActivity extends AppCompatActivity implements View.OnClickListener{

    RadioGroup rg;
    Button btnNext;
    int checkId = 0;
    Prefs prefs;
    String testErrorTech = "ПРЕДПИСАНИЯ";
    DiseaseView view1,view2,view3,view4, view5, view6;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_stop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = new Prefs(this);

        if(prefs.getVaht()==0) testErrorTech = "ДИАГНОСТИКА";
        //setErrorTech();

        rg = (RadioGroup) findViewById(R.id.select_stop_rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioButton5:
                        checkId = 0;
                        break;
                    case R.id.radioButton4:
                        checkId = 1;
                        break;
                    case R.id.radioButton3:
                        checkId = 2;
                        break;
                    case R.id.radioButton2:
                        checkId = 3;
                        break;
                    case R.id.radioButton:
                        checkId = 4;
                        break;
                }
            }
        });
        btnNext = (Button) findViewById(R.id.select_stop_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStopActivity.this, StopActivity.class);
                intent.putExtra("id",checkId);
                startActivity(intent);
            }
        });

        view1 = (DiseaseView) findViewById(R.id.view1);
        view1.setImage(R.drawable.dihanie_512);
        view1.setText("Дыхание и расслабление");
        view1.setOnClickListener(this);

        view2 = (DiseaseView) findViewById(R.id.view2);
        view2.setImage(R.drawable.paket_512);
        view2.setText("Дыхание в бумажный пакет");
        view2.setOnClickListener(this);

        view3 = (DiseaseView) findViewById(R.id.view3);
        view3.setImage(R.drawable.tort_512);
        view3.setText("Свечки на торте");
        view3.setOnClickListener(this);

        view4 = (DiseaseView) findViewById(R.id.view4);
        view4.setImage(R.drawable.jivot_512);
        view4.setText("Дихание животом");
        view4.setOnClickListener(this);

        view5 = (DiseaseView) findViewById(R.id.view5);
        view5.setImage(R.drawable.stiranie_512);
        view5.setText("Потрите картинку и получите напутствие на предстоящий день");
        view5.setOnClickListener(this);

        view6 = (DiseaseView) findViewById(R.id.view6);
        view6.setImage(R.drawable.schet_512);
        view6.setText("Счет");
        view6.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        setErrorTech();
        //super.onBackPressed();
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

    private void setErrorTech() {

        final Dialog dialogEdit = new Dialog(this);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_error_tech);


        TextView tvErrorText = (TextView) dialogEdit.findViewById(R.id.error_tech_tv);


        Button buttonErrorTech = (Button) dialogEdit.findViewById(R.id.error_tech_btn);
        buttonErrorTech.setText(testErrorTech);

        buttonErrorTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefs.getVaht()==0) startActivity(new Intent(SelectStopActivity.this, TestPAActivity.class));
                else startActivity(new Intent(SelectStopActivity.this, PredpisanieActivity.class));
            }
        });

        ImageView imgClose = (ImageView) dialogEdit.findViewById(R.id.error_tech_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEdit.dismiss();
                finish();
            }
        });




        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogEdit.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogEdit.show();
        dialogEdit.getWindow().setAttributes(lp);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view1:
                checkId = 0;
                break;
            case R.id.view2:
                checkId = 5;
                break;
            case R.id.view3:
                checkId = 2;
                break;
            case R.id.view4:
                checkId = 3;
                break;
            case R.id.view5:
                checkId = 4;
                break;
            case R.id.view6:
                checkId = 1;
                break;
        }
        Intent intent = new Intent(SelectStopActivity.this, StopActivity.class);
        intent.putExtra("id",checkId);
        startActivity(intent);
    }

}
