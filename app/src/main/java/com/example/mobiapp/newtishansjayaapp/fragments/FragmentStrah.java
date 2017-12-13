package com.example.mobiapp.newtishansjayaapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.MainActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.TestPAActivity;
import com.example.mobiapp.newtishansjayaapp.view.CardView;

/**
 * Created by mobi app on 28.09.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentStrah extends Fragment implements View.OnClickListener {


    Context context;

    public FragmentStrah(Context context) {
        this.context = context;
    }

    View v;
    CardView cardView1, cardView2;
    Button btn_next;
    int position = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_strah, container, false);
        init();

        return v;
    }
    private void init(){
        cardView1 = (CardView) v.findViewById(R.id.strah_card1);
        cardView1.setChecked();
        cardView1.setText(this.getResources().getString(R.string.question_strah_1));

        cardView2 = (CardView) v.findViewById(R.id.strah_card2);
        cardView2.setText(this.getResources().getString(R.string.question_strah_2));

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);

        btn_next = (Button) v.findViewById(R.id.strah_btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.strah_card1:
                reset();
                if(cardView1.check()) position = 2;
                else position = 1;
                break;
            case R.id.strah_card2:
                reset();
                if(cardView2.check()) position = 1;
                else position = 2;
                break;
            case R.id.strah_btn_next:
                next();
                break;

        }
    }
    private void next(){
        int i = position;
        switch (position){
            case 1:
                ((TestPAActivity) context).fragmentDone();
                break;
            case 2:
                ((TestPAActivity) context).fragmentDone();
                break;
        }
    }

    private void reset(){
        cardView1.dontChecked();
        cardView2.dontChecked();
    }
}
