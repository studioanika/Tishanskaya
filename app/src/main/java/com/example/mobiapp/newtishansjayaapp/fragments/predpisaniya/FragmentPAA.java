package com.example.mobiapp.newtishansjayaapp.fragments.predpisaniya;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.ListPredActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.ZhurnalActivity;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mobi app on 23.11.2017.
 */

public class FragmentPAA extends Fragment {

    View v;
    Prefs prefs;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd kk:mm";

    TextView tv_col_day, tv_text;
    CardView card_vaht,card_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_pred_pa, container, false);

        return v;
    }

    private void initView(){
        tv_col_day = (TextView) v.findViewById(R.id.fragment_pred_pa_tv_col_day);
        tv_text = (TextView) v.findViewById(R.id.fragment_pred_pa_tv_text);
        card_vaht = (CardView) v.findViewById(R.id.cardView3);
        card_vaht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ZhurnalActivity.class);
                startActivity(intent);
            }
        });
        card_list = (CardView) v.findViewById(R.id.cardView2);
        card_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(v.getContext(), ListPredActivity.class));
            }
        });
    }

    private void init() {
        initView();
        try {
            prefs = new Prefs(v.getContext());
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

            int col_day = (int)minus / 86400000;
            Log.e("col_day:", String.valueOf(col_day));

            setTextPred(col_day);

            String d = "";
        } catch (Exception e) {
            Log.e("error_fragment:", e.toString());
            e.printStackTrace();
        }
    }

    private void setTextPred(int col_day){
        if(col_day<=6){
            if((6-col_day)==0){
                tv_col_day.setText("Следующее предписание будет доступно завтра");
            }else {
                tv_col_day.setText("Следующее предписание будет доступно через " + (6 - col_day) + " день/дней");
                tv_text.setText(getResources().getString(R.string.predpisanie_vaht));
            }
        }else if(col_day>6&& col_day<=13){
            if((13-col_day)==0){
                tv_col_day.setText("Следующее предписание будет доступно завтра");
            }else {
                tv_col_day.setText("Следующее предписание будет доступно через " + (13 - col_day) + " день/дней");
                tv_text.setText(getResources().getString(R.string.predpisanie_fantazi));
            }
        }else if(col_day==14){
            tv_col_day.setText("Следующее предписание будет доступно завтра");
            tv_text.setText(getResources().getString(R.string.predpisanie_fantazi_5));
        }
        else{
            tv_col_day.setText("Это последнее предписание");
            tv_text.setText(getResources().getString(R.string.predpisanie_spec_pa));
        }
    }

    @Override
    public void onResume() {
        init();
        super.onResume();
    }

}
