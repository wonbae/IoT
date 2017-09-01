package com.d_code.dev_auto.homesecretary.presentation.view.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.HomeSecretaryApplication;
import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.data.entity.Event;
import com.d_code.dev_auto.homesecretary.data.entity.EventDao;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEvent;
import com.d_code.dev_auto.homesecretary.data.entity.FixedEventDao;
import com.d_code.dev_auto.homesecretary.data.model.Weather;
import com.d_code.dev_auto.homesecretary.data.repository.WeatherRepo;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.FixedItemAdapter;
import com.d_code.dev_auto.homesecretary.presentation.view.component.adapter.NotifyViewAdapter;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class NotifyActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NotifyViewAdapter notifyViewAdapter;

    @BindView(R.id.fixed_item_gridlist)
    GridView fixedGridView;
    @BindView(R.id.notify_today)
    TextView today;
    private HomeSecretaryApplication hsApp;
    private EventDao eventDao;
    private FixedEventDao fixedEventDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
        initGreenDAO();
        init();

    }
    private void init(){
        notifyViewAdapter = new NotifyViewAdapter(getApplicationContext());

        recyclerView = (RecyclerView)findViewById(R.id.notify_list);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(notifyViewAdapter);
        setWeatherView();
    }
    private void initGreenDAO(){
        hsApp = (HomeSecretaryApplication) (getApplication());
        eventDao = hsApp.getDaoSession().getEventDao();
        fixedEventDao = hsApp.getDaoSession().getFixedEventDao();
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

                    Drawable icon = getDrawable(Weather.icon_drawable_id[iCode]);
                    String weatherName = sky.getName();
                    String weatherComment = Weather.weather_coment[iCode];

                    Event weatherEvent = new Event();
                    weatherEvent.setTitle(weatherName);
                    weatherEvent.setType(0);
                    weatherEvent.setDetail(weatherComment);
                    weatherEvent.setICode(iCode);
                    notifyViewAdapter.addWeatherEvent(weatherEvent);
                    loadActivityEvent();
                    loadFixedEvent();

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
    public void loadActivityEvent(){
        Date todayDate = CalendarDay.today().getDate();
        QueryBuilder<Event> eventQueryBuilder = eventDao.queryBuilder();
        ArrayList<Event> activityEvents = (ArrayList<Event>)eventQueryBuilder
                .where(EventDao.Properties.Type.eq(1), EventDao.Properties.Date.eq(todayDate))
                .list();
        if(activityEvents.size()>0)
            notifyViewAdapter.addActivityEvent(activityEvents);
        else {
            Event active = new Event();
            active.setType(1);
            active.setPathUri("");
            active.setDepartureToArrival("일정이 없습니다");
            activityEvents.add(active);
            notifyViewAdapter.addActivityEvent(activityEvents);
        }
    }
    public void loadFixedEvent(){
        FixedItemAdapter adapter = new FixedItemAdapter(getApplication());
        fixedGridView.setAdapter(adapter);

        adapter.setFixedEvents((ArrayList<FixedEvent>) fixedEventDao.loadAll());


    }
}
