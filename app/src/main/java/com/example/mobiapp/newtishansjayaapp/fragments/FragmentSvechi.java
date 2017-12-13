package com.example.mobiapp.newtishansjayaapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiapp.newtishansjayaapp.R;

/**
 * Created by mobi app on 27.09.2017.
 */

public class FragmentSvechi extends Fragment {

    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_svechi, container, false);

        return v;
    }


}
