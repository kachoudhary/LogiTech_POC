package com.topcoder.autoinsurance.model;

public class MyListData {

    private String movieTitle;
    private int imgURL;

    public MyListData(String movieTitle, int imgURL) {
        this.movieTitle = movieTitle;
        this.imgURL = imgURL;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }
}
