package com.zsy.forms.coolo.database;

import org.litepal.crud.DataSupport;

/**
 * Created by forms on 2017/8/16.
 */

public class County extends DataSupport {
    private int id;
    private String CountyName;
    private int CountyCode;
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

    public int getCountyCode() {
        return CountyCode;
    }

    public void setCountyCode(int countyCode) {
        CountyCode = countyCode;
    }

    public String getCountyName() {
        return CountyName;
    }

    public void setCountyName(String countyName) {
        CountyName = countyName;
    }
}
