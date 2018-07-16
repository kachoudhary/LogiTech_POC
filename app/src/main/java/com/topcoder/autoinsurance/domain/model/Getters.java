package com.topcoder.autoinsurance.domain.model;

/* Created by Kartikey Choudhary */


import android.content.Context;

public class Getters {

    private String ctyname;
    private String Country;
    private Integer temperature;
    private String weather;


    public Getters(String ctyname, String country, Integer temperature, String weather) {
        this.ctyname = ctyname;
        Country = country;
        this.temperature = temperature;
        this.weather = weather;
    }


    public String getCtyname() {
        return ctyname;
    }

    public String getCountry() {
        return Country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }
}
