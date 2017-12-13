package com.example.mobiapp.newtishansjayaapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newtishansjayaapp.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

/**
 * Created by mobi app on 27.09.2017.
 */

public class FragmentScratchView extends Fragment {

    View v;
    int[] arrDrawable = new int[]{R.drawable.scratch1,R.drawable.scratch2,R.drawable.scratch3,R.drawable.scratch4,
            R.drawable.scratch5,R.drawable.scratch6,R.drawable.scratch7,R.drawable.scratch8,R.drawable.scratch9,
            R.drawable.scratch10,R.drawable.scratch11,R.drawable.scratch12,R.drawable.scratch13,R.drawable.scratch14,
            R.drawable.scratch15,R.drawable.scratch16,R.drawable.scratch17,R.drawable.scratch18,R.drawable.scratch19,
            R.drawable.scratch20,R.drawable.scratch21,R.drawable.scratch22,R.drawable.scratch23,R.drawable.scratch24};
    ImageView imgl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_scratch_view, container, false);

        return v;
    }

    private void randomImage(){

        MobileAds.initialize(this.getActivity(),getResources().getString(R.string.ads));
        AdView mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        imgl = (ImageView) v.findViewById(R.id.fragment_scratch_img);

        Random r = new Random();
        int i1 = r.nextInt(23);
        //imgl.setImageDrawable(getResources().getDrawable(arrDrawable[i1]));
        Glide.with(imgl.getContext()).load(arrDrawable[i1]).into(imgl);

    }

    @Override
    public void onResume() {
        randomImage();
        super.onResume();
    }
}
