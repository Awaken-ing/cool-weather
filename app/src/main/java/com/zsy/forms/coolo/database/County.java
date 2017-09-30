package com.zsy.forms.coolo.database;

import org.litepal.crud.DataSupport;

/**
 * Created by forms on 2017/8/16.
 */

public class County extends DataSupport {
    private int id;
    private String CountyName;
    private String weatherId;
    private int CityId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }


    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getCountyName() {
        return CountyName;
    }

    public void setCountyName(String countyName) {
        CountyName = countyName;
    }
}
