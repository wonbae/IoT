package com.d_code.dev_auto.homesecretary.presentation.view.activity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.model.Weather;
import com.d_code.dev_auto.homesecretary.data.repository.WeatherRepo;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.TabPagerAdapter;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TabPagerAdapter adapterViewPager;
    private BeaconManager beaconManager;
    private Region region;

    @BindView(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;
    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;

    @BindView(R.id.weather_today)
    TextView today;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.weather_txt)
    TextView weather;
    @BindView(R.id.weather_img)
    LinearLayout weather_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        initBLE();
    }
    private void initUI(){
        adapterViewPager = new TabPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        setWeatherView();
        initNavigationBar();

    }
    private void initBLE(){
        checkPermissions();
        beaconManager = new BeaconManager(this);
        region = new Region("monitoring region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 40259, 11605);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(final Region region, List<Beacon> list) {
                if(!list.isEmpty()){
                    Beacon nearestBeacon = list.get(0);

                    Log.d("NearestBeacon", "is" + nearestBeacon.getRssi());

                    if(nearestBeacon.getRssi() > -70){

                        beaconManager.stopRanging(region);
                        startCheckListActivity();
                        beaconManager.startRanging(region);
                    }
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }
    private void startCheckListActivity(){
        Intent i = new Intent(MainActivity.this, NotifyActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, i, PendingIntent.FLAG_ONE_SHOT);
        try {
            pi.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public void checkPermissions(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},0
                );
            }
        }

    }
    private void initNavigationBar(){

        int activeColor = getResources().getColor(R.color.tab_active);
        int inactiveColor = getResources().getColor(R.color.tab_inactive);
        int prevColor = getResources().getColor(R.color.prev_color);

        navigationTabBar.setIsTitled(true);
        navigationTabBar.setInactiveColor(inactiveColor);
        navigationTabBar.setActiveColor(activeColor);
        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        Drawable iList = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_list)
                .sizeDp(24);
        Drawable iCalendar = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_calendar)
                .sizeDp(24);
        Drawable iCheck = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_check_square_o)
                .sizeDp(24);
        Drawable iSetting = new IconicsDrawable(this)
                .icon(FontAwesome.Icon.faw_cog)
                .sizeDp(24);

        models.add(
                new NavigationTabBar.Model.Builder(
                        iList,
                        Color.parseColor(colors[0]))
                        .title("일정")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        iCalendar,
                        Color.parseColor(colors[0]))
                        .title("달력")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        iCheck,
                        Color.parseColor(colors[0]))
                        .title("고정")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        iSetting,
                        Color.parseColor(colors[0]))
                        .title("설정")
                        .build()
        );



        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);
        navigationTabBar.setBadgeSize(20);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                //model.hideBadge();
            }
        });

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {


            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });
        navigationTabBar.bringToFront();
    }

    public void setWeatherView(){

        Retrofit client = new Retrofit.Builder().baseUrl("http://apis.skplanetx.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        final WeatherRepo.WeatherApiInterface weatherApi =
                client.create(WeatherRepo.WeatherApiInterface.class);
        String lat = String.valueOf(35.891734);
        String lon = String.valueOf(128.6106913);
        Call<WeatherRepo> call = weatherApi.get_Weather_retrofit(1, lat, lon);
        call.enqueue(new Callback<WeatherRepo>() {
            @Override
            public void onResponse(Call<WeatherRepo> call, Response<WeatherRepo> response) {
                WeatherRepo weatherRepo = response.body();
                if (weatherRepo.getResult().getCode().equals("9200")) {
                    WeatherRepo.weather.hourly.Sky sky = weatherRepo.getWeather().getHourly().get(0).getSky();
                    String[] code = sky.getCode().split("SKY_\\S");
                    int iCode = Integer.parseInt(code[1]);
                    weatherIcon.setImageDrawable(getDrawable(Weather.icon_drawable_id[iCode]));
                    weather_img.setBackground(getDrawable(Weather.bg_drawable_id[iCode]));
                    weather.setText(sky.getName());
                    DateFormat FORMATTER = new SimpleDateFormat("yyyy. MM. dd.");
                    today.setText(FORMATTER.format(CalendarDay.today().getDate()));
                } else {
                    Log.d("Error", "server return error code :" + weatherRepo.getResult().getCode());
                }
            }

            @Override
            public void onFailure(Call<WeatherRepo> call, Throwable t) {
                Log.d("Error", "onFailure" + t);
            }
        });
    }
}
