package com.kalvi.elaptopsale;

/**
 * Created by TOEFL PROXY on 05-05-2018.
 */

public class LapModel {
    String Laphead;
    String lapcontent;
    String lapimage;
    int rating;

    public LapModel(String laphead, String lapcontent, String lapimage, int rating) {
        Laphead = laphead;
        this.lapcontent = lapcontent;
        this.lapimage = lapimage;
        this.rating = rating;
    }

    public String getLaphead() {
        return Laphead;
    }

    public void setLaphead(String laphead) {
        Laphead = laphead;
    }

    public String getLapcontent() {
        return lapcontent;
    }

    public void setLapcontent(String lapcontent) {
        this.lapcontent = lapcontent;
    }

    public String getLapimage() {
        return lapimage;
    }

    public void setLapimage(String lapimage) {
        this.lapimage = lapimage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
