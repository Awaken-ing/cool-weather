package com.zsy.forms.coolo.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by forms on 2017/9/26.
 */

public class ForeCast {
    public String data;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public  More more;

    public class Temperature{
        public String Max;
        public String Min;
    }

    public class More{
        @SerializedName("txt_d")
        public String info;
    }


}
