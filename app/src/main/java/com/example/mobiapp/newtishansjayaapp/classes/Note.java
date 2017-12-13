package com.example.mobiapp.newtishansjayaapp.classes;

/**
 * Created by mobi app on 21.09.2017.
 */

public class Note {

    int id;
    public String dat;
    public String time;
    public String place;
    public String people;
    public String situation;
    public String simptoms;
    public String simptoms1;
    public String simptoms2;
    public String thought;

    public String getSimptoms1() {
        return simptoms1;
    }

    public void setSimptoms1(String simptoms1) {
        this.simptoms1 = simptoms1;
    }

    public String getSimptoms2() {
        return simptoms2;
    }

    public void setSimptoms2(String simptoms2) {
        this.simptoms2 = simptoms2;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSimptoms() {
        return simptoms;
    }

    public void setSimptoms(String simptoms) {
        this.simptoms = simptoms;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public Note(int id, String dat, String time, String place, String people, String situation, String simptoms, String simptoms1, String simptoms2, String thought) {

        this.id = id;
        this.dat = dat;
        this.time = time;
        this.place = place;
        this.people = people;
        this.situation = situation;
        this.simptoms = simptoms;
        this.simptoms1 = simptoms1;
        this.simptoms2 = simptoms2;
        this.thought = thought;
    }
}