package com.example.mobiapp.newtishansjayaapp.fragments;

import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.classes.Paths;
import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.FillableLoaderBuilder;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by mobi app on 21.09.2017.
 */

public class FragmentMan extends Fragment implements MediaPlayer.OnCompletionListener {

    View v;
    int seconds = 0;
    final Handler handler = new Handler();
    final Handler handlerDelay = new Handler();
    private Runnable myRunnable, myRunnableDelay;
    private Animation mEnlargeAnimation, mArcAnim;
    private boolean isAnim = false;
    FillableLoader fillableLoaderIn;
    ImageView imgAnim;
    //SmallBang mSmallBang;
    TextView tvTimer, tvPlay;
    private boolean isVdoh = false;
    RelativeLayout rel_play;
    private boolean isPlay = false;
    ImageView img_play;
    FillableLoader fillableLoader;
    //GifView pGif;
    MediaPlayer mediaPlayer;
    ImageView courses_img_gif, courses_img_gif_clone;
    ImageView img_bg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            v = inflater.inflate(R.layout.fragment_man, container, false);
            MobileAds.initialize(this.getActivity(),getResources().getString(R.string.ads));
            AdView mAdView = (AdView) v.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
            RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.courses_empty);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500, 500);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            fillableLoaderIn = loaderBuilder
                    .parentView(relativeLayout)
                    .layoutParams(params)
                    .originalDimensions(500, 500)
                    .svgPath(Paths.LEGHKIE)
                    .strokeWidth(2)
                    .strokeColor(Color.parseColor("#1c9ade"))
                    .fillColor(Color.parseColor("#1c9ade"))
                    .strokeDrawingDuration(1)
                    .fillDuration(4500)
                    .clippingTransform(new WavesClippingTransform())
                    .build();

            fillableLoaderIn.setSvgPath(Paths.LEGHKIE);
            //fillableLoaderIn.setVisibility(View.GONE);
            //fillableLoaderIn.start();




            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    private void init(){
        try {
            img_bg = (ImageView) v.findViewById(R.id.man_img_bg);
            Glide.with(img_bg.getContext()).load(R.drawable.bg_dih).into(img_bg);
            courses_img_gif = (ImageView) v.findViewById(R.id.courses_img_gif);
            courses_img_gif_clone = (ImageView) v.findViewById(R.id.courses_img_gif_clone);
            Glide.with(this).load(R.drawable.m3).into(courses_img_gif);
            Glide.with(this).load(R.drawable.m3).into(courses_img_gif_clone);
            courses_img_gif_clone.setVisibility(View.GONE);
            //tvPlay = (TextView) v.findViewById(R.id.courses_play_text);
            tvTimer = (TextView) v.findViewById(R.id.courses_timerText);
            rel_play = (RelativeLayout) v.findViewById(R.id.courses_play_rel);
            img_play = (ImageView) v.findViewById(R.id.courses_img_paly);
            rel_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isPlay) {
                        in();
                        startSound1();
                        if(isPlay)animImage();
                        isPlay = true;
                        isVdoh = true;
                        if(myRunnable!=null)handler.removeCallbacks(myRunnable);
                        runTimer(0);
                        img_play.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_pause_black_24dp));
                    }else {
                        reset();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void in(){
        try {
            //startSound1();
            //courses_img_gif_clone.setVisibility(View.VISIBLE);
            Glide.with(this).load(R.drawable.m1).transition(GenericTransitionOptions.with(animationObjectIn)).into(courses_img_gif);
            //courses_img_gif_clone.setVisibility(View.GONE);
            isPlay = true;
            isVdoh = true;
            if(myRunnable!=null)handler.removeCallbacks(myRunnable);
            if(myRunnableDelay!=null)handlerDelay.removeCallbacks(myRunnableDelay);
            runTimer(0);
            fillableLoaderIn.setVisibility(View.GONE);
            fillableLoaderIn.setRotation(0);
            fillableLoaderIn.setVisibility(View.VISIBLE);
            fillableLoaderIn.setSvgPath(Paths.LEGHKIE);
            fillableLoaderIn.start();
            //fillableLoaderIn.setVisibility(View.GONE);
            fillableLoaderIn.setOnStateChangeListener(new OnStateChangeListener() {
                @Override
                public void onStateChange(int state) {
                    switch(state) {
                        case State.FILL_STARTED:

                            break;
                        case State.FINISHED:
                            delay();
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ViewPropertyTransition.Animator animationObjectIn = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(500);
            fadeAnim.start();
        }
    };

    ViewPropertyTransition.Animator animationObjectDelay = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(2000);
            fadeAnim.start();
        }
    };

    ViewPropertyTransition.Animator animationObjectOut = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(500);
            fadeAnim.start();
        }
    };

    ViewPropertyTransition.Animator animationObjectFirst = new ViewPropertyTransition.Animator() {
        @Override
        public void animate(View view) {
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            fadeAnim.setDuration(500);
            fadeAnim.start();
        }
    };

    private void out(){
        try {
            handlerDelay.removeCallbacks(myRunnableDelay);
            //startSound2();
            Glide.with(this).load(R.drawable.m2).transition(GenericTransitionOptions.with(animationObjectOut)).into(courses_img_gif);
            isVdoh = false;
            handler.removeCallbacks(myRunnable);
            runTimer(1);
            animImage();
            fillableLoaderIn.setVisibility(View.GONE);
            fillableLoaderIn.setRotation(180);
            fillableLoaderIn.setVisibility(View.VISIBLE);
            fillableLoaderIn.setSvgPath(Paths.LEGHKIE);
            fillableLoaderIn.start();
            fillableLoaderIn.setOnStateChangeListener(new OnStateChangeListener() {
                @Override
                public void onStateChange(int state) {
                    switch(state) {
                        case State.FILL_STARTED:

                            break;
                        case State.FINISHED:
                            in();
                            break;
                    }
                }
            });

            //runTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delay(){

        Glide.with(this).load(R.drawable.m3).transition(GenericTransitionOptions.with(animationObjectDelay)).into(courses_img_gif);
        handler.removeCallbacks(myRunnable);
        long time = 2100;
        final long startTime = System.currentTimeMillis() + time;
        //pGif = null;
        try {
            //seconds = 7;
            myRunnableDelay = new Runnable() {
                @Override
                public void run(){
                    long millis = startTime - System.currentTimeMillis();
                    if(millis>0) {
                        int seconds = (int) (millis / 1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        String text = String.format("%02d", seconds);
                        tvTimer.setText("Задержи: "+text);
                        handlerDelay.postDelayed(this, 500);
                    }else {
                        handlerDelay.removeCallbacks(this);
                        out();
                    }
                }
            };
            handlerDelay.post(myRunnableDelay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset(){
        try {
            Glide.with(this).load(R.drawable.m3).into(courses_img_gif);
            isPlay = false;
            handler.removeCallbacks(myRunnable);
            img_play.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
            fillableLoaderIn.reset();
            releaseMP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void animImage(){
//        imgAnim = (ImageView) v.findViewById(R.id.courses_imgAnima);
//        mSmallBang = SmallBang.attach2Window(this.getActivity());
//
//        mSmallBang.bang(imgAnim);
    }

    private void visibleElements() {
        isAnim = false;
//        ovalImage.clearAnimation();
//        arc1.setVisibility(View.VISIBLE);
//        arc2.setVisibility(View.VISIBLE);
    }

    public void runTimer(int type){
        long time = 4110;
        switch (type){
            case 1:
                time = 5110;
                break;
        }


        final long startTime = System.currentTimeMillis() + time;
        //pGif = null;
        try {
            //seconds = 7;
            myRunnable = new Runnable() {
                @Override
                public void run(){
                    long millis = startTime - System.currentTimeMillis();
                    int seconds = (int) (millis / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    String text = String.format("%02d", seconds);
                    if(isVdoh) tvTimer.setText("Вдох: "+text);
                    else tvTimer.setText("Выдох: "+text);
                    handler.postDelayed(this, 500);
                }
            };
            handler.post(myRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    private void resetArc(){
//        ovalImage.startAnimation(mEnlargeAnimation);
////        arc1.startAnimation(mArcAnim);
////        arc2.startAnimation(mArcAnim);
////        arc1.setArcColor(this.getResources().getColor(R.color.circle_center));
////        arc2.setArcColor(this.getResources().getColor(R.color.circle_center));
////        arc1.setProgressColor(this.getResources().getColor(R.color.circle_center));
////        arc2.setProgressColor(this.getResources().getColor(R.color.circle_center));
//        arc1.setVisibility(View.GONE);
//        arc2.setVisibility(View.GONE);
//        releaseMP();
//        startSound3();
//    }
//
//    private void resetSeek(){
//        arc1.setProgress(0);
//        arc2.setProgress(0);
//        seconds = 0;
//        secondsTwo = 0;
//    }
//
    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startSound1(){
        releaseMP();
        mediaPlayer = MediaPlayer.create(this.getActivity(), R.raw.sound1);
        mediaPlayer.start();
    }

    private void startSound2(){
        releaseMP();
        mediaPlayer = MediaPlayer.create(this.getActivity(), R.raw.relax2);
        mediaPlayer.start();
    }
    private void startSound3(){
        releaseMP();
        mediaPlayer = MediaPlayer.create(this.getActivity(), R.raw.relax3);
        mediaPlayer.start();
    }

    private void listenerSet(){
        if (mediaPlayer == null)
            return;
        mediaPlayer.setOnCompletionListener(this);
    }

    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMP();
    }

    @Override
    public void onPause() {
        super.onPause();
        reset();
    }
}
