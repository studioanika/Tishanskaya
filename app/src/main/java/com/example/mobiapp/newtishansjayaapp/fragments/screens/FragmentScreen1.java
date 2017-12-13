package com.example.mobiapp.newtishansjayaapp.fragments.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.MainActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.view.AnimationFragmentIn;

/**
 * Created by mobi app on 27.09.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentScreen1 extends Fragment {

    View v;
    TextView tv_next;
    MainActivity activity;

    @SuppressLint("ValidFragment")
    public FragmentScreen1(Context context) {
        activity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_screen_1, container, false);

        return v;
    }

    private void init(){
        tv_next = (TextView) v.findViewById(R.id.sr1_tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.nextFragment();
            }
        });

    }

    @Override
    public void onResume() {
        init();
        super.onResume();
    }
}
