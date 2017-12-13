package com.example.mobiapp.newtishansjayaapp.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.mobiapp.newtishansjayaapp.R;

/**
 * Created by mobi app on 29.09.2017.
 */

public class AnimationFragmentIn implements Animation.AnimationListener {

    View v;
    Animation sunRiseAnimation;

    public AnimationFragmentIn(View v) {
        this.v = v;

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        v.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
