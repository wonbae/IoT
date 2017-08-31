package com.d_code.dev_auto.homesecretary.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.d_code.dev_auto.homesecretary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mcauto on 2017. 9. 1..
 */

public class NotifyActivity extends Activity {
    @BindView(R.id.weather_notice_section)
    private LinearLayout  weather_notice_section;
    @BindView(R.id.path_section)
    private LinearLayout path_section;
    @BindView(R.id.weather_section_subtitle)
    private FrameLayout weather_section_usbtitle;
    @BindView(R.id.path_section_subtitle)
    private FrameLayout path_section_subtitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);


    }
}
