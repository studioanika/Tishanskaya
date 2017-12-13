package com.example.mobiapp.newtishansjayaapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.PredpisanieActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.TestPAActivity;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.view.CardView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mobi app on 26.09.2017.
 */
@SuppressLint("ValidFragment")
public class FragmentAgorafobia extends Fragment implements View.OnClickListener {

    TestPAActivity activity;
    View v;
    CardView cardView1, cardView2;
    Button btn_next;
    int position = 1;
    Prefs prefs;

    public FragmentAgorafobia(TestPAActivity activity){

        this.activity = activity;
        prefs = new Prefs(this.activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_agorafobiya, container, false);
        init();
        return  v;
    }

    private void init(){
        cardView1 = (CardView) v.findViewById(R.id.agorafobiya_card1);
        cardView1.setChecked();
        cardView1.setText(this.getResources().getString(R.string.simptom_yes));

        cardView2 = (CardView) v.findViewById(R.id.agorafobiya_card2);
        cardView2.setText(this.getResources().getString(R.string.simptom_no));

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);

        btn_next = (Button) v.findViewById(R.id.agorafobiya_btn_next);
        btn_next.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.agorafobiya_card1:
                reset();
                if(cardView1.check()) position = 2;
                else position = 1;
                break;
            case R.id.agorafobiya_card2:
                reset();
                if(cardView2.check()) position = 1;
                else position = 2;
                break;
            case R.id.agorafobiya_btn_next:
                next();
                break;
        }
    }
    private void reset(){
        cardView1.dontChecked();
        cardView2.dontChecked();
    }

    private void next(){
        int i = position;
        switch (position){
            case 1:
                showAlert(1);
                /*setPrefs(3);
                activity.fragmentStrah();*/
                break;
            case 2:
                showAlert(2);
                /*setPrefs(1);
                activity.fragmentDone();*/
                //Toast.makeText(activity, "Возможно ваше состояние имеет какой-либо органический фактор, обратитесь пожалуйста к вашему врачу.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setPrefs(int i) {
        prefs.setVaht(i);
    }


    private void showAlert(final int code){
        final Dialog dialogEdit = new Dialog(cardView1.getContext());
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_for_fragment_done);

        TextView tv = (TextView) dialogEdit.findViewById(R.id.alert_for_don_text);
        switch (code){
            case 3:
                tv.setText("По результатам диагностики, ваши симптомы сходны с симптомами, характерными для агорафобии без панических атак");
                break;
            case 1:
                tv.setText("По результатам диагностики, ваши симптомы сходны с симптомами, характерными для панических атак.");
                break;
        }

        Button btn = (Button) dialogEdit.findViewById(R.id.alert_for_don_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (code){
                    case 1:
                        setPrefs(1);
                        activity.fragmentDone();
                        break;
                    case 3:
                        setPrefs(3);
                        activity.fragmentStrah();
                        break;
                }
                dialogEdit.dismiss();
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
