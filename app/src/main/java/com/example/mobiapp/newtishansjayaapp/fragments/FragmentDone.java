package com.example.mobiapp.newtishansjayaapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiapp.newtishansjayaapp.MainActivity;
import com.example.mobiapp.newtishansjayaapp.PredpisanieActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.ZhurnalActivity;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;
import com.example.mobiapp.newtishansjayaapp.reminder.ReminderManager;
import com.example.mobiapp.newtishansjayaapp.reminder.RemindersDbAdapter;
import com.example.mobiapp.newtishansjayaapp.view.CardView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mobi app on 02.10.2017.
 */

@SuppressLint("ValidFragment")
public class FragmentDone extends Fragment implements View.OnClickListener, RewardedVideoAdListener {

    View v;
    int position = 1;
    CardView cardView1, cardView2, cardView3;
    Button btn_next;
    Context context;
    Prefs prefs;
    TextView tv_result;
    EditText editTextCode;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String AD_UNIT_ID = "ca-app-pub-9722123246327592/1913090048";
    private static final String APP_ID = "ca-app-pub-9722123246327592~1831885913";
    private RewardedVideoAd mRewardedVideoAd;

    public FragmentDone(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_done, container, false);

        prefs = new Prefs(v.getContext());
        init();

        MobileAds.initialize(v.getContext(), APP_ID);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(v.getContext());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        return v;

    }

    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }

    private void showRewardedVideo() {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }else Toast.makeText(v.getContext(), "Реклама загружается...", Toast.LENGTH_SHORT).show();
    }

    private void init(){


        cardView1 = (CardView) v.findViewById(R.id.done_card1);
        //cardView1.setChecked();
        cardView1.setText(this.getResources().getString(R.string.simptom_done_1));

        cardView2 = (CardView) v.findViewById(R.id.done_card2);
        cardView2.setText(this.getResources().getString(R.string.simptom_done_2));

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);

        btn_next = (Button) v.findViewById(R.id.done_btn_next);
        btn_next.setOnClickListener(this);

        tv_result = (TextView) v.findViewById(R.id.done_result);
        String res = "";
        switch (prefs.getVaht()){
            case 1:
                res = "Панические атаки";
                break;
            case 2:
                res = "Агорафобия с паническими атаками";
                break;
            case 3:
                res = "Агорафобия без панических атак";
                break;
            case 4:
                res = "Монофобическое расстройство";
                break;
        }
        tv_result.setText("Результат тестирования:\n"+res);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.done_card1:
                exit();
                break;
            case R.id.done_card2:
                setCode();
                break;

        }
    }

    private void alertNO(){
        final Dialog dialogEdit = new Dialog(context);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_no_pred);

        Button btn = (Button) dialogEdit.findViewById(R.id.alert_no_pred_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.vk_group)));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogEdit.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogEdit.show();
        dialogEdit.getWindow().setAttributes(lp);
    }

    private void setCode() {

        final Dialog dialogEdit = new Dialog(context);
        //dialogEdit.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEdit.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEdit.setContentView(R.layout.alert_code);

        editTextCode = (EditText) dialogEdit.findViewById(R.id.alert_code_edit_code);

        RelativeLayout rel_vk, rel_fb, rel_gm, rel_inst;

        rel_fb = (RelativeLayout) dialogEdit.findViewById(R.id.alert_code_rel_fb);
        rel_vk = (RelativeLayout) dialogEdit.findViewById(R.id.alert_code_rel_vk);
        rel_inst = (RelativeLayout) dialogEdit.findViewById(R.id.alert_code_rel_inst);
        rel_gm = (RelativeLayout) dialogEdit.findViewById(R.id.alert_code_rel_gm);

        rel_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.fb_group)));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        rel_inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.inst_group)));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        rel_vk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.vk_group)));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button buttonVK = (Button) dialogEdit.findViewById(R.id.alert_code_button_vk);
        buttonVK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.vk_group)));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        rel_gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.gmail)});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Получение кода");
                    intent.putExtra(Intent.EXTRA_TEXT, "Я хочу получить код.");

                    startActivity(Intent.createChooser(intent, "Победить страх"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button buttonADS = (Button) dialogEdit.findViewById(R.id.alert_code_button_ads);
        buttonADS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRewardedVideo();
            }
        });

        if(!prefs.getPredCode().equals("")) editTextCode.setText(prefs.getPredCode());

        Button buttonNEXT = (Button) dialogEdit.findViewById(R.id.alert_code_button_next);
        buttonNEXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextCode.getText().toString().equals("победить страх") || editTextCode.getText().toString().equals("Победить страх")) {
                    prefs.setPredCode();
                    try {

                        Date date = new Date();
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_FORMAT);
                        String reminderDateTime = dateTimeFormat.format(date.getTime());
                        prefs.setPred(reminderDateTime);

                        startActivity(new Intent(getActivity(),PredpisanieActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else Toast.makeText(dialogEdit.getContext(),"Неверный код", Toast.LENGTH_SHORT).show();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogEdit.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogEdit.show();
        dialogEdit.getWindow().setAttributes(lp);

    }

    private void exit() {
        alertNO();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(v.getContext(), "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
       // Toast.makeText(v.getContext(), "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        // Preload the next video ad.
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        editTextCode.setText("Победить страх");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //Toast.makeText(v.getContext(), "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(v.getContext(), "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(v.getContext(), "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(v.getContext(), "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }


}
