package com.zsy.forms.coolo.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by forms on 2017/9/26.
 */

public class Weather {
    public AQI aqi;
    public String status;
    public Basic basic;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<ForeCast> foreCastList;
}
