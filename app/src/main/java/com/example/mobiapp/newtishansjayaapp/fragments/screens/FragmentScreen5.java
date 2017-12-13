package com.example.mobiapp.newtishansjayaapp.fragments.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newtishansjayaapp.DescriptionPAActivity;
import com.example.mobiapp.newtishansjayaapp.MainActivity;
import com.example.mobiapp.newtishansjayaapp.PredpisanieActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.SelectStopActivity;
import com.example.mobiapp.newtishansjayaapp.SimptomsPAActivity;
import com.example.mobiapp.newtishansjayaapp.StopActivity;
import com.example.mobiapp.newtishansjayaapp.TestPAActivity;
import com.example.mobiapp.newtishansjayaapp.ZhurnalActivity;
import com.example.mobiapp.newtishansjayaapp.classes.GeneratePredpisanie;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.reminder.AddNoteActivity;
import com.example.mobiapp.newtishansjayaapp.view.AnimationFragmentIn;
import com.example.mobiapp.newtishansjayaapp.view.CircularView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by mobi app on 28.09.2017.
 */

public class FragmentScreen5 extends Fragment {

    View v;
    ImageView img_circle;
    Prefs prefs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_screen_5, container, false);
        prefs = new Prefs(this.getContext());

        return v;
    }

    private void initCircularView(){

        MobileAds.initialize(this.getActivity(),getResources().getString(R.string.ads));
        AdView mAdView = (AdView) v.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        img_circle = (ImageView) v.findViewById(R.id.main_img_circle);
        img_circle.setVisibility(View.VISIBLE);
        Glide.with(this).load(R.drawable.circle ).into(img_circle);
        CircularView cl = (CircularView) v.findViewById(R.id.circular_layout);
        cl.setVisibility(View.VISIBLE);
        if (cl != null) {
            cl.setOnCircularItemClickListener(new CircularView.OnCircularItemClickListener() {
                @Override
                public void onCircularItemClick(int index) {
                    switch (index){
                        case -1:
                            try {
                                Intent intent_stop = new Intent(v.getContext(),SelectStopActivity.class);
                                startActivity(intent_stop);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
//                            GeneratePredpisanie gp = new GeneratePredpisanie(v.getContext());
//                            //gp.saveState("Hello 16-22");
//                            gp.generatePA();
                            break;
                        case 1:
                            if(prefs.getVaht()!=0) {
                                Intent intent_zhurnal = new Intent(v.getContext(), ZhurnalActivity.class);
                                startActivity(intent_zhurnal);
                            }else showSnack();
//
                            break;
                        case 2:
                            Intent intent_test_pa = new Intent(v.getContext(), TestPAActivity.class);
                            startActivity(intent_test_pa);
                            break;
                        case 3:
//                            Intent intent_description = new Intent(v.getContext(), DescriptionPAActivity.class);
//                            startActivity(intent_description);
                            break;
                        case 0:
                            Intent intent_description = new Intent(v.getContext(), DescriptionPAActivity.class);
                            startActivity(intent_description);
//                            Intent intent_simptoms = new Intent(v.getContext(), SimptomsPAActivity.class);
//                            startActivity(intent_simptoms);
                            break;
                    }

                }
            });
        }

    }

    @Override
    public void onResume() {
        initCircularView();
        super.onResume();
    }

    public void showSnack(){
        Snackbar snackbar = Snackbar.make(v,"Требуется пройти тест." , Snackbar.LENGTH_LONG)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(), TestPAActivity.class));
                    }
                });
        snackbar.setDuration(8000);
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        // 8 секунд
        snackbar.show();
    }
}
