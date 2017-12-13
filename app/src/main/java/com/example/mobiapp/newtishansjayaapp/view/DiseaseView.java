package com.example.mobiapp.newtishansjayaapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiapp.newtishansjayaapp.R;

/**
 * Created by mobi app on 26.09.2017.
 */

public class DiseaseView extends RelativeLayout {

    RelativeLayout rel_blue;
    ImageView img_check, img_logo;
    TextView text;

    public DiseaseView(Context context) {
        super(context);
        init();
    }

    public DiseaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiseaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.disease_view, this);
        this.rel_blue = (RelativeLayout) findViewById(R.id.rel_blue);
        this.img_check = (ImageView) findViewById(R.id.img_check);
        this.text = (TextView) findViewById(R.id.text);
        this.img_logo = (ImageView) findViewById(R.id.img_logo);
    }

    public boolean check(){
        if(rel_blue.getVisibility()== View.GONE){
            rel_blue.setVisibility(View.VISIBLE);
            img_check.setVisibility(View.VISIBLE);
            return true;
        }else {
            rel_blue.setVisibility(View.GONE);
            img_check.setVisibility(View.GONE);
            return false;
        }
    }

    public void setImage(int drawable){
        Glide.with(this).load(drawable).into(img_logo);
        //img_logo.setImageDrawable(drawable);
    }
    public void setText(String tex){
        text.setText(tex);
    }
}
