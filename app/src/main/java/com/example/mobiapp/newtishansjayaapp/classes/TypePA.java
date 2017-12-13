package com.example.mobiapp.newtishansjayaapp.classes;

/**
 * Created by mobi app on 22.09.2017.
 */

public class TypePA {

    private String name;
    private String text;
    private String description;
    private boolean isDesc;

    public TypePA(String name, String text, String description) {
        this.name = name;
        this.text = text;
        this.description = description;
        isDesc = false;
    }

    public boolean isDesc() {
        return isDesc;
    }

    public void setDesc(boolean desc) {
        isDesc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
