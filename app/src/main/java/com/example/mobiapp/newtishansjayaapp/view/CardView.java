package com.example.mobiapp.newtishansjayaapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.R;

/**
 * Created by mobi app on 26.09.2017.
 */
public class CardView extends RelativeLayout {

    TextView tv;
    RadioButton rb;
    RelativeLayout rel;
    ImageView img;

    public CardView(Context context) {
        super(context);
        init();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.card_view, this);
        this.tv = (TextView) findViewById(R.id.card_text);
        this.rel = (RelativeLayout) findViewById(R.id.card_rel);
        this.rb = (RadioButton) findViewById(R.id.card_radio);
        this.img = (ImageView) findViewById(R.id.card_img);
    }

    public void setText(String txt){
        tv.setText(txt);
    }

    public boolean check(){
        if(rb.isChecked()) {
            rb.setChecked(false);
            rel.setBackgroundColor(getResources().getColor(R.color.black_overlay));
            img.setImageDrawable(getResources().getDrawable(R.drawable.ic_unactive));
            return true;
        }else {
            rb.setChecked(true);
            rel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            img.setImageDrawable(getResources().getDrawable(R.drawable.ic_active));
            return false;
        }
    }

    public void setChecked(){
        img.setImageDrawable(getResources().getDrawable(R.drawable.ic_active));
        rb.setChecked(true);
        rel.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void dontChecked(){
        img.setImageDrawable(getResources().getDrawable(R.drawable.ic_unactive));
        rb.setChecked(false);
        rel.setBackgroundColor(getResources().getColor(R.color.black_overlay));
    }
}