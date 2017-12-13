package com.example.mobiapp.newtishansjayaapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.DescriptionPAActivity;
import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.classes.TypePA;
import com.example.mobiapp.newtishansjayaapp.db.Prefs;

import java.util.List;

/**
 * Created by mobi app on 22.09.2017.
 */

public class TypePAAdapter extends RecyclerView.Adapter<TypePAAdapter.ViewHolder>{

    List<TypePA> list;
    DescriptionPAActivity activity;
    Prefs prefs;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvName, tvDesc, tvClick, tvPredp;
        CardView cardView;

        public ViewHolder(View v) {
            super(v);

            tvName = (TextView) v.findViewById(R.id.note_date);
            tvDesc = (TextView) v.findViewById(R.id.note_time);
            tvClick = (TextView) v.findViewById(R.id.type_click);
            tvPredp = (TextView) v.findViewById(R.id.type_predpisanie);
            cardView = (CardView) v.findViewById(R.id.type_pa_card);

        }
    }

    // Конструктор
    public TypePAAdapter(DescriptionPAActivity activity, List<TypePA> list) {
        this.activity = activity;
        this.list = list;
        prefs = new Prefs(this.activity);
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public TypePAAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type_pa, parent, false);

        //ViewHolder vh = new ViewHolder(v);
        return new TypePAAdapter.ViewHolder(v);
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(final TypePAAdapter.ViewHolder holder, final int position) {

        try {
            final TypePA typePA = list.get(position);
            holder.tvName.setText(typePA.getName());
            holder.tvDesc.setText(typePA.getText());
            holder.tvClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!typePA.isDesc()) {
                        holder.tvClick.setText("Скрыть");
                        typePA.setDesc(true);
                        holder.tvDesc.setText(typePA.getDescription());
                    }else {
                        typePA.setDesc(false);
                        holder.tvDesc.setText(typePA.getText());
                        holder.tvClick.setText("Подробнее");
                    }
                }
            });
            //if(prefs.getVaht()==position+1) holder.tvPredp.setEnabled(true);
            holder.tvPredp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(prefs.getVaht()==0 && prefs.getPredCode().length()<3) activity.showSnack();
                    else if(prefs.getVaht()!=position+1) activity.showSnackNewTask();
                    else {
                        //activity.startTest(position);
                        activity.showSnackPred(position);
                    }
                }
            });
            if(prefs.getVaht()==position+1) holder.cardView.setBackgroundColor(holder.cardView.getContext().getResources().getColor(R.color.for_type_pa));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return list.size();
    }



}
