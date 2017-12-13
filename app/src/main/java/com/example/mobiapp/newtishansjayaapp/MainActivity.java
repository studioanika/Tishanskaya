package com.example.mobiapp.newtishansjayaapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen1;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen2;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen3;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen4;
import com.example.mobiapp.newtishansjayaapp.fragments.screens.FragmentScreen5;
import com.example.mobiapp.newtishansjayaapp.view.CircularView;
import com.example.mobiapp.newtishansjayaapp.view.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {

    Context context;
    private static Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    Prefs prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = new Prefs(this);
        context = this;
    }
    private void initSlider(){
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setVisibility(View.VISIBLE);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);



    }
    public void backFragment(){
        switch (mPager.getCurrentItem()){
            case 0:
                break;
            case 1:
                mPager.setCurrentItem(0);
                break;
            case 2:
                mPager.setCurrentItem(1);
                break;
            case 3:
                mPager.setCurrentItem(2);
        }
    }
    public void nextFragment(){
        switch (mPager.getCurrentItem()){
            case 0:
                mPager.setCurrentItem(1);
                break;
            case 1:
                mPager.setCurrentItem(2);
                break;
            case 2:
                mPager.setCurrentItem(3);
                break;
            case 3:
                mPager.setCurrentItem(4);
                break;
        }
    }
    @Override
    protected void onPostResume() {
        //initSlider();
        if(prefs.getFirst()==0) initSlider();
        else transactionFragment();
        super.onPostResume();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FragmentScreen1(context);
                case 1:
                    return new FragmentScreen2(context);
                case 2:
                    return new FragmentScreen3(context);
                case 3:
                    prefs.setFirst();
                    return new FragmentScreen4(context);
                case 4:
                    return new FragmentScreen5();

                default:return new FragmentScreen1(context);

            }

        }



        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    private void transactionFragment(){
        fragment = new FragmentScreen5();
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    private void setErrorTech() {

        final Dialog dialogEdit = new Dialog(this);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_exit);

        TextView tv_site = (TextView) dialogEdit.findViewById(R.id.alert_exit_site);
        TextView tv_exit = (TextView) dialogEdit.findViewById(R.id.alert_exit_exit);

        tv_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://studiolt.by/"));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogEdit.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialogEdit.show();
        dialogEdit.getWindow().setAttributes(lp);

    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true); return true;
        } return super.onKeyDown(keyCode, event);
    }

}
