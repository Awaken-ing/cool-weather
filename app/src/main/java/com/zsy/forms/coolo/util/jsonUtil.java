package com.zsy.forms.coolo.util;

import android.text.TextUtils;

import com.zsy.forms.coolo.database.City;
import com.zsy.forms.coolo.database.County;
import com.zsy.forms.coolo.database.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by forms on 2017/8/16.
 */

public class jsonUtil {

    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray provinceJson = new JSONArray(response);
                for (int i = 0, j = provinceJson.length(); i < j; i++) {
                    JSONObject provinceObject = provinceJson.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provionceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray cityJson = new JSONArray(response);
                for (int i = 0, j = cityJson.length(); i < j; i++) {
                    JSONObject cityObject = cityJson.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provionceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response, int CountyId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray countyJson = new JSONArray(response);
                for (int i = 0, j = countyJson.length(); i < j; i++) {
                    JSONObject countyObject = countyJson.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherCode("weather_id");
                    county.setCityId(CountyId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
