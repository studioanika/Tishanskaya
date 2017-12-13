package com.example.mobiapp.newtishansjayaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobiapp.newtishansjayaapp.R;
import com.example.mobiapp.newtishansjayaapp.ZhurnalActivity;
import com.example.mobiapp.newtishansjayaapp.classes.Note;

import java.util.List;

/**
 * Created by mobi app on 21.09.2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    List<Note> list;
    ZhurnalActivity activity;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // наш пункт состоит только из одного TextView
        public TextView tvDate, tvTime, tvText, tvPlace, tvPeople, tvSituation, tvThoughts, tvSymptoms, tvSymptoms2,tvSymptoms3;

        public ViewHolder(View v) {
            super(v);

            tvDate = (TextView) v.findViewById(R.id.note_date);
            tvTime = (TextView) v.findViewById(R.id.note_time);
            tvText = (TextView) v.findViewById(R.id.note_text);
            tvPlace = (TextView) v.findViewById(R.id.note_place);
            tvPeople = (TextView) v.findViewById(R.id.note_people);
            tvSituation = (TextView) v.findViewById(R.id.note_situation);
            tvThoughts = (TextView) v.findViewById(R.id.note_thoughts);
            tvSymptoms = (TextView) v.findViewById(R.id.note_symptoms);
            tvSymptoms2 = (TextView) v.findViewById(R.id.note_symptoms2);
            tvSymptoms3 = (TextView) v.findViewById(R.id.note_symptoms3);

        }
    }

    // Конструктор
    public NoteAdapter(ZhurnalActivity activity, List<Note> list) {
        this.activity = activity;
        this.list = list;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        //ViewHolder vh = new ViewHolder(v);
        return new NoteAdapter.ViewHolder(v);
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, final int position) {

        try {
            Note note = list.get(position);
            holder.tvDate.setText(note.getDat());
            holder.tvTime.setText(note.getTime());
            holder.tvPlace.setText(note.getPlace());
            holder.tvPeople.setText(note.getPeople());
            holder.tvSituation.setText(note.getSituation());
            holder.tvThoughts.setText(note.getThought());
            holder.tvSymptoms.setText(note.getSimptoms());
            holder.tvSymptoms2.setText(note.getSimptoms1());
            holder.tvSymptoms3.setText(note.getSimptoms2());
            //holder.tvText.setText(note.getText());

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