package com.zsy.forms.coolo.database;

import org.litepal.crud.DataSupport;

/**
 * Created by forms on 2017/8/16.
 */

public class Province extends DataSupport {
    private int id;
    private String ProvinceName;
    private int ProvinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceCode(int provinceCode) {
        ProvinceCode = provinceCode;
    }

    public int getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }
}
