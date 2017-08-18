package com.zsy.forms.coolo.database;

import org.litepal.crud.DataSupport;

/**
 * Created by forms on 2017/8/16.
 */

public class City extends DataSupport {
    private int id;
    private String CityName;
    private int CityCode;
    private int ProvinceId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public int getCityCode() {
        return CityCode;
    }

    public void setCityCode(int cityCode) {
        CityCode = cityCode;
    }
}
