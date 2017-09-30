package com.zsy.forms.coolo.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zsy.forms.coolo.R;
import com.zsy.forms.coolo.base.ForeCast;
import com.zsy.forms.coolo.base.Weather;
import com.zsy.forms.coolo.util.HttpUtil;
import com.zsy.forms.coolo.util.jsonUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by forms on 2017/9/27.
 */

public class WeatherActivity extends AppCompatActivity {
    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout foreCastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ImageView ivBing;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        if (Build.VERSION.SDK_INT >= 21) {
            View decoView = getWindow().getDecorView();
            decoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = sp.getString("weather", null);
        if (weatherString != null) {
            Weather weather = jsonUtil.handlerWeatherResponse(weatherString);
            showWeatherInfo(weather);
        } else {
            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }
        String bingPic = sp.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(ivBing);
        } else {
            loadBingPic();
        }

    }

    public void initView() {
        ivBing = (ImageView) findViewById(R.id.ivBing);
        weatherLayout = (ScrollView) findViewById(R.id.weatherLayout);
        titleCity = (TextView) findViewById(R.id.titleCity);
        titleUpdateTime = (TextView) findViewById(R.id.titleUpdateTime);
        degreeText = (TextView) findViewById(R.id.degreeText);
        weatherInfoText = (TextView) findViewById(R.id.weatherInfoText);
        foreCastLayout = (LinearLayout) findViewById(R.id.foreCastLayout);
        aqiText = (TextView) findViewById(R.id.aqiText);
        pm25Text = (TextView) findViewById(R.id.pm25Text);
        comfortText = (TextView) findViewById(R.id.comfortText);
        carWashText = (TextView) findViewById(R.id.carWashText);
        sportText = (TextView) findViewById(R.id.sportText);
    }

    private void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=609445d1d47841e18c02f95e2f1fcf1e";
        HttpUtil.sendOkhttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = jsonUtil.handlerWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loadBingPic();
    }

    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        foreCastLayout.removeAllViews();
        for (ForeCast foreCast : weather.foreCastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, foreCastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.dataText);
            TextView infoText = (TextView) view.findViewById(R.id.infoText);
            TextView maxText = (TextView) view.findViewById(R.id.maxText);
            TextView minText = (TextView) view.findViewById(R.id.minText);
            Log.e("cd", "f" + foreCast.data);
            Log.e("cd", "f" + foreCast.more.info);
            Log.e("cd", "f" + foreCast.temperature.Max);
            Log.e("cd", "f" + foreCast.temperature.Min);
            dateText.setText(foreCast.data);
            infoText.setText(foreCast.more.info);
            maxText.setText(foreCast.temperature.Max);
            minText.setText(foreCast.temperature.Min);
            foreCastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfort = "舒适度:" + weather.suggestion.comfort.info;
        String carWash = "洗车指数:" + weather.suggestion.carWash.info;
        String sport = "运动建议:" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    private void loadBingPic() {
        final String requestPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkhttpRequest(requestPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(ivBing);
                    }
                });
            }
        });
    }
}
