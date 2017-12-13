package com.example.mobiapp.newtishansjayaapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.TestPAActivity;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.view.CardView;

/**
 * Created by mobi app on 26.09.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentSimptoms extends Fragment implements View.OnClickListener {

    TestPAActivity activity;
    View v;
    int position = 1;
    CardView cardView1, cardView2, cardView3;
    Button btn_next;
    private static final String APP_PREFERENCES = "config";
    private static final String APP_PREFERENCES_TEST = "test";
    private SharedPreferences mSettings;
    Prefs prefs;

    public FragmentSimptoms(TestPAActivity activity){

        this.activity = activity;
        prefs = new Prefs(this.activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_simptoms, container, false);
        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        init();
        return v;
    }

    private void init(){
        cardView1 = (CardView) v.findViewById(R.id.simptoms_card1);
        cardView1.setChecked();
        cardView1.setText(this.getResources().getString(R.string.simptom1_1));

        cardView2 = (CardView) v.findViewById(R.id.simptoms_card2);
        cardView2.setText(this.getResources().getString(R.string.simptom1_2));

        cardView3 = (CardView) v.findViewById(R.id.simptoms_card3);
        cardView3.setText(this.getResources().getString(R.string.simptom1_3));

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);

        btn_next = (Button) v.findViewById(R.id.simptoms_btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.simptoms_card1:
                reset();
                if(!cardView1.check()) position = 1;
                break;
            case R.id.simptoms_card2:
                reset();
                if(!cardView2.check()) position = 2;
                break;
            case R.id.simptoms_card3:
                reset();
                if(!cardView3.check()) position = 3;
                break;
            case R.id.simptoms_btn_next:
                next();
                break;
        }
    }

    private void reset(){
        cardView1.dontChecked();
        cardView2.dontChecked();
        cardView3.dontChecked();
    }

    private void next(){
        switch (position){
            case 1:
                activity.fragmentPanika();
                break;
            case 2:
                showAlert();
                /*setPrefs(4);
                activity.fragmentDone();*/
                break;
            case 3:
                Toast.makeText(activity,"Возможно ваше состояние имеет какой-либо органический фактор, обратитесь пожалуйста к вашему врачу.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setPrefs(int position){
       prefs.setVaht(position);
    }

    private void showAlert(){
        final Dialog dialogEdit = new Dialog(cardView1.getContext());
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_for_fragment_done);

        TextView tv = (TextView) dialogEdit.findViewById(R.id.alert_for_don_text);
        tv.setText("По результатам диагностики, ваши симптомы сходны с симптомами, характерными для монофобического расстройства.");

        Button btn = (Button) dialogEdit.findViewById(R.id.alert_for_don_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPrefs(4);
                activity.fragmentDone();
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