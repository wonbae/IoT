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
    public static final String[] weather_coment={
            "상태 없음",
            "오늘 날씨가 정말로 좋아요!",
            "구름이 적당히 있습니다",
            "구름이 많아요",
            "대체로 흐리고 비가 옵니다",

            "대체로 흐리고 눈이 옵니다",
            "대체로 흐리고 비 또는 눈이 내리겠습니다",
            "날씨가 흐려요",
            "흐리고 비가 오겠습니다",
            "흐리고 눈이 오겠습니다",

            "흐리고 비 또는 눈이 오겠습니다",
            "흐리고 천둥번개가 있습니다",
            "비를 동반한 천둥번개가 치겠습니다",
            "눈을 동반한 천둥번개가 치겠습니다",
            "눈 또는 비를 동반한 천둥번개가 치겠습니다"
    };
    public static final int[] bg_drawable_id= {
            R.drawable.destination_section,
            R.drawable.bg_sunny,
            R.drawable.bg_soso_cloud,
            R.drawable.bg_cloudy,
            R.drawable.bg_rainy,

            R.drawable.bg_rainy,
            R.drawable.bg_snow,
            R.drawable.bg_rain,
            R.drawable.bg_rainy,
            R.drawable.bg_snow,

            R.drawable.bg_rain,
            R.drawable.bg_cloudy,
            R.drawable.bg_cloudy,
            R.drawable.bg_cloudy,
            R.drawable.bg_rain
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

