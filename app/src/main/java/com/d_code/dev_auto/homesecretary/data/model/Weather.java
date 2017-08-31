package com.d_code.dev_auto.homesecretary.data.model;

import com.d_code.dev_auto.homesecretary.R;

/**
 * Created by hyeongjukim on 2017. 8. 29..
 */

public class Weather {
    private String temperature;
    private String cloud;
    private String wind_direction;
    private String wind_speed;
    private String icon;
    public static final int[] icon_drawable_id = {
            R.drawable.weather_38, R.drawable.weather_01, R.drawable.weather_02, R.drawable.weather_03, R.drawable.weather_12, R.drawable.weather_13, R.drawable.weather_14, R.drawable.weather_18, R.drawable.weather_21, R.drawable.weather_32,
            R.drawable.weather_04, R.drawable.weather_29, R.drawable.weather_26, R.drawable.weather_27, R.drawable.weather_28
    };
    public static final int[] bg_drawable_id= {
//      R.drawable.
    };
    public static Weather getInstance() {
        Weather weather = new Weather();
        return weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}

