package com.example.mobiapp.newtishansjayaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.db.Prefs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListPredActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4;
    TextView tv1_1, tv2_2, tv3_3, tv4_4;
    RelativeLayout rel1, rel2, rel3, rel4;
    ImageView img1, img2, img3, img4;
    Prefs prefs;
    int col_day = 0;
    CardView card4, card1, card2, card3;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pred);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        init();

    }

    private void init() {

        prefs = new Prefs(this);

        tv1 = (TextView) findViewById(R.id.list_pred_text1);
        tv2 = (TextView) findViewById(R.id.list_pred_text2);
        tv3 = (TextView) findViewById(R.id.list_pred_text3);
        tv4 = (TextView) findViewById(R.id.list_pred_text4);


        tv1_1 = (TextView) findViewById(R.id.list_pred_text1_1);
        tv2_2 = (TextView) findViewById(R.id.list_pred_text2_2);
        tv3_3 = (TextView) findViewById(R.id.list_pred_text3_3);
        tv4_4 = (TextView) findViewById(R.id.list_pred_text4_4);


        img1 = (ImageView) findViewById(R.id.list_pred_img1);
        img2 = (ImageView) findViewById(R.id.list_pred_img2);
        img3 = (ImageView) findViewById(R.id.list_pred_img3);
        img4 = (ImageView) findViewById(R.id.list_pred_img4);

        rel1 = (RelativeLayout) findViewById(R.id.list_pred_rel1);
        rel2 = (RelativeLayout) findViewById(R.id.list_pred_rel2);
        rel3 = (RelativeLayout) findViewById(R.id.list_pred_rel3);
        rel4 = (RelativeLayout) findViewById(R.id.list_pred_rel4);

        card4 = (CardView) findViewById(R.id.card4);
        card3 = (CardView) findViewById(R.id.card3);
        card2 = (CardView) findViewById(R.id.card2);
        card1 = (CardView) findViewById(R.id.card1);

        //card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        initTime();
        initView();

    }

    private void initView(){

        /*switch (prefs.getVaht()){
            case 1:
                initPA();
                break;
            case 2:
                initPAA();
                break;
            case 3:
                initA();
                break;
            case 4:
                initM();
                break;
        }*/

        initM();

    }

    private void initM() {

        if(col_day<=6){
            initCard1("Фантазия страха", true, false, true);
            initCard2("Фантазия страха 5 раз по 5 мин", false, false, false);
            initCard3("Специфическое предписание 1", false, false, false);
            initCard4("Специфическое предписание 2", false, false, false);
        }else if(col_day==7){
            initCard1("Фантазия страха", true, true, true);
            initCard2("Фантазия страха 5 раз по 5 мин", true, false, true);
            initCard3("Специфическое предписание 1", false, false, false);
            initCard4("Специфическое предписание 2", false, false, false);
        }else if(col_day>7&& col_day<=14){
            initCard1("Фантазия страха", true, true, true);
            initCard2("Фантазия страха 5 раз по 5 мин", true, true, true);
            initCard3("Специфическое предписание 1", true, false, true);
            initCard4("Специфическое предписание 2", false, false, false);
        }else{
            initCard1("Фантазия страха", true, true, true);
            initCard2("Фантазия страха 5 раз по 5 мин", true, true, true);
            initCard3("Специфическое предписание 1", true, true, true);
            initCard4("Специфическое предписание 2", false, false, true);
        }

    }

    private void initA() {
    }

    private void initPAA() {

        if(col_day<=6){
            initCard1("Вахтенный журнал", true, false, true);
            initCard2("Фантазия страха", false, false, false);
            initCard3("Фантазия страха 5 раз по 5 мин", false, false, false);
            initCard4("Вахтенный журнал", false, false, false);
        }else if(col_day>6&& col_day<=13){
            initCard1("Вахтенный журнал", true, true, true);
            initCard2("Фантазия страха", true, false, true);
            initCard3("Фантазия страха 5 раз по 5 мин", false, false, false);
            initCard4("Вахтенный журнал", false, false, false);
        }else if(col_day==14){
            initCard1("Вахтенный журнал", true, true, true);
            initCard2("Фантазия страха", true, true, true);
            initCard3("Фантазия страха 5 раз по 5 мин", true, false, true);
            initCard4("Вахтенный журнал", false, false, false);
        }
        else{
            initCard1("Вахтенный журнал", true, true, true);
            initCard2("Фантазия страха", true, true, true);
            initCard3("Фантазия страха 5 раз по 5 мин", true, true, true);
            initCard4("Вахтенный журнал", false, false, true);
        }

    }

    private void initPA() {
        card4.setVisibility(View.GONE);
        // init text pred
        if(col_day<=6){
            initCard1("Вахтенный журнал", true, false, true);
            initCard2("Фантазия страха", false, false, false);
            initCard3("Фантазия страха 5 раз по 5 мин", false, false, false);
        }else if(col_day>6 && col_day<=13){
            initCard1("Вахтенный журнал", true, true, true);
            initCard2("Фантазия страха", true, false, true);
            initCard3("Фантазия страха 5 раз по 5 мин", false, false, false);
        }else {
            initCard1("Вахтенный журнал", true, true, true);
            initCard2("Фантазия страха", true, true, true);
            initCard3("Фантазия страха 5 раз по 5 мин", true, false, true);
        }

    }

    private void initCard1(String text, boolean isImg, boolean isDone,boolean isColor){

        if(isImg) {
            if(isDone){
                img1.setVisibility(View.VISIBLE);
                tv1_1.setVisibility(View.GONE);

            }else {
                img1.setVisibility(View.GONE);
                tv1_1.setVisibility(View.VISIBLE);
            }
        }
        tv1.setText(text);
        if(isColor) rel1.setBackground(getResources().getDrawable(R.drawable.circle_image));

    }

    private void initCard2(String text, boolean isImg, boolean isDone,boolean isColor){

        if(isImg) {
            if(isDone){
                img2.setVisibility(View.VISIBLE);
                tv2_2.setVisibility(View.GONE);

            }else {
                img2.setVisibility(View.GONE);
                tv2_2.setVisibility(View.VISIBLE);
            }
        }
        tv2.setText(text);
        if(isColor) rel2.setBackground(getResources().getDrawable(R.drawable.circle_image));

    }
    private void initCard3(String text, boolean isImg, boolean isDone,boolean isColor){

        if(isImg) {
            if(isDone){
                img3.setVisibility(View.VISIBLE);
                tv3_3.setVisibility(View.GONE);

            }else {
                img3.setVisibility(View.GONE);
                tv3_3.setVisibility(View.VISIBLE);
            }
        }
        tv3.setText(text);
        if(isColor) rel3.setBackground(getResources().getDrawable(R.drawable.circle_image));

    }
    private void initCard4(String text, boolean isImg, boolean isDone,boolean isColor){

        if(isImg) {
            if(isDone){
                img4.setVisibility(View.VISIBLE);
                tv4_4.setVisibility(View.GONE);

            }else {
                img4.setVisibility(View.GONE);
                tv4_4.setVisibility(View.VISIBLE);
            }
        }
        tv4.setText(text);
        if(isColor) rel4.setBackground(getResources().getDrawable(R.drawable.circle_image));

    }

    private void initTime(){
        try {
            String date = prefs.getPred();
            Log.e("date_start:", prefs.getPred());

            Date date1 = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            String reminderDateTime = dateFormat.format(date1.getTime());
            Log.e("date_current:", reminderDateTime);

            Date date_time_start = dateTimeFormat.parse(prefs.getPred()+" 00:01");//   изменить
            Date date_time_current = dateTimeFormat.parse(reminderDateTime+" 00:01");// изменить

            Log.e("date_time_start:", date_time_start.toString());
            Log.e("date_time_current:", date_time_current.toString());


            long date_time_start_l = date_time_start.getTime();
            long date_time_current_l = date_time_current.getTime();

            Log.e("date_time_start_long:", String.valueOf(date_time_start_l));
            Log.e("date_time_current_long:", String.valueOf(date_time_current_l));


            long minus = date_time_current_l - date_time_start_l;
            Log.e("date_time_minus+long:", String.valueOf(minus));

            col_day = (int)minus / 86400000;
            //col_day = 15;   // исправить
            Log.e("col_day:", String.valueOf(col_day));


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                try {
                    startActivity(new Intent(ListPredActivity.this, PredpisanieActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // do what you want to be done on home button click event
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card1:
                clickCard1(1);
                break;
            case R.id.card2:
                clickCard2(1);
                break;
            case R.id.card3:
                clickCard3(1);
                break;
            case R.id.card4:
                clickCard4(1);
                break;
        }
    }

    public void clickCard1(int i){
        switch (prefs.getVaht()){
            case 1:

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    public void clickCard2(int i){
        switch (prefs.getVaht()){
            case 1:
                if(col_day<=6){
                    if((6-col_day)==0){
                        showToast("Это предписание будет доступно завтра");
                    }else {
                        showToast("Это предписание будет доступно через " + (6 - col_day) + " день/дней");
                    }
                }
                break;
            case 2:
                if((6-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (6 - col_day) + " день/дней");

                }
                break;
            case 3:
                break;
            case 4:
                if((6-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (6 - col_day) + " день/дней");
                }
                break;
        }
    }

    public void clickCard3(int i){
        switch (prefs.getVaht()){
            case 1:
                if(col_day>0 && col_day<=13){
                    if((13-col_day)==0){
                        showToast("Это предписание будет доступно завтра");
                    }else {
                        showToast("Это предписание будет доступно через " + (13 - col_day) + " день/дней");
                    }}
                break;
            case 2:
                if((13-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (13 - col_day) + " день/дней");
                }
                break;
            case 3:
                break;
            case 4:
                if((7-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (7 - col_day) + " день/дней");
                }
                break;
        }
    }

    public void clickCard4(int i){
        switch (prefs.getVaht()){
            case 1:
                break;
            case 2:
                if((14-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (14 - col_day) + " день/дней");
                }
                break;
            case 3:
                break;
            case 4:
                if((14-col_day)==0){
                    showToast("Это предписание будет доступно завтра");
                }else {
                    showToast("Это предписание будет доступно через " + (14 - col_day) + " день/дней");
                }
                break;
        }
    }

    private void showToast(String text){

        Toast.makeText(card1.getContext(), text, Toast.LENGTH_SHORT).show();

    }

}
