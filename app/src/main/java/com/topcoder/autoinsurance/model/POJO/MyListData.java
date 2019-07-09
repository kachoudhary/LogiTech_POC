package com.topcoder.autoinsurance.model.POJO;

import java.net.URL;

public class MyListData {

    private String movieTitle;
    private URL imgURL;

    public MyListData(String movieTitle, URL imgURL) {
        this.movieTitle = movieTitle;
        this.imgURL = imgURL;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public URL getImgURL() {
        return imgURL;
    }

    public void setImgURL(URL imgURL) {
        this.imgURL = imgURL;
    }
}
