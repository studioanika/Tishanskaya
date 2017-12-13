package com.example.mobiapp.newtishansjayaapp.classes;

/**
 * Created by mobi app on 27.10.2017.
 */

public class ItemPredpisanie {

    Long rowId;
    String title;
    String body;
    String date;
    String sound;

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Long getRowId() {

        return rowId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getSound() {
        return sound;
    }

    public ItemPredpisanie(Long rowId, String title, String body, String date, String sound) {

        this.rowId = rowId;
        this.title = title;
        this.body = body;
        this.date = date;
        this.sound = sound;
    }
}
